package com.pd.monitor.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/upload")
public class BigFileUploadController {
    @Resource
    private RedisTemplate redisTemplate;

    // 分片上传接口[1,6](@ref)
    @PostMapping("/chunk")
    public ResponseEntity<Map<String, Object>> uploadChunk(
            @RequestParam("file") MultipartFile chunk,
            @RequestParam("chunkNumber") int chunkNumber,
            @RequestParam("identifier") String identifier) throws IOException {

        // 存储分片到临时目录
        Path chunkPath = Paths.get("C:/tmp/uploads", identifier, chunkNumber + ".part");
        Files.createDirectories(chunkPath.getParent());
        Files.write(chunkPath, chunk.getBytes());

        // 记录已上传分片[3](@ref)
        redisTemplate.opsForSet().add(identifier + "_chunks", chunkNumber);

        // 返回已上传的分片列表（供前端断点续传）
        Set<Object> uploaded = redisTemplate.opsForSet().members(identifier + "_chunks");
        List<Integer> uploadedChunks = uploaded.stream()
                .map(o -> Integer.parseInt(o.toString()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(Collections.singletonMap("uploadedChunks", uploadedChunks));
    }

    // 合并分片接口[2,6](@ref)
    @GetMapping("/merge")
    public ResponseEntity<String> mergeChunks(
            @RequestParam String identifier,
            @RequestParam String fileName,
            @RequestParam long totalChunks) throws IOException {

        Path tempDir = Paths.get("C:/tmp/uploads", identifier);
        Path finalPath = Paths.get("C:/tmp/videos", fileName);
        // 确保目标目录存在（新增代码）
        Path videoDir = finalPath.getParent();
        if (!Files.exists(videoDir)) {
            Files.createDirectories(videoDir); // 自动创建多级目录
        }

        // 在合并前增加校验逻辑（网页8][8](@ref)）
        long chunkCount = Files.list(tempDir)
                .filter(p -> p.getFileName().toString().matches("\\d+\\.part"))
                .count();

        if (chunkCount != totalChunks) { // 需从前端传入总分片数
            throw new IllegalStateException("分片缺失，已上传：" + chunkCount + "/" + totalChunks);
        }

        try (BufferedOutputStream output = new BufferedOutputStream(Files.newOutputStream(finalPath))) {
            // 按序号合并分片
            Files.list(tempDir)
                    .sorted(Comparator.comparingInt(p -> Integer.parseInt(p.getFileName().toString().split("\\.")[0])))
                    .forEach(chunk -> {
                        try {
                            Files.copy(chunk, output);
                            Files.delete(chunk);
                        } catch (IOException e) {
                            throw new RuntimeException("分片合并失败: " + chunk, e);
                        }
                    });
            Files.delete(tempDir);
        }

        // 清理Redis记录
        redisTemplate.delete(identifier + "_chunks");
        return ResponseEntity.ok("文件合并成功: " + finalPath);
    }

    // 秒传校验接口[6](@ref)
    @GetMapping("/check")
    public ResponseEntity<Map<String, Object>> checkFile(@RequestParam String md5) {
        File target = new File("/videos", md5 + ".mp4");
        return ResponseEntity.ok(Collections.singletonMap("exists", target.exists()));
    }
}
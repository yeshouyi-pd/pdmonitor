package com.pd.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.Fileinfo;
import com.pd.server.main.domain.FileinfoExample;
import com.pd.server.main.dto.FileDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.FileService;
import com.pd.server.main.service.FileinfoService;
import com.pd.server.util.Base64ToMultipartFile;
import com.pd.server.util.DateTools;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@RequestMapping("/uploadfile")
@RestController
public class UploadFileController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadFileController.class);

    public static final String BUSINESS_NAME = "文件上传";

    @Resource
    private RedisTemplate redisTemplate;


    @Resource
    private FileService fileService;

    @Resource
    private FileinfoService fileinfoService;



    @RequestMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile file,String use) throws IOException {
        // 保存文件到本地
        String key = UuidUtil.getShortUuid();
        String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
        String basePath = picStorePath +use+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM");
        File filepath = new File(basePath);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String filehz = fileName.substring(fileName.lastIndexOf("."));
        String fullPath = basePath+File.separator+key+filehz;
        File dest = new File(fullPath);
        file.transferTo(dest);//写入当本地磁盘
        String readpath = "/system/f/"+use+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM")+File.separator+key+filehz;
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(readpath);
        return responseDto;
    }

    @RequestMapping("/uploadbig")

    public ResponseDto uploadbig(@RequestParam MultipartFile shard,
                                 String use,
                                 String name,
                                 String suffix,
                                 Integer size,
                                 Integer shardIndex,
                                 Integer shardSize,
                                 Integer shardTotal,
                                 String key,
                                 String f1,
                                 String f2) throws Exception {
        //LOG.info("上传文件开始");

        FileDto fileDto = new FileDto();
        fileDto.setName(name);
        fileDto.setSize(size);
        fileDto.setSuffix(suffix);
        fileDto.setUse(use);
        fileDto.setShardIndex(shardIndex);
        fileDto.setShardSize(shardSize);
        fileDto.setShardTotal(shardTotal);
        fileDto.setKey(key);
        fileDto.setF1(f1);
        fileDto.setF2(f2);

        String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
        String basePath = picStorePath +use+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM");


        //如果文件夹不存在则创建

        File fullDir = new File(basePath);
        if (!fullDir.exists()) {
            fullDir.mkdirs();
        }

        String path = new StringBuffer()
                .append(key)
                .append(".")
                .append(suffix)
                .toString();
        String localPath = new StringBuffer(path)
                .append(".")
                .append(fileDto.getShardIndex())
                .toString();
        String fullPath = basePath +File.separator+ localPath;
        File dest = new File(fullPath);
        shard.transferTo(dest);
        //LOG.info(dest.getAbsolutePath());

        //LOG.info("保存文件记录开始");
        fileDto.setPath(use+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM")+File.separator+path);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        fileDto.setPath("/system/f/"+use+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM")+ File.separator+path);
        responseDto.setContent(fileDto);

        if (fileDto.getShardIndex().equals(fileDto.getShardTotal())) {
            this.merge(fileDto);
        }
        return responseDto;
    }

    public void merge(FileDto fileDto) throws Exception {
        String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
        //LOG.info("合并分片开始");
        String path = fileDto.getPath(); //http://127.0.0.1:9000/file/f/course\6sfSqfOwzmik4A4icMYuUe.mp4
        path = path.replace("/system/f", ""); //course\6sfSqfOwzmik4A4icMYuUe.mp4
        Integer shardTotal = fileDto.getShardTotal();
        File newFile = new File(picStorePath + path);
        FileOutputStream outputStream = new FileOutputStream(newFile, true);//文件追加写入
        FileInputStream fileInputStream = null;//分片文件
        byte[] byt = new byte[10 * 1024 * 1024];
        int len;

        try {
            for (int i = 0; i < shardTotal; i++) {
                // 读取第i个分片
                fileInputStream = new FileInputStream(new File(picStorePath + path + "." + (i + 1))); //  course\6sfSqfOwzmik4A4icMYuUe.mp4.1
                while ((len = fileInputStream.read(byt)) != -1) {
                    outputStream.write(byt, 0, len);
                }
            }
            //==================
            Fileinfo vo = new Fileinfo();
            vo.setKey(fileDto.getKey());
            vo.setXmbh(fileDto.getF1());
            vo.setSbsn(fileDto.getF2());
            vo.setCjsj(new Date());
            fileinfoService.insert(vo);
            //==================


        } catch (IOException e) {
            LOG.error("分片合并异常", e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                outputStream.flush();
                outputStream.close();
                //LOG.info("IO流关闭");
            } catch (Exception e) {
                LOG.error("IO流关闭", e);
            }
        }
        //LOG.info("合并分片结束");

        System.gc();
        Thread.sleep(300);

        // 删除分片
        //LOG.info("删除分片开始");
        for (int i = 0; i < shardTotal; i++) {
            String filePath = picStorePath + path + "." + (i + 1);
            File file = new File(filePath);
            boolean result = file.delete();
            //LOG.info("删除{}，{}", filePath, result ? "成功" : "失败");
        }
        //LOG.info("删除分片结束");
    }

    @GetMapping("/check/{key}")
    public ResponseDto check(@PathVariable String key) {
        //LOG.info("检查上传分片开始：{}", key);
        ResponseDto responseDto = new ResponseDto();
        FileDto fileDto = fileService.findByKey(key);
        if (fileDto != null) {
            fileDto.setPath("/system/f/" + fileDto.getPath());
        }
        responseDto.setContent(fileDto);
        return responseDto;
    }
    @PostMapping("/savefileinfo")
    public ResponseDto savefileinfo(@RequestBody  FileDto fileDto){
        ResponseDto responseDto = new ResponseDto();
        FileinfoExample example = new FileinfoExample();
        FileinfoExample.Criteria ca = example.createCriteria();
        ca.andXmbhEqualTo(fileDto.getF1());
        ca.andSbsnEqualTo(fileDto.getF2());
        ca.andKeyEqualTo(fileDto.getKey());
        int count = fileinfoService.querycount(example);
        Fileinfo vo = new Fileinfo();
        vo.setKey(fileDto.getKey());
        vo.setXmbh(fileDto.getF1());
        vo.setSbsn(fileDto.getF2());
        vo.setCjsj(new Date());
        if(count > 0){
            //修改
            fileinfoService.updates(vo ,example);
        }else{
            //添加
            fileinfoService.insert(vo);
        }

        return responseDto;
    }

}


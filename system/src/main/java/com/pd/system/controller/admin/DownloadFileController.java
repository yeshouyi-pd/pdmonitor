package com.pd.system.controller.admin;

import com.pd.server.config.RedisCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RequestMapping("/downloadfile")
@RestController
public class DownloadFileController {
    private static final Logger LOG = LoggerFactory.getLogger(UploadFileController.class);

    public static final String BUSINESS_NAME = "本地文件下载";

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping ("/downloadLocal")
    public void downloadLocal(HttpServletResponse response) throws FileNotFoundException, UnsupportedEncodingException {
        // 下载本地文件
        String fileName = "水生哺乳类智慧监测平台管理手册.pdf".toString(); // 文件的默认保存名
        // 读到流中
        String storePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
        InputStream inStream = new FileInputStream(storePath+"operationManual"+ File.separator+fileName);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"), "ISO_8859_1"));
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping ("/downloadVideo")
    public void downloadVideo(HttpServletResponse response) throws FileNotFoundException, UnsupportedEncodingException {
        // 下载本地文件
        String fileName = "WebComponentsKit.exe".toString(); // 文件的默认保存名
        // 读到流中
        String storePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
        InputStream inStream = new FileInputStream(storePath+"operationManual"+ File.separator+fileName);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"), "ISO_8859_1"));
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

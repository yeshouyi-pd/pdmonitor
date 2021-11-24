package com.pd.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/download/audio")
public class DownloadAudioController {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadAudioController.class);
    public static final String BUSINESS_NAME = "下载江豚音频文件";

    @GetMapping("/downAudioFile")
    public void downAudioFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileUrl = request.getParameter("fileUrl");
        String fileName = request.getParameter("fileName");
        System.out.println(fileUrl);
        System.out.println(fileName);
        String remoteFileUrl = java.net.URLEncoder.encode(fileName, "UTF-8").replace("+","%20");
        if (null == remoteFileUrl || remoteFileUrl.length() == 0) {
            throw new RuntimeException("remoteFileUrl is invalid!");
        }
        System.out.println(fileUrl+remoteFileUrl);
        URL url = new URL(fileUrl+remoteFileUrl);
        BufferedInputStream in = null;
        // URLConnection conn = url.openConnection();
        // in = new BufferedInputStream(conn.getInputStream());
        // 这和上面两句一样的效果
        in = new BufferedInputStream(url.openStream());
        response.reset();
        response.setContentType("application/octet-stream");
        fileName = new String(fileName.getBytes(), "ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        // 将网络输入流转换为输出流
        int i;
        while ((i = in.read()) != -1) {
            response.getOutputStream().write(i);
        }
        in.close();
        response.getOutputStream().close();
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String wjlj = request.getParameter("wjlj");
        System.out.println(wjlj);
        String fileName = wjlj.substring(wjlj.lastIndexOf("/")+1);
        BufferedInputStream in = null;
        in = new BufferedInputStream(new FileInputStream(wjlj));
        response.reset();
        response.setContentType("application/octet-stream");
        fileName = new String(fileName.getBytes(), "ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        // 将网络输入流转换为输出流
        int i;
        while ((i = in.read()) != -1) {
            response.getOutputStream().write(i);
        }
        in.close();
        response.getOutputStream().close();
    }

}

package com.pd.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("/download/audio")
public class DownloadAudioController {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadAudioController.class);
    public static final String BUSINESS_NAME = "下载江豚音频文件";

    @GetMapping("/downAudioFile")
    public void downAudioFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String remoteFileUrl = request.getParameter("fileUrl");
        String fileName = request.getParameter("fileName");
        if (null == remoteFileUrl || remoteFileUrl.length() == 0) {
            throw new RuntimeException("remoteFileUrl is invalid!");
        }
        URL url = new URL(remoteFileUrl);
        BufferedInputStream in = null;
        // URLConnection conn = url.openConnection();
        // in = new BufferedInputStream(conn.getInputStream());
        // 这和上面两句一样的效果
        in = new BufferedInputStream(url.openStream());
        response.reset();
        response.setContentType("application/octet-stream");
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

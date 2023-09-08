package com.pd.server.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class PushFile {

    private static final Logger LOG = LoggerFactory.getLogger(PushFile.class);

    public static void pushFile1(String fileurl){
        try {
            long size = HttpUtil.downloadFile(fileurl, FileUtil.file("C:/file"));
            String uploadUrl = "http://171.34.76.171:8880/sonar_dolphin/api/recognize/sonar_dolphin/record/acceptance_file"; // 远程服务器上传URL
            File file = new File("C:\\file\\"+fileurl.substring(fileurl.lastIndexOf("/")+1));
            HashMap<String, Object> paramMap = new HashMap<>();
            //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
            paramMap.put("file", file);
            String result= HttpUtil.post(uploadUrl, paramMap);
            LOG.error("完成文件推送："+result);
            //file.delete();
        }catch (Exception e){
            LOG.error("向江西赣江推送文件错误："+e.getMessage()+"---"+fileurl);
        }
    }

    public static void pushFile(String fileurl){
        try {
            String uploadUrl = "http://171.34.76.171:8880/sonar_dolphin/api/recognize/sonar_dolphin/record/acceptance_file"; // 远程服务器上传URL
            String boundary = Long.toHexString(System.currentTimeMillis()); // 随机边界标识

            // 创建HTTP POST请求
            HttpURLConnection conn = (HttpURLConnection) new URL(uploadUrl).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            // 创建文件上传数据
            String fileData = getFileData(fileurl);
            // 创建文件上传请求体
            String requestBody = "--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"file\"; filename=\"" + fileurl.substring(fileurl.lastIndexOf("/")+1) + "\"\r\n"
                    + "Content-Type: " + getFileType(fileurl) + "\r\n" + "\r\n"
                    + fileData + "\r\n"
                    + "--" + boundary + "--\r\n";

            // 写入请求体到输出流
            OutputStream out = conn.getOutputStream();
            out.write(requestBody.getBytes("UTF-8"));
            out.flush();
            out.close();

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        }catch (Exception e){
            LOG.error("向江西赣江推送文件错误："+e.getMessage()+"---"+fileurl);
        }
    }

    private static String getFileData(String filePath) throws IOException {
        URL url = new URL(filePath);
        File file = new File("C:\\file");
        FileWriter fileWriter = new FileWriter(file);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        BufferedInputStream fis = new BufferedInputStream(conn.getInputStream());
        ByteOutputStream bos = new ByteOutputStream();
        int i;
        while ((i = fis.read()) != -1) {
            bos.write(i);
        }
        bos.close();
        fis.close();
        fileWriter.write(bos.toString());
        return bos.toString();
    }

    private static String getFileType(String filePath) {
        return URLConnection.guessContentTypeFromName(filePath);
    }

    public static void main(String[] args){
        //pushFile("http://49.239.193.146:49053/tempData/YY002/2023_09_07_21_10_53.jpg");
       pushFile1("http://49.239.193.146:49053/tempData/JXYSA4001/2023_09_08_13_07_15_2023_09_08_13_09_22_3_A4.txt");
    }


}

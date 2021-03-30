package com.pd.monitor.wx.wxutlis.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SendPostUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SendPostUtil.class);

    public static void main(String[] args){
        String url = "https://dayin.hainanpost.cn/api/order/get_all_address";
        Map<String, Object> parameters = new HashMap<String,Object>();
        parameters.put("login_name","hkcgs");
        parameters.put("login_pwd","4xsdfqVR5nqbqSt");
        parameters.put("time_stamp",Long.toString(new Date().getTime()));
        String sign = BuildSign.buildSign(parameters,"5fb87f2227f0112dd919c7e120340613");
        Map<String, Object> requestParam = new HashMap<String, Object>();
        requestParam.put("sign",sign);
        requestParam.put("data",parameters);
//        System.out.println(requestParam);
//        JSONObject result = HttpClient.httpsRequest(url,"POST",JSON.toJSONString(requestParam));
        JSONObject result = sendPost(url,requestParam);
        //System.out.println(result);
    }

    /**
     * Post方法
     * @param authHost
     * @param param
     * @return
     */
    public static JSONObject sendPost(String authHost,Map<String, Object> param) {
        JSONObject result = null;
        BufferedReader reader = null;
        String line = null;
        try {
            URL url = new URL(authHost);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方式
            connection.setRequestMethod("POST");
            // 设置是否向HttpURLConnection输出
            connection.setDoOutput(true);
            // 设置是否从httpUrlConnection读入
            connection.setDoInput(true);
            // 设置是否使用缓存
            connection.setUseCaches(false);
            //设置参数类型是json格式
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.connect();
            String body = JSON.toJSONString(param);
//            String body = requestString;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求
                StringBuilder resultBuilder = new StringBuilder();
                // 循环读取流
                while ((line = reader.readLine()) != null) {
                    resultBuilder.append(line).append(System.getProperty("line.separator"));// "\n"
                }
                result = JSONObject.parseObject(resultBuilder.toString());
                LOG.info("success:"+result);
            }else {
                LOG.info("fail:"+connection.getResponseCode()+"--"+connection.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String postData(String urlStr, String data){
        return postData(urlStr, data, null);
    }

    public static String postData(String urlStr, String data, String contentType){
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            if(contentType != null){
                conn.setRequestProperty("content-type", contentType);
            }
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            if(data == null){
                data = "";
            }
            writer.write(data);
            writer.flush();
            writer.close();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            LOG.error("Error connecting to " + urlStr + ": " + e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

}

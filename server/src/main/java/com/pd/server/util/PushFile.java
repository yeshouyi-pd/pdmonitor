package com.pd.server.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;

public class PushFile {

    private static final Logger LOG = LoggerFactory.getLogger(PushFile.class);

    public static void pushFile1(String fileurl){
        HashMap<String, Object> paramMap = new HashMap<>();
        try {
            String uploadUrl = "http://117.164.188.3:8880/sonar_dolphin/api/recognize/sonar_dolphin/record/acceptance_file"; // 远程服务器上传URL
            File file = new File(fileurl.replace("http://159.226.163.121:8081","D:\\FileinfoApi53"));
            //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
            paramMap.put("file", file);
            String result= HttpUtil.post(uploadUrl, paramMap);
            LOG.error("完成文件推送："+result);
        }catch (Exception e){
            LOG.error("向江西赣江推送文件错误："+e.getMessage()+"---"+fileurl);
        }finally {
            paramMap.remove("file");

        }
    }

    public static void main(String[] args){
       pushFile1("http://159.226.163.121:8081/tempData/JXYSA4001/2025_10_14_16_45_05.jpg");
    }


}

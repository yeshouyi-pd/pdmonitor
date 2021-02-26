package com.pd.wxbase.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.wxbase.wx.conf.WxRedisConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
    public static final String BUSINESS_NAME = "文件上传";

    /**
     * 上传文件
     * @param upfile
     * @param action
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload")
    public String upload(MultipartFile upfile,String action) throws IOException {
        String path = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
        JSONObject json=new JSONObject();
        String state = "SUCCESS";
        if("uploadimage".equals(action)){
            System.out.println("-------------------开始上传图片-------------------");
            String picPath = "";
            String name = "";
            try{
                String hz = upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf("."));
                name = "xxfb_" + new Date().getTime() + hz;
                File pathFile = new File(path);
                if(!pathFile.exists()){
                    pathFile.mkdirs();
                }
                picPath = path + name;
                upfile.transferTo(new File(picPath));
            }catch(Exception e){
                e.printStackTrace();
                state="FAIL";
            }
            System.out.println("-------------------结束上传图片-------------------");
            json.put("state", state);
            json.put("url", "?filename="+picPath);
            json.put("title", name);
            //json.put("original", upfile.getOriginalFilename());
            json.put("type", upfile.getContentType());
            json.put("size", upfile.getSize());
            json.put("type", upfile.getContentType());
            json.put("size", upfile.getSize());
        }else{
            json.put("state", state);
        }
        return JSONObject.toJSONString(json);
    }

    @RequestMapping("/readImg")
    public void readImg(String filename, HttpServletResponse response) throws Exception {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            long fileLength = new File(filename).length();
            //设置文件输出类型
            response.setContentType("application/octet-stream;charset=UTF-8");
            //设置输出长度
            response.setHeader("Content-Length", String.valueOf(fileLength));
            //获取输入流
            bis = new BufferedInputStream(new FileInputStream(filename));
            //输出流
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if(null != bis){
                bis.close();
            }
            if(null != bos){
                bos.close();
            }
        }
    }

    /*public void upload(@RequestParam("file") CommonsMultipartFile[] file){
        String wjm = "";
        try {
            File pathFile = new File("D:\\123");
            if(!pathFile.exists()){
                pathFile.mkdirs();
            }
            StringBuffer sb = new StringBuffer();
            Date date1 = new Date();
            for (CommonsMultipartFile f:file) {
                System.out.println("文件名称：" + f.getOriginalFilename());
                wjm = date1.getTime() + f.getOriginalFilename();
                //将文件上传到服务器
                f.transferTo(new File("D:\\123" + "/" + wjm));
                sb.append(wjm + ",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}

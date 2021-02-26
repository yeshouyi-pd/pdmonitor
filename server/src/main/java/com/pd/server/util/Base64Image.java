package com.pd.server.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;

public class Base64Image {
    private static final Logger log = LoggerFactory.getLogger(Base64Image.class);

    public static String imgeArray [] = {"bmp","dib","gif","jfif","jpe","jpeg","jpg","png","tif","tiff","ico"};

    public static void main(String[] args) {
//		String strImg="";
//		try {
//			// 测试从Base64编码转换为图片文件
//			strImg = toBase64ByBuffer(new File(
//					"D:/DocImage/0000_22001.jpg"));
//			System.out.println(strImg);
//			// 保存文件
//			FileOutputStream out = new FileOutputStream("D:\\11.txt");
//			out.write(strImg.getBytes());
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

        String pic = "D:\\pic\\001.jpg";
        String base64Str = GetImageStr(pic);
        System.out.println("======="+base64Str);
        /*String base64Str = "";
        GenerateImage(base64Str, "D:\\11.jpg");*/
        // long endTime = System.currentTimeMillis();
        // System.out.println(endTime - startTime);
        // getBase64ToByte("D:\\vams_pic\\机动车查验记录表3");
    }

    /**
     * 直接传入数组对象
     * */
    public static String getImageStr(byte[] bts) {
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return Base64.encodeBase64String(bts);
        // 返回Base64编码过的字节数组字符串
        //return encoder.encode(bts);
    }

    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imgFilePath
     * @return
     */
    public static String GetImageStr(String imgFilePath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "文件丢失！";
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    /**
     * 根据base64字符串生成图片
     * @param imgStr
     * @param imgFilePath
     * @return
     */
    public static boolean GenerateImage(String imgStr, String imgFilePath) {
        // 图像数据为空
        if (imgStr == null) {
            return false;
        }
        // 替换空格为换行
        if (imgStr.indexOf(" ") >= 0) {
            imgStr = imgStr.replaceAll(" ", "");
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            // 不进行Base64解码,直接保存base64码
            // byte[] bytes = imgStr.getBytes();
            for (int i = 0; i < bytes.length; ++i) {
                // 调整异常数据
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            // 保存文件
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr
     * @param imgFilePath
     * @return
     */
    public static boolean GenerateImage2(String imgStr, String imgFilePath) {
        // 图像数据为空
        if (imgStr == null) {
            return false;
        }
        // 替换空格为换行
        if (imgStr.indexOf(" ") >= 0) {
            imgStr = imgStr.replaceAll(" ", "");
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                // 调整异常数据
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            // 保存文件
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static byte[] getBase64ToBytePic(String base64Str) {
        // 图像数据为空
        if (base64Str == null) {
            return null;
        }
        // 替换空格为换行
        if (base64Str.indexOf(" ") >= 0) {
            base64Str = base64Str.replaceAll(" ", "");
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(base64Str);
            // 不进行Base64解码,直接保存base64码
            // byte[] bytes = imgStr.getBytes();
            for (int i = 0; i < bytes.length; ++i) {
                // 调整异常数据
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }

            return bytes;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 图片到byte数组
     * @username：ltj
     * @Title: image2byte
     * @Description: TODO
     * @param: @param path
     * @param: @return
     * @return: byte[]
     * @throws
     */
    public static byte[] image2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }
    /**
     * base64 视频base64字符串
     * videoFilePath  输出视频文件路径带文件名
     */
    public static void base64ToVideo(String base64, String videoFilePath) {
        try {
            //base解密
            byte[] videoByte = new sun.misc.BASE64Decoder().decodeBuffer(base64);
            File videoFile = new File(videoFilePath);
            //输入视频文件
            FileOutputStream fos = new FileOutputStream(videoFile);
            fos.write(videoByte, 0, videoByte.length);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            log.error("base64转换为视频异常");
        }
    }
    /**
     * 本地图片转成InputStream流对象。需要一个参数，base64编码的字符串
     * @param base64string
     * @return
     */
    public static InputStream BaseToInputStream(String base64string){
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            for (int i = 0; i < bytes1.length; ++i) {
                // 调整异常数据
                if (bytes1[i] < 0) {
                    bytes1[i] += 256;
                }
            }
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return stream;
    }

    /**
     * 判断是否为图片
     * @param picurl
     * @return
     */
    public static boolean isPic(String picurl){
        if("".equals(picurl) || null==picurl){
            // 返回不和合法
            return false;
        }
        //获取文件后缀
        int inxex = picurl.lastIndexOf(".");
        String tmpName = picurl.substring(picurl.lastIndexOf(".") + 1,picurl.length());
        if(null == tmpName || "".equals(tmpName) ||inxex==-1){
            return false;
        }else{
            for(int i=0;i<imgeArray.length;i++){
                if(tmpName.toLowerCase().equals(imgeArray[i])){
                    return true;
                }
            }
            return true;
        }
    }

    /**
     * 将base64字符保存文本文件
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void toFile(String base64Code, String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

}

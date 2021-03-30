package com.pd.monitor.wx.wxutlis.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.apache.commons.codec.binary.Hex;
public class JKUtils {

    /**
     * 排序后生成待签名数据
     * @param treeMap
     * @return
     */
    public static String createLinkString(TreeMap<String,String> treeMap){
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<Map.Entry<String, String>> it = treeMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            stringBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String result = stringBuffer.substring(0,stringBuffer.length()-1);
        return result;
    }

    /**
     * 安全签名
     * @param data
     * @return
     */
    public static String getSign(String data) {
        String signature = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(data.getBytes("UTF-8"));
            byte[] digest = crypt.digest();
            signature = Hex.encodeHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return signature;
    }

    public static void main(String[] args) throws Exception {
        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.put("appId", "APPID88888");
        treeMap.put("serialNo", "sn12345678900987654321");
        treeMap.put("timeStamp", "20210222155624");
        treeMap.put("method", "***");
        treeMap.put("data", "ikNgUf3H6EcWETumW9f0bpHToa1Me1ph0uMOHE9BMoY=");
        treeMap.put("appKey", "123456");
        String px = createLinkString(treeMap);
        String sign = getSign(px);
        System.out.println("签名："+sign);
    }

}

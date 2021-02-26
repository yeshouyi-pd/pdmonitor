package com.pd.wxbase.wx.wxutlis.utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Aes {

    public static final String appKey = "123456";

    /**
     * AES编码
     * @param date
     * @param middleKey
     * @return
     */
    public static String getEncoder(String date ,byte[] middleKey) {
        try {
            // 生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(new SecureRandom(middleKey));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();
            Key key = new SecretKeySpec(byteKey, "AES");
            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(date.getBytes());
            return getEncoderByte(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解码
     * @param date
     * @param middleKey
     * @return
     */
    public static String getDecoder(String date, byte[] middleKey){
        try {
            // 生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(new SecureRandom(middleKey));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();
            Key key = new SecretKeySpec(byteKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoderByte = getDecoderByte(date);
            byte[] result = cipher.doFinal(decoderByte);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * base64编码
     * @param orig
     * @return
     */
    public static String getEncoderByte(byte[] orig) {
        String str = "";
        str = Base64.getEncoder().encodeToString(orig);
        return str;
    }

    /**
     * base64解码
     * @param decodeStr
     * @return
     */
    public static byte[] getDecoderByte(String decodeStr) {
        byte[] decode = Base64.getDecoder().decode(decodeStr);
        return decode;
    }

    /**
     * MD5编码
     * @param origin
     * @param charsetname
     * @return
     */
    public static byte[] MD5EncodeByte(String origin, String charsetname) {
        byte[] digest = null;
        String resultString = "";
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                digest = md.digest(resultString.getBytes());
            } else
                digest = md.digest(resultString.getBytes(charsetname));
        } catch (Exception exception) {
        }
        return digest;
    }

    public static void main(String[] args) throws Exception {
        String data = "{\"billNo\":\"000000000001\"}";
        byte[] key = MD5EncodeByte(appKey, "UTF-8");
        String jiam = Aes.getEncoder(data, key);
        System.out.println("加密:"+jiam);
        String jiem = getDecoder(jiam, key);
        System.out.println("解密:"+jiem);
    }

}

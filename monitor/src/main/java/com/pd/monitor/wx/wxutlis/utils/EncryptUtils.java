package com.pd.monitor.wx.wxutlis.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

public class EncryptUtils {


private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
//        map.put("zjhm","420123545687952123654");
        map.put("deptcode","13131313");
        map.put("ywfl","2");
        map.put("ywlx","7");
        String dataStr = JSONObject.toJSONString(map);
        String dataAesEncrypt = EncryptUtils.aesEncrypt(dataStr, Md5Util.getMD5("123456", 16));
        System.out.println(dataAesEncrypt);
    }

    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥  
     * @return 加密后的byte[]
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
         KeyGenerator kgen = KeyGenerator.getInstance("AES");
         kgen.init(128);
         Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
         cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

         return cipher.doFinal(content.getBytes("utf-8"));
    }

     /**
      * AES加密为base 64 code
      *
      * @param content 待加密的内容
      * @param encryptKey 加密密钥
      * @return 加密后的base 64 code
      */
     public static String aesEncrypt(String content, String encryptKey) throws Exception {
         return base64Encode(aesEncryptToBytes(content, encryptKey));
     }

     public static String base64Encode(byte[] bytes){
         return Base64.encodeBase64String(bytes);
     }

    /**
      * AES解密
      *
      * @param encryptBytes 待解密的byte[]
      * @param decryptKey 解密密钥
      * @return 解密后的String
      */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
         KeyGenerator kgen = KeyGenerator.getInstance("AES");
         kgen.init(128);
         Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
         cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
         byte[] decryptBytes = cipher.doFinal(encryptBytes);
         return new String(decryptBytes);
    }

     /**
      * 将base 64 code AES解密
      *
      * @param encryptStr 待解密的base 64 code
      * @param decryptKey 解密密钥
      * @return 解密后的string
      */
     public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
         return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
     }

     public static byte[] base64Decode(String base64Code) throws Exception{
         return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
     }
}

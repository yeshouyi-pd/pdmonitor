package com.pd.monitor.wx.wxutlis.utils;

import java.security.MessageDigest;
import java.util.*;

public class SignSHA {

    public static void main(String[] args) throws Exception {
        Map<String, Object> sArray = new HashMap<>();
        sArray.put("appId","APPID88888");
        sArray.put("serialNo","sn12345678900987654321");
        sArray.put("timeStamp","20210222155624");
        sArray.put("method","NewsHeadlines");
        sArray.put("data","KG2miYeWQxsM+Txusqy+Hg==");
        sArray.put("sign","120000");
        String sign = buildSign(sArray);
        System.out.println(sign);
    }

    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 生成签名结果
     * @param sArray 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildSign(Map<String, Object> sArray) throws Exception {
        Map<String, Object> result = paraFilter(sArray);
        String prestr = createLinkString(result); //把数组所有元素，按照“参数参数值”的模式拼接成字符串
        String mysign = shaEncode(prestr);
        return mysign;
    }

    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, Object> paraFilter(Map<String, Object> sArray) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            Object value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("returnMsg") || key.equalsIgnoreCase("returnCode")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数参数值”的模式拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = params.get(key);
            prestr = prestr + key + value;
        }
        return prestr;
    }
}

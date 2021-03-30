package com.pd.monitor.wx.wxutlis.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.*;

/**
 * 创建订单
 */
public class BuildSign {
    public static void main(String[] args) throws Exception {
        String data = "{ \"login_name\": \"ceshi\",\"login_pwd\": \"ceshi123\",\"time_stamp\": \"1561175770\"}";
        Map<String, Object> params = JSONObject.parseObject(data, new TypeReference<Map<String, Object>>(){});
        System.out.println(params);
        System.out.println(buildSign(params,"abc"));
    }

    /**
     * 生成签名结果
     * @param sArray 要签名的数组
     * @param key 通知密钥
     * @return 签名结果字符串
     */
    public static String buildSign(Map<String, Object> sArray,String key) {
        Map<String, Object> result = paraFilter(sArray);
        //System.out.println(result);
        String prestr = createLinkString(result); //把数组所有元素，按照“参数参数值”的模式拼接成字符串
        prestr = key + prestr; //把接口密钥+拼接后的字符串直接连接起来
        System.out.println("MD5加密之前的字符串:"+prestr);
        String mysign = Md5Util.getMD5(prestr,32);
        System.out.println("MD5加密之后的字符窜:"+mysign);
        if (mysign != null) {
            mysign = mysign.toUpperCase();
        }
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
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
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
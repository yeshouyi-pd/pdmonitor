package com.pd.monitor.wx.wxutlis.utils;


import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.dto.basewx.my.ResultDto;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *@Title TencentAITools.java
 *@description TODO
 *@version 1.0
 */
public class TencentAITools {

	/**
     * 获取时间戳(秒)
     */
    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
    
    /** 
     * 取出一个指定长度大小的随机正整数. 
     * @param length 
     *            int 设定所取出随机数的长度。length小于11 
     * @return int 返回生成的随机数。 
     */  
    public static int buildRandom(int length) {  
        int num = 1;  
        double random = Math.random();  
        if (random < 0.1) {  
            random = random + 0.1;  
        }  
        for (int i = 0; i < length; i++) {  
            num = num * 10;  
        }  
        return (int) ((random * num));  
    }  
	
	/** 
     * 获取当前时间 yyyyMMddHHmmss 
     */  
    public static String getCurrTime() {  
        Date now = new Date();  
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
        String s = outFormat.format(now);  
        return s;  
    } 
	
    /**
     * 生成随机字符串
     */ 
    public static String getNonceStr() {
        String currTime = getCurrTime();  
        String strTime = currTime.substring(8, currTime.length());  
        String strRandom = buildRandom(4) + "";  
        return strTime + strRandom;
    }
	
    /**
     * 获取所有请求参数集合
     * @Title: getParamsMap
     * @Description:TODO
     * @param:@param person_Id_body	非公共参数集合
     * @param:@return
     * @param:@throws Exception 
     * @return Map<String,String>
     * @author ldq
     * @date 2018-5-17上午10:13:22
     */
    public static Map<String,String> getParamsMap(Map<String,String> person_Id_body) throws Exception {
		person_Id_body.put("app_id", TencentAPI.APP_ID_AI);
		person_Id_body.put("time_stamp", TencentAITools.getTimestamp());
		person_Id_body.put("nonce_str", TencentAITools.getNonceStr());
		String sign = TencentAISignSort.getSignature(person_Id_body);
//		System.err.println(sign);
		person_Id_body.put("sign", sign);  //将Sign也放入Map中
		return person_Id_body;
    }
    
    /**
     * 获取AI识别返回
     * @Title: getAIResult
     * @Description:TODO
     * @param:@param person_Id_body	所有请求参数map集合
     * @param:@return
     * @param:@throws Exception 
     * @return Result<T>
     * @author ldq
     * @date 2018-5-17上午10:21:07
     */
    public static <T> ResultDto<T> getAIResult(String jkurl, Map<String,String> person_Id_body, Class T) throws Exception{
	    //这我就不用说了吧，这是头信息需要的
	    Map<String, String> headers = new HashMap<>();   //headers头
	    headers.put("Content-Type", "application/x-www-form-urlencoded");
	    HttpResponse responseBD = HttpsUtil4Tencent.doPostTencentAI(jkurl, headers, person_Id_body);
	    String json = EntityUtils.toString(responseBD.getEntity());
        ResultDto<T> result = (ResultDto<T>) JSONObject.toJavaObject(JSONObject.parseObject(json), ResultDto.class);
	    return result;
    }
    
    /**
     * 获取AI识别返回 json 串
     * @Title: getAIResultJson
     * @Description:TODO
     * @param:@param jkurl
     * @param:@param person_Id_body
     * @param:@return
     * @param:@throws Exception 
     * @return String
     * @author ldq
     * @date 2018-5-22上午11:53:18
     */
    public static JSONObject getAIResultJson(String jkurl,Map<String,String> person_Id_body) throws Exception{
    	//这我就不用说了吧，这是头信息需要的
    	Map<String, String> headers = new HashMap<>();   //headers头
    	headers.put("Content-Type", "application/x-www-form-urlencoded");
    	HttpResponse responseBD = HttpsUtil4Tencent.doPostTencentAI(jkurl, headers, person_Id_body);
    	String json = EntityUtils.toString(responseBD.getEntity());
    	return JSONObject.parseObject(json);
    }
}

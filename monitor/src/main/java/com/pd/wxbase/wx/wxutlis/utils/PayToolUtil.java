package com.pd.wxbase.wx.wxutlis.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

public class PayToolUtil {
	
    /** 
     * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。 
     * @return boolean 
     */  
	public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            String v = (String)entry.getValue();  
            if(!"sign".equals(k) && null != v && !"".equals(v)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + API_KEY);  
        //算出摘要  
        String mysign = MD5Utils.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
        String tenpaySign = ((String)packageParams.get("sign")).toLowerCase();  
        return tenpaySign.equals(mysign);  
    }  
   
    /** 
     * @author 
     * @date 2016-4-22 
     * @Description：sign签名 
     * @param characterEncoding 
     *            编码格式 
     * @return
     */  
	public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = (String) entry.getValue();  
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");
            }  
        }  
        sb.append("key=" + API_KEY);  
        String sign = MD5Utils.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;  
    }  
   
    /** 
     * @author 
     * @date 2016-4-22 
     * @Description：将请求参数转换为xml格式的string 
     * @param parameters 
     *            请求参数 
     * @return 
     */  
	public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        Set es = parameters.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = (String) entry.getValue();  
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {  
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");  
            } else {  
                sb.append("<" + k + ">" + v + "</" + k + ">");  
            }  
        }  
        sb.append("</xml>");  
        return sb.toString();  
    }  
   
    /** 
     * 取出一个指定长度大小的随机正整数. 
     *  
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
     *  
     * @return String 
     */  
    public static String getCurrTime() {  
        Date now = new Date();  
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
        String s = outFormat.format(now);  
        return s;  
    }  
    
    /**
     * string转date
     * @username：ltj
     * @Title: FormatDate
     * @Description: TODO
     * @param: @param date (yyyyMMddHHmmss)
     * @param: @return   
     * @return: Date   
     * @throws
     */
	public static Date FormatDate(String date){
    	return new Date(Integer.valueOf(date.substring(0,4)),Integer.valueOf(date.substring(4,6)),Integer.valueOf(date.substring(6,8)),Integer.valueOf(date.substring(8,10)),Integer.valueOf(date.substring(10,12)),Integer.valueOf(date.substring(12,14)));
    }
    public static void main(String[] args) {
		String date = "20170829173230";
		System.out.println(FormatDate(date));
	}
    
    /**
     * 随机字符串生成   
     */
    public static String getRandomString(int length) { // length表示生成字符串的长度  
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            int number = random.nextInt(base.length());  
            sb.append(base.charAt(number));  
        }  
        return sb.toString();  
    }
    
    /**
     * 随机字符串生成(不限制长度)
     */
    public static String createNoncestr() {  
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
	    String res = "";  
	    for (int i = 0; i < 16; i++) {  
	        Random rd = new Random();  
	        res += chars.charAt(rd.nextInt(chars.length() - 1));  
	    }  
	    return res;  
	 }
    
    /**
	 * 获取当前系统前一天日期
	 * @username：ltj
	 * @Title: getNextDay
	 * @Description: TODO
	 * @param: @param date
	 * @param: @return   
	 * @return: Date   
	 * @throws
	 */
	public static Date getNextDay(Date date) {  
         Calendar calendar = Calendar.getInstance();  
         calendar.setTime(date);  
         calendar.add(Calendar.DAY_OF_MONTH, -1);  
         date = calendar.getTime();  
         return date;  
    }  

	/**
	 * 返回"yyyyMMdd"格式日期 
	 * @username：ltj
	 * @Title: getDateYYYYMMDD
	 * @Description: TODO
	 * @param: @return   
	 * @return: String   
	 * @throws
	 */
    public static String getDateYYYYMMDD(Date newdate){
        long datetime = newdate.getTime() ;
        Timestamp timestamp = new Timestamp(datetime) ;
        String currentdate = (timestamp.toString()).substring(0,4)+ (timestamp.toString()).substring(5,7)  +(timestamp.toString()).substring(8,10);
        return currentdate;
    }
    
    /**
	 * 返回"yyyy-MM-dd"格式日期 
	 * @username：ltj
	 * @Title: getDate
	 * @Description: TODO
	 * @param: @return   
	 * @return: String   
	 * @throws
	 */
    public static String getDate(Date newdate){
        long datetime = newdate.getTime() ;
        Timestamp timestamp = new Timestamp(datetime) ;
        String currentdate = (timestamp.toString()).substring(0,4)+ "-" + (timestamp.toString()).substring(5,7)+"-" +(timestamp.toString()).substring(8,10);
        return currentdate;
    }
    
    
}

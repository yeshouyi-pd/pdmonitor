package com.pd.server.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTools {

	/**
	 * 格式化类型：年月日（yyyy-MM-dd）
	 * */
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	/**
	 * 格式化类型：xxxx年xx月xx日
	 * */
	public static final String $yyyy_MM_dd = "yyyy年MM月dd日";
	/**
	 * 格式化类型：年月日（yyyyMMdd）
	 * */
	public static final String yyyyMMdd = "yyyyMMdd";
	/**
	 * 格式化类型：年月日时分秒（yyyy-MM-dd HH:mm:ss）
	 * */
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 格式化类型：年月日时分秒（yyyyMMddHHmmss）
	 * */
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	/**
	 * 转换类型：yyyy年MM月dd日  HH:mm:ss
	 * */
	public static final String nian_yue_ri_shi_fen_miao="yyyy年MM月dd日  HH:mm:ss";

	private DateTools() {
	}

	/**
	 * 获取格式化日期字符串
	 * 
	 * @param date
	 *            日期对象
	 * @param type
	 *            格式化方式，参考：DateTools的静态常量
	 * @author BraveHeartWzm
	 * 
	 * */
	public static String getFormatDate(Date date, String type) {
		String ret = null;
		if (null == date) {
			return null;
		} else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
			ret = simpleDateFormat.format(date);
			simpleDateFormat = null;
		}
		return ret;
	}

	/**
	 * 获取当前的格式化日期字符串
	 * 
	 * @param type
	 *            格式化方式，参考：DateTools的静态常量
	 * @author BraveHeartWzm
	 * */
	public static String getCurrentFormatDate(String type) {
		return getFormatDate(new Date(), type);
	}

	/**
	 * 字符串转换日期
	 * */
	public static Date toDate(String dateStr, String type) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("日期转换异常");
		}
		return null;
	}

	/**
	 * 获取当前时间 年月 yyyyMM
	 * 
	 * @return 如：201312
	 * @author wangwei 2013-12-31
	 */
	public static String getYM() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String YM = sdf.format(new Date());
		return YM;
	}

	/**
	 * 获取当前时间的年月日
	 * 
	 * @return
	 */
	public static String getYMD() {
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
		String YMD = sdf.format(new Date());
		return YMD;
	}

	/**
	 * 获取当前时间 年月日时分秒 yyyyMMddHHmmss
	 * 
	 * @return
	 * 
	 * @author WangWei 2014-1-8
	 */
	public static String getYmdhms() {
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMddHHmmss);
		String YM = sdf.format(new Date());
		return YM;
	}

	public static String date2Ym(String strDate) {
		DateFormat gmtFormat = new SimpleDateFormat("yyyyMM");
		TimeZone cstTime = TimeZone.getTimeZone("CST");
		gmtFormat.setTimeZone(cstTime);
		return gmtFormat.format(new Date(strDate));
	}

	// 当前时间
	public static String nowTime() {

		Date d = new Date();
		// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 转换格式
		return sdf.format(d).toString();

	}

	/**
	 * 获取时间序列ID 日期格式：yyyyMMddHHmmss
	 * */
	public static String getDateSeqId1(String head, String seq) {
		StringBuffer buffer = new StringBuffer();
		if (null != head && !"".equals(head)) {
			buffer.append(head);
		}
		// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		buffer.append(sdf.format(new Date()).toString());
		buffer.append(seq);
		return buffer.toString();
	}

	/**
	 * 获取时间序列ID 日期格式：yyMMddHHmmss
	 * */
	public static String getDateSeqId2(String head, String seq) {
		StringBuffer buffer = new StringBuffer();
		if (null != head && !"".equals(head)) {
			buffer.append(head);
		}
		// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		buffer.append(sdf.format(new Date()).toString());
		buffer.append(seq);
		return buffer.toString();
	}

	/**
	 * 获取时间序列ID 日期格式：yyyyMMdd
	 * */
	public static String getDateSeqId3(String head, String seq) {
		StringBuffer buffer = new StringBuffer();
		if (null != head && !"".equals(head)) {
			buffer.append(head);
		}
		// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		buffer.append(sdf.format(new Date()).toString());
		buffer.append(seq);
		return buffer.toString();
	}

	// ===================================================

	public static void main(String[] args) {
		System.out.println(DateTools.getDatePoorS(DateTools.toDate("2021-12-8 10:30:00",DateTools.yyyy_MM_dd_HH_mm_ss),DateTools.toDate("2021-12-8 10:00:00",DateTools.yyyy_MM_dd_HH_mm_ss)));
		System.out.println(DateTools.getDatePoor(DateTools.toDate("2021-12-8 10:30:00",DateTools.yyyy_MM_dd_HH_mm_ss),DateTools.toDate("2021-12-8 10:00:00",DateTools.yyyy_MM_dd_HH_mm_ss)));
	}

	/**
	 *    
     *计算两个日期之间相差的天数  
	 * @Title: daysBetween 
	 * @Description: TODO
	 * @param @param smdate 较小的时间 
	 * @param @param bdate 较大的时间 
	 * @param @return 相差天数 
	 * @param @throws ParseException    设定文件 
	 * @return int    返回类型 
	 * @throws 
	 * @author zl
	 * 2016-8-22
	*/
	public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }

	/**
	 * 计算两个时间的分钟差
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long d1 = df.parse(df.format(nowDate)).getTime();
			long d2 = df.parse(df.format(endDate)).getTime();
			long diff=(d2-d1)/1000/60;
			return String.valueOf(diff);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "0";
	}

	/**
	 * 计算两个时间的秒钟差
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static String getDatePoorS(Date endDate, Date nowDate) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long d1 = df.parse(df.format(nowDate)).getTime();
			long d2 = df.parse(df.format(endDate)).getTime();
			long diff=(d2-d1)/1000;
			return String.valueOf(diff);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "0";
	}

}

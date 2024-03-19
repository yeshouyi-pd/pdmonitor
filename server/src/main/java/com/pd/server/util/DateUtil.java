package com.pd.server.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
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

    /*
     * 系统日期的后一天
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String str = sdf.format(new Date());
        //System.out.println(sdf.format(getNextDay(new Date())));
        //System.out.println(sdf.format(getYearBefore(new Date())));
        System.out.println(sdf.format(getSecondLater(DateUtil.toDate("2024-03-19 08:50:00","yyyy-MM-dd HH:mm:ss"),-180)));
        System.out.println(sdf.format(getSecondLater(DateUtil.toDate("2024-03-19 08:50:00","yyyy-MM-dd HH:mm:ss"),180)));
        System.out.println(sdf.format(getSecondLater(DateUtil.toDate("2024-03-19 08:56:05","yyyy-MM-dd HH:mm:ss"),0)));
        System.out.println(sdf.format(getSecondLater(DateUtil.toDate("2024-03-19 08:56:05","yyyy-MM-dd HH:mm:ss"),180)));

        String str = "2022_09_08_14_29_43_2022_09_08_14_29_46_1_A2.txt";//\d{n,}
        //2022_09_05_20_18_16.jpg
        //2022_09_05_20_18_16.png
        //2022_09_05_20_18_16.txt
        //2022_09_05_20_18_16.wav
        //2022_09_05_20_18_16_predation.jpg
        //2022_08_16_15_44_54_predation.png
        //2022_08_16_15_44_54_predation.txt
        //2022_08_16_15_44_54_predation.wav
        //2022_07_27_13_36_50_predation_59.txt
        //2022_07_30_09_32_15_0.txt
        //2022_09_05_09_04_54_0_A2.txt
        //2022_09_08_14_29_43_2022_09_08_14_29_46_1_A2.txt
        String pattern = "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A2.txt$";
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}.txt$";
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}.jpg$";
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}.png$";
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}.wav$";
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation.jpg$"
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation.png$"
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation.txt$"
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation.wav$"
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}.txt$"
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}.txt$";
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A2.txt$";
        // "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A2.txt$";


        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());

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
    // 获取上周一
    public static String getLastWeekMonday(Date date) {
        Date a = DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(cal.getTime());
    }


    // 获取上周日
    public static String getLastWeekSunday(Date date) {
        Date a = DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(cal.getTime());
    }

    // 获取当前周一
    public static String getWeekMonday(Date date) {
        Date a = DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.WEEK_OF_YEAR, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(cal.getTime());
    }


    // 获取当前周日
    public static String getWeekSunday(Date date) {
        Date a = DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(cal.getTime());
    }
**/

    /**
     * 日期推后N天
     * @Title: getDaysLater
     * @Description:TODO
     * @param:@param date
     * @param:@param count	天数
     * @param:@return
     * @return Date
     * @author ldq
     * @date 2018-3-23下午12:40:35
     */
    public static Date getDaysLater(Date date,int count){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DAY_OF_YEAR, count);
        return calendar.getTime();
    }

    public static Date getHoursLater(Date date,int count){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.HOUR_OF_DAY, count);
        return calendar.getTime();
    }

    public static Date getMinutesLater(Date date,int count){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MINUTE, count);
        return calendar.getTime();
    }

    public static Date getSecondLater(Date date,int count){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.SECOND, count);
        return calendar.getTime();
    }

    /**
     * 日期推前一年
     * @param date
     * @return
     */
    public static Date getYearBefore(Date date){
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)-1);
        return curr.getTime();
    }

    /*
     * 系统日期的后num天
     */
    public static Date getNextNumDay(Date date,int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        date = calendar.getTime();
        return date;
    }

    /**
     * 系统日期往前或者往后推num月
     * @param num
     * @return
     */
    public static Date getMonthBeforeOrLater(int num){
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+num);
        return curr.getTime();
    }


}

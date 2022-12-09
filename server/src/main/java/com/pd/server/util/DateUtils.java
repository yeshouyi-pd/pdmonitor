package com.pd.server.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    /**
     * 时间转字符串
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateToStr(Date date) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 时间转字符串
     * @return 返回时间类型
     */
    public static String getDateToStrFormat(Date date, String Format) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(Format);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 字符串转时间
     * @return 返回时间类型
     */
    public static Date getStrToDateFormat(String date, String Format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(Format);
        Date parse = formatter.parse(date);
        return parse;
    }

    /**
     * 字符串转时间
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getStrToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = formatter.parse(date);
        return parse;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(getBeginWeek());
        System.out.println(getEndWeek());
        System.out.println(getBeginDayStrOfSomeMonth(0));
        System.out.println(getEndDayStrOfSomeMonth(0));
        System.out.println(getBeginDayStrOfSomeMonth(1));
        System.out.println(getEndDayStrOfSomeMonth(1));
    }

    //获取某年某月的第一天日期
    public static Date getStartMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getTime();
    }

    //获取某年某月的最后一天日期
    public static Date getEndMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    // 获取本周开始时间
    public static String getBeginWeek() {
        Calendar cal = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayWeek==1){
            dayWeek = 8;
        }
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        return DateUtil.getFormatDate(getDayStartTime(cal.getTime()),"yyyy-MM-dd HH:mm:ss");
    }

    // 获取本周结束时间
    public static String getEndWeek() {
        Calendar cal = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayWeek==1){
            dayWeek = 8;
        }
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
        return DateUtil.getFormatDate(getDayStartTime(cal.getTime()),"yyyy-MM-dd HH:mm:ss");
    }

    // 获取前面第几个月（上月为第一个月）的开始时间
    public static String getBeginDayStrOfSomeMonth(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1 - i, 1);
        return DateUtil.getFormatDate(getDayStartTime(calendar.getTime()),"yyyy-MM-dd HH:mm:ss");
    }

    // 获取前面第几个月（上月为第一个月）的结束时间
    public static String getEndDayStrOfSomeMonth(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1 - i, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1 - i, day);
        return DateUtil.getFormatDate(getDayEndTime(calendar.getTime()),"yyyy-MM-dd HH:mm:ss");
    }

    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }
    //获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }
}

package com.pd.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        System.out.println(new Date());
        System.out.println(getDateToStr(new Date()));
        System.out.println(getStrToDateFormat("2020-10-21","yyyy-MM-dd"));
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
}

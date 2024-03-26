package com.pd.monitor.netsdk.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * yymmddhh
     * @param odate
     * @param otime
     * @return
     */
    public static String getOdate(String odate, String otime) {
        LocalDate date = LocalDate.parse(odate);
        LocalTime time = LocalTime.parse(otime);
        LocalDateTime dateTime = LocalDateTime.of(date,time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHH");
        return dateTime.format(formatter);
    }
}

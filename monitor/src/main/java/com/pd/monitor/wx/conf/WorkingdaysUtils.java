package com.pd.wxbase.wx.conf;

import com.pd.server.config.RedisCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 获取有效工作日
 */

public class WorkingdaysUtils {



    //获取时间的方法
    public static Date getBeforeOrAfterDate(Date date, int num) {
        Calendar calendar = Calendar.getInstance();//获取日历
        calendar.add(Calendar.DATE, num);
        Date d = calendar.getTime();//把日历转换为Date
        return d;
    }


    /**
     * 获取后N天的法定上班时间
     * 2为上班
     * 1为休息
     * @param maxday
     * @return
     */
    public static Map<String,String>  getWorkingDays(int maxday){
        Map totalMap = new TreeMap();//1 休息 2上班
        totalMap = (Map) BaseWxController.redisTstaticemplate.opsForValue().get(RedisCode.ISWORKINGDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式转换
        Map<String,String> map = new LinkedHashMap<String,String>();
        for (int i = 0; i < maxday; i++) {
            String thisDay = sdf.format(getBeforeOrAfterDate(new Date(), i));
                    if(getintInWeek(thisDay) >5){//周末
                        if("2".equals(totalMap.get(thisDay))){//周末上班
                            map.put(thisDay,"2");
                        }else{//休息
                            map.put(thisDay,"1");
                        }

                    }else{//工作日
                        if("1".equals(totalMap.get(thisDay))){//工作日休息
                            map.put(thisDay,"1");
                        }else{//上班
                            map.put(thisDay,"2");
                        }
                    }

        }
        return map;

    }


    public static void main(String[] args) {
        getWorkingDays(7);

    }


    /**
     * 获取今天是星期几
     * @param dates
     * @return
     */
    public  static  int getintInWeek(String dates) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = f.parse(dates);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        cal.setTime(d);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0) w = 7;
        return  w;
    }
}

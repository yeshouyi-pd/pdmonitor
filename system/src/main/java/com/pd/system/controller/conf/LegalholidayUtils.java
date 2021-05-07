package com.pd.system.controller.conf;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.config.SpringUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 获取当前年的法定节假日
 * 每月更新一次
 */
@Component
public class LegalholidayUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     *  每天中午十二点触发
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public static void loop() {
        Map totalMap = new TreeMap();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        for(int w = 0;w <= 1; w++){
            int thisyear = (year+w);
            String stryear = thisyear+"";
            String url = "http://opendata.baidu.com/api.php?query="+stryear+"%E5%B9%B4&resource_id=6018&format=json";
            String s = LegalholidayUtils.sendGet(url, "");
            Map<String, Object> map = (Map<String, Object>) JSONObject.parse(s);
            List list = (List) map.get("data");
            if(!CollectionUtils.isEmpty(list)){
                Map<String, Object> innerMap = (Map<String, Object>) ( list.get(0));
                if(null != innerMap.get("holiday") ){
                    List holidayList = new ArrayList();
                    Object obj = innerMap.get("holiday");
                    if(obj instanceof List) {
                        holidayList = (List) obj;
                    }else {
                        holidayList.add((Map)obj);
                    }

                    for(int i = 0; i < holidayList.size(); i++) {
                        List innerHolidayList = (List) ((Map)holidayList.get(i)).get("list");
                        for(int j = 0; j < innerHolidayList.size(); j++) {
                            Map el = (Map) innerHolidayList.get(j);
                            try {
                                String dates[] = el.get("date").toString().split("-");
                                String thisdata = "";
                                if(dates.length ==3){
                                    if(dates[1].length() ==1){
                                        thisdata = dates[0]+"-0"+dates[1];
                                    }else{
                                        thisdata = dates[0]+"-"+dates[1];
                                    }
                                    if(dates[2].length() ==1){
                                        thisdata =thisdata+"-0"+dates[2];
                                    }else{
                                        thisdata = thisdata+"-"+dates[2];
                                    }
                                }
                                totalMap.put(thisdata, el.get("status"));
                            }catch (Exception e){
                            }
                        }
                    }
                }
            }
        }

        RedisConfig.redisTstaticemplate.opsForValue().set(RedisCode.ISWORKINGDAY, totalMap);//将参数信息写入redis缓存

        //  for (Object o : totalMap.keySet()) {
          //  System.out.println("key=" + o + " value=" + totalMap.get(o));
          // }

    }




    public static void main(String[] args) {
        loop();
        getWeek("2021-07-16");

    }



    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "/" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            int cot = 0;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取当前是星期几
     * @param dates
     */
    public  static  void getWeek(String dates) {
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
        System.out.println("星期" + w);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(LegalholidayUtils.applicationContext == null) {
            LegalholidayUtils.applicationContext = applicationContext;
        }
    }
}

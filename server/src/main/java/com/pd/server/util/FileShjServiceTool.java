package com.pd.server.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileShjServiceTool {

    //方位角统计
    public static JSONObject getStatic(String jtnr){
        String[] nrArr = jtnr.split("/");
        Map<String, Map<String,Integer>> rqMap = new HashMap<>();
        Map<String,Integer> rqTsMap = new HashMap<>();
        for(String rqandjdStr : nrArr){
            String[] rqandjd = rqandjdStr.split("-");//2022_10_15_02_15_14 0:105,1:235
            String rqStr = rqandjd[0].substring(0,16);//2022_10_15_02_15
            String jdStr = rqandjd[1];//0:105,1:235
            if(rqMap.containsKey(rqStr)){
                Map<String,Integer> jdNum = rqMap.get(rqStr);
                String[] jdarr = jdStr.split(",");//0:105 1:235
                rqTsMap.put(rqStr, rqTsMap.get(rqStr)+jdarr.length);
                for(String jd : jdarr){
                    String[] arr = jd.split(":");//0 105
                    Float jtjd = Float.parseFloat(arr[1]);
                    if(jtjd>=0 && jtjd<45){
                        jdNum.put("north_northeast",jdNum.get("north_northeast")+1);
                    }else if(jtjd>=45 && jtjd<90){
                        jdNum.put("northeast_east",jdNum.get("northeast_east")+1);
                    }else if(jtjd>=90 && jtjd<135){
                        jdNum.put("east_eastsouth",jdNum.get("east_eastsouth")+1);
                    }else if(jtjd>=135 && jtjd<180){
                        jdNum.put("eastsouth_south",jdNum.get("eastsouth_south")+1);
                    }else if(jtjd>=180 && jtjd<225){
                        jdNum.put("south_southwest",jdNum.get("south_southwest")+1);
                    }else if(jtjd>=225 && jtjd<270){
                        jdNum.put("southwest_west",jdNum.get("southwest_west")+1);
                    }else if(jtjd>=270 && jtjd<315){
                        jdNum.put("west_westnorth",jdNum.get("west_westnorth")+1);
                    }else if(jtjd>=315 && jtjd<360){
                        jdNum.put("westnorth_north",jdNum.get("westnorth_north")+1);
                    }
                }
                rqMap.put(rqStr, jdNum);
            }else{
                Map<String,Integer> jdNum = new HashMap<>();
                jdNum.put("north_northeast",0);
                jdNum.put("northeast_east",0);
                jdNum.put("east_eastsouth",0);
                jdNum.put("eastsouth_south",0);
                jdNum.put("south_southwest",0);
                jdNum.put("southwest_west",0);
                jdNum.put("west_westnorth",0);
                jdNum.put("westnorth_north",0);
                String[] jdarr = jdStr.split(",");
                rqTsMap.put(rqStr, jdarr.length);
                for(String jd : jdarr){
                    String[] arr = jd.split(":");//0 105
                    Float jtjd = Float.parseFloat(arr[1]);
                    if(jtjd>=0 && jtjd<45){
                        jdNum.put("north_northeast",jdNum.get("north_northeast")+1);
                    }else if(jtjd>=45 && jtjd<90){
                        jdNum.put("northeast_east",jdNum.get("northeast_east")+1);
                    }else if(jtjd>=90 && jtjd<135){
                        jdNum.put("east_eastsouth",jdNum.get("east_eastsouth")+1);
                    }else if(jtjd>=135 && jtjd<180){
                        jdNum.put("eastsouth_south",jdNum.get("eastsouth_south")+1);
                    }else if(jtjd>=180 && jtjd<225){
                        jdNum.put("south_southwest",jdNum.get("south_southwest")+1);
                    }else if(jtjd>=225 && jtjd<270){
                        jdNum.put("southwest_west",jdNum.get("southwest_west")+1);
                    }else if(jtjd>=270 && jtjd<315){
                        jdNum.put("west_westnorth",jdNum.get("west_westnorth")+1);
                    }else if(jtjd>=315 && jtjd<360){
                        jdNum.put("westnorth_north",jdNum.get("westnorth_north")+1);
                    }
                }
                rqMap.put(rqStr, jdNum);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rqMap",rqMap);
        jsonObject.put("rqTsMap",rqTsMap);
        return jsonObject;
    }

}

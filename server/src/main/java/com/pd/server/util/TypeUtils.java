package com.pd.server.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class TypeUtils {
    /** 预警类型**/
    public static  final String   WJLX ="wjlx";

    public static  final String   TYPE ="type";
    /** 头数**/
    public static  final String   TS ="ts";

    public static  final String   TXTLX ="txtlx";
    /** 文件名称**/
    public static  final String   WJMC ="wjmc";


    public static final String KEY_1 = "KEY_1";
    public static final String KEY_2 = "KEY_2";
    public static final String KEY_3 = "KEY_3";
    public static final String KEY_4 = "KEY_4";
    public static final String KEY_5 = "KEY_5";
    public static final String KEY_6 = "KEY_6";
    public static final String KEY_7 = "KEY_7";
    public static final String KEY_8 = "KEY_8";
    public static final String KEY_9 = "KEY_9";
    public static final String KEY_10 = "KEY_10";
    public static final String KEY_11 = "KEY_11";
    public static final String KEY_12 = "KEY_12";
    public static final String KEY_13 = "KEY_13";


    public static final String ZZ_1 = "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}.txt$";
    public static final String ZZ_2 = "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}.jpg$";
    public static final String ZZ_3 = "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}.png$";
    public static final String ZZ_4 = "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}.wav$";
    public static final String ZZ_5 = "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation.jpg$";
    public static final String ZZ_6=  "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation.png$";
    public static final String ZZ_7 = "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation.txt$";
    public static final String ZZ_8 = "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation.wav$";
    public static final String ZZ_9 = "^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}.txt$";
    public static final String ZZ_10 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}.txt$";
    public static final String ZZ_11 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A2.txt$";
    public static final String ZZ_12 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A2.txt$";
    public static final String ZZ_13 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A2_\\w{1,}.mp4$";

    public static Map<String,String> patternmap = new HashMap<>();
    static {
        patternmap.put(KEY_1,ZZ_1);
        patternmap.put(KEY_2,ZZ_2);
        patternmap.put(KEY_3,ZZ_3);
        patternmap.put(KEY_4,ZZ_4);
        patternmap.put(KEY_5,ZZ_5);
        patternmap.put(KEY_6,ZZ_6);
        patternmap.put(KEY_7,ZZ_7);
        patternmap.put(KEY_8,ZZ_8);
        patternmap.put(KEY_9,ZZ_9);
        patternmap.put(KEY_10,ZZ_10);
        patternmap.put(KEY_11,ZZ_11);
        patternmap.put(KEY_12,ZZ_12);
        patternmap.put(KEY_13,ZZ_13);
    }


    /**
     * 根据文件类型返回map 存在一下key 值 可能为空
     * wjlx
     * type
     * ts
     * txtlx
     * wjmc
     * @param info
     * @return
     */
    public static Map<String,String> getType(String info){
        Map<String,String> typemap = new HashMap<>();
        try {
            info=info.trim();
            Set set = patternmap.entrySet();
            String[] tss=  info.split("_");
            String wjlx = "";
            String type = "";
            String ts = "";
            String txtlx = "";
            String wjmc = info.substring(0,19);
            for(Object key:set){
                Map.Entry entry = (Map.Entry) key;
                if( Pattern.matches(entry.getValue().toString(), info)){
                    System.out.println(entry.getKey()+"-"+entry.getValue());
                    if(KEY_1.equals(entry.getKey())){
                        type = "1001";
                        wjlx ="3";
                        txtlx="1";
                    }else if(KEY_2.equals(entry.getKey())){
                        type = "1002";
                        wjlx ="1";

                    }else if(KEY_3.equals(entry.getKey())){
                        type = "1003";
                        wjlx ="1";

                    }else if(KEY_4.equals(entry.getKey())){
                        type = "1004";
                        wjlx ="2";

                    }else if(KEY_5.equals(entry.getKey())){
                        type = "1005";
                        wjlx ="1";

                    }else if(KEY_6.equals(entry.getKey())){
                        type = "1006";
                        wjlx ="1";

                    }else if(KEY_7.equals(entry.getKey())){
                        type = "1007";
                        wjlx ="3";
                        txtlx="1";
                    }else if(KEY_8.equals(entry.getKey())){
                        type = "1008";
                        wjlx ="2";

                    }else if(KEY_9.equals(entry.getKey())){
                        type = "1009";
                        wjlx ="3";
                        txtlx="2";
                        ts=tss[7].split("\\.")[0];
                    }else if(KEY_10.equals(entry.getKey())){
                        type = "1010";
                        wjlx ="3";
                        txtlx="2";
                        ts=tss[6].split("\\.")[0];
                    }else if(KEY_11.equals(entry.getKey())){
                        type = "1011";
                        wjlx ="3";
                        txtlx="3";
                        ts=tss[6];

                    }else if(KEY_12.equals(entry.getKey())){
                        type = "1012";
                        wjlx ="3";
                        txtlx="3";
                        ts = tss[12];

                    }else if(KEY_13.equals(entry.getKey())){
                        type = "1013";
                        wjlx ="4";
                        ts = tss[12];

                    }
                    break;
                }
            }
            if("0".equals(ts)){
                ts = "1";
            }
            typemap.put(WJLX,wjlx);
            typemap.put(TYPE,type);
            typemap.put(TS,ts);
            typemap.put(TXTLX,txtlx);
            typemap.put(WJMC,wjmc);
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            return typemap;

        }
    }

    public static void main(String[] args) {
        String u1  = "2022_09_05_20_18_16.txt";
        String u2  = "2022_09_05_20_18_16.jpg";
        String u3  = "2022_09_05_20_18_16.png";
        String u4  = "2022_09_05_20_18_16.wav";
        String u5  = "2022_09_05_20_18_16_predation.jpg";
        String u6  = "2022_08_16_15_44_54_predation.png";
        String u7  = "2022_08_16_15_44_54_predation.txt";
        String u8  = "2022_08_16_15_44_54_predation.wav";
        String u9  = "2022_07_27_13_36_50_predation_59.txt";
        String u10  = "2022_07_30_09_32_15_0.txt";
        String u11  = "2022_09_05_09_04_54_0_A2.txt";
        String u12  = "2022_09_08_14_29_43_2022_09_08_14_29_46_1_A2.txt";
        String tst = "2022_09_09_10_18_12_0.wav";
        String u13 = "2022_09_08_14_29_43_2022_09_08_14_29_46_1_A2_TD33.mp4";
        Map<String,String> map =  getType(u13);
        Set set = map.entrySet();
        for(Object key:set){
            Map.Entry entry = (Map.Entry) key;
            System.out.println(entry.getKey()+"---"+entry.getValue());
        }



    }



}

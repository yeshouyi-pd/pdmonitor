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
    public static final String KEY_14 = "KEY_14";
    public static final String KEY_15 = "KEY_15";
    public static final String KEY_16 = "KEY_16";
    public static final String KEY_17 = "KEY_17";
    public static final String KEY_18 = "KEY_18";
    public static final String KEY_19 = "KEY_19";
    public static final String KEY_20 = "KEY_20";
    public static final String KEY_21 = "KEY_21";
    public static final String KEY_22 = "KEY_22";
    public static final String KEY_23 = "KEY_23";
    public static final String KEY_24 = "KEY_24";
    public static final String KEY_25 = "KEY_25";
    public static final String KEY_26 = "KEY_26";
    public static final String KEY_27 = "KEY_27";
    public static final String KEY_29 = "KEY_29";
    public static final String KEY_30 = "KEY_30";
    public static final String KEY_31 = "KEY_31";

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
    public static final String ZZ_14 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4.txt$";
    public static final String ZZ_15 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}_A4.txt$";
    public static final String ZZ_16 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4_\\w{1,}.mp4$";
    public static final String ZZ_17 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}_A4_\\w{1,}.mp4$";
    public static final String ZZ_18 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_FISH_\\d{1,}.txt$";
    public static final String ZZ_19 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_YFP_\\d{1,}.txt$";
    public static final String ZZ_20 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4.txt$";
    public static final String ZZ_21 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4_\\w{1,}.mp4$";
    public static final String ZZ_22 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}_A4.txt$";
    public static final String ZZ_23 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}_A4_\\w{1,}.mp4$";
    public static final String ZZ_24 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4.txt$";
    public static final String ZZ_25 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4_\\w{1,}.mp4$";
    public static final String ZZ_26 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}_A4.txt$";
    public static final String ZZ_27 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}_A4_\\w{1,}.mp4$";
    public static final String ZZ_29 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}.wav$";
    public static final String ZZ_30 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}.jpg$";
    public static final String ZZ_31 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}.png$";
    public static final String ZZ_32 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_Becon.txt$";//信标文件
    public static final String ZZ_33 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_Space_\\d{1,}.txt$";//驱离文件
    public static final String ZZ_34 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_angle.txt$";//驱离文件

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
        patternmap.put(KEY_14,ZZ_14);
        patternmap.put(KEY_15,ZZ_15);
        patternmap.put(KEY_16,ZZ_16);
        patternmap.put(KEY_17,ZZ_17);
        patternmap.put(KEY_18,ZZ_18);
        patternmap.put(KEY_19,ZZ_19);
        patternmap.put(KEY_20,ZZ_20);
        patternmap.put(KEY_21,ZZ_21);
        patternmap.put(KEY_22,ZZ_22);
        patternmap.put(KEY_23,ZZ_23);
        patternmap.put(KEY_24,ZZ_24);
        patternmap.put(KEY_25,ZZ_25);
        patternmap.put(KEY_26,ZZ_26);
        patternmap.put(KEY_27,ZZ_27);
        patternmap.put(KEY_29,ZZ_29);
        patternmap.put(KEY_30,ZZ_30);
        patternmap.put(KEY_31,ZZ_31);
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
            String txtlx = "0";
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
                        txtlx="1";
                        //ts=tss[6].split("\\.")[0];//2024年5月31日之后，这个字段代表新物种(海江豚)的秒数
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

                    }else if(KEY_14.equals(entry.getKey())){
                        type = "1014";
                        wjlx ="3";
                        ts = tss[6];
                        txtlx = "4";
                    }else if(KEY_15.equals(entry.getKey())){
                        type = "1015";
                        wjlx ="3";
                        ts = tss[7];
                        txtlx = "4";
                    }else if(KEY_16.equals(entry.getKey())){
                        type = "1016";
                        wjlx ="4";
                        ts = tss[6];

                    }else if(KEY_17.equals(entry.getKey())){
                        type = "1017";
                        wjlx ="4";
                        ts = tss[7];

                    }else if(KEY_18.equals(entry.getKey())){
                        type = "1018";
                        wjlx ="5";
                        ts=tss[7].split("\\.")[0];

                    }else if(KEY_19.equals(entry.getKey())){
                        type = "1019";
                        wjlx ="6";
                        ts=tss[7].split("\\.")[0];

                    }else if(KEY_20.equals(entry.getKey())){
                        type = "1020";
                        wjlx ="3";
                        ts=tss[13];
                        txtlx ="5";
                    }else if(KEY_21.equals(entry.getKey())){
                        type = "1021";
                        wjlx ="4";
                        ts=tss[13];

                    }else if(KEY_22.equals(entry.getKey())){
                        type = "1022";
                        wjlx ="3";
                        ts=tss[13];
                        txtlx ="5";
                    }else if(KEY_23.equals(entry.getKey())){
                        type = "1023";
                        wjlx ="4";
                        ts=tss[13];

                    }else if(KEY_24.equals(entry.getKey())){
                        type = "1024";
                        wjlx ="3";
                        ts=tss[12];
                        txtlx ="5";
                    }else if(KEY_25.equals(entry.getKey())){
                        type = "1025";
                        wjlx ="4";
                        ts=tss[12];

                    }else if(KEY_26.equals(entry.getKey())){
                        type = "1026";
                        wjlx ="3";
                        ts=tss[14];
                        txtlx ="5";
                    }else if(KEY_27.equals(entry.getKey())){
                        type = "1027";
                        wjlx ="4";
                        ts=tss[14];

                    }else if(KEY_29.equals(entry.getKey())){
                        type = "1029";
                        wjlx ="2";
                        txtlx="1";
                        //ts=tss[6].split("\\.")[0];//2024年5月31日之后，这个字段代表新物种(海江豚)的秒数
                    }else if(KEY_30.equals(entry.getKey())){
                        type = "1030";
                        wjlx ="1";
                        txtlx="1";
                        //ts=tss[6].split("\\.")[0];//2024年5月31日之后，这个字段代表新物种(海江豚)的秒数
                    }else if(KEY_31.equals(entry.getKey())){
                        type = "1031";
                        wjlx ="1";
                        txtlx="1";
                        //ts=tss[6].split("\\.")[0];//2024年5月31日之后，这个字段代表新物种(海江豚)的秒数
                    }
                    break;
                }
            }
            if("0".equals(ts)){
                if(!("1020".equals(type)||"1022".equals(type)||"1024".equals(type)||"1026".equals(type))){
                    ts = "1";
                }
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
        String u12  = "2022_09_08_14_29_43_2022_09_08_14_29_46_0_A2.txt";
        String tst = "2022_09_09_10_18_12_0.wav";
        String u13 = "2022_09_08_14_29_43_2022_09_08_14_29_46_1_A2_TD33.mp4";
        String u14 = "2022_10_09_14_36_30_2_A4.txt";
        String u15 = "2022_10_09_14_36_30_predation_2_A4.txt";
        String u16 = "2022_10_09_14_36_30_2_A4_TD33.mp4";
        String u17 = "2022_10_09_14_36_30_predation_2_A4_TD33.mp4";
        String u18 = "2022_10_09_14_36_30_FISH_144.txt";
        String u19 = "2022_10_09_14_36_30_YFP_189.txt";
        String u20 = "2022_10_15_23_09_20_predation_2022_10_15_23_10_36_0_A4.txt";
        String u21 = "2022_10_15_23_09_20_predation_2022_10_15_23_10_36_0_A4_TD33.mp4";
        String u22 = "2022_10_15_23_07_10_2022_10_15_23_07_50_predation_0_A4.txt";
        String u23 = "2022_10_15_23_07_10_2022_10_15_23_07_50_predation_2_A4_TD33.mp4";
        String u24 = "2022_10_15_23_02_36_2022_10_15_23_02_36_0_A4.txt";
        String u25 = "2022_10_15_23_02_36_2022_10_15_23_02_36_1_A4_TD33.mp4";
        String u26 = "2022_10_15_23_02_36_predation_2022_10_15_23_02_36_predation_1_A4.txt";
        String u27 = "2022_10_15_23_02_36_predation_2022_10_15_23_02_36_predation_1_A4_TD33.mp4";
        String u29  = "2022_07_30_09_32_15_0.wav";
        String u30  = "2022_07_30_09_32_15_0.jpg";
        String u31  = "2022_07_30_09_32_15_0.png";

        Map<String,String> map =  getType(u12);
        Set set = map.entrySet();
        for(Object key:set){
            Map.Entry entry = (Map.Entry) key;
            System.out.println(entry.getKey()+"---"+entry.getValue());
        }



    }



}

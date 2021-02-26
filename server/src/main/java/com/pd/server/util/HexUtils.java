package com.pd.server.util;

public class HexUtils {
    /**
     * 16进制数字字符集
     */
    private static String hexString="0123456789ABCDEF";

    /**
     * 转化字符串为十六进制编码
     */
    public static String toHexString(String s){
        String str="";
        for (int i=0;i<s.length();i++){
            int ch = (int)s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 转化十六进制编码为字符串
     */
    public static String toStringHex(String s){
        byte[] baKeyword = new byte[s.length()/2];
        for(int i = 0; i < baKeyword.length; i++){
            try{
                baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        try{
            s = new String(baKeyword, "utf-8");//UTF-16le:Not
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return s;
    }


    public static void main(String[] args){
        String str = HexUtils.toStringHex("420114198802150544");
        System.out.println(str+"----"+HexUtils.toHexString(str));
    }
}

package com.pd.server.util;

import java.util.UUID;

public class UuidUtil {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    /**
     * 获取短UUID
     * @return
     */
    public static String getShortUuid() {
        StringBuffer longBuffer = new StringBuffer();
        // 使用两个UUID拼接
        String uuid1 = UuidUtil.getUuid().replace("-", "");
        String uuid2 = UuidUtil.getUuid().replace("-", "");
        String combinedUuid = uuid1 + uuid2; // 64位字符

        // 现在可以安全地循环12次
        for (int i = 0; i < 12; i++) {
            String str = combinedUuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            longBuffer.append(chars[x % 0x3E]);
        }
        return longBuffer.toString();
    }

    /**
     * 获得32位UUID
     */
    public static String getUuid(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    public static void main(String[] args) {

        System.out.println(getShortUuid());
    }
}

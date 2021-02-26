package com.pd.server.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {
    /**
     * base64编码
     * @param orig
     * @return
     */
    public static String getEncoder(String orig) {
        String str = "";
        str = Base64.getEncoder().encodeToString(orig.getBytes(StandardCharsets.UTF_8));
        return str;

    }

    /**
     * base64解码
     * @param decodeStr
     * @return
     */
    public static String getDecoder(String decodeStr) {
        String unDecodeStr=new String(Base64.getDecoder().decode(decodeStr),StandardCharsets.UTF_8);
        return unDecodeStr;

    }

}

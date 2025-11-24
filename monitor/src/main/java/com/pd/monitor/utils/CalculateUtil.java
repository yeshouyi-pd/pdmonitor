package com.pd.monitor.utils;

import java.text.DecimalFormat;

public class CalculateUtil {

    /**
     * 计算结果百分比，保留1位小数
     *
     * @param v1 除数
     * @param v2 被除数
     * @return
     */
    public static String calculateResultOfPercent(double v1, double v2) {
        if (v2 == 0) {
            return "0%";
        }

        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(v1 / v2);
    }

}

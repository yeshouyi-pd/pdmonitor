package com.pd.sonar.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * 声呐数据工具类
 * 用于处理声呐数据的特殊格式，如SV值数组和时间戳数组的解析
 */
public class SonarDataUtil {
    
    /**
     * 解析SV值数组
     * 将字符串格式的SV值数组转换为float数组
     * 
     * @param bufferSonar 字符串格式的SV值数组
     * @param bufferSize 数组大小
     * @return float数组
     */
    public static float[] parseSonarBuffer(String bufferSonar, int bufferSize) {
        if (bufferSonar == null || bufferSize <= 0) {
            return new float[0];
        }
        
        try {
            byte[] bytes = bufferSonar.getBytes("ISO-8859-1");
            if (bytes.length != bufferSize * 4) { // float占4字节
                System.err.println("SV值数组大小不匹配: 期望=" + (bufferSize * 4) + ", 实际=" + bytes.length);
                return new float[0];
            }
            
            float[] result = new float[bufferSize];
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            bb.order(ByteOrder.LITTLE_ENDIAN); // 根据C++代码，使用小端序
            
            for (int i = 0; i < bufferSize; i++) {
                result[i] = bb.getFloat();
            }
            
            return result;
        } catch (Exception e) {
            System.err.println("解析SV值数组失败: " + e.getMessage());
            return new float[0];
        }
    }
    
    /**
     * 解析时间戳数组
     * 将字符串格式的时间戳数组转换为long数组
     * 
     * @param bufferTimeStamp 字符串格式的时间戳数组
     * @param bufferSize 数组大小
     * @return long数组
     */
    public static long[] parseTimeStampBuffer(String bufferTimeStamp, int bufferSize) {
        if (bufferTimeStamp == null || bufferSize <= 0) {
            return new long[0];
        }
        
        try {
            byte[] bytes = bufferTimeStamp.getBytes("ISO-8859-1");
            if (bytes.length != bufferSize * 8) { // LONGLONG占8字节
                System.err.println("时间戳数组大小不匹配: 期望=" + (bufferSize * 8) + ", 实际=" + bytes.length);
                return new long[0];
            }
            
            long[] result = new long[bufferSize];
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            bb.order(ByteOrder.LITTLE_ENDIAN); // 根据C++代码，使用小端序
            
            for (int i = 0; i < bufferSize; i++) {
                result[i] = bb.getLong();
            }
            
            return result;
        } catch (Exception e) {
            System.err.println("解析时间戳数组失败: " + e.getMessage());
            return new long[0];
        }
    }
    
    /**
     * 将SV值数组转换为二维数组
     * 根据ping个数和点的个数重新组织数据
     * 
     * @param svArray 一维SV值数组
     * @param numPing ping个数
     * @param numDot 每个ping中点的个数
     * @return 二维SV值数组
     */
    public static float[][] convertTo2DArray(float[] svArray, int numPing, int numDot) {
        if (svArray == null || svArray.length != numPing * (numDot + 1)) {
            return new float[0][0];
        }
        
        float[][] result = new float[numPing][numDot + 1];
        int index = 0;
        
        for (int i = 0; i < numPing; i++) {
            for (int j = 0; j < numDot; j++) {
                result[i][j] = svArray[index++];
            }
            // 每行的最后一个值是当前ping的平均SV
            result[i][numDot] = svArray[index++];
        }
        
        return result;
    }
    
    /**
     * 获取SV值对应的颜色索引
     * 根据说明书中的色卡算法
     * 
     * @param svValue SV值
     * @param svMin SV最小值（默认-70）
     * @param svMax SV最大值（默认-34）
     * @return 颜色索引（0-63）
     */
    public static int getColorIndex(float svValue, float svMin, float svMax) {
        if (svValue < svMin) {
            return 64; // 超出范围，使用默认颜色
        }
        
        int colorIndex = (int) ((svValue - svMin) / (svMax - svMin) * 63);
        
        if (colorIndex < 0) {
            colorIndex = 0;
        } else if (colorIndex > 63) {
            colorIndex = 63;
        }
        
        return colorIndex;
    }
    
    /**
     * 获取SV值对应的颜色索引（使用默认范围）
     * 
     * @param svValue SV值
     * @return 颜色索引（0-63）
     */
    public static int getColorIndex(float svValue) {
        return getColorIndex(svValue, -70.0f, -34.0f);
    }
    
    /**
     * 格式化时间戳为可读时间
     * 
     * @param timestamp 时间戳（毫秒）
     * @return 格式化的时间字符串
     */
    public static String formatTimestamp(long timestamp) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(new java.util.Date(timestamp));
        } catch (Exception e) {
            return "时间格式错误";
        }
    }
    
    /**
     * 计算SV值的统计信息
     * 
     * @param svArray SV值数组
     * @return 统计信息字符串
     */
    public static String getSVStatistics(float[] svArray) {
        if (svArray == null || svArray.length == 0) {
            return "无数据";
        }
        
        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;
        float sum = 0;
        
        for (float value : svArray) {
            if (value < min) min = value;
            if (value > max) max = value;
            sum += value;
        }
        
        float average = sum / svArray.length;
        
        return String.format("SV统计: 最小值=%.2f dB, 最大值=%.2f dB, 平均值=%.2f dB, 数据点数=%d", 
                           min, max, average, svArray.length);
    }
    
    /**
     * 验证SV值数组的有效性
     * 
     * @param svArray SV值数组
     * @param expectedSize 期望大小
     * @return 是否有效
     */
    public static boolean validateSVArray(float[] svArray, int expectedSize) {
        if (svArray == null || svArray.length != expectedSize) {
            return false;
        }
        
        // 检查SV值是否在合理范围内（-100 dB 到 0 dB）
        for (float value : svArray) {
            if (value < -100 || value > 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 验证时间戳数组的有效性
     * 
     * @param timestampArray 时间戳数组
     * @param expectedSize 期望大小
     * @return 是否有效
     */
    public static boolean validateTimestampArray(long[] timestampArray, int expectedSize) {
        if (timestampArray == null || timestampArray.length != expectedSize) {
            return false;
        }
        
        // 检查时间戳是否在合理范围内（1970年到2100年）
        long minTimestamp = 0L;
        long maxTimestamp = 4102444800000L; // 2100年1月1日
        
        for (long timestamp : timestampArray) {
            if (timestamp < minTimestamp || timestamp > maxTimestamp) {
                return false;
            }
        }
        
        return true;
    }
} 
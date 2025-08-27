package com.pd.sonar.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 声呐数据编码工具类
 * 用于处理声呐数据的编码和解码，特别是二进制数据的 Base64 转换
 */
public class SonarDataEncoder {
    
    private static final Logger LOG = LoggerFactory.getLogger(SonarDataEncoder.class);
    
    /**
     * 将 buffer_sonar 数据转换为 Base64 编码
     * 解决数据库存储时的字符集问题
     * 
     * @param bufferSonar 原始 buffer_sonar 数据
     * @return Base64 编码后的字符串，如果转换失败则返回原始数据
     */
    public static String encodeBufferSonar(String bufferSonar) {
        if (bufferSonar == null) {
            return null;
        }
        
        try {
            // 将字符串转换为字节数组，然后进行 Base64 编码
            byte[] bytes = bufferSonar.getBytes(StandardCharsets.ISO_8859_1);
            String encoded = Base64.getEncoder().encodeToString(bytes);
            
            LOG.debug("buffer_sonar 编码成功: 原始长度={}, 编码后长度={}", 
                    bufferSonar.length(), encoded.length());
            
            return encoded;
            
        } catch (Exception e) {
            LOG.error("转换 buffer_sonar 为 Base64 编码失败", e);
            // 如果转换失败，返回原始数据
            return bufferSonar;
        }
    }
    
    /**
     * 将 Base64 编码的 buffer_sonar 数据解码回原始格式
     * 
     * @param encodedBufferSonar Base64 编码的 buffer_sonar 数据
     * @return 解码后的原始数据，如果解码失败则返回 null
     */
    public static String decodeBufferSonar(String encodedBufferSonar) {
        if (encodedBufferSonar == null) {
            return null;
        }
        
        try {
            // 检查是否为有效的 Base64 编码
            if (!isValidBase64(encodedBufferSonar)) {
                LOG.warn("数据不是有效的 Base64 编码，可能已经是原始数据");
                return encodedBufferSonar;
            }
            
            // 进行 Base64 解码
            byte[] bytes = Base64.getDecoder().decode(encodedBufferSonar);
            String decoded = new String(bytes, StandardCharsets.ISO_8859_1);
            
            LOG.debug("buffer_sonar 解码成功: 编码长度={}, 解码后长度={}", 
                    encodedBufferSonar.length(), decoded.length());
            
            return decoded;
            
        } catch (Exception e) {
            LOG.error("解码 buffer_sonar 失败", e);
            return null;
        }
    }
    
    /**
     * 检查字符串是否为有效的 Base64 编码
     * 
     * @param str 待检查的字符串
     * @return 如果是有效的 Base64 编码返回 true，否则返回 false
     */
    public static boolean isValidBase64(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        
        try {
            // 尝试解码，如果成功则说明是有效的 Base64
            Base64.getDecoder().decode(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * 检查数据是否需要编码
     * 如果数据包含非ASCII字符，则需要编码
     * 
     * @param data 待检查的数据
     * @return 如果需要编码返回 true，否则返回 false
     */
    public static boolean needsEncoding(String data) {
        if (data == null) {
            return false;
        }
        
        // 检查是否包含非ASCII字符
        return data.chars().anyMatch(ch -> ch < 32 || ch > 126);
    }
    
    /**
     * 智能编码：只在需要时进行编码
     * 
     * @param bufferSonar 原始数据
     * @return 编码后的数据（如果需要编码）或原始数据
     */
    public static String smartEncode(String bufferSonar) {
        if (bufferSonar == null) {
            return null;
        }
        
        if (needsEncoding(bufferSonar)) {
            LOG.info("检测到非ASCII字符，进行 Base64 编码");
            return encodeBufferSonar(bufferSonar);
        } else {
            LOG.debug("数据只包含ASCII字符，无需编码");
            return bufferSonar;
        }
    }
    
    /**
     * 获取编码信息摘要
     * 
     * @param originalData 原始数据
     * @param encodedData 编码后的数据
     * @return 编码信息摘要
     */
    public static String getEncodingSummary(String originalData, String encodedData) {
        if (originalData == null || encodedData == null) {
            return "数据为 null";
        }
        
        StringBuilder summary = new StringBuilder();
        summary.append("编码摘要: ");
        summary.append("原始长度=").append(originalData.length());
        summary.append(", 编码后长度=").append(encodedData.length());
        summary.append(", 编码比例=").append(String.format("%.2f", (double) encodedData.length() / originalData.length()));
        
        if (needsEncoding(originalData)) {
            summary.append(", 包含非ASCII字符");
        } else {
            summary.append(", 仅ASCII字符");
        }
        
        return summary.toString();
    }
} 
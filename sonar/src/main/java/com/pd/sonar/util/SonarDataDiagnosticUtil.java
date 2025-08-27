package com.pd.sonar.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 声呐数据诊断工具类
 * 用于诊断和解决声呐数据的一致性问题
 */
public class SonarDataDiagnosticUtil {
    
    private static final Logger LOG = LoggerFactory.getLogger(SonarDataDiagnosticUtil.class);
    
    /**
     * 诊断 buffer_sonar 数据
     * @param jsonObject 包含声呐数据的JSON对象
     * @param dataType 数据类型（用于日志标识）
     */
    public static void diagnoseBufferSonar(JSONObject jsonObject, String dataType) {
        try {
            String bufferSonar = jsonObject.getString("buffer_sonar");
            Integer bufferSizeSonar = jsonObject.getInteger("buffer_size_sonar");
            
            LOG.error("=== {} buffer_sonar 诊断开始 ===", dataType);
            
            if (bufferSonar == null) {
                LOG.warn("buffer_sonar 为 null");
                return;
            }
            
            if (bufferSizeSonar == null) {
                LOG.warn("buffer_size_sonar 为 null");
                return;
            }
            
            // 基本信息
            LOG.error("buffer_sonar 长度: {}", bufferSonar.length());
            LOG.error("buffer_size_sonar: {}", bufferSizeSonar);
            LOG.error("期望字节数: {}", bufferSizeSonar * 4);
            
            // 字符编码分析
            analyzeCharacterEncoding(bufferSonar, dataType);
            
            // 数据完整性检查
            checkDataIntegrity(bufferSonar, bufferSizeSonar, dataType);
            
            // 特殊字符检查
            checkSpecialCharacters(bufferSonar, dataType);
            
            // Base64 编码检查
            checkBase64Encoding(bufferSonar, dataType);
            
            LOG.error("=== {} buffer_sonar 诊断完成 ===", dataType);
            
        } catch (Exception e) {
            LOG.error("诊断 buffer_sonar 数据时出错", e);
        }
    }
    
    /**
     * 分析字符编码
     */
    private static void analyzeCharacterEncoding(String data, String dataType) {
        LOG.error("--- {} 字符编码分析 ---", dataType);
        
        try {
            // 尝试不同的编码方式
            byte[] utf8Bytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] isoBytes = data.getBytes(StandardCharsets.ISO_8859_1);
            byte[] asciiBytes = data.getBytes(StandardCharsets.US_ASCII);
            
            LOG.error("UTF-8 字节数: {}", utf8Bytes.length);
            LOG.error("ISO-8859-1 字节数: {}", isoBytes.length);
            LOG.error("ASCII 字节数: {}", asciiBytes.length);
            
            // 检查编码一致性
            if (utf8Bytes.length != isoBytes.length) {
                LOG.warn("UTF-8 和 ISO-8859-1 编码长度不一致，可能存在编码问题");
            }
            
        } catch (Exception e) {
            LOG.error("字符编码分析失败", e);
        }
    }
    
    /**
     * 检查数据完整性
     */
    private static void checkDataIntegrity(String data, Integer expectedSize, String dataType) {
        LOG.error("--- {} 数据完整性检查 ---", dataType);
        
        int actualLength = data.length();
        int expectedBytes = expectedSize * 4; // float 类型占 4 字节
        
        LOG.error("实际字符串长度: {}", actualLength);
        LOG.error("期望字节数: {}", expectedBytes);
        
        if (actualLength != expectedBytes) {
            LOG.warn("数据长度不匹配！期望: {}, 实际: {}", expectedBytes, actualLength);
            
            // 分析可能的原因
            if (actualLength > expectedBytes) {
                LOG.warn("实际长度大于期望长度，可能原因：编码转换、特殊字符处理等");
            } else {
                LOG.warn("实际长度小于期望长度，可能原因：数据截断、编码丢失等");
            }
        } else {
            LOG.error("数据长度匹配正常");
        }
    }
    
    /**
     * 检查特殊字符
     */
    private static void checkSpecialCharacters(String data, String dataType) {
        LOG.error("--- {} 特殊字符检查 ---", dataType);
        
        int controlCharCount = 0;
        int nonAsciiCount = 0;
        int printableCount = 0;
        
        for (char c : data.toCharArray()) {
            if (Character.isISOControl(c)) {
                controlCharCount++;
            }
            if (c > 127) {
                nonAsciiCount++;
            }
            if (c >= 32 && c <= 126) {
                printableCount++;
            }
        }
        
        LOG.error("控制字符数量: {}", controlCharCount);
        LOG.error("非ASCII字符数量: {}", nonAsciiCount);
        LOG.error("可打印ASCII字符数量: {}", printableCount);
        
        if (controlCharCount > 0) {
            LOG.warn("发现控制字符，可能影响数据一致性");
        }
        
        if (nonAsciiCount > 0) {
            LOG.warn("发现非ASCII字符，可能影响数据一致性");
        }
    }
    
    /**
     * 检查Base64编码
     */
    private static void checkBase64Encoding(String data, String dataType) {
        LOG.error("--- {} Base64编码检查 ---", dataType);
        
        try {
            // 检查是否为有效的Base64编码
            byte[] decoded = Base64.getDecoder().decode(data);
            LOG.error("Base64解码成功，解码后字节数: {}", decoded.length);
            
            // 检查解码后的数据是否符合期望
            Integer bufferSizeSonar = null;
            try {
                // 这里需要从外部传入，暂时跳过
                // bufferSizeSonar = getBufferSizeSonar();
            } catch (Exception e) {
                // 忽略
            }
            
            if (bufferSizeSonar != null && decoded.length == bufferSizeSonar * 4) {
                LOG.error("Base64解码后的数据大小符合期望");
            }
            
        } catch (IllegalArgumentException e) {
            LOG.error("数据不是有效的Base64编码: {}", e.getMessage());
        } catch (Exception e) {
            LOG.error("Base64编码检查失败", e);
        }
    }
    
    /**
     * 生成数据摘要用于对比
     */
    public static String generateDataDigest(String data) {
        if (data == null) {
            return "null";
        }
        
        try {
            // 简单的数据摘要：长度 + 前100个字符的哈希
            String preview = data.length() > 100 ? data.substring(0, 100) : data;
            int hash = preview.hashCode();
            return String.format("length=%d,hash=%d,preview=%s", data.length(), hash, preview);
        } catch (Exception e) {
            return "error:" + e.getMessage();
        }
    }
    
    /**
     * 对比两个 buffer_sonar 数据
     */
    public static void compareBufferSonar(String data1, String data2, String label1, String label2) {
        LOG.error("=== buffer_sonar 数据对比 ===");
        LOG.error("{}: {}", label1, generateDataDigest(data1));
        LOG.error("{}: {}", label2, generateDataDigest(data2));
        
        if (data1 == null && data2 == null) {
            LOG.error("两个数据都为 null，一致");
            return;
        }
        
        if (data1 == null || data2 == null) {
            LOG.warn("一个数据为 null，另一个不为 null，不一致");
            return;
        }
        
        if (data1.equals(data2)) {
            LOG.error("两个数据完全一致");
            return;
        }
        
        // 长度对比
        if (data1.length() != data2.length()) {
            LOG.warn("数据长度不一致: {}={}, {}={}", label1, data1.length(), label2, data2.length());
        }
        
        // 内容对比
        int minLength = Math.min(data1.length(), data2.length());
        int firstDiffIndex = -1;
        
        for (int i = 0; i < minLength; i++) {
            if (data1.charAt(i) != data2.charAt(i)) {
                firstDiffIndex = i;
                break;
            }
        }
        
        if (firstDiffIndex != -1) {
            LOG.warn("首次差异位置: {}, {}='{}', {}='{}'", 
                    firstDiffIndex, 
                    label1, 
                    data1.charAt(firstDiffIndex),
                    label2, 
                    data2.charAt(firstDiffIndex));
        }
    }
} 
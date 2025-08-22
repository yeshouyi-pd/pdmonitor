package com.pd.sonar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 单体目标信息数据模型类
 * 对应STU_SINGLE_TARGET结构体
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleTargetData {

    @JsonProperty("id")
    private Long id; // 序号
    @JsonProperty("device_code")
    private String deviceCode; // 声呐换能器的编号
    @JsonProperty("ac_time")
    private String acTime; // 采集时间
    @JsonProperty("num")
    private Integer num; // 单体数量
    @JsonProperty("array_single_target")
    private List<SingleValue> arraySingleTarget; // 单体数组
    
    /**
     * 单体值结构体
     * 对应STU_SINGLE_VALUE结构体
     */
    public static class SingleValue {
        @JsonProperty("dTS")
        private Double dTS; // 目标强度
        @JsonProperty("dRange")
        private Double dRange; // 目标距离换能器距离
        @JsonProperty("lTime")
        private Long lTime; // 目标出现时间
        @JsonProperty("nBioTypeCode")
        private Integer nBioTypeCode; // 生物类别代码
        
        public SingleValue() {}
        
        public SingleValue(Double dTS, Double dRange, Long lTime, Integer nBioTypeCode) {
            this.dTS = dTS;
            this.dRange = dRange;
            this.lTime = lTime;
            this.nBioTypeCode = nBioTypeCode;
        }
        
        // Getters and Setters
        public Double getDTS() {
            return dTS;
        }
        
        public void setDTS(Double dTS) {
            this.dTS = dTS;
        }
        
        public Double getDRange() {
            return dRange;
        }
        
        public void setDRange(Double dRange) {
            this.dRange = dRange;
        }
        
        public Long getLTime() {
            return lTime;
        }
        
        public void setLTime(Long lTime) {
            this.lTime = lTime;
        }
        
        public Integer getNBioTypeCode() {
            return nBioTypeCode;
        }
        
        public void setNBioTypeCode(Integer nBioTypeCode) {
            this.nBioTypeCode = nBioTypeCode;
        }
        
        /**
         * 获取生物类别名称
         */
        public String getBioTypeName() {
            switch (nBioTypeCode) {
                case 1701:
                    return "毛虾";
                case 1702:
                    return "水母";
                case 1703:
                    return "鱼群";
                case 1704:
                    return "藻类";
                case 1705:
                    return "其他";
                default:
                    return "未知";
            }
        }
        
        @Override
        public String toString() {
            return "SingleValue{" +
                    "dTS=" + dTS +
                    ", dRange=" + dRange +
                    ", lTime=" + lTime +
                    ", nBioTypeCode=" + nBioTypeCode +
                    '}';
        }
    }
    
    public SingleTargetData() {}
    
    public SingleTargetData(Long id, String deviceCode, String acTime, Integer num, List<SingleValue> arraySingleTarget) {
        this.id = id;
        this.deviceCode = deviceCode;
        this.acTime = acTime;
        this.num = num;
        this.arraySingleTarget = arraySingleTarget;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDeviceCode() {
        return deviceCode;
    }
    
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
    
    public String getAcTime() {
        return acTime;
    }
    
    public void setAcTime(String acTime) {
        this.acTime = acTime;
    }
    
    public Integer getNum() {
        return num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }
    
    public List<SingleValue> getArraySingleTarget() {
        return arraySingleTarget;
    }
    
    public void setArraySingleTarget(List<SingleValue> arraySingleTarget) {
        this.arraySingleTarget = arraySingleTarget;
    }
    
    /**
     * 获取目标数量信息
     */
    public String getTargetCountInfo() {
        return String.format("检测到 %d 个单体目标", num);
    }
    
    @Override
    public String toString() {
        return "SingleTargetData{" +
                "id=" + id +
                ", deviceCode='" + deviceCode + '\'' +
                ", acTime='" + acTime + '\'' +
                ", num=" + num +
                ", arraySingleTarget=" + arraySingleTarget +
                '}';
    }
} 
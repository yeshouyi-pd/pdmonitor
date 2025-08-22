package com.pd.sonar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * 声呐参数信息数据模型类
 * 对应STU_NEPTUNE_SONAR_PARA结构体
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SonarParameterData {

    @JsonProperty("id")
    private Long id; // 序号
    @JsonProperty("device_code")
    private String deviceCode; // 声呐换能器的编号
    @JsonProperty("avrMax")
    private Double avrMax; // 计算平均SV的最大深度
    @JsonProperty("avrMin")
    private Double avrMin; // 计算平均SV的最小深度
    @JsonProperty("cross_section_area")
    private Double crossSectionArea; // 截面面积
    @JsonProperty("mapParaValue")
    private Map<String, String> mapParaValue; // 报警阈值
    @JsonProperty("weight_or_flux_acc_time")
    private Integer weightOrFluxAccTime; // 生物重量或通量累加时间
    @JsonProperty("change_time")
    private String changeTime; // 更改时间
    
    public SonarParameterData() {}
    
    public SonarParameterData(Long id, String deviceCode, Double avrMax, Double avrMin,
                             Double crossSectionArea, Map<String, String> mapParaValue,
                             Integer weightOrFluxAccTime, String changeTime) {
        this.id = id;
        this.deviceCode = deviceCode;
        this.avrMax = avrMax;
        this.avrMin = avrMin;
        this.crossSectionArea = crossSectionArea;
        this.mapParaValue = mapParaValue;
        this.weightOrFluxAccTime = weightOrFluxAccTime;
        this.changeTime = changeTime;
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
    
    public Double getAvrMax() {
        return avrMax;
    }
    
    public void setAvrMax(Double avrMax) {
        this.avrMax = avrMax;
    }
    
    public Double getAvrMin() {
        return avrMin;
    }
    
    public void setAvrMin(Double avrMin) {
        this.avrMin = avrMin;
    }
    
    public Double getCrossSectionArea() {
        return crossSectionArea;
    }
    
    public void setCrossSectionArea(Double crossSectionArea) {
        this.crossSectionArea = crossSectionArea;
    }
    
    public Map<String, String> getMapParaValue() {
        return mapParaValue;
    }
    
    public void setMapParaValue(Map<String, String> mapParaValue) {
        this.mapParaValue = mapParaValue;
    }
    
    public Integer getWeightOrFluxAccTime() {
        return weightOrFluxAccTime;
    }
    
    public void setWeightOrFluxAccTime(Integer weightOrFluxAccTime) {
        this.weightOrFluxAccTime = weightOrFluxAccTime;
    }
    
    public String getChangeTime() {
        return changeTime;
    }
    
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }
    
    /**
     * 获取深度范围信息
     */
    public String getDepthRangeInfo() {
        return String.format("深度范围: %.1f ~ %.1f m", avrMin, avrMax);
    }
    
    /**
     * 获取截面面积信息
     */
    public String getCrossSectionInfo() {
        return String.format("截面面积: %.2f ㎡", crossSectionArea);
    }
    
    /**
     * 获取累加时间信息
     */
    public String getAccumulationTimeInfo() {
        return String.format("累加时间: %d 分钟", weightOrFluxAccTime);
    }
    
    @Override
    public String toString() {
        return "SonarParameterData{" +
                "id=" + id +
                ", deviceCode='" + deviceCode + '\'' +
                ", avrMax=" + avrMax +
                ", avrMin=" + avrMin +
                ", crossSectionArea=" + crossSectionArea +
                ", mapParaValue=" + mapParaValue +
                ", weightOrFluxAccTime=" + weightOrFluxAccTime +
                ", changeTime='" + changeTime + '\'' +
                '}';
    }
} 
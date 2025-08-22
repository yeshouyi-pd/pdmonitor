package com.pd.sonar.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 生物量数据模型类
 * 对应STU_NEPTUNE_PARA_VALUE结构体
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SonarBiomassData {

    @JsonProperty("id")
    private Long id; // 序号
    @JsonProperty("device_code")
    private String deviceCode; // 声呐换能器的编号
    @JsonProperty("mapParaValue")
    private Map<String, Double> mapParaValue; // 参数值
    @JsonProperty("ac_time")
    private String acTime; // 采集时间
    
    // 生物量代码常量
    public static final String BIOMASS_DENSITY = "0501"; // 生物密度——单位：个/m³
    public static final String BIOMASS_WEIGHT_ACCUMULATED = "0502"; // 生物重量累加值——单位：kg
    public static final String BIOMASS_WEIGHT = "0503"; // 生物重量——单位：kg (或生物通量——单位：个/㎡·s)
    public static final String AVERAGE_SV = "0504"; // 平均SV——单位：dB
    
    public SonarBiomassData() {}
    
    public SonarBiomassData(Long id, String deviceCode, Map<String, Double> mapParaValue, String acTime) {
        this.id = id;
        this.deviceCode = deviceCode;
        this.mapParaValue = mapParaValue;
        this.acTime = acTime;
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
    
    public Map<String, Double> getMapParaValue() {
        return mapParaValue;
    }
    
    public void setMapParaValue(Map<String, Double> mapParaValue) {
        this.mapParaValue = mapParaValue;
    }
    
    public String getAcTime() {
        return acTime;
    }
    
    public void setAcTime(String acTime) {
        this.acTime = acTime;
    }
    
    @Override
    public String toString() {
        return "SonarBiomassData{" +
                "id=" + id +
                ", deviceCode='" + deviceCode + '\'' +
                ", mapParaValue=" + mapParaValue +
                ", acTime='" + acTime + '\'' +
                '}';
    }
} 
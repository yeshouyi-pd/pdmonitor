package com.pd.sonar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 回波图信息数据模型类
 * 对应STU_NEPTUNE_SONAR_ECHO结构体
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SonarEchoData {

    @JsonProperty("id")
    private Long id; // 序号
    @JsonProperty("device_code")
    private String deviceCode; // 声呐换能器的编号
    @JsonProperty("ac_time")
    private String acTime; // 采集时间
    @JsonProperty("num_ping")
    private Integer numPing; // ping个数
    @JsonProperty("num_dot")
    private Integer numDot; // ping中点的个数
    @JsonProperty("dR")
    private Double dR; // 相邻点间距
    @JsonProperty("dbMax")
    private Double dbMax; // 色卡最大值
    @JsonProperty("dbMin")
    private Double dbMin; // 色卡最小值
    @JsonProperty("AverSV")
    private Double averSV; // 当前这段数据的平均SV
    @JsonProperty("avrMax")
    private Double avrMax; // 计算平均SV的最大深度
    @JsonProperty("avrMin")
    private Double avrMin; // 计算平均SV的最小深度
    @JsonProperty("buffer_sonar")
    private String bufferSonar; // SV值数组
    @JsonProperty("buffer_size_sonar")
    private Integer bufferSizeSonar; // SV值数量
    @JsonProperty("buffer_timeStamp")
    private String bufferTimeStamp; // 时间戳数组
    @JsonProperty("buffer_size_timeStamp")
    private Integer bufferSizeTimeStamp; // 时间戳数量
    
    public SonarEchoData() {}
    
    public SonarEchoData(Long id, String deviceCode, String acTime, Integer numPing, Integer numDot,
                        Double dR, Double dbMax, Double dbMin, Double averSV, Double avrMax, Double avrMin,
                        String bufferSonar, Integer bufferSizeSonar, String bufferTimeStamp, Integer bufferSizeTimeStamp) {
        this.id = id;
        this.deviceCode = deviceCode;
        this.acTime = acTime;
        this.numPing = numPing;
        this.numDot = numDot;
        this.dR = dR;
        this.dbMax = dbMax;
        this.dbMin = dbMin;
        this.averSV = averSV;
        this.avrMax = avrMax;
        this.avrMin = avrMin;
        this.bufferSonar = bufferSonar;
        this.bufferSizeSonar = bufferSizeSonar;
        this.bufferTimeStamp = bufferTimeStamp;
        this.bufferSizeTimeStamp = bufferSizeTimeStamp;
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
    
    public Integer getNumPing() {
        return numPing;
    }
    
    public void setNumPing(Integer numPing) {
        this.numPing = numPing;
    }
    
    public Integer getNumDot() {
        return numDot;
    }
    
    public void setNumDot(Integer numDot) {
        this.numDot = numDot;
    }
    
    public Double getDR() {
        return dR;
    }
    
    public void setDR(Double dR) {
        this.dR = dR;
    }
    
    public Double getDbMax() {
        return dbMax;
    }
    
    public void setDbMax(Double dbMax) {
        this.dbMax = dbMax;
    }
    
    public Double getDbMin() {
        return dbMin;
    }
    
    public void setDbMin(Double dbMin) {
        this.dbMin = dbMin;
    }
    
    public Double getAverSV() {
        return averSV;
    }
    
    public void setAverSV(Double averSV) {
        this.averSV = averSV;
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
    
    public String getBufferSonar() {
        return bufferSonar;
    }
    
    public void setBufferSonar(String bufferSonar) {
        this.bufferSonar = bufferSonar;
    }
    
    public Integer getBufferSizeSonar() {
        return bufferSizeSonar;
    }
    
    public void setBufferSizeSonar(Integer bufferSizeSonar) {
        this.bufferSizeSonar = bufferSizeSonar;
    }
    
    public String getBufferTimeStamp() {
        return bufferTimeStamp;
    }
    
    public void setBufferTimeStamp(String bufferTimeStamp) {
        this.bufferTimeStamp = bufferTimeStamp;
    }
    
    public Integer getBufferSizeTimeStamp() {
        return bufferSizeTimeStamp;
    }
    
    public void setBufferSizeTimeStamp(Integer bufferSizeTimeStamp) {
        this.bufferSizeTimeStamp = bufferSizeTimeStamp;
    }
    
    /**
     * 获取回波图尺寸信息
     */
    public String getEchoImageInfo() {
        return String.format("回波图尺寸: %d x %d, 相邻点间距: %.6f m", numPing, numDot, dR);
    }
    
    /**
     * 获取SV值范围信息
     */
    public String getSVRangeInfo() {
        return String.format("SV值范围: %.1f ~ %.1f dB, 平均SV: %.1f dB", dbMin, dbMax, averSV);
    }
    
    @Override
    public String toString() {
        return "SonarEchoData{" +
                "id=" + id +
                ", deviceCode='" + deviceCode + '\'' +
                ", acTime='" + acTime + '\'' +
                ", numPing=" + numPing +
                ", numDot=" + numDot +
                ", dR=" + dR +
                ", dbMax=" + dbMax +
                ", dbMin=" + dbMin +
                ", averSV=" + averSV +
                ", avrMax=" + avrMax +
                ", avrMin=" + avrMin +
                ", bufferSizeSonar=" + bufferSizeSonar +
                ", bufferSizeTimeStamp=" + bufferSizeTimeStamp +
                '}';
    }
} 
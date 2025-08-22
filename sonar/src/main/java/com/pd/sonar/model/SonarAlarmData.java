package com.pd.sonar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 报警信息数据模型类
 * 对应STU_NEPTUNE_ALARM_DATA结构体
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SonarAlarmData {

    @JsonProperty("id")
    private Long id; // 序号
    @JsonProperty("device_code")
    private String deviceCode; // 声呐换能器的编号
    @JsonProperty("alarm_type")
    private Integer alarmType; // 报警级别
    @JsonProperty("alarm_desc")
    private String alarmDesc; // 报警描述
    @JsonProperty("alarm_time")
    private String alarmTime; // 报警时间
    
    // 报警级别常量
    public static final int ALARM_LEVEL_ONE = 1; // 一级报警
    public static final int ALARM_LEVEL_TWO = 2; // 二级报警
    public static final int ALARM_LEVEL_THREE = 3; // 三级报警
    
    public SonarAlarmData() {}
    
    public SonarAlarmData(Long id, String deviceCode, Integer alarmType, String alarmDesc, String alarmTime) {
        this.id = id;
        this.deviceCode = deviceCode;
        this.alarmType = alarmType;
        this.alarmDesc = alarmDesc;
        this.alarmTime = alarmTime;
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
    
    public Integer getAlarmType() {
        return alarmType;
    }
    
    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }
    
    public String getAlarmDesc() {
        return alarmDesc;
    }
    
    public void setAlarmDesc(String alarmDesc) {
        this.alarmDesc = alarmDesc;
    }
    
    public String getAlarmTime() {
        return alarmTime;
    }
    
    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }
    
    /**
     * 获取报警级别描述
     */
    public String getAlarmLevelDesc() {
        switch (alarmType) {
            case ALARM_LEVEL_ONE:
                return "一级报警";
            case ALARM_LEVEL_TWO:
                return "二级报警";
            case ALARM_LEVEL_THREE:
                return "三级报警";
            default:
                return "未知级别";
        }
    }
    
    @Override
    public String toString() {
        return "SonarAlarmData{" +
                "id=" + id +
                ", deviceCode='" + deviceCode + '\'' +
                ", alarmType=" + alarmType +
                ", alarmDesc='" + alarmDesc + '\'' +
                ", alarmTime='" + alarmTime + '\'' +
                '}';
    }
} 
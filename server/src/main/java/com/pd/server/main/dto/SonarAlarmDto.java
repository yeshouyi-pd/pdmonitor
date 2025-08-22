package com.pd.server.main.dto;


public class SonarAlarmDto {

    /**
    * 
    */
    private String id;

    /**
    * 声呐换能器的编号
    */
    private String deviceCode;

    /**
    * 报警级别
    */
    private Integer alarmType;

    /**
    * 报警描述
    */
    private String alarmDesc;

    /**
    * 报警时间
    */
    private String alarmTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", deviceCode=").append(deviceCode);
            sb.append(", alarmType=").append(alarmType);
            sb.append(", alarmDesc=").append(alarmDesc);
            sb.append(", alarmTime=").append(alarmTime);
        sb.append("]");
        return sb.toString();
    }

}
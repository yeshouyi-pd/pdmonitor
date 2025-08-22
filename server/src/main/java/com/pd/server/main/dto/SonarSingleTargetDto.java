package com.pd.server.main.dto;


public class SonarSingleTargetDto {

    /**
    * 序号
    */
    private String id;

    /**
    * 声呐换能器的编号
    */
    private String deviceCode;

    /**
    * 采集时间
    */
    private String acTime;

    /**
    * 单体数量
    */
    private Integer num;

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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", deviceCode=").append(deviceCode);
            sb.append(", acTime=").append(acTime);
            sb.append(", num=").append(num);
        sb.append("]");
        return sb.toString();
    }

}
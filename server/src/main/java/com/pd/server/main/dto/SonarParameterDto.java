package com.pd.server.main.dto;


public class SonarParameterDto {

    /**
    * 
    */
    private String id;

    /**
    * 声呐换能器的编号
    */
    private String deviceCode;

    /**
    * 计算平均SV的最大深度
    */
    private Double avrMax;

    /**
    * 计算平均SV的最小深度
    */
    private Double avrMin;

    /**
    * 截面面积
    */
    private Double crossSectionArea;

    /**
    * 报警阈值
    */
    private String mapParaValue;

    /**
    * 生物重量或通量累加时间
    */
    private Integer weightOrFluxAccTime;

    /**
    * 更改时间
    */
    private String changeTime;

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

    public String getMapParaValue() {
        return mapParaValue;
    }

    public void setMapParaValue(String mapParaValue) {
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", deviceCode=").append(deviceCode);
            sb.append(", avrMax=").append(avrMax);
            sb.append(", avrMin=").append(avrMin);
            sb.append(", crossSectionArea=").append(crossSectionArea);
            sb.append(", mapParaValue=").append(mapParaValue);
            sb.append(", weightOrFluxAccTime=").append(weightOrFluxAccTime);
            sb.append(", changeTime=").append(changeTime);
        sb.append("]");
        return sb.toString();
    }

}
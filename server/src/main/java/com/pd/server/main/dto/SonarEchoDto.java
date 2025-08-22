package com.pd.server.main.dto;


public class SonarEchoDto {

    /**
    * 
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
    * ping个数
    */
    private Integer numPing;

    /**
    * ping中点的个数
    */
    private Integer numDot;

    /**
    * 相邻点间距
    */
    private Double dr;

    /**
    * 色卡最大值
    */
    private Double dbMax;

    /**
    * 色卡最小值
    */
    private Double dbMin;

    /**
    * 当前这段数据的平均SV
    */
    private Double averSv;

    /**
    * 计算平均SV的最大深度
    */
    private Double avrMax;

    /**
    * 计算平均SV的最小深度
    */
    private Double avrMin;

    /**
    * SV值数组
    */
    private String bufferSonar;

    /**
    * SV值数量
    */
    private Integer bufferSizeSonar;

    /**
    * 时间戳数组
    */
    private String bufferTimeStamp;

    /**
    * 时间戳数量
    */
    private Integer bufferSizeTimeStamp;

    /**
    * 类型：1回波图2平均SV回波图
    */
    private Integer type;

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

    public Double getDr() {
        return dr;
    }

    public void setDr(Double dr) {
        this.dr = dr;
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

    public Double getAverSv() {
        return averSv;
    }

    public void setAverSv(Double averSv) {
        this.averSv = averSv;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
            sb.append(", numPing=").append(numPing);
            sb.append(", numDot=").append(numDot);
            sb.append(", dr=").append(dr);
            sb.append(", dbMax=").append(dbMax);
            sb.append(", dbMin=").append(dbMin);
            sb.append(", averSv=").append(averSv);
            sb.append(", avrMax=").append(avrMax);
            sb.append(", avrMin=").append(avrMin);
            sb.append(", bufferSonar=").append(bufferSonar);
            sb.append(", bufferSizeSonar=").append(bufferSizeSonar);
            sb.append(", bufferTimeStamp=").append(bufferTimeStamp);
            sb.append(", bufferSizeTimeStamp=").append(bufferSizeTimeStamp);
            sb.append(", type=").append(type);
        sb.append("]");
        return sb.toString();
    }

}
package com.pd.server.main.domain;

public class SonarEcho {
    private String id;

    private String deviceCode;

    private String acTime;

    private Integer numPing;

    private Integer numDot;

    private Double dr;

    private Double dbMax;

    private Double dbMin;

    private Double averSv;

    private Double avrMax;

    private Double avrMin;

    private Integer bufferSizeSonar;

    private Integer bufferSizeTimeStamp;

    private Integer type;

    private String bufferSonar;

    private String bufferTimeStamp;

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

    public Integer getBufferSizeSonar() {
        return bufferSizeSonar;
    }

    public void setBufferSizeSonar(Integer bufferSizeSonar) {
        this.bufferSizeSonar = bufferSizeSonar;
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

    public String getBufferSonar() {
        return bufferSonar;
    }

    public void setBufferSonar(String bufferSonar) {
        this.bufferSonar = bufferSonar;
    }

    public String getBufferTimeStamp() {
        return bufferTimeStamp;
    }

    public void setBufferTimeStamp(String bufferTimeStamp) {
        this.bufferTimeStamp = bufferTimeStamp;
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
        sb.append(", bufferSizeSonar=").append(bufferSizeSonar);
        sb.append(", bufferSizeTimeStamp=").append(bufferSizeTimeStamp);
        sb.append(", type=").append(type);
        sb.append(", bufferSonar=").append(bufferSonar);
        sb.append(", bufferTimeStamp=").append(bufferTimeStamp);
        sb.append("]");
        return sb.toString();
    }
}
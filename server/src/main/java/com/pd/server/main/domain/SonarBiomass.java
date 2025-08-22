package com.pd.server.main.domain;

public class SonarBiomass {
    private String id;

    private String deviceCode;

    private Double biomassDensity;

    private Double biomassWeightAccumulated;

    private Double biomassWeight;

    private Double averageSv;

    private String acTime;

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

    public Double getBiomassDensity() {
        return biomassDensity;
    }

    public void setBiomassDensity(Double biomassDensity) {
        this.biomassDensity = biomassDensity;
    }

    public Double getBiomassWeightAccumulated() {
        return biomassWeightAccumulated;
    }

    public void setBiomassWeightAccumulated(Double biomassWeightAccumulated) {
        this.biomassWeightAccumulated = biomassWeightAccumulated;
    }

    public Double getBiomassWeight() {
        return biomassWeight;
    }

    public void setBiomassWeight(Double biomassWeight) {
        this.biomassWeight = biomassWeight;
    }

    public Double getAverageSv() {
        return averageSv;
    }

    public void setAverageSv(Double averageSv) {
        this.averageSv = averageSv;
    }

    public String getAcTime() {
        return acTime;
    }

    public void setAcTime(String acTime) {
        this.acTime = acTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deviceCode=").append(deviceCode);
        sb.append(", biomassDensity=").append(biomassDensity);
        sb.append(", biomassWeightAccumulated=").append(biomassWeightAccumulated);
        sb.append(", biomassWeight=").append(biomassWeight);
        sb.append(", averageSv=").append(averageSv);
        sb.append(", acTime=").append(acTime);
        sb.append("]");
        return sb.toString();
    }
}
package com.pd.server.main.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Turbidity {
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dateTime;

    private String turbidityH;

    private String turibidityL;

    private String depth;

    private String temperature;

    private String conductivity;

    private String salinity;

    private String batVolt;

    private String bz;

    private String sm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTurbidityH() {
        return turbidityH;
    }

    public void setTurbidityH(String turbidityH) {
        this.turbidityH = turbidityH;
    }

    public String getTuribidityL() {
        return turibidityL;
    }

    public void setTuribidityL(String turibidityL) {
        this.turibidityL = turibidityL;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getConductivity() {
        return conductivity;
    }

    public void setConductivity(String conductivity) {
        this.conductivity = conductivity;
    }

    public String getSalinity() {
        return salinity;
    }

    public void setSalinity(String salinity) {
        this.salinity = salinity;
    }

    public String getBatVolt() {
        return batVolt;
    }

    public void setBatVolt(String batVolt) {
        this.batVolt = batVolt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", turbidityH=").append(turbidityH);
        sb.append(", turibidityL=").append(turibidityL);
        sb.append(", depth=").append(depth);
        sb.append(", temperature=").append(temperature);
        sb.append(", conductivity=").append(conductivity);
        sb.append(", salinity=").append(salinity);
        sb.append(", batVolt=").append(batVolt);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }
}
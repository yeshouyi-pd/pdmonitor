package com.pd.server.main.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class WaveData {
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    private String sbbh;

    private String waveH;

    private String waveDirection;

    private String wavePeriod;

    private String bz;

    private String sm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getWaveH() {
        return waveH;
    }

    public void setWaveH(String waveH) {
        this.waveH = waveH;
    }

    public String getWaveDirection() {
        return waveDirection;
    }

    public void setWaveDirection(String waveDirection) {
        this.waveDirection = waveDirection;
    }

    public String getWavePeriod() {
        return wavePeriod;
    }

    public void setWavePeriod(String wavePeriod) {
        this.wavePeriod = wavePeriod;
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
        sb.append(", cjsj=").append(cjsj);
        sb.append(", sbbh=").append(sbbh);
        sb.append(", waveH=").append(waveH);
        sb.append(", waveDirection=").append(waveDirection);
        sb.append(", wavePeriod=").append(wavePeriod);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }
}
package com.pd.server.main.domain;

import java.util.Date;

public class WaterQuality {
    private String id;

    private String sbsn;

    private String ip;

    private Integer port;

    private String datacenter;

    private Float waterDepth;

    private Float waterVelocity;

    private String temperature;

    private String oxidative;

    private String salinity;

    private String cod;

    private String turbidity;

    private String ph;

    private String transparency;

    private String ad;

    private String suspended;

    private String ppm;

    private String chlFlu;

    private String chlorophyll;

    private String cyanobacteria;

    private String orp;

    private String oil;

    private String phosphorus;

    private Date createTime;

    private String fzr;

    private String bz;

    private String sm1;

    private String sm2;

    private String sm3;

    private String sm4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbsn() {
        return sbsn;
    }

    public void setSbsn(String sbsn) {
        this.sbsn = sbsn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDatacenter() {
        return datacenter;
    }

    public void setDatacenter(String datacenter) {
        this.datacenter = datacenter;
    }

    public Float getWaterDepth() {
        return waterDepth;
    }

    public void setWaterDepth(Float waterDepth) {
        this.waterDepth = waterDepth;
    }

    public Float getWaterVelocity() {
        return waterVelocity;
    }

    public void setWaterVelocity(Float waterVelocity) {
        this.waterVelocity = waterVelocity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getOxidative() {
        return oxidative;
    }

    public void setOxidative(String oxidative) {
        this.oxidative = oxidative;
    }

    public String getSalinity() {
        return salinity;
    }

    public void setSalinity(String salinity) {
        this.salinity = salinity;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(String turbidity) {
        this.turbidity = turbidity;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getTransparency() {
        return transparency;
    }

    public void setTransparency(String transparency) {
        this.transparency = transparency;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSuspended() {
        return suspended;
    }

    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }

    public String getPpm() {
        return ppm;
    }

    public void setPpm(String ppm) {
        this.ppm = ppm;
    }

    public String getChlFlu() {
        return chlFlu;
    }

    public void setChlFlu(String chlFlu) {
        this.chlFlu = chlFlu;
    }

    public String getChlorophyll() {
        return chlorophyll;
    }

    public void setChlorophyll(String chlorophyll) {
        this.chlorophyll = chlorophyll;
    }

    public String getCyanobacteria() {
        return cyanobacteria;
    }

    public void setCyanobacteria(String cyanobacteria) {
        this.cyanobacteria = cyanobacteria;
    }

    public String getOrp() {
        return orp;
    }

    public void setOrp(String orp) {
        this.orp = orp;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public String getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(String phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSm1() {
        return sm1;
    }

    public void setSm1(String sm1) {
        this.sm1 = sm1;
    }

    public String getSm2() {
        return sm2;
    }

    public void setSm2(String sm2) {
        this.sm2 = sm2;
    }

    public String getSm3() {
        return sm3;
    }

    public void setSm3(String sm3) {
        this.sm3 = sm3;
    }

    public String getSm4() {
        return sm4;
    }

    public void setSm4(String sm4) {
        this.sm4 = sm4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sbsn=").append(sbsn);
        sb.append(", ip=").append(ip);
        sb.append(", port=").append(port);
        sb.append(", datacenter=").append(datacenter);
        sb.append(", waterDepth=").append(waterDepth);
        sb.append(", waterVelocity=").append(waterVelocity);
        sb.append(", temperature=").append(temperature);
        sb.append(", oxidative=").append(oxidative);
        sb.append(", salinity=").append(salinity);
        sb.append(", cod=").append(cod);
        sb.append(", turbidity=").append(turbidity);
        sb.append(", ph=").append(ph);
        sb.append(", transparency=").append(transparency);
        sb.append(", ad=").append(ad);
        sb.append(", suspended=").append(suspended);
        sb.append(", ppm=").append(ppm);
        sb.append(", chlFlu=").append(chlFlu);
        sb.append(", chlorophyll=").append(chlorophyll);
        sb.append(", cyanobacteria=").append(cyanobacteria);
        sb.append(", orp=").append(orp);
        sb.append(", oil=").append(oil);
        sb.append(", phosphorus=").append(phosphorus);
        sb.append(", createTime=").append(createTime);
        sb.append(", fzr=").append(fzr);
        sb.append(", bz=").append(bz);
        sb.append(", sm1=").append(sm1);
        sb.append(", sm2=").append(sm2);
        sb.append(", sm3=").append(sm3);
        sb.append(", sm4=").append(sm4);
        sb.append("]");
        return sb.toString();
    }
}
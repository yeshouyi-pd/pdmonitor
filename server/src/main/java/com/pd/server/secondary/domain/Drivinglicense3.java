package com.pd.server.secondary.domain;

public class Drivinglicense3 {
    private String sfzmhm;

    private String xm;

    private String zt;

    private String cclzrq;

    private String yxqs;

    private String yxqz;

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getCclzrq() {
        return cclzrq;
    }

    public void setCclzrq(String cclzrq) {
        this.cclzrq = cclzrq;
    }

    public String getYxqs() {
        return yxqs;
    }

    public void setYxqs(String yxqs) {
        this.yxqs = yxqs;
    }

    public String getYxqz() {
        return yxqz;
    }

    public void setYxqz(String yxqz) {
        this.yxqz = yxqz;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sfzmhm=").append(sfzmhm);
        sb.append(", xm=").append(xm);
        sb.append(", zt=").append(zt);
        sb.append(", cclzrq=").append(cclzrq);
        sb.append(", yxqs=").append(yxqs);
        sb.append(", yxqz=").append(yxqz);
        sb.append("]");
        return sb.toString();
    }
}
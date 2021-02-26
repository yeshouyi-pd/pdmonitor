package com.pd.server.secondary.domain;

public class Vehicle3 {
    private String sfzmhm;

    private String hpzl;

    private String hphm;

    private String syr;

    private String clsbdh;

    private String fdjh;

    private String ccdjrq;

    private String yxqz;

    private String zt;

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    public String getCcdjrq() {
        return ccdjrq;
    }

    public void setCcdjrq(String ccdjrq) {
        this.ccdjrq = ccdjrq;
    }

    public String getYxqz() {
        return yxqz;
    }

    public void setYxqz(String yxqz) {
        this.yxqz = yxqz;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sfzmhm=").append(sfzmhm);
        sb.append(", hpzl=").append(hpzl);
        sb.append(", hphm=").append(hphm);
        sb.append(", syr=").append(syr);
        sb.append(", clsbdh=").append(clsbdh);
        sb.append(", fdjh=").append(fdjh);
        sb.append(", ccdjrq=").append(ccdjrq);
        sb.append(", yxqz=").append(yxqz);
        sb.append(", zt=").append(zt);
        sb.append("]");
        return sb.toString();
    }
}
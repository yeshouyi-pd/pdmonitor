package com.pd.server.main.domain;

public class VideoEvent {
    private String id;

    private String sbbh;

    private String kssj;

    private String jssj;

    private String rq;

    private String wjlj;

    private String wjmc;

    private Integer sfysp;

    private String bz;

    private String sm;

    private String sxtip;

    private String yf;

    private String nf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getWjlj() {
        return wjlj;
    }

    public void setWjlj(String wjlj) {
        this.wjlj = wjlj;
    }

    public String getWjmc() {
        return wjmc;
    }

    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }

    public Integer getSfysp() {
        return sfysp;
    }

    public void setSfysp(Integer sfysp) {
        this.sfysp = sfysp;
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

    public String getSxtip() {
        return sxtip;
    }

    public void setSxtip(String sxtip) {
        this.sxtip = sxtip;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sbbh=").append(sbbh);
        sb.append(", kssj=").append(kssj);
        sb.append(", jssj=").append(jssj);
        sb.append(", rq=").append(rq);
        sb.append(", wjlj=").append(wjlj);
        sb.append(", wjmc=").append(wjmc);
        sb.append(", sfysp=").append(sfysp);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append(", sxtip=").append(sxtip);
        sb.append(", yf=").append(yf);
        sb.append(", nf=").append(nf);
        sb.append("]");
        return sb.toString();
    }
}
package com.pd.server.main.domain;

public class BeconFileStatistics {
    private String id;

    private String xbid;

    private String sbbhs;

    private String sbmcs;

    private String rq;

    private String cjsjs;

    private String gps;

    private String bz;

    private String sm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXbid() {
        return xbid;
    }

    public void setXbid(String xbid) {
        this.xbid = xbid;
    }

    public String getSbbhs() {
        return sbbhs;
    }

    public void setSbbhs(String sbbhs) {
        this.sbbhs = sbbhs;
    }

    public String getSbmcs() {
        return sbmcs;
    }

    public void setSbmcs(String sbmcs) {
        this.sbmcs = sbmcs;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getCjsjs() {
        return cjsjs;
    }

    public void setCjsjs(String cjsjs) {
        this.cjsjs = cjsjs;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
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
        sb.append(", xbid=").append(xbid);
        sb.append(", sbbhs=").append(sbbhs);
        sb.append(", sbmcs=").append(sbmcs);
        sb.append(", rq=").append(rq);
        sb.append(", cjsjs=").append(cjsjs);
        sb.append(", gps=").append(gps);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }
}
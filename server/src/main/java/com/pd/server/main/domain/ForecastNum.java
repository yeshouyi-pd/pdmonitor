package com.pd.server.main.domain;

public class ForecastNum {
    private String id;

    private String sbbh;

    private String cxcs;

    private String cxsj;

    private String cxrq;

    private String depcode;

    private String xmbh;

    private String bz;

    private String sm;

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

    public String getCxcs() {
        return cxcs;
    }

    public void setCxcs(String cxcs) {
        this.cxcs = cxcs;
    }

    public String getCxsj() {
        return cxsj;
    }

    public void setCxsj(String cxsj) {
        this.cxsj = cxsj;
    }

    public String getCxrq() {
        return cxrq;
    }

    public void setCxrq(String cxrq) {
        this.cxrq = cxrq;
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
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
        sb.append(", sbbh=").append(sbbh);
        sb.append(", cxcs=").append(cxcs);
        sb.append(", cxsj=").append(cxsj);
        sb.append(", cxrq=").append(cxrq);
        sb.append(", depcode=").append(depcode);
        sb.append(", xmbh=").append(xmbh);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }
}
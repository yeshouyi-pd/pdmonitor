package com.pd.server.main.domain;

import java.util.Date;

public class EquipmentRecord {
    private Integer id;

    private String sbbh;

    private Date cjsj;

    private String zt;

    private String bz;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sbbh=").append(sbbh);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", zt=").append(zt);
        sb.append(", bz=").append(bz);
        sb.append("]");
        return sb.toString();
    }
}
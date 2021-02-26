package com.pd.server.main.domain;

import java.util.Date;

public class VehParkLotStatistics {
    private String id;

    private Integer ddchp;

    private Integer ddcnp;

    private Integer ddcwp;

    private Integer mtcyp;

    private Integer mtcwp;

    private Integer slc;

    private Integer qc;

    private Integer nyc;

    private Integer qt;

    private String sysOrgCode;

    private String zt;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDdchp() {
        return ddchp;
    }

    public void setDdchp(Integer ddchp) {
        this.ddchp = ddchp;
    }

    public Integer getDdcnp() {
        return ddcnp;
    }

    public void setDdcnp(Integer ddcnp) {
        this.ddcnp = ddcnp;
    }

    public Integer getDdcwp() {
        return ddcwp;
    }

    public void setDdcwp(Integer ddcwp) {
        this.ddcwp = ddcwp;
    }

    public Integer getMtcyp() {
        return mtcyp;
    }

    public void setMtcyp(Integer mtcyp) {
        this.mtcyp = mtcyp;
    }

    public Integer getMtcwp() {
        return mtcwp;
    }

    public void setMtcwp(Integer mtcwp) {
        this.mtcwp = mtcwp;
    }

    public Integer getSlc() {
        return slc;
    }

    public void setSlc(Integer slc) {
        this.slc = slc;
    }

    public Integer getQc() {
        return qc;
    }

    public void setQc(Integer qc) {
        this.qc = qc;
    }

    public Integer getNyc() {
        return nyc;
    }

    public void setNyc(Integer nyc) {
        this.nyc = nyc;
    }

    public Integer getQt() {
        return qt;
    }

    public void setQt(Integer qt) {
        this.qt = qt;
    }

    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ddchp=").append(ddchp);
        sb.append(", ddcnp=").append(ddcnp);
        sb.append(", ddcwp=").append(ddcwp);
        sb.append(", mtcyp=").append(mtcyp);
        sb.append(", mtcwp=").append(mtcwp);
        sb.append(", slc=").append(slc);
        sb.append(", qc=").append(qc);
        sb.append(", nyc=").append(nyc);
        sb.append(", qt=").append(qt);
        sb.append(", sysOrgCode=").append(sysOrgCode);
        sb.append(", zt=").append(zt);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
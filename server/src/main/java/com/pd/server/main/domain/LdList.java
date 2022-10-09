package com.pd.server.main.domain;

import java.util.Date;

public class LdList {
    private String iccid;

    private String jd;

    private String wd;

    private String sbms;

    private String ipport;

    private String state;

    private Date upsj;

    private String deptcode;

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getSbms() {
        return sbms;
    }

    public void setSbms(String sbms) {
        this.sbms = sbms;
    }

    public String getIpport() {
        return ipport;
    }

    public void setIpport(String ipport) {
        this.ipport = ipport;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getUpsj() {
        return upsj;
    }

    public void setUpsj(Date upsj) {
        this.upsj = upsj;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", iccid=").append(iccid);
        sb.append(", jd=").append(jd);
        sb.append(", wd=").append(wd);
        sb.append(", sbms=").append(sbms);
        sb.append(", ipport=").append(ipport);
        sb.append(", state=").append(state);
        sb.append(", upsj=").append(upsj);
        sb.append(", deptcode=").append(deptcode);
        sb.append("]");
        return sb.toString();
    }
}
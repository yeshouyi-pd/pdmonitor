package com.pd.server.secondary.domain;

import java.util.Date;

public class DlbDataSec {
    private Integer id;

    private String iccid;

    private String ipport;

    private String wd;

    private String sj;

    private String cdzt;

    private Date sjdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getIpport() {
        return ipport;
    }

    public void setIpport(String ipport) {
        this.ipport = ipport;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public String getCdzt() {
        return cdzt;
    }

    public void setCdzt(String cdzt) {
        this.cdzt = cdzt;
    }

    public Date getSjdate() {
        return sjdate;
    }

    public void setSjdate(Date sjdate) {
        this.sjdate = sjdate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", iccid=").append(iccid);
        sb.append(", ipport=").append(ipport);
        sb.append(", wd=").append(wd);
        sb.append(", sj=").append(sj);
        sb.append(", cdzt=").append(cdzt);
        sb.append(", sjdate=").append(sjdate);
        sb.append("]");
        return sb.toString();
    }
}
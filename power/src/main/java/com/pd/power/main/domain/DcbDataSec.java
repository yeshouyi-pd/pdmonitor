package com.pd.power.main.domain;

import java.util.Date;

public class DcbDataSec {
    private Integer id;

    private String iccid;

    private String ipport;

    private String dy;

    private String rl;

    private String dl;

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

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getRl() {
        return rl;
    }

    public void setRl(String rl) {
        this.rl = rl;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
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
        sb.append(", dy=").append(dy);
        sb.append(", rl=").append(rl);
        sb.append(", dl=").append(dl);
        sb.append(", sjdate=").append(sjdate);
        sb.append("]");
        return sb.toString();
    }
}
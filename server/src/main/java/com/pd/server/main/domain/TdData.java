package com.pd.server.main.domain;

import java.util.Date;

public class TdData {
    private Integer id;

    private String iccid;

    private String ipport;

    private String tdlx;

    private String tdh;

    private String dy;

    private String dl;

    private String tdzt;

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

    public String getTdlx() {
        return tdlx;
    }

    public void setTdlx(String tdlx) {
        this.tdlx = tdlx;
    }

    public String getTdh() {
        return tdh;
    }

    public void setTdh(String tdh) {
        this.tdh = tdh;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public String getTdzt() {
        return tdzt;
    }

    public void setTdzt(String tdzt) {
        this.tdzt = tdzt;
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
        sb.append(", tdlx=").append(tdlx);
        sb.append(", tdh=").append(tdh);
        sb.append(", dy=").append(dy);
        sb.append(", dl=").append(dl);
        sb.append(", tdzt=").append(tdzt);
        sb.append(", sjdate=").append(sjdate);
        sb.append("]");
        return sb.toString();
    }
}
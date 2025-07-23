package com.pd.server.main.domain;

import java.util.Date;

public class BeconFileTyToday {
    private String id;

    private String sbbh;

    private String wjlj;

    private String xbid;

    private Date cjsj;

    private Date jssj;

    private String deptcode;

    private String rq;

    private String gps;

    private String jd;

    private String wd;

    private String sm1;

    private String sm;

    private String bz;

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

    public String getWjlj() {
        return wjlj;
    }

    public void setWjlj(String wjlj) {
        this.wjlj = wjlj;
    }

    public String getXbid() {
        return xbid;
    }

    public void setXbid(String xbid) {
        this.xbid = xbid;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
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

    public String getSm1() {
        return sm1;
    }

    public void setSm1(String sm1) {
        this.sm1 = sm1;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
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
        sb.append(", wjlj=").append(wjlj);
        sb.append(", xbid=").append(xbid);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", jssj=").append(jssj);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", rq=").append(rq);
        sb.append(", gps=").append(gps);
        sb.append(", jd=").append(jd);
        sb.append(", wd=").append(wd);
        sb.append(", sm1=").append(sm1);
        sb.append(", sm=").append(sm);
        sb.append(", bz=").append(bz);
        sb.append("]");
        return sb.toString();
    }
}
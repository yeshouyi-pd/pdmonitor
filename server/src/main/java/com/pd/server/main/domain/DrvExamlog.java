package com.pd.server.main.domain;

import java.util.Date;

public class DrvExamlog {
    private String id;

    private String xm;

    private String sfzmhm;

    private String sjh;

    private String cfbh;

    private String dtkm;

    private Date kssj;

    private Date jssj;

    private Integer sdfs;

    private String sfjg;

    private String wxid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    public String getCfbh() {
        return cfbh;
    }

    public void setCfbh(String cfbh) {
        this.cfbh = cfbh;
    }

    public String getDtkm() {
        return dtkm;
    }

    public void setDtkm(String dtkm) {
        this.dtkm = dtkm;
    }

    public Date getKssj() {
        return kssj;
    }

    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public Integer getSdfs() {
        return sdfs;
    }

    public void setSdfs(Integer sdfs) {
        this.sdfs = sdfs;
    }

    public String getSfjg() {
        return sfjg;
    }

    public void setSfjg(String sfjg) {
        this.sfjg = sfjg;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", xm=").append(xm);
        sb.append(", sfzmhm=").append(sfzmhm);
        sb.append(", sjh=").append(sjh);
        sb.append(", cfbh=").append(cfbh);
        sb.append(", dtkm=").append(dtkm);
        sb.append(", kssj=").append(kssj);
        sb.append(", jssj=").append(jssj);
        sb.append(", sdfs=").append(sdfs);
        sb.append(", sfjg=").append(sfjg);
        sb.append(", wxid=").append(wxid);
        sb.append("]");
        return sb.toString();
    }
}
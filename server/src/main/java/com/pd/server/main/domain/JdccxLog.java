package com.pd.server.main.domain;

import java.util.Date;

public class JdccxLog {
    private String id;

    private String createBy;

    private Date createTime;

    private String qqcs;

    private String fhztm;

    private String cjh;

    private String zcrq;

    private String sccj;

    private String dw;

    private String sjhm;

    private String hpzl;

    private String zhhm;

    private String hphm;

    private String syr;

    private String fhjg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getQqcs() {
        return qqcs;
    }

    public void setQqcs(String qqcs) {
        this.qqcs = qqcs;
    }

    public String getFhztm() {
        return fhztm;
    }

    public void setFhztm(String fhztm) {
        this.fhztm = fhztm;
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
    }

    public String getZcrq() {
        return zcrq;
    }

    public void setZcrq(String zcrq) {
        this.zcrq = zcrq;
    }

    public String getSccj() {
        return sccj;
    }

    public void setSccj(String sccj) {
        this.sccj = sccj;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getZhhm() {
        return zhhm;
    }

    public void setZhhm(String zhhm) {
        this.zhhm = zhhm;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getFhjg() {
        return fhjg;
    }

    public void setFhjg(String fhjg) {
        this.fhjg = fhjg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", qqcs=").append(qqcs);
        sb.append(", fhztm=").append(fhztm);
        sb.append(", cjh=").append(cjh);
        sb.append(", zcrq=").append(zcrq);
        sb.append(", sccj=").append(sccj);
        sb.append(", dw=").append(dw);
        sb.append(", sjhm=").append(sjhm);
        sb.append(", hpzl=").append(hpzl);
        sb.append(", zhhm=").append(zhhm);
        sb.append(", hphm=").append(hphm);
        sb.append(", syr=").append(syr);
        sb.append(", fhjg=").append(fhjg);
        sb.append("]");
        return sb.toString();
    }
}
package com.pd.server.main.domain;

import java.util.Date;

public class VehLicelost {
    private String id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    private String hpzl;

    private String hphm;

    private String lqdz;

    private String lqdh;

    private String lqrxm;

    private String lqrdh;

    private Date lqrq;

    private String sfzmhm;

    private String lqsm;

    private String zt;

    private String openid;

    private String bz;

    private String lsh;

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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getLqdz() {
        return lqdz;
    }

    public void setLqdz(String lqdz) {
        this.lqdz = lqdz;
    }

    public String getLqdh() {
        return lqdh;
    }

    public void setLqdh(String lqdh) {
        this.lqdh = lqdh;
    }

    public String getLqrxm() {
        return lqrxm;
    }

    public void setLqrxm(String lqrxm) {
        this.lqrxm = lqrxm;
    }

    public String getLqrdh() {
        return lqrdh;
    }

    public void setLqrdh(String lqrdh) {
        this.lqrdh = lqrdh;
    }

    public Date getLqrq() {
        return lqrq;
    }

    public void setLqrq(Date lqrq) {
        this.lqrq = lqrq;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getLqsm() {
        return lqsm;
    }

    public void setLqsm(String lqsm) {
        this.lqsm = lqsm;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
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
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", sysOrgCode=").append(sysOrgCode);
        sb.append(", hpzl=").append(hpzl);
        sb.append(", hphm=").append(hphm);
        sb.append(", lqdz=").append(lqdz);
        sb.append(", lqdh=").append(lqdh);
        sb.append(", lqrxm=").append(lqrxm);
        sb.append(", lqrdh=").append(lqrdh);
        sb.append(", lqrq=").append(lqrq);
        sb.append(", sfzmhm=").append(sfzmhm);
        sb.append(", lqsm=").append(lqsm);
        sb.append(", zt=").append(zt);
        sb.append(", openid=").append(openid);
        sb.append(", bz=").append(bz);
        sb.append(", lsh=").append(lsh);
        sb.append("]");
        return sb.toString();
    }
}
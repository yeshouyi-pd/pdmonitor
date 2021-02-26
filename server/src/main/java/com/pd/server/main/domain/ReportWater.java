package com.pd.server.main.domain;

import java.util.Date;

public class ReportWater {
    private String id;

    private String sfzmhm;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    private String zsdd;

    private String gps;

    private String zsddxx;

    private String sbr;

    private String sbrsjh;

    private String sbsm;

    private String shr;

    private Date shsj;

    private String clyj;

    private String zt;

    private String bz;

    private String openid;

    private String lsh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
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

    public String getZsdd() {
        return zsdd;
    }

    public void setZsdd(String zsdd) {
        this.zsdd = zsdd;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getZsddxx() {
        return zsddxx;
    }

    public void setZsddxx(String zsddxx) {
        this.zsddxx = zsddxx;
    }

    public String getSbr() {
        return sbr;
    }

    public void setSbr(String sbr) {
        this.sbr = sbr;
    }

    public String getSbrsjh() {
        return sbrsjh;
    }

    public void setSbrsjh(String sbrsjh) {
        this.sbrsjh = sbrsjh;
    }

    public String getSbsm() {
        return sbsm;
    }

    public void setSbsm(String sbsm) {
        this.sbsm = sbsm;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public Date getShsj() {
        return shsj;
    }

    public void setShsj(Date shsj) {
        this.shsj = shsj;
    }

    public String getClyj() {
        return clyj;
    }

    public void setClyj(String clyj) {
        this.clyj = clyj;
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
        sb.append(", sfzmhm=").append(sfzmhm);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", sysOrgCode=").append(sysOrgCode);
        sb.append(", zsdd=").append(zsdd);
        sb.append(", gps=").append(gps);
        sb.append(", zsddxx=").append(zsddxx);
        sb.append(", sbr=").append(sbr);
        sb.append(", sbrsjh=").append(sbrsjh);
        sb.append(", sbsm=").append(sbsm);
        sb.append(", shr=").append(shr);
        sb.append(", shsj=").append(shsj);
        sb.append(", clyj=").append(clyj);
        sb.append(", zt=").append(zt);
        sb.append(", bz=").append(bz);
        sb.append(", openid=").append(openid);
        sb.append(", lsh=").append(lsh);
        sb.append("]");
        return sb.toString();
    }
}
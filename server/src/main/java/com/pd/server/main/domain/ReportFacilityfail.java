package com.pd.server.main.domain;

import java.util.Date;

public class ReportFacilityfail {
    private String id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    private String gzdd;

    private String gzlx;

    private String gzgps;

    private String gzxxdz;

    private String gzms;

    private String sbr;

    private String sbrsjh;

    private String sfzmhm;

    private String shr;

    private Date shsj;

    private String zt;

    private String bz;

    private String clyj;

    private String lsh;

    private String openid;

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

    public String getGzdd() {
        return gzdd;
    }

    public void setGzdd(String gzdd) {
        this.gzdd = gzdd;
    }

    public String getGzlx() {
        return gzlx;
    }

    public void setGzlx(String gzlx) {
        this.gzlx = gzlx;
    }

    public String getGzgps() {
        return gzgps;
    }

    public void setGzgps(String gzgps) {
        this.gzgps = gzgps;
    }

    public String getGzxxdz() {
        return gzxxdz;
    }

    public void setGzxxdz(String gzxxdz) {
        this.gzxxdz = gzxxdz;
    }

    public String getGzms() {
        return gzms;
    }

    public void setGzms(String gzms) {
        this.gzms = gzms;
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

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
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

    public String getClyj() {
        return clyj;
    }

    public void setClyj(String clyj) {
        this.clyj = clyj;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
        sb.append(", gzdd=").append(gzdd);
        sb.append(", gzlx=").append(gzlx);
        sb.append(", gzgps=").append(gzgps);
        sb.append(", gzxxdz=").append(gzxxdz);
        sb.append(", gzms=").append(gzms);
        sb.append(", sbr=").append(sbr);
        sb.append(", sbrsjh=").append(sbrsjh);
        sb.append(", sfzmhm=").append(sfzmhm);
        sb.append(", shr=").append(shr);
        sb.append(", shsj=").append(shsj);
        sb.append(", zt=").append(zt);
        sb.append(", bz=").append(bz);
        sb.append(", clyj=").append(clyj);
        sb.append(", lsh=").append(lsh);
        sb.append(", openid=").append(openid);
        sb.append("]");
        return sb.toString();
    }
}
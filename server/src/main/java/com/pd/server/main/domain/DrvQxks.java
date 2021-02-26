package com.pd.server.main.domain;

import java.util.Date;

public class DrvQxks {
    private String id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    private String xm;

    private String zjlx;

    private String sfzmhm;

    private String sjhm;

    private String qxkm;

    private Date yykssj;

    private String sqsm;

    private String shr;

    private Date shsj;

    private String shsm;

    private String bz;

    private String openid;

    private String lsh;

    private String zt;

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

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getQxkm() {
        return qxkm;
    }

    public void setQxkm(String qxkm) {
        this.qxkm = qxkm;
    }

    public Date getYykssj() {
        return yykssj;
    }

    public void setYykssj(Date yykssj) {
        this.yykssj = yykssj;
    }

    public String getSqsm() {
        return sqsm;
    }

    public void setSqsm(String sqsm) {
        this.sqsm = sqsm;
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

    public String getShsm() {
        return shsm;
    }

    public void setShsm(String shsm) {
        this.shsm = shsm;
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

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
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
        sb.append(", xm=").append(xm);
        sb.append(", zjlx=").append(zjlx);
        sb.append(", sfzmhm=").append(sfzmhm);
        sb.append(", sjhm=").append(sjhm);
        sb.append(", qxkm=").append(qxkm);
        sb.append(", yykssj=").append(yykssj);
        sb.append(", sqsm=").append(sqsm);
        sb.append(", shr=").append(shr);
        sb.append(", shsj=").append(shsj);
        sb.append(", shsm=").append(shsm);
        sb.append(", bz=").append(bz);
        sb.append(", openid=").append(openid);
        sb.append(", lsh=").append(lsh);
        sb.append(", zt=").append(zt);
        sb.append("]");
        return sb.toString();
    }
}
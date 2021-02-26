package com.pd.server.main.domain;

import java.util.Date;

public class WebchatNewinfo {
    private String id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    private String title;

    private String gjz;

    private String xxlb;

    private String zt;

    private String shr;

    private Date shsj;

    private String lsh;

    private String shsm;

    private String bz;

    private String iszd;

    private String isfb;

    private String content;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGjz() {
        return gjz;
    }

    public void setGjz(String gjz) {
        this.gjz = gjz;
    }

    public String getXxlb() {
        return xxlb;
    }

    public void setXxlb(String xxlb) {
        this.xxlb = xxlb;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
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

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
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

    public String getIszd() {
        return iszd;
    }

    public void setIszd(String iszd) {
        this.iszd = iszd;
    }

    public String getIsfb() {
        return isfb;
    }

    public void setIsfb(String isfb) {
        this.isfb = isfb;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        sb.append(", title=").append(title);
        sb.append(", gjz=").append(gjz);
        sb.append(", xxlb=").append(xxlb);
        sb.append(", zt=").append(zt);
        sb.append(", shr=").append(shr);
        sb.append(", shsj=").append(shsj);
        sb.append(", lsh=").append(lsh);
        sb.append(", shsm=").append(shsm);
        sb.append(", bz=").append(bz);
        sb.append(", iszd=").append(iszd);
        sb.append(", isfb=").append(isfb);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}
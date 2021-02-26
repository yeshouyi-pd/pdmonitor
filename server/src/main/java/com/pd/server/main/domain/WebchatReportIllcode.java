package com.pd.server.main.domain;

import java.util.Date;

public class WebchatReportIllcode {
    private String id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    private String wflb;

    private String wfxwxx;

    private String wfxwms;

    private String sfsx;

    private Integer jljr;

    private String fffs;

    private String bz;

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

    public String getWflb() {
        return wflb;
    }

    public void setWflb(String wflb) {
        this.wflb = wflb;
    }

    public String getWfxwxx() {
        return wfxwxx;
    }

    public void setWfxwxx(String wfxwxx) {
        this.wfxwxx = wfxwxx;
    }

    public String getWfxwms() {
        return wfxwms;
    }

    public void setWfxwms(String wfxwms) {
        this.wfxwms = wfxwms;
    }

    public String getSfsx() {
        return sfsx;
    }

    public void setSfsx(String sfsx) {
        this.sfsx = sfsx;
    }

    public Integer getJljr() {
        return jljr;
    }

    public void setJljr(Integer jljr) {
        this.jljr = jljr;
    }

    public String getFffs() {
        return fffs;
    }

    public void setFffs(String fffs) {
        this.fffs = fffs;
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
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", sysOrgCode=").append(sysOrgCode);
        sb.append(", wflb=").append(wflb);
        sb.append(", wfxwxx=").append(wfxwxx);
        sb.append(", wfxwms=").append(wfxwms);
        sb.append(", sfsx=").append(sfsx);
        sb.append(", jljr=").append(jljr);
        sb.append(", fffs=").append(fffs);
        sb.append(", bz=").append(bz);
        sb.append("]");
        return sb.toString();
    }
}
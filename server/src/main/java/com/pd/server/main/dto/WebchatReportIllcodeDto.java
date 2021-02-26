package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WebchatReportIllcodeDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 创建人
    */
    private String createBy;

    /**
    * 创建日期
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 更新人
    */
    private String updateBy;

    /**
    * 更新日期
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
    * 所属部门
    */
    private String sysOrgCode;

    /**
    * 违法类别代码|(codeset-0621)
    */
    private String wflb;

    /**
    * 违法行为详细|(后台审核人员违法行为详细，与举报的违法行为奖励关联)
    */
    private String wfxwxx;

    /**
    * 违法行为
    */
    private String wfxwms;

    /**
    * 是否上限|1是，2否
    */
    private String sfsx;

    /**
    * 奖励金额
    */
    private Integer jljr;

    /**
    * 发放方式
    */
    private String fffs;

    /**
    * 备注
    */
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
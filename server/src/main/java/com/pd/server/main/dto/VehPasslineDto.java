package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class VehPasslineDto extends PageDto {

    /**
     *
     */
    private String id;

    /**
     * 区县
     */
    private String qx;

    /**
    * 街道地址
    */
    private String jddz;

    /**
    * 排序
    */
    private Integer px;

    /**
    * 描述
    */
    private String ms;

    /**
    * 备注
    */
    private String bz;

    /**
    * 创建人
    */
    private String createBy;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 更新人
    */
    private String updateBy;

    /**
    * 更新时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
    * 创建部门
    */
    private String sysOrgCode;

    /**
    * 街道代码
    */
    private String jddm;

    /**
    * 状态
    */
    private String zt;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getQx() {
    return qx;
    }

    public void setQx(String qx) {
    this.qx = qx;
    }

    public String getJddz() {
    return jddz;
    }

    public void setJddz(String jddz) {
    this.jddz = jddz;
    }

    public Integer getPx() {
    return px;
    }

    public void setPx(Integer px) {
    this.px = px;
    }

    public String getMs() {
    return ms;
    }

    public void setMs(String ms) {
    this.ms = ms;
    }

    public String getBz() {
    return bz;
    }

    public void setBz(String bz) {
    this.bz = bz;
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

    public String getJddm() {
    return jddm;
    }

    public void setJddm(String jddm) {
    this.jddm = jddm;
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
    sb.append(", qx=").append(qx);
    sb.append(", jddz=").append(jddz);
    sb.append(", px=").append(px);
    sb.append(", ms=").append(ms);
    sb.append(", bz=").append(bz);
    sb.append(", createBy=").append(createBy);
    sb.append(", createTime=").append(createTime);
    sb.append(", updateBy=").append(updateBy);
    sb.append(", updateTime=").append(updateTime);
    sb.append(", sysOrgCode=").append(sysOrgCode);
    sb.append(", jddm=").append(jddm);
    sb.append(", zt=").append(zt);
sb.append("]");
return sb.toString();
}

}
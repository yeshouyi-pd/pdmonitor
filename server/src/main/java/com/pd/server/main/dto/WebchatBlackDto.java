package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WebchatBlackDto extends PageDto{

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
    * 姓名
    */
    private String xm;

    /**
    * 身份证明号码
    */
    private String sfzmhm;

    /**
    * 原因
    */
    private String yy;

    /**
    * 原因说明
    */
    private String yysm;

    /**
    * 开始时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date kssj;

    /**
    * 结束时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date jssj;

    /**
    * 状态
    */
    private String zt;

    /**
    * 微信用户id
    */
    private String openid;

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

    public String getYy() {
    return yy;
    }

    public void setYy(String yy) {
    this.yy = yy;
    }

    public String getYysm() {
    return yysm;
    }

    public void setYysm(String yysm) {
    this.yysm = yysm;
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
    sb.append(", sfzmhm=").append(sfzmhm);
    sb.append(", yy=").append(yy);
    sb.append(", yysm=").append(yysm);
    sb.append(", kssj=").append(kssj);
    sb.append(", jssj=").append(jssj);
    sb.append(", zt=").append(zt);
    sb.append(", openid=").append(openid);
    sb.append(", bz=").append(bz);
sb.append("]");
return sb.toString();
}

}
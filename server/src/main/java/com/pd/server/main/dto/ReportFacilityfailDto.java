package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class ReportFacilityfailDto extends PageDto{

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
    * 故障地点
    */
    private String gzdd;

    /**
    * 故障类型
    */
    private String gzlx;

    /**
    * 故障坐标
    */
    private String gzgps;

    /**
    * 详细地址
    */
    private String gzxxdz;

    /**
    * 故障描述
    */
    private String gzms;

    /**
    * 上报人
    */
    private String sbr;

    /**
    * 手机号
    */
    private String sbrsjh;

    /**
    * 身份证号码
    */
    private String sfzmhm;

    /**
    * 审核人
    */
    private String shr;

    /**
    * 审核时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date shsj;

    /**
    * 状态
    */
    private String zt;

    /**
    * 备注
    */
    private String bz;

    /**
    * 处理意见
    */
    private String clyj;

    /**
    * 流水号
    */
    private String lsh;

    /**
    * 微信用户id
    */
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
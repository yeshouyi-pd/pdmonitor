package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class ReportWaterDto extends PageDto{

    /**
    * 主键
    */
    private String id;

    /**
    * 上报人身份证号码
    */
    private String sfzmhm;

    /**
    * 上报时间
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
    * 处理部门
    */
    private String sysOrgCode;

    /**
    * 渍水地点
    */
    private String zsdd;

    /**
    * 渍水坐标
    */
    private String gps;

    /**
    * 渍水详细地址
    */
    private String zsddxx;

    /**
    * 上报人
    */
    private String sbr;

    /**
    * 上报人手机号
    */
    private String sbrsjh;

    /**
    * 上报说明
    */
    private String sbsm;

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
    * 处理反馈
    */
    private String clyj;

    /**
    * 状态
    */
    private String zt;

    /**
    * 备注
    */
    private String bz;

    /**
    * 微信用户openid
    */
    private String openid;

    /**
    * 流水号
    */
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
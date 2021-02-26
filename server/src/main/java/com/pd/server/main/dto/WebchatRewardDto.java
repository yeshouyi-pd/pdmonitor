package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WebchatRewardDto extends PageDto{

    /**
    * id
    */
    private String id;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String createTime;

    /**
    * 创建人
    */
    private String createBy;

    /**
    * 更新时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String updateTime;

    /**
    * 更新人
    */
    private String updateBy;

    /**
    * 所属部门
    */
    private String sysOrgCode;

    /**
    * 违法行为详细
    */
    private String wfxwxx;

    /**
    * 身份证明号码
    */
    private String sfzmhm;

    /**
    * 手机号
    */
    private String sjh;

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
    * 审核原因
    */
    private String shyy;

    /**
    * 审核说明
    */
    private String shsm;

    /**
    * 状态|2待审核4审核通过5审核不通过
    */
    private String zt;

    /**
    * 用户openid
    */
    private String openid;

    /**
    * 流水号
    */
    private String lsh;

    /**
    * 奖励发放状态|0未发放1已发放
    */
    private String ffzt;

    /**
    * 车牌类型
    */
    private String cplx;

    /**
    * 车牌号码
    */
    private String cphm;

    /**
    * 发放时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ffsj;

    /**
    * 发放人
    */
    private String ffr;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getCreateTime() {
    return createTime;
    }

    public void setCreateTime(String createTime) {
    this.createTime = createTime;
    }

    public String getCreateBy() {
    return createBy;
    }

    public void setCreateBy(String createBy) {
    this.createBy = createBy;
    }

    public String getUpdateTime() {
    return updateTime;
    }

    public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
    }

    public String getUpdateBy() {
    return updateBy;
    }

    public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
    }

    public String getSysOrgCode() {
    return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
    this.sysOrgCode = sysOrgCode;
    }

    public String getWfxwxx() {
    return wfxwxx;
    }

    public void setWfxwxx(String wfxwxx) {
    this.wfxwxx = wfxwxx;
    }

    public String getSfzmhm() {
    return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
    this.sfzmhm = sfzmhm;
    }

    public String getSjh() {
    return sjh;
    }

    public void setSjh(String sjh) {
    this.sjh = sjh;
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

    public String getShyy() {
    return shyy;
    }

    public void setShyy(String shyy) {
    this.shyy = shyy;
    }

    public String getShsm() {
    return shsm;
    }

    public void setShsm(String shsm) {
    this.shsm = shsm;
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

    public String getLsh() {
    return lsh;
    }

    public void setLsh(String lsh) {
    this.lsh = lsh;
    }

    public String getFfzt() {
    return ffzt;
    }

    public void setFfzt(String ffzt) {
    this.ffzt = ffzt;
    }

    public String getCplx() {
    return cplx;
    }

    public void setCplx(String cplx) {
    this.cplx = cplx;
    }

    public String getCphm() {
    return cphm;
    }

    public void setCphm(String cphm) {
    this.cphm = cphm;
    }

    public Date getFfsj() {
    return ffsj;
    }

    public void setFfsj(Date ffsj) {
    this.ffsj = ffsj;
    }

    public String getFfr() {
    return ffr;
    }

    public void setFfr(String ffr) {
    this.ffr = ffr;
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", createTime=").append(createTime);
    sb.append(", createBy=").append(createBy);
    sb.append(", updateTime=").append(updateTime);
    sb.append(", updateBy=").append(updateBy);
    sb.append(", sysOrgCode=").append(sysOrgCode);
    sb.append(", wfxwxx=").append(wfxwxx);
    sb.append(", sfzmhm=").append(sfzmhm);
    sb.append(", sjh=").append(sjh);
    sb.append(", shr=").append(shr);
    sb.append(", shsj=").append(shsj);
    sb.append(", shyy=").append(shyy);
    sb.append(", shsm=").append(shsm);
    sb.append(", zt=").append(zt);
    sb.append(", openid=").append(openid);
    sb.append(", lsh=").append(lsh);
    sb.append(", ffzt=").append(ffzt);
    sb.append(", cplx=").append(cplx);
    sb.append(", cphm=").append(cphm);
    sb.append(", ffsj=").append(ffsj);
    sb.append(", ffr=").append(ffr);
sb.append("]");
return sb.toString();
}

}
package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WebchatIllegalSaveDto extends PageDto{

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
    * 违法行为描述
    */
    private String wfxwms;

    /**
    * 违法类别(codeset-0621)
    */
    private String wflb;

    /**
    * 违法行为
    */
    private String wfxw;

    /**
    * 是否车载照片|1是0不是
    */
    private String czzp;

    /**
    * 违法时间
    */
    private String wfsj;

    /**
    * 违法路段
    */
    private String wfld;

    /**
    * 坐标信息
    */
    private String wfgps;

    /**
    * 详细地址
    */
    private String wfldxxdz;

    /**
    * 车牌类型
    */
    private String cplx;

    /**
    * 车牌号码
    */
    private String cphm;

    /**
    * 举报人
    */
    private String jbr;

    /**
    * 身份证明号码
    */
    private String sfzmhm;

    /**
    * 手机号
    */
    private String sjh;

    /**
    * 备注
    */
    private String bz;

    /**
    * 审核人
    */
    private String shr;

    /**
    * 审核原因
    */
    private String shyy;

    /**
    * 审核时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date shsj;

    /**
    * 审核说明
    */
    private String shsm;

    /**
    * 状态1已上报2审核通过3审核不通过
    */
    private String zt;

    /**
    * 微信用户id
    */
    private String openid;

    /**
    * 流水号
    */
    private String lsh;

    /**
    * 奖励发放0未发放1已发放
    */
    private String jlff;

    /**
    * 违法行为详细|(codeset-0621)
    */
    private String wfxwxx;

    private String isChecked;

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

    public String getWfxwms() {
    return wfxwms;
    }

    public void setWfxwms(String wfxwms) {
    this.wfxwms = wfxwms;
    }

    public String getWflb() {
    return wflb;
    }

    public void setWflb(String wflb) {
    this.wflb = wflb;
    }

    public String getWfxw() {
    return wfxw;
    }

    public void setWfxw(String wfxw) {
    this.wfxw = wfxw;
    }

    public String getCzzp() {
    return czzp;
    }

    public void setCzzp(String czzp) {
    this.czzp = czzp;
    }

    public String getWfsj() {
    return wfsj;
    }

    public void setWfsj(String wfsj) {
    this.wfsj = wfsj;
    }

    public String getWfld() {
    return wfld;
    }

    public void setWfld(String wfld) {
    this.wfld = wfld;
    }

    public String getWfgps() {
    return wfgps;
    }

    public void setWfgps(String wfgps) {
    this.wfgps = wfgps;
    }

    public String getWfldxxdz() {
    return wfldxxdz;
    }

    public void setWfldxxdz(String wfldxxdz) {
    this.wfldxxdz = wfldxxdz;
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

    public String getJbr() {
    return jbr;
    }

    public void setJbr(String jbr) {
    this.jbr = jbr;
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

    public String getBz() {
    return bz;
    }

    public void setBz(String bz) {
    this.bz = bz;
    }

    public String getShr() {
    return shr;
    }

    public void setShr(String shr) {
    this.shr = shr;
    }

    public String getShyy() {
    return shyy;
    }

    public void setShyy(String shyy) {
    this.shyy = shyy;
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

    public String getJlff() {
    return jlff;
    }

    public void setJlff(String jlff) {
    this.jlff = jlff;
    }

    public String getWfxwxx() {
    return wfxwxx;
    }

    public void setWfxwxx(String wfxwxx) {
    this.wfxwxx = wfxwxx;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
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
    sb.append(", wfxwms=").append(wfxwms);
    sb.append(", wflb=").append(wflb);
    sb.append(", wfxw=").append(wfxw);
    sb.append(", czzp=").append(czzp);
    sb.append(", wfsj=").append(wfsj);
    sb.append(", wfld=").append(wfld);
    sb.append(", wfgps=").append(wfgps);
    sb.append(", wfldxxdz=").append(wfldxxdz);
    sb.append(", cplx=").append(cplx);
    sb.append(", cphm=").append(cphm);
    sb.append(", jbr=").append(jbr);
    sb.append(", sfzmhm=").append(sfzmhm);
    sb.append(", sjh=").append(sjh);
    sb.append(", bz=").append(bz);
    sb.append(", shr=").append(shr);
    sb.append(", shyy=").append(shyy);
    sb.append(", shsj=").append(shsj);
    sb.append(", shsm=").append(shsm);
    sb.append(", zt=").append(zt);
    sb.append(", openid=").append(openid);
    sb.append(", lsh=").append(lsh);
    sb.append(", jlff=").append(jlff);
    sb.append(", wfxwxx=").append(wfxwxx);
    sb.append(", isChecked=").append(isChecked);
sb.append("]");
return sb.toString();
}

}
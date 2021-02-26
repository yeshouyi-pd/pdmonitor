package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class VehInsuranceinfoDto extends PageDto{

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
    * 保险公司代码
    */
    private String sysOrgCode;

    /**
    * 号牌种类
    */
    private String hpzl;

    /**
    * 号牌号码
    */
    private String hphm;

    /**
    * 车辆识别代号
    */
    private String clsbdh;

    /**
    * 保险凭证号码
    */
    private String bxpzh;

    /**
    * 保险金额
    */
    private String bxje;

    /**
    * 生效日期
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date sxrq;

    /**
    * 终止日期
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date zzrq;

    /**
    * 被保险人名称
    */
    private String bbxr;

    /**
    * 被保险人身份证明号码
    */
    private String bbxrsfzmhm;

    /**
    * 交强险投标状态|1-确认（处于投标确认状态）2-一般投保退保3-重复投保退保4-起保前退保
    */
    private String jqxtbzt;

    /**
    * 车船税纳税情况
    */
    private String ccsnsqk;

    /**
    * 车船税纳税金额
    */
    private String ccsnsje;

    /**
    * 车船税完税/免税凭证号码
    */
    private String wspzhm;

    /**
    * 车船税纳税人识别号
    */
    private String nsrsbh;

    /**
    * 车船税纳税日期
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date ccsnsrq;

    /**
    * 车船税纳税税所属时期起始日期
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date ccsnsqsrq;

    /**
    * 车船税纳税税所属时期终止日期
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date ccsnszzrq;

    /**
    * 流水号
    */
    private String lsh;

    /**
    * 备注
    */
    private String bz;

    /**
    * 状态
    */
    private String zt;

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
    * 审核说明
    */
    private String shsm;

    /**
    * 审核原因
    */
    private String shyy;

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

    public String getHpzl() {
    return hpzl;
    }

    public void setHpzl(String hpzl) {
    this.hpzl = hpzl;
    }

    public String getHphm() {
    return hphm;
    }

    public void setHphm(String hphm) {
    this.hphm = hphm;
    }

    public String getClsbdh() {
    return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
    this.clsbdh = clsbdh;
    }

    public String getBxpzh() {
    return bxpzh;
    }

    public void setBxpzh(String bxpzh) {
    this.bxpzh = bxpzh;
    }

    public String getBxje() {
    return bxje;
    }

    public void setBxje(String bxje) {
    this.bxje = bxje;
    }

    public Date getSxrq() {
    return sxrq;
    }

    public void setSxrq(Date sxrq) {
    this.sxrq = sxrq;
    }

    public Date getZzrq() {
    return zzrq;
    }

    public void setZzrq(Date zzrq) {
    this.zzrq = zzrq;
    }

    public String getBbxr() {
    return bbxr;
    }

    public void setBbxr(String bbxr) {
    this.bbxr = bbxr;
    }

    public String getBbxrsfzmhm() {
    return bbxrsfzmhm;
    }

    public void setBbxrsfzmhm(String bbxrsfzmhm) {
    this.bbxrsfzmhm = bbxrsfzmhm;
    }

    public String getJqxtbzt() {
    return jqxtbzt;
    }

    public void setJqxtbzt(String jqxtbzt) {
    this.jqxtbzt = jqxtbzt;
    }

    public String getCcsnsqk() {
    return ccsnsqk;
    }

    public void setCcsnsqk(String ccsnsqk) {
    this.ccsnsqk = ccsnsqk;
    }

    public String getCcsnsje() {
    return ccsnsje;
    }

    public void setCcsnsje(String ccsnsje) {
    this.ccsnsje = ccsnsje;
    }

    public String getWspzhm() {
    return wspzhm;
    }

    public void setWspzhm(String wspzhm) {
    this.wspzhm = wspzhm;
    }

    public String getNsrsbh() {
    return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
    this.nsrsbh = nsrsbh;
    }

    public Date getCcsnsrq() {
    return ccsnsrq;
    }

    public void setCcsnsrq(Date ccsnsrq) {
    this.ccsnsrq = ccsnsrq;
    }

    public Date getCcsnsqsrq() {
    return ccsnsqsrq;
    }

    public void setCcsnsqsrq(Date ccsnsqsrq) {
    this.ccsnsqsrq = ccsnsqsrq;
    }

    public Date getCcsnszzrq() {
    return ccsnszzrq;
    }

    public void setCcsnszzrq(Date ccsnszzrq) {
    this.ccsnszzrq = ccsnszzrq;
    }

    public String getLsh() {
    return lsh;
    }

    public void setLsh(String lsh) {
    this.lsh = lsh;
    }

    public String getBz() {
    return bz;
    }

    public void setBz(String bz) {
    this.bz = bz;
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

    public String getShsm() {
    return shsm;
    }

    public void setShsm(String shsm) {
    this.shsm = shsm;
    }

    public String getShyy() {
    return shyy;
    }

    public void setShyy(String shyy) {
    this.shyy = shyy;
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
    sb.append(", hpzl=").append(hpzl);
    sb.append(", hphm=").append(hphm);
    sb.append(", clsbdh=").append(clsbdh);
    sb.append(", bxpzh=").append(bxpzh);
    sb.append(", bxje=").append(bxje);
    sb.append(", sxrq=").append(sxrq);
    sb.append(", zzrq=").append(zzrq);
    sb.append(", bbxr=").append(bbxr);
    sb.append(", bbxrsfzmhm=").append(bbxrsfzmhm);
    sb.append(", jqxtbzt=").append(jqxtbzt);
    sb.append(", ccsnsqk=").append(ccsnsqk);
    sb.append(", ccsnsje=").append(ccsnsje);
    sb.append(", wspzhm=").append(wspzhm);
    sb.append(", nsrsbh=").append(nsrsbh);
    sb.append(", ccsnsrq=").append(ccsnsrq);
    sb.append(", ccsnsqsrq=").append(ccsnsqsrq);
    sb.append(", ccsnszzrq=").append(ccsnszzrq);
    sb.append(", lsh=").append(lsh);
    sb.append(", bz=").append(bz);
    sb.append(", zt=").append(zt);
    sb.append(", shr=").append(shr);
    sb.append(", shsj=").append(shsj);
    sb.append(", shsm=").append(shsm);
    sb.append(", shyy=").append(shyy);
sb.append("]");
return sb.toString();
}

}
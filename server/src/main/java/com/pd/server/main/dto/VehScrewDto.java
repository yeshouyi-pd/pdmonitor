package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class VehScrewDto extends PageDto{

    private Integer sumsl;

    private Integer xssl;

    private Integer xxsl;
    /**
    * 主键
    */
    private String id;

    /**
    * 申请人
    */
    private String createBy;

    /**
    * 申请时间
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
    * 申领网点
    */
    private String sysOrgCode;

    /**
    * 身份证明号码
    */
    private String sfzmhm;

    /**
    * 号牌号码
    */
    private String hphm;

    /**
    * 申请原因
    */
    private String sqyy;

    /**
    * 申领数量
    */
    private String slsl;

    /**
    * 收件人
    */
    private String sjr;

    /**
    * 联系电话
    */
    private String lxdh;

    /**
    * 省份
    */
    private String sf;

    /**
    * 城市
    */
    private String cs;

    /**
    * 区县
    */
    private String qx;

    /**
    * 街道地址
    */
    private String jddz;

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
    * 备注
    */
    private String bz;

    /**
    * 微信用户id
    */
    private String openid;

    /**
    * 审核原因说明
    */
    private String shyy;

    /**
    * 业务名称
    */
    private String ywmc;

    /**
    * 流水号
    */
    private String lsh;

    /**
    * 是否本人车辆1是0否
    */
    private String sfbr;

    /**
    * 申领类别1线上申领2线下申领
    */
    private String sllb;

    /**
     * 单号
     */
    private String dh;

    /**
     * 地区
     */
    private String dq;

    public Integer getSumsl() {
        return sumsl;
    }

    public void setSumsl(Integer sumsl) {
        this.sumsl = sumsl;
    }

    public Integer getXssl() {
        return xssl;
    }

    public void setXssl(Integer xssl) {
        this.xssl = xssl;
    }

    public Integer getXxsl() {
        return xxsl;
    }

    public void setXxsl(Integer xxsl) {
        this.xxsl = xxsl;
    }

    public String getDq() {
        return dq;
    }

    public void setDq(String dq) {
        this.dq = dq;
    }

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

    public String getSfzmhm() {
    return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
    this.sfzmhm = sfzmhm;
    }

    public String getHphm() {
    return hphm;
    }

    public void setHphm(String hphm) {
    this.hphm = hphm;
    }

    public String getSqyy() {
    return sqyy;
    }

    public void setSqyy(String sqyy) {
    this.sqyy = sqyy;
    }

    public String getSlsl() {
    return slsl;
    }

    public void setSlsl(String slsl) {
    this.slsl = slsl;
    }

    public String getSjr() {
    return sjr;
    }

    public void setSjr(String sjr) {
    this.sjr = sjr;
    }

    public String getLxdh() {
    return lxdh;
    }

    public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
    }

    public String getSf() {
    return sf;
    }

    public void setSf(String sf) {
    this.sf = sf;
    }

    public String getCs() {
    return cs;
    }

    public void setCs(String cs) {
    this.cs = cs;
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

    public String getShyy() {
    return shyy;
    }

    public void setShyy(String shyy) {
    this.shyy = shyy;
    }

    public String getYwmc() {
    return ywmc;
    }

    public void setYwmc(String ywmc) {
    this.ywmc = ywmc;
    }

    public String getLsh() {
    return lsh;
    }

    public void setLsh(String lsh) {
    this.lsh = lsh;
    }

    public String getSfbr() {
    return sfbr;
    }

    public void setSfbr(String sfbr) {
    this.sfbr = sfbr;
    }

    public String getSllb() {
    return sllb;
    }

    public void setSllb(String sllb) {
    this.sllb = sllb;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
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
    sb.append(", sfzmhm=").append(sfzmhm);
    sb.append(", hphm=").append(hphm);
    sb.append(", sqyy=").append(sqyy);
    sb.append(", slsl=").append(slsl);
    sb.append(", sjr=").append(sjr);
    sb.append(", lxdh=").append(lxdh);
    sb.append(", sf=").append(sf);
    sb.append(", cs=").append(cs);
    sb.append(", qx=").append(qx);
    sb.append(", jddz=").append(jddz);
    sb.append(", zt=").append(zt);
    sb.append(", shr=").append(shr);
    sb.append(", shsj=").append(shsj);
    sb.append(", bz=").append(bz);
    sb.append(", openid=").append(openid);
    sb.append(", shyy=").append(shyy);
    sb.append(", ywmc=").append(ywmc);
    sb.append(", lsh=").append(lsh);
    sb.append(", sfbr=").append(sfbr);
    sb.append(", sllb=").append(sllb);
sb.append("]");
return sb.toString();
}

}
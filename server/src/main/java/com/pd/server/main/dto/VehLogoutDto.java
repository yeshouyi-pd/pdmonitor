package com.pd.server.main.dto;

        import java.util.Date;
        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonFormat;

public class VehLogoutDto extends PageDto{

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
    * 机动车序号
    */
    private String xh;

    /**
    * 号牌种类
    */
    private String hpzl;

    /**
    * 号牌号码
    */
    private String hphm;

    /**
    * 注销原因
    */
    private String zxyy;

    /**
    * 回收企业名称
    */
    private String hsqymc;

    /**
    * 注销日期
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date zxrq;

    /**
    * 回收号牌
    */
    private String hshp;

    /**
    * 回收行驶证
    */
    private String hsxsz;

    /**
    * 回收登记证书
    */
    private String hsdjzs;

    /**
    * 处罚机关
    */
    private String cfjg;

    /**
    * 处罚决定书编号
    */
    private String jdsbh;

    /**
    * 处罚时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cfsj;

    /**
    * 监销民警
    */
    private String jxmj;

    /**
    * 注销机关
    */
    private String zxjg;

    /**
    * 经办人
    */
    private String jbr;

    /**
    * 管理部门（审核部门）
    */
    private String glbm;

    /**
    * 备注
    */
    private String bz;

    /**
    * 车辆品牌
    */
    private String clpp1;

    /**
    * 车辆型号
    */
    private String clxh;

    /**
    * 机动车所有人
    */
    private String syr;

    /**
    * 车辆识别代号
    */
    private String clsbdh;

    /**
    * 报废日期
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date bfrq;

    /**
    * 流水号
    */
    private String lsh;

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
    * 状态
    */
    private String zt;

    private List<String> tpListStr;

    private String videoLj;

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

    public String getXh() {
    return xh;
    }

    public void setXh(String xh) {
    this.xh = xh;
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

    public String getZxyy() {
    return zxyy;
    }

    public void setZxyy(String zxyy) {
    this.zxyy = zxyy;
    }

    public String getHsqymc() {
    return hsqymc;
    }

    public void setHsqymc(String hsqymc) {
    this.hsqymc = hsqymc;
    }

    public Date getZxrq() {
    return zxrq;
    }

    public void setZxrq(Date zxrq) {
    this.zxrq = zxrq;
    }

    public String getHshp() {
    return hshp;
    }

    public void setHshp(String hshp) {
    this.hshp = hshp;
    }

    public String getHsxsz() {
    return hsxsz;
    }

    public void setHsxsz(String hsxsz) {
    this.hsxsz = hsxsz;
    }

    public String getHsdjzs() {
    return hsdjzs;
    }

    public void setHsdjzs(String hsdjzs) {
    this.hsdjzs = hsdjzs;
    }

    public String getCfjg() {
    return cfjg;
    }

    public void setCfjg(String cfjg) {
    this.cfjg = cfjg;
    }

    public String getJdsbh() {
    return jdsbh;
    }

    public void setJdsbh(String jdsbh) {
    this.jdsbh = jdsbh;
    }

    public Date getCfsj() {
    return cfsj;
    }

    public void setCfsj(Date cfsj) {
    this.cfsj = cfsj;
    }

    public String getJxmj() {
    return jxmj;
    }

    public void setJxmj(String jxmj) {
    this.jxmj = jxmj;
    }

    public String getZxjg() {
    return zxjg;
    }

    public void setZxjg(String zxjg) {
    this.zxjg = zxjg;
    }

    public String getJbr() {
    return jbr;
    }

    public void setJbr(String jbr) {
    this.jbr = jbr;
    }

    public String getGlbm() {
    return glbm;
    }

    public void setGlbm(String glbm) {
    this.glbm = glbm;
    }

    public String getBz() {
    return bz;
    }

    public void setBz(String bz) {
    this.bz = bz;
    }

    public String getClpp1() {
    return clpp1;
    }

    public void setClpp1(String clpp1) {
    this.clpp1 = clpp1;
    }

    public String getClxh() {
    return clxh;
    }

    public void setClxh(String clxh) {
    this.clxh = clxh;
    }

    public String getSyr() {
    return syr;
    }

    public void setSyr(String syr) {
    this.syr = syr;
    }

    public String getClsbdh() {
    return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
    this.clsbdh = clsbdh;
    }

    public Date getBfrq() {
    return bfrq;
    }

    public void setBfrq(Date bfrq) {
    this.bfrq = bfrq;
    }

    public String getLsh() {
    return lsh;
    }

    public void setLsh(String lsh) {
    this.lsh = lsh;
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

    public List<String> getTpListStr() {
        return tpListStr;
    }

    public void setTpListStr(List<String> tpListStr) {
        this.tpListStr = tpListStr;
    }

    public String getVideoLj() {
        return videoLj;
    }

    public void setVideoLj(String videoLj) {
        this.videoLj = videoLj;
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
    sb.append(", xh=").append(xh);
    sb.append(", hpzl=").append(hpzl);
    sb.append(", hphm=").append(hphm);
    sb.append(", zxyy=").append(zxyy);
    sb.append(", hsqymc=").append(hsqymc);
    sb.append(", zxrq=").append(zxrq);
    sb.append(", hshp=").append(hshp);
    sb.append(", hsxsz=").append(hsxsz);
    sb.append(", hsdjzs=").append(hsdjzs);
    sb.append(", cfjg=").append(cfjg);
    sb.append(", jdsbh=").append(jdsbh);
    sb.append(", cfsj=").append(cfsj);
    sb.append(", jxmj=").append(jxmj);
    sb.append(", zxjg=").append(zxjg);
    sb.append(", jbr=").append(jbr);
    sb.append(", glbm=").append(glbm);
    sb.append(", bz=").append(bz);
    sb.append(", clpp1=").append(clpp1);
    sb.append(", clxh=").append(clxh);
    sb.append(", syr=").append(syr);
    sb.append(", clsbdh=").append(clsbdh);
    sb.append(", bfrq=").append(bfrq);
    sb.append(", lsh=").append(lsh);
    sb.append(", shr=").append(shr);
    sb.append(", shsj=").append(shsj);
    sb.append(", shyy=").append(shyy);
    sb.append(", shsm=").append(shsm);
    sb.append(", zt=").append(zt);
sb.append("]");
return sb.toString();
}

}
package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class VehRobberyDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 创建人
    */
    private String createBy;

    /**
    * 报案日期
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
    * 报案人
    */
    private String xm;

    /**
    * 身份证明号码
    */
    private String sfzmhm;

    /**
    * 联系电话
    */
    private String lxdh;

    /**
    * 家庭住址
    */
    private String jtzz;

    /**
    * 被套车牌
    */
    private String hphm;

    /**
    * 被套车品牌
    */
    private String clpp;

    /**
    * 车辆被套牌证据
    */
    private String zj;

    /**
    * 情况说明
    */
    private String qksm;

    /**
    * 备注
    */
    private String bz;

    /**
    * 状态
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
    * 车辆类别1个人车辆2单位车辆
    */
    private String lb;

    /**
    * 号牌种类
    */
    private String hpzl;

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

    public String getLxdh() {
    return lxdh;
    }

    public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
    }

    public String getJtzz() {
    return jtzz;
    }

    public void setJtzz(String jtzz) {
    this.jtzz = jtzz;
    }

    public String getHphm() {
    return hphm;
    }

    public void setHphm(String hphm) {
    this.hphm = hphm;
    }

    public String getClpp() {
    return clpp;
    }

    public void setClpp(String clpp) {
    this.clpp = clpp;
    }

    public String getZj() {
    return zj;
    }

    public void setZj(String zj) {
    this.zj = zj;
    }

    public String getQksm() {
    return qksm;
    }

    public void setQksm(String qksm) {
    this.qksm = qksm;
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

    public String getLb() {
    return lb;
    }

    public void setLb(String lb) {
    this.lb = lb;
    }

    public String getHpzl() {
    return hpzl;
    }

    public void setHpzl(String hpzl) {
    this.hpzl = hpzl;
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
    sb.append(", lxdh=").append(lxdh);
    sb.append(", jtzz=").append(jtzz);
    sb.append(", hphm=").append(hphm);
    sb.append(", clpp=").append(clpp);
    sb.append(", zj=").append(zj);
    sb.append(", qksm=").append(qksm);
    sb.append(", bz=").append(bz);
    sb.append(", zt=").append(zt);
    sb.append(", openid=").append(openid);
    sb.append(", lsh=").append(lsh);
    sb.append(", shr=").append(shr);
    sb.append(", shsj=").append(shsj);
    sb.append(", shsm=").append(shsm);
    sb.append(", lb=").append(lb);
    sb.append(", hpzl=").append(hpzl);
sb.append("]");
return sb.toString();
}

}
package com.pd.server.main.dto;

        import java.util.Date;
        import java.util.List;
        import java.util.Map;

        import com.fasterxml.jackson.annotation.JsonFormat;

public class VehLicelostDto extends  PageDto{

    /**
    * 主键
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
    * 号牌种类
    */
    private String hpzl;

    /**
    * 号牌号码
    */
    private String hphm;

    /**
    * 领取地址
    */
    private String lqdz;

    /**
    * 领取电话
    */
    private String lqdh;

    /**
    * 领取人姓名
    */
    private String lqrxm;

    /**
    * 领取人电话
    */
    private String lqrdh;

    /**
    * 领取日期
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lqrq;

    /**
    * 身份证明号码
    */
    private String sfzmhm;

    /**
    * 领取说明
    */
    private String lqsm;

    /**
    * 状态
    */
    private String zt;

    /**
    * 微信用户openid
    */
    private String openid;

    /**
    * 备注
    */
    private String bz;

    /**
    * 流水号
    */
    private String lsh;

    /**
     * 批量车牌信息
     */
    private List<Map<String, Object>> cpInfos;

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

    public String getLqdz() {
    return lqdz;
    }

    public void setLqdz(String lqdz) {
    this.lqdz = lqdz;
    }

    public String getLqdh() {
    return lqdh;
    }

    public void setLqdh(String lqdh) {
    this.lqdh = lqdh;
    }

    public String getLqrxm() {
    return lqrxm;
    }

    public void setLqrxm(String lqrxm) {
    this.lqrxm = lqrxm;
    }

    public String getLqrdh() {
    return lqrdh;
    }

    public void setLqrdh(String lqrdh) {
    this.lqrdh = lqrdh;
    }

    public Date getLqrq() {
    return lqrq;
    }

    public void setLqrq(Date lqrq) {
    this.lqrq = lqrq;
    }

    public String getSfzmhm() {
    return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
    this.sfzmhm = sfzmhm;
    }

    public String getLqsm() {
    return lqsm;
    }

    public void setLqsm(String lqsm) {
    this.lqsm = lqsm;
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

    public String getLsh() {
    return lsh;
    }

    public void setLsh(String lsh) {
    this.lsh = lsh;
    }

    public List<Map<String, Object>> getCpInfos() {
        return cpInfos;
    }

    public void setCpInfos(List<Map<String, Object>> cpInfos) {
        this.cpInfos = cpInfos;
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
    sb.append(", lqdz=").append(lqdz);
    sb.append(", lqdh=").append(lqdh);
    sb.append(", lqrxm=").append(lqrxm);
    sb.append(", lqrdh=").append(lqrdh);
    sb.append(", lqrq=").append(lqrq);
    sb.append(", sfzmhm=").append(sfzmhm);
    sb.append(", lqsm=").append(lqsm);
    sb.append(", zt=").append(zt);
    sb.append(", openid=").append(openid);
    sb.append(", bz=").append(bz);
    sb.append(", lsh=").append(lsh);
sb.append("]");
return sb.toString();
}

}
package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class DrvQxksDto extends PageDto{

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
    * 姓名
    */
    private String xm;

    /**
    * 证件类型
    */
    private String zjlx;

    /**
    * 身份证明号码
    */
    private String sfzmhm;

    /**
    * 手机号码
    */
    private String sjhm;

    /**
    * 取消考试科目
    */
    private String qxkm;

    /**
    * 预约考试时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date yykssj;

    private String yykssjstr;

    /**
    * 申请说明
    */
    private String sqsm;

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
    * 备注
    */
    private String bz;

    /**
    * 微信用户id
    */
    private String openid;

    /**
    * 流水号
    */
    private String lsh;

    /**
    * 状态
    */
    private String zt;


    public String getYykssjstr() {
        return yykssjstr;
    }

    public void setYykssjstr(String yykssjstr) {
        this.yykssjstr = yykssjstr;
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

    public String getXm() {
    return xm;
    }

    public void setXm(String xm) {
    this.xm = xm;
    }

    public String getZjlx() {
    return zjlx;
    }

    public void setZjlx(String zjlx) {
    this.zjlx = zjlx;
    }

    public String getSfzmhm() {
    return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
    this.sfzmhm = sfzmhm;
    }

    public String getSjhm() {
    return sjhm;
    }

    public void setSjhm(String sjhm) {
    this.sjhm = sjhm;
    }

    public String getQxkm() {
    return qxkm;
    }

    public void setQxkm(String qxkm) {
    this.qxkm = qxkm;
    }

    public Date getYykssj() {
    return yykssj;
    }

    public void setYykssj(Date yykssj) {
    this.yykssj = yykssj;
    }

    public String getSqsm() {
    return sqsm;
    }

    public void setSqsm(String sqsm) {
    this.sqsm = sqsm;
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

    public String getZt() {
    return zt;
    }

    public void setZt(String zt) {
    this.zt = zt;
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
    sb.append(", zjlx=").append(zjlx);
    sb.append(", sfzmhm=").append(sfzmhm);
    sb.append(", sjhm=").append(sjhm);
    sb.append(", qxkm=").append(qxkm);
    sb.append(", yykssj=").append(yykssj);
    sb.append(", sqsm=").append(sqsm);
    sb.append(", shr=").append(shr);
    sb.append(", shsj=").append(shsj);
    sb.append(", shsm=").append(shsm);
    sb.append(", bz=").append(bz);
    sb.append(", openid=").append(openid);
    sb.append(", lsh=").append(lsh);
    sb.append(", zt=").append(zt);
sb.append("]");
return sb.toString();
}

}
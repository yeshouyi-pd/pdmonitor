package com.pd.server.main.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VehPassDto extends PageDto{


    private List<String> checktxxl;
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
     * 处理部门
     */
    private String sysOrgCode;

    /**
     * 个人/单位
     */
    private String sqr;

    /**
     * 通行证类型
     */
    private String txzlx;

    /**
     * 出发地
     */
    private String cfd;

    /**
     * 目的地
     */
    private String mdd;

    /**
     * 有效期开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date yxqkssj;

    /**
     * 有效期结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date yxqjssj;

    /**
     * 号牌种类
     */
    private String hpzl;

    /**
     * 号牌号码
     */
    private String hphm;

    /**
     * 所有人
     */
    private String syr;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 申请说明
     */
    private String sqsm;

    /**
     * 通行线路
     */
    private String txxl;

    /**
     *
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
     * 微信用户id
     */
    private String openid;

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 备注
     */
    private String bz;

    /**
     * 审批有效期开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date shyxqkssj;

    /**
     * 审批有效期结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date shyxqjssj;

    /**
     *
     */
    private String txzbh;

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

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getTxzlx() {
        return txzlx;
    }

    public void setTxzlx(String txzlx) {
        this.txzlx = txzlx;
    }

    public String getCfd() {
        return cfd;
    }

    public void setCfd(String cfd) {
        this.cfd = cfd;
    }

    public String getMdd() {
        return mdd;
    }

    public void setMdd(String mdd) {
        this.mdd = mdd;
    }

    public Date getYxqkssj() {
        return yxqkssj;
    }

    public void setYxqkssj(Date yxqkssj) {
        this.yxqkssj = yxqkssj;
    }

    public Date getYxqjssj() {
        return yxqjssj;
    }

    public void setYxqjssj(Date yxqjssj) {
        this.yxqjssj = yxqjssj;
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

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getSqsm() {
        return sqsm;
    }

    public void setSqsm(String sqsm) {
        this.sqsm = sqsm;
    }

    public String getTxxl() {
        return txxl;
    }

    public void setTxxl(String txxl) {
        this.txxl = txxl;
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

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Date getShyxqkssj() {
        return shyxqkssj;
    }

    public void setShyxqkssj(Date shyxqkssj) {
        this.shyxqkssj = shyxqkssj;
    }

    public Date getShyxqjssj() {
        return shyxqjssj;
    }

    public void setShyxqjssj(Date shyxqjssj) {
        this.shyxqjssj = shyxqjssj;
    }

    public String getTxzbh() {
        return txzbh;
    }

    public void setTxzbh(String txzbh) {
        this.txzbh = txzbh;
    }

    public List<String> getChecktxxl() {
        return checktxxl;
    }

    public void setChecktxxl(List<String> checktxxl) {
        this.checktxxl = checktxxl;
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
        sb.append(", sqr=").append(sqr);
        sb.append(", txzlx=").append(txzlx);
        sb.append(", cfd=").append(cfd);
        sb.append(", mdd=").append(mdd);
        sb.append(", yxqkssj=").append(yxqkssj);
        sb.append(", yxqjssj=").append(yxqjssj);
        sb.append(", hpzl=").append(hpzl);
        sb.append(", hphm=").append(hphm);
        sb.append(", syr=").append(syr);
        sb.append(", lxdh=").append(lxdh);
        sb.append(", sqsm=").append(sqsm);
        sb.append(", txxl=").append(txxl);
        sb.append(", zt=").append(zt);
        sb.append(", shr=").append(shr);
        sb.append(", shsj=").append(shsj);
        sb.append(", shsm=").append(shsm);
        sb.append(", openid=").append(openid);
        sb.append(", lsh=").append(lsh);
        sb.append(", bz=").append(bz);
        sb.append(", shyxqkssj=").append(shyxqkssj);
        sb.append(", shyxqjssj=").append(shyxqjssj);
        sb.append(", txzbh=").append(txzbh);
        sb.append("]");
        return sb.toString();
    }

}
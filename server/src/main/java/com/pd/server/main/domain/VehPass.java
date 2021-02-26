package com.pd.server.main.domain;

import java.util.Date;

public class VehPass {
    private String id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    private String sqr;

    private String txzlx;

    private String cfd;

    private String mdd;

    private Date yxqkssj;

    private Date yxqjssj;

    private String hpzl;

    private String hphm;

    private String syr;

    private String lxdh;

    private String sqsm;

    private String txxl;

    private String zt;

    private String shr;

    private Date shsj;

    private String shsm;

    private String openid;

    private String lsh;

    private String bz;

    private Date shyxqkssj;

    private Date shyxqjssj;

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
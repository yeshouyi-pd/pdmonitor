package com.pd.server.main.domain;

import java.util.Date;

public class VehScrew {
    private String id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    private String sfzmhm;

    private String hphm;

    private String sqyy;

    private String slsl;

    private String sjr;

    private String lxdh;

    private String sf;

    private String cs;

    private String qx;

    private String jddz;

    private String zt;

    private String shr;

    private Date shsj;

    private String bz;

    private String openid;

    private String shyy;

    private String ywmc;

    private String lsh;

    private String sfbr;

    private String sllb;

    private String dh;

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
        sb.append(", dh=").append(dh);
        sb.append("]");
        return sb.toString();
    }
}
package com.pd.server.main.domain;

import java.util.Date;

public class WaterEquipment {
    private String id;

    private String sbmc;

    private String sbsn;

    private Integer port;

    private String ip;

    private String deptcode;

    private String centerCode;

    private String sblb;

    private String dqzl;

    private String sbxh;

    private String gps;

    private String fzr;

    private String fzrdh;

    private String sblc;

    private String jdfw;

    private String fzwz;

    private String sbcj;

    private String sbzt;

    private String bz;

    private Date createTime;

    private String createBy;

    private Date gxsj;

    private String updateBy;

    private String sm1;

    private String sm2;

    private String sm3;

    private String openYqzt;

    private String openSbxt;

    private String openTyndcb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getSbsn() {
        return sbsn;
    }

    public void setSbsn(String sbsn) {
        this.sbsn = sbsn;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public String getSblb() {
        return sblb;
    }

    public void setSblb(String sblb) {
        this.sblb = sblb;
    }

    public String getDqzl() {
        return dqzl;
    }

    public void setDqzl(String dqzl) {
        this.dqzl = dqzl;
    }

    public String getSbxh() {
        return sbxh;
    }

    public void setSbxh(String sbxh) {
        this.sbxh = sbxh;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getFzrdh() {
        return fzrdh;
    }

    public void setFzrdh(String fzrdh) {
        this.fzrdh = fzrdh;
    }

    public String getSblc() {
        return sblc;
    }

    public void setSblc(String sblc) {
        this.sblc = sblc;
    }

    public String getJdfw() {
        return jdfw;
    }

    public void setJdfw(String jdfw) {
        this.jdfw = jdfw;
    }

    public String getFzwz() {
        return fzwz;
    }

    public void setFzwz(String fzwz) {
        this.fzwz = fzwz;
    }

    public String getSbcj() {
        return sbcj;
    }

    public void setSbcj(String sbcj) {
        this.sbcj = sbcj;
    }

    public String getSbzt() {
        return sbzt;
    }

    public void setSbzt(String sbzt) {
        this.sbzt = sbzt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getSm1() {
        return sm1;
    }

    public void setSm1(String sm1) {
        this.sm1 = sm1;
    }

    public String getSm2() {
        return sm2;
    }

    public void setSm2(String sm2) {
        this.sm2 = sm2;
    }

    public String getSm3() {
        return sm3;
    }

    public void setSm3(String sm3) {
        this.sm3 = sm3;
    }

    public String getOpenYqzt() {
        return openYqzt;
    }

    public void setOpenYqzt(String openYqzt) {
        this.openYqzt = openYqzt;
    }

    public String getOpenSbxt() {
        return openSbxt;
    }

    public void setOpenSbxt(String openSbxt) {
        this.openSbxt = openSbxt;
    }

    public String getOpenTyndcb() {
        return openTyndcb;
    }

    public void setOpenTyndcb(String openTyndcb) {
        this.openTyndcb = openTyndcb;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sbmc=").append(sbmc);
        sb.append(", sbsn=").append(sbsn);
        sb.append(", port=").append(port);
        sb.append(", ip=").append(ip);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", centerCode=").append(centerCode);
        sb.append(", sblb=").append(sblb);
        sb.append(", dqzl=").append(dqzl);
        sb.append(", sbxh=").append(sbxh);
        sb.append(", gps=").append(gps);
        sb.append(", fzr=").append(fzr);
        sb.append(", fzrdh=").append(fzrdh);
        sb.append(", sblc=").append(sblc);
        sb.append(", jdfw=").append(jdfw);
        sb.append(", fzwz=").append(fzwz);
        sb.append(", sbcj=").append(sbcj);
        sb.append(", sbzt=").append(sbzt);
        sb.append(", bz=").append(bz);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", gxsj=").append(gxsj);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", sm1=").append(sm1);
        sb.append(", sm2=").append(sm2);
        sb.append(", sm3=").append(sm3);
        sb.append(", openYqzt=").append(openYqzt);
        sb.append(", openSbxt=").append(openSbxt);
        sb.append(", openTyndcb=").append(openTyndcb);
        sb.append("]");
        return sb.toString();
    }
}
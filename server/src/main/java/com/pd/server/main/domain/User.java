package com.pd.server.main.domain;

import java.util.Date;

public class User {
    private String id;

    private String loginName;

    private String name;

    private String password;

    private String deptcode;

    private String rode;

    private String tel;

    private Date yhyxq;

    private Date mmyxq;

    private String sfjy;

    private String sfzhm;

    private String jjbh;

    private String ipstart;

    private String ipend;

    private String mac;

    private String zt;

    private Date createTime;

    private Date updateTime;

    private Date lastloginTime;

    private Integer sbcs;

    private String yj;

    private String jyw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getRode() {
        return rode;
    }

    public void setRode(String rode) {
        this.rode = rode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getYhyxq() {
        return yhyxq;
    }

    public void setYhyxq(Date yhyxq) {
        this.yhyxq = yhyxq;
    }

    public Date getMmyxq() {
        return mmyxq;
    }

    public void setMmyxq(Date mmyxq) {
        this.mmyxq = mmyxq;
    }

    public String getSfjy() {
        return sfjy;
    }

    public void setSfjy(String sfjy) {
        this.sfjy = sfjy;
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getJjbh() {
        return jjbh;
    }

    public void setJjbh(String jjbh) {
        this.jjbh = jjbh;
    }

    public String getIpstart() {
        return ipstart;
    }

    public void setIpstart(String ipstart) {
        this.ipstart = ipstart;
    }

    public String getIpend() {
        return ipend;
    }

    public void setIpend(String ipend) {
        this.ipend = ipend;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastloginTime() {
        return lastloginTime;
    }

    public void setLastloginTime(Date lastloginTime) {
        this.lastloginTime = lastloginTime;
    }

    public Integer getSbcs() {
        return sbcs;
    }

    public void setSbcs(Integer sbcs) {
        this.sbcs = sbcs;
    }

    public String getYj() {
        return yj;
    }

    public void setYj(String yj) {
        this.yj = yj;
    }

    public String getJyw() {
        return jyw;
    }

    public void setJyw(String jyw) {
        this.jyw = jyw;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", rode=").append(rode);
        sb.append(", tel=").append(tel);
        sb.append(", yhyxq=").append(yhyxq);
        sb.append(", mmyxq=").append(mmyxq);
        sb.append(", sfjy=").append(sfjy);
        sb.append(", sfzhm=").append(sfzhm);
        sb.append(", jjbh=").append(jjbh);
        sb.append(", ipstart=").append(ipstart);
        sb.append(", ipend=").append(ipend);
        sb.append(", mac=").append(mac);
        sb.append(", zt=").append(zt);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", lastloginTime=").append(lastloginTime);
        sb.append(", sbcs=").append(sbcs);
        sb.append(", yj=").append(yj);
        sb.append(", jyw=").append(jyw);
        sb.append("]");
        return sb.toString();
    }
}
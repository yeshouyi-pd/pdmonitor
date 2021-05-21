package com.pd.server.main.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserDto  extends PageDto{

    /**
     * id
     */
    private String id;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    private String deptcode;

    private String rode;

    private String tel;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date yhyxq;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date mmyxq;

    private String sfjy;

    private String sfzhm;

    private String jjbh;

    private String ipstart;

    private String ipend;

    private String mac;

    private String zt;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date lastloginTime;

    private Integer sbcs;

    private String yj;

    private String jyw;
    /**
     * 验证码
     */
    private String imageCode;

    /**
     * 图片验证码token
     */
    private String imageCodeToken;

    private String oldPwd;

    private String newPwd;


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

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getImageCodeToken() {
        return imageCodeToken;
    }

    public void setImageCodeToken(String imageCodeToken) {
        this.imageCodeToken = imageCodeToken;
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

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", deptcode='" + deptcode + '\'' +
                ", rode='" + rode + '\'' +
                ", imageCode='" + imageCode + '\'' +
                ", imageCodeToken='" + imageCodeToken + '\'' +
                '}';
    }
}
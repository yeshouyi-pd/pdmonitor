package com.pd.server.main.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

public class UserVideoDto extends PageDto{

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

    /**
    * 部门
    */
    private String deptcode;

    /**
    * 角色
    */
    private String rode;

    /**
    * 手机号码
    */
    private String tel;

    /**
    * 用户有效期
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private String yhyxq;

    /**
    * 密码有效期
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private String mmyxq;

    /**
    * 是否警员| 'Y' 是 'N'否
    */
    private String sfjy;

    /**
    * 身份证号码
    */
    private String sfzhm;

    /**
    * 警员编号
    */
    private String jjbh;

    /**
    * ip开始
    */
    private String ipstart;

    /**
    * ip结束
    */
    private String ipend;

    /**
    * MAC
    */
    private String mac;

    /**
    * 是否启用| 'Y' 是 'N'否
    */
    private String zt;

    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private String createTime;

    /**
    * 修改时间
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private String updateTime;

    /**
    * 最后登录成功时间
    */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private String lastloginTime;

    /**
    * 登录失败次数
    */
    private Integer sbcs;

    /**
    * 预警|1 正常 2 异常
    */
    private String yj;

    /**
    * 校验位
    */
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

    private String xmbm;//项目编号

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

    public String getYhyxq() {
        return yhyxq;
    }

    public void setYhyxq(String yhyxq) {
        this.yhyxq = yhyxq;
    }

    public String getMmyxq() {
        return mmyxq;
    }

    public void setMmyxq(String mmyxq) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastloginTime() {
        return lastloginTime;
    }

    public void setLastloginTime(String lastloginTime) {
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

    public String getXmbm() {
        return xmbm;
    }

    public void setXmbm(String xmbm) {
        this.xmbm = xmbm;
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
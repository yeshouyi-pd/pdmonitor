package com.pd.server.main.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class LoginUserDto {

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

    /**
     * 登录凭证
     */
    private String token;

    /**
     * 所有资源，用于前端界面控制
     */
    private List<ResourceDto> resources;

    /**
     * 所有资源中的请求，用于后端接口拦截
     */
    private HashSet<String> requests;

    private Map<String,String> deptmap;//所有部门

    private Map<String,String> usermap;//所有用户

    private Map<String,List<String>> xmbhsbsns;//项目编号对应的所有的设备编号

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<ResourceDto> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDto> resources) {
        this.resources = resources;
    }

    public HashSet<String> getRequests() {
        return requests;
    }

    public void setRequests(HashSet<String> requests) {
        this.requests = requests;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public Map<String, String> getDeptmap() {
        return deptmap;
    }

    public void setDeptmap(Map<String, String> deptmap) {
        this.deptmap = deptmap;
    }

    public Map<String, String> getUsermap() {
        return usermap;
    }

    public void setUsermap(Map<String, String> usermap) {
        this.usermap = usermap;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, List<String>> getXmbhsbsns() {
        return xmbhsbsns;
    }

    public void setXmbhsbsns(Map<String, List<String>> xmbhsbsns) {
        this.xmbhsbsns = xmbhsbsns;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginUserDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", loginName='").append(loginName).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", resources=").append(resources);
        sb.append(", requests=").append(requests);
        sb.append('}');
        return sb.toString();
    }

}
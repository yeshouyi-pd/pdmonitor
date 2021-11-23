package com.pd.server.main.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CameraInfoDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 设备sn
    */
    private String sbsn;

    /**
    * 机构编号
    */
    private String deptcode;

    /**
    * 摄像头IP
    */
    private String ip;

    /**
    * 设备端口
    */
    private Integer port;

    /**
    * 摄像头用户名
    */
    private String username;

    /**
    * 摄像头密码
    */
    private String camerapws;

    /**
    * 设备端口
    */
    private Integer sbdk;

    /**
    * 所属DVR的IP（备用地址）
    */
    private String dvrip;

    /**
    * 
    */
    private String sm1;

    /**
    * 
    */
    private String sm2;

    /**
    * 
    */
    private String sm3;

    /**
    * 
    */
    private String sm4;

    /**
    * 
    */
    private String sm5;

    /**
    * 项目编号
    */
    private String xmbh;

    /**
    * 创建人
    */
    private String cjr;

    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 更新时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gxsj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbsn() {
        return sbsn;
    }

    public void setSbsn(String sbsn) {
        this.sbsn = sbsn;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCamerapws() {
        return camerapws;
    }

    public void setCamerapws(String camerapws) {
        this.camerapws = camerapws;
    }

    public Integer getSbdk() {
        return sbdk;
    }

    public void setSbdk(Integer sbdk) {
        this.sbdk = sbdk;
    }

    public String getDvrip() {
        return dvrip;
    }

    public void setDvrip(String dvrip) {
        this.dvrip = dvrip;
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

    public String getSm4() {
        return sm4;
    }

    public void setSm4(String sm4) {
        this.sm4 = sm4;
    }

    public String getSm5() {
        return sm5;
    }

    public void setSm5(String sm5) {
        this.sm5 = sm5;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", sbsn=").append(sbsn);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", ip=").append(ip);
            sb.append(", port=").append(port);
            sb.append(", username=").append(username);
            sb.append(", camerapws=").append(camerapws);
            sb.append(", sbdk=").append(sbdk);
            sb.append(", dvrip=").append(dvrip);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
            sb.append(", sm4=").append(sm4);
            sb.append(", sm5=").append(sm5);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", cjr=").append(cjr);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", gxsj=").append(gxsj);
        sb.append("]");
        return sb.toString();
    }

}
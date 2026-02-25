package com.pd.server.main.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class WaterEquipmentDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 设备名称
    */
    private String sbmc;

    /**
    * 设备SN
    */
    private String sbsn;

    /**
    * 设备端口
    */
    private Integer port;

    /**
    * 设备IP
    */
    private String ip;

    /**
    * 所属监测点
    */
    private String deptcode;

    /**
    * 所属数据中心
    */
    private String centerCode;

    /**
    * 设备类别
    */
    private String sblb;

    /**
    * 设备读取指令
    */
    private String dqzl;

    /**
    * 设备型号
    */
    private String sbxh;

    /**
    * 设备gps坐标
    */
    private String gps;

    /**
    * 设备负责人
    */
    private String fzr;

    /**
    * 负责人电话
    */
    private String fzrdh;

    /**
    * 设备量程
    */
    private String sblc;

    /**
    * 设备进度范围
    */
    private String jdfw;

    /**
    * 设备放置位置
    */
    private String fzwz;

    /**
    * 设备厂家
    */
    private String sbcj;

    /**
    * 设备状态1正常2离线3设备故障
    */
    private String sbzt;

    /**
    * 备注
    */
    private String bz;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 创建人
    */
    private String createBy;

    /**
    * 更新时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gxsj;

    /**
    * 更新人
    */
    private String updateBy;

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

    private String xmbh;

    /**
     *是否打开仪器姿态|0未启用1启用
     */
    private String openYqzt;

    /**
     *是否打开设备心跳|0未启用1启用
     */
    private String openSbxt;

    /**
     *是否打开太阳能电池板|0未启用1启用
     */
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

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
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
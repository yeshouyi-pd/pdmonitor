package com.pd.server.main.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InterfaceLogDto extends PageDto{

    /**
    * 主键
    */
    private String id;

    /**
    * 请求ip
    */
    private String ip;

    /**
    * 请求参数
    */
    private String qqcs;

    /**
    * 返回数据
    */
    private String fhsj;

    /**
    * 请求时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date qqsj;

    /**
    * 请求人员
    */
    private String qqry;

    /**
    * 备注
    */
    private String bz;

    /**
    * 备注字段1
    */
    private String bz1;

    /**
    * 备注字段2
    */
    private String bz2;

    /**
    * 备注字段3
    */
    private String bz3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getQqcs() {
        return qqcs;
    }

    public void setQqcs(String qqcs) {
        this.qqcs = qqcs;
    }

    public String getFhsj() {
        return fhsj;
    }

    public void setFhsj(String fhsj) {
        this.fhsj = fhsj;
    }

    public Date getQqsj() {
        return qqsj;
    }

    public void setQqsj(Date qqsj) {
        this.qqsj = qqsj;
    }

    public String getQqry() {
        return qqry;
    }

    public void setQqry(String qqry) {
        this.qqry = qqry;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }

    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    public String getBz3() {
        return bz3;
    }

    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", ip=").append(ip);
            sb.append(", qqcs=").append(qqcs);
            sb.append(", fhsj=").append(fhsj);
            sb.append(", qqsj=").append(qqsj);
            sb.append(", qqry=").append(qqry);
            sb.append(", bz=").append(bz);
            sb.append(", bz1=").append(bz1);
            sb.append(", bz2=").append(bz2);
            sb.append(", bz3=").append(bz3);
        sb.append("]");
        return sb.toString();
    }

}
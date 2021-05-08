package com.pd.server.main.domain;

import java.util.Date;

public class WaterQualityAvg {
    private String id;

    private String ip;

    private String datacenter;

    private String jcxm;

    private String dataResult;

    private Date createTime;

    private String sm1;

    private String sm2;

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

    public String getDatacenter() {
        return datacenter;
    }

    public void setDatacenter(String datacenter) {
        this.datacenter = datacenter;
    }

    public String getJcxm() {
        return jcxm;
    }

    public void setJcxm(String jcxm) {
        this.jcxm = jcxm;
    }

    public String getDataResult() {
        return dataResult;
    }

    public void setDataResult(String dataResult) {
        this.dataResult = dataResult;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", datacenter=").append(datacenter);
        sb.append(", jcxm=").append(jcxm);
        sb.append(", dataResult=").append(dataResult);
        sb.append(", createTime=").append(createTime);
        sb.append(", sm1=").append(sm1);
        sb.append(", sm2=").append(sm2);
        sb.append("]");
        return sb.toString();
    }
}
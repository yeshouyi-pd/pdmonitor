package com.pd.server.main.domain;

import java.util.Date;

public class InterfaceLog {
    private String id;

    private String ip;

    private Date qqsj;

    private String qqry;

    private String bz;

    private String bz1;

    private String bz2;

    private String bz3;

    private String qqcs;

    private String fhsj;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", qqsj=").append(qqsj);
        sb.append(", qqry=").append(qqry);
        sb.append(", bz=").append(bz);
        sb.append(", bz1=").append(bz1);
        sb.append(", bz2=").append(bz2);
        sb.append(", bz3=").append(bz3);
        sb.append(", qqcs=").append(qqcs);
        sb.append(", fhsj=").append(fhsj);
        sb.append("]");
        return sb.toString();
    }
}
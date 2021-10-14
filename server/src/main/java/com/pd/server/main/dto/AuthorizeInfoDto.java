package com.pd.server.main.dto;


public class AuthorizeInfoDto {

    /**
    * 主键
    */
    private String id;

    /**
    * 单位名称
    */
    private String dwmc;

    /**
    * 设备编号
    */
    private String sbsn;

    /**
    * 授权码
    */
    private String sqm;

    /**
    * 客户ip
    */
    private String ip;

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

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getSbsn() {
        return sbsn;
    }

    public void setSbsn(String sbsn) {
        this.sbsn = sbsn;
    }

    public String getSqm() {
        return sqm;
    }

    public void setSqm(String sqm) {
        this.sqm = sqm;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
            sb.append(", dwmc=").append(dwmc);
            sb.append(", sbsn=").append(sbsn);
            sb.append(", sqm=").append(sqm);
            sb.append(", ip=").append(ip);
            sb.append(", bz=").append(bz);
            sb.append(", bz1=").append(bz1);
            sb.append(", bz2=").append(bz2);
            sb.append(", bz3=").append(bz3);
        sb.append("]");
        return sb.toString();
    }

}
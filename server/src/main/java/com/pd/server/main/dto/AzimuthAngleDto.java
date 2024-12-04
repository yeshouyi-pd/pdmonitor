package com.pd.server.main.dto;


public class AzimuthAngleDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 设备编号
    */
    private String sbbh;

    /**
    * 日期
    */
    private String rq;

    /**
    * 小时
    */
    private String xs;

    /**
    * 分钟
    */
    private String fz;

    /**
    * 头数
    */
    private Integer ts;

    /**
    * 部门编号
    */
    private String deptcode;

    /**
    * 北-北东
    */
    private Integer northNortheast;

    /**
    * 北东-东
    */
    private Integer northeastEast;

    /**
    * 东-东南
    */
    private Integer eastEastsouth;

    /**
    * 东南-南
    */
    private Integer eastsouthSouth;

    /**
    * 南-南西
    */
    private Integer southSouthwest;

    /**
    * 南西-西
    */
    private Integer southwestWest;

    /**
    * 西-西北
    */
    private Integer westWestnorth;

    /**
    * 西北-北
    */
    private Integer westnorthNorth;

    private String xmbh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getXs() {
        return xs;
    }

    public void setXs(String xs) {
        this.xs = xs;
    }

    public String getFz() {
        return fz;
    }

    public void setFz(String fz) {
        this.fz = fz;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public Integer getNorthNortheast() {
        return northNortheast;
    }

    public void setNorthNortheast(Integer northNortheast) {
        this.northNortheast = northNortheast;
    }

    public Integer getNortheastEast() {
        return northeastEast;
    }

    public void setNortheastEast(Integer northeastEast) {
        this.northeastEast = northeastEast;
    }

    public Integer getEastEastsouth() {
        return eastEastsouth;
    }

    public void setEastEastsouth(Integer eastEastsouth) {
        this.eastEastsouth = eastEastsouth;
    }

    public Integer getEastsouthSouth() {
        return eastsouthSouth;
    }

    public void setEastsouthSouth(Integer eastsouthSouth) {
        this.eastsouthSouth = eastsouthSouth;
    }

    public Integer getSouthSouthwest() {
        return southSouthwest;
    }

    public void setSouthSouthwest(Integer southSouthwest) {
        this.southSouthwest = southSouthwest;
    }

    public Integer getSouthwestWest() {
        return southwestWest;
    }

    public void setSouthwestWest(Integer southwestWest) {
        this.southwestWest = southwestWest;
    }

    public Integer getWestWestnorth() {
        return westWestnorth;
    }

    public void setWestWestnorth(Integer westWestnorth) {
        this.westWestnorth = westWestnorth;
    }

    public Integer getWestnorthNorth() {
        return westnorthNorth;
    }

    public void setWestnorthNorth(Integer westnorthNorth) {
        this.westnorthNorth = westnorthNorth;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", sbbh=").append(sbbh);
            sb.append(", rq=").append(rq);
            sb.append(", xs=").append(xs);
            sb.append(", fz=").append(fz);
            sb.append(", ts=").append(ts);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", northNortheast=").append(northNortheast);
            sb.append(", northeastEast=").append(northeastEast);
            sb.append(", eastEastsouth=").append(eastEastsouth);
            sb.append(", eastsouthSouth=").append(eastsouthSouth);
            sb.append(", southSouthwest=").append(southSouthwest);
            sb.append(", southwestWest=").append(southwestWest);
            sb.append(", westWestnorth=").append(westWestnorth);
            sb.append(", westnorthNorth=").append(westnorthNorth);
        sb.append("]");
        return sb.toString();
    }

}
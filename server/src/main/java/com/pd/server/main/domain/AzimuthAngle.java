package com.pd.server.main.domain;

public class AzimuthAngle {
    private String id;

    private String sbbh;

    private String rq;

    private String xs;

    private String fz;

    private String ts;

    private String deptcode;

    private Integer northNortheast;

    private Integer northeastEast;

    private Integer eastEastsouth;

    private Integer eastsouthSouth;

    private Integer southSouthwest;

    private Integer southwestWest;

    private Integer westWestnorth;

    private Integer westnorthNorth;

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

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
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
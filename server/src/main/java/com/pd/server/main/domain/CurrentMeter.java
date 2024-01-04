package com.pd.server.main.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CurrentMeter {
    private String id;

    private String productNumber;

    private String serialNumber;

    private String absSpeed;

    private String direction;

    private String north;

    private String east;

    private String heading;

    private String tiltX;

    private String tiltY;

    private String spStd;

    private String strength;

    private String pingCount;

    private String absTilt;

    private String maxTilt;

    private String stdTilt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    private String bz;

    private String sm;

    private Double uspeed;

    private Double vspeed;

    private Double zetaData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAbsSpeed() {
        return absSpeed;
    }

    public void setAbsSpeed(String absSpeed) {
        this.absSpeed = absSpeed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTiltX() {
        return tiltX;
    }

    public void setTiltX(String tiltX) {
        this.tiltX = tiltX;
    }

    public String getTiltY() {
        return tiltY;
    }

    public void setTiltY(String tiltY) {
        this.tiltY = tiltY;
    }

    public String getSpStd() {
        return spStd;
    }

    public void setSpStd(String spStd) {
        this.spStd = spStd;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getPingCount() {
        return pingCount;
    }

    public void setPingCount(String pingCount) {
        this.pingCount = pingCount;
    }

    public String getAbsTilt() {
        return absTilt;
    }

    public void setAbsTilt(String absTilt) {
        this.absTilt = absTilt;
    }

    public String getMaxTilt() {
        return maxTilt;
    }

    public void setMaxTilt(String maxTilt) {
        this.maxTilt = maxTilt;
    }

    public String getStdTilt() {
        return stdTilt;
    }

    public void setStdTilt(String stdTilt) {
        this.stdTilt = stdTilt;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public Double getUspeed() {
        return uspeed;
    }

    public void setUspeed(Double uspeed) {
        this.uspeed = uspeed;
    }

    public Double getVspeed() {
        return vspeed;
    }

    public void setVspeed(Double vspeed) {
        this.vspeed = vspeed;
    }

    public Double getZetaData() {
        return zetaData;
    }

    public void setZetaData(Double zetaData) {
        this.zetaData = zetaData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productNumber=").append(productNumber);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", absSpeed=").append(absSpeed);
        sb.append(", direction=").append(direction);
        sb.append(", north=").append(north);
        sb.append(", east=").append(east);
        sb.append(", heading=").append(heading);
        sb.append(", tiltX=").append(tiltX);
        sb.append(", tiltY=").append(tiltY);
        sb.append(", spStd=").append(spStd);
        sb.append(", strength=").append(strength);
        sb.append(", pingCount=").append(pingCount);
        sb.append(", absTilt=").append(absTilt);
        sb.append(", maxTilt=").append(maxTilt);
        sb.append(", stdTilt=").append(stdTilt);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append(", uspeed=").append(uspeed);
        sb.append(", vspeed=").append(vspeed);
        sb.append(", zetaData=").append(zetaData);
        sb.append("]");
        return sb.toString();
    }
}
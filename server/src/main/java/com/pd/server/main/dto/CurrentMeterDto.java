package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class CurrentMeterDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 产品编号
    */
    private String productNumber;

    /**
    * 序列号
    */
    private String serialNumber;

    /**
    * abs速度
    */
    private String absSpeed;

    /**
    * 方向
    */
    private String direction;

    /**
    * 北
    */
    private String north;

    /**
    * 东
    */
    private String east;

    /**
    * 朝向
    */
    private String heading;

    /**
    * 倾斜度X
    */
    private String tiltX;

    /**
    * 倾斜度Y
    */
    private String tiltY;

    /**
    * Sp标准
    */
    private String spStd;

    /**
    * 力度
    */
    private String strength;

    /**
    * 平计数
    */
    private String pingCount;

    /**
    * Abs倾斜度
    */
    private String absTilt;

    /**
    * 最大倾斜度
    */
    private String maxTilt;

    /**
    * 标准倾斜度
    */
    private String stdTilt;

    /**
    * 采集时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 备注
    */
    private String bz;

    /**
    * 说明
    */
    private String sm;

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
        sb.append("]");
        return sb.toString();
    }

}
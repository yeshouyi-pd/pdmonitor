package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class TurbidityDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 采集时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dateTime;

    /**
    * 浊度高量程
    */
    private String turbidityH;

    /**
    * 浊度低量程
    */
    private String turibidityL;

    /**
    * 深度
    */
    private String depth;

    /**
    * 温度
    */
    private String temperature;

    /**
    * 电导率
    */
    private String conductivity;

    /**
    * 盐度
    */
    private String salinity;

    /**
    * 电池电压
    */
    private String batVolt;

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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTurbidityH() {
        return turbidityH;
    }

    public void setTurbidityH(String turbidityH) {
        this.turbidityH = turbidityH;
    }

    public String getTuribidityL() {
        return turibidityL;
    }

    public void setTuribidityL(String turibidityL) {
        this.turibidityL = turibidityL;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getConductivity() {
        return conductivity;
    }

    public void setConductivity(String conductivity) {
        this.conductivity = conductivity;
    }

    public String getSalinity() {
        return salinity;
    }

    public void setSalinity(String salinity) {
        this.salinity = salinity;
    }

    public String getBatVolt() {
        return batVolt;
    }

    public void setBatVolt(String batVolt) {
        this.batVolt = batVolt;
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
            sb.append(", dateTime=").append(dateTime);
            sb.append(", turbidityH=").append(turbidityH);
            sb.append(", turibidityL=").append(turibidityL);
            sb.append(", depth=").append(depth);
            sb.append(", temperature=").append(temperature);
            sb.append(", conductivity=").append(conductivity);
            sb.append(", salinity=").append(salinity);
            sb.append(", batVolt=").append(batVolt);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

}
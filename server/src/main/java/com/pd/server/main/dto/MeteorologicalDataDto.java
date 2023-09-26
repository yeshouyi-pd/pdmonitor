package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class MeteorologicalDataDto extends PageDto{

    /**
    *
    */
    private String id;

    /**
    * 风速
    */
    private Double speed;

    /**
    * 风向
    */
    private String winddirection;

    /**
    * 温度
    */
    private Double temperature;

    /**
    * 湿度
    */
    private Double humidity;

    /**
    * 大气压力
    */
    private Double pressure;

    /**
    * 分钟雨量
    */
    private Double minuterainfall;

    /**
    * 小时雨量
    */
    private Double hourrainfall;

    /**
    * 24小时雨量
    */
    private Double dayrainfall;

    /**
    * 测量雨量累加值
    */
    private Double rainfallaccumulation;

    /**
    * 太阳光强度
    */
    private Double solarintensity;

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

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getMinuterainfall() {
        return minuterainfall;
    }

    public void setMinuterainfall(Double minuterainfall) {
        this.minuterainfall = minuterainfall;
    }

    public Double getHourrainfall() {
        return hourrainfall;
    }

    public void setHourrainfall(Double hourrainfall) {
        this.hourrainfall = hourrainfall;
    }

    public Double getDayrainfall() {
        return dayrainfall;
    }

    public void setDayrainfall(Double dayrainfall) {
        this.dayrainfall = dayrainfall;
    }

    public Double getRainfallaccumulation() {
        return rainfallaccumulation;
    }

    public void setRainfallaccumulation(Double rainfallaccumulation) {
        this.rainfallaccumulation = rainfallaccumulation;
    }

    public Double getSolarintensity() {
        return solarintensity;
    }

    public void setSolarintensity(Double solarintensity) {
        this.solarintensity = solarintensity;
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
            sb.append(", speed=").append(speed);
            sb.append(", winddirection=").append(winddirection);
            sb.append(", temperature=").append(temperature);
            sb.append(", humidity=").append(humidity);
            sb.append(", pressure=").append(pressure);
            sb.append(", minuterainfall=").append(minuterainfall);
            sb.append(", hourrainfall=").append(hourrainfall);
            sb.append(", dayrainfall=").append(dayrainfall);
            sb.append(", rainfallaccumulation=").append(rainfallaccumulation);
            sb.append(", solarintensity=").append(solarintensity);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

}
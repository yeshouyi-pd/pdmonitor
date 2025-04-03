package com.pd.server.main.dto.basewx.my;

public class MeteorologicalDataSum {

    /**
     * 日期
     */
    private String rq;

    /**
     * 风速
     */
    private Double speed;

    /**
     * 温度
     */
    private Double temperature;

    /**
     * 湿度
     */
    private Double humidity;

    /**
     * 总数
     */
    private Integer total;

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

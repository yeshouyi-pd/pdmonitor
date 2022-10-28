package com.pd.power.main.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DcbDataSecDto extends PageDto {

    /**
    * 
    */
    private Integer id;

    /**
    * SIM卡卡号
    */
    private String iccid;

    /**
    * 设备IP:端口
    */
    private String ipport;

    /**
    * 电池电压
    */
    private String dy;

    /**
    * 电池的等效剩余容量预估
    */
    private String rl;

    /**
    * 当前的电池电流，（放电状态，放电电流为10mA）
    */
    private String dl;

    /**
    * 事件时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sjdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getIpport() {
        return ipport;
    }

    public void setIpport(String ipport) {
        this.ipport = ipport;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getRl() {
        return rl;
    }

    public void setRl(String rl) {
        this.rl = rl;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public Date getSjdate() {
        return sjdate;
    }

    public void setSjdate(Date sjdate) {
        this.sjdate = sjdate;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", iccid=").append(iccid);
            sb.append(", ipport=").append(ipport);
            sb.append(", dy=").append(dy);
            sb.append(", rl=").append(rl);
            sb.append(", dl=").append(dl);
            sb.append(", sjdate=").append(sjdate);
        sb.append("]");
        return sb.toString();
    }

}
package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class TdDataDto extends PageDto{

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
    * 通道数据类型：40-对应雷达电源通道的工作状态；41-对应指示灯输出通道的工作状态；42-对应喇叭电源输出通道的工作状态；43-对应备用输出通道的工作状态。
    */
    private String tdlx;

    /**
    * 表示当前通道为指示灯输出通道
    */
    private String tdh;

    /**
    * 当前通道输出电压，对应电压为V
    */
    private String dy;

    /**
    * 表示当前通道的电流值。（单位为mA，有3mA的误差）
    */
    private String dl;

    /**
    * 当前通道故障状态。（无故障为00/01）
    */
    private String tdzt;

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

    public String getTdlx() {
        return tdlx;
    }

    public void setTdlx(String tdlx) {
        this.tdlx = tdlx;
    }

    public String getTdh() {
        return tdh;
    }

    public void setTdh(String tdh) {
        this.tdh = tdh;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public String getTdzt() {
        return tdzt;
    }

    public void setTdzt(String tdzt) {
        this.tdzt = tdzt;
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
            sb.append(", tdlx=").append(tdlx);
            sb.append(", tdh=").append(tdh);
            sb.append(", dy=").append(dy);
            sb.append(", dl=").append(dl);
            sb.append(", tdzt=").append(tdzt);
            sb.append(", sjdate=").append(sjdate);
        sb.append("]");
        return sb.toString();
    }

}
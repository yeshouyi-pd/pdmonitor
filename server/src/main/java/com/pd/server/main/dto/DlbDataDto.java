package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class DlbDataDto extends PageDto{

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
    * 当前电路板温度,摄氏度
    */
    private String wd;

    /**
    * 转接板当前时间
    */
    private String sj;

    /**
    * 充电开关打开状态
    */
    private String cdzt;

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

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public String getCdzt() {
        return cdzt;
    }

    public void setCdzt(String cdzt) {
        this.cdzt = cdzt;
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
            sb.append(", wd=").append(wd);
            sb.append(", sj=").append(sj);
            sb.append(", cdzt=").append(cdzt);
            sb.append(", sjdate=").append(sjdate);
        sb.append("]");
        return sb.toString();
    }

}
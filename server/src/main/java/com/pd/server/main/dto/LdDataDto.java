package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class LdDataDto {

    /**
    * 
    */
    private Integer id;

    /**
    * SIM卡号
    */
    private String iccid;

    /**
    * 来向
    */
    private String lx;

    /**
    * 来向速度
    */
    private String lxsd;

    /**
    * 去向
    */
    private String qx;

    /**
    * 去向速度
    */
    private String qxsd;

    /**
    * IP和端口
    */
    private String ipport;

    /**
    * 时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sjdate;

    /**
    * 所属部门
    */
    private String deptcode;

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

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getLxsd() {
        return lxsd;
    }

    public void setLxsd(String lxsd) {
        this.lxsd = lxsd;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getQxsd() {
        return qxsd;
    }

    public void setQxsd(String qxsd) {
        this.qxsd = qxsd;
    }

    public String getIpport() {
        return ipport;
    }

    public void setIpport(String ipport) {
        this.ipport = ipport;
    }

    public Date getSjdate() {
        return sjdate;
    }

    public void setSjdate(Date sjdate) {
        this.sjdate = sjdate;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", iccid=").append(iccid);
            sb.append(", lx=").append(lx);
            sb.append(", lxsd=").append(lxsd);
            sb.append(", qx=").append(qx);
            sb.append(", qxsd=").append(qxsd);
            sb.append(", ipport=").append(ipport);
            sb.append(", sjdate=").append(sjdate);
            sb.append(", deptcode=").append(deptcode);
        sb.append("]");
        return sb.toString();
    }

}
package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class LdListDto {

    /**
    * SIM卡卡号
    */
    private String iccid;

    /**
    * 经度
    */
    private String jd;

    /**
    * 纬度
    */
    private String wd;

    /**
    * 设备描述
    */
    private String sbms;

    /**
    * 设备IP:端口
    */
    private String ipport;

    /**
    * 状态0:离线;1:在线
    */
    private String state;

    /**
    * 最后在线时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date upsj;

    /**
    * 部门代码
    */
    private String deptcode;

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getSbms() {
        return sbms;
    }

    public void setSbms(String sbms) {
        this.sbms = sbms;
    }

    public String getIpport() {
        return ipport;
    }

    public void setIpport(String ipport) {
        this.ipport = ipport;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getUpsj() {
        return upsj;
    }

    public void setUpsj(Date upsj) {
        this.upsj = upsj;
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
            sb.append(", iccid=").append(iccid);
            sb.append(", jd=").append(jd);
            sb.append(", wd=").append(wd);
            sb.append(", sbms=").append(sbms);
            sb.append(", ipport=").append(ipport);
            sb.append(", state=").append(state);
            sb.append(", upsj=").append(upsj);
            sb.append(", deptcode=").append(deptcode);
        sb.append("]");
        return sb.toString();
    }

}
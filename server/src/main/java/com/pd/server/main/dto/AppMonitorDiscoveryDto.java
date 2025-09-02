package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class AppMonitorDiscoveryDto extends  PageDto{
    private String id;

    private String mid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fxsj;

    private String sjl;

    private String sgcz;

    private String sfw;

    private String sff;

    private String swzm;

    private String sqtzx;

    private String sqtzy;

    private String sqtzd;

    private String czsbh;

    private String hzjajl;

    private String hmztds;

    private String hxwms;

    private String hhjlxdm;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date scjs;

    private String gczxm;

    private String deptcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Date getFxsj() {
        return fxsj;
    }

    public void setFxsj(Date fxsj) {
        this.fxsj = fxsj;
    }

    public String getSjl() {
        return sjl;
    }

    public void setSjl(String sjl) {
        this.sjl = sjl;
    }

    public String getSgcz() {
        return sgcz;
    }

    public void setSgcz(String sgcz) {
        this.sgcz = sgcz;
    }

    public String getSfw() {
        return sfw;
    }

    public void setSfw(String sfw) {
        this.sfw = sfw;
    }

    public String getSff() {
        return sff;
    }

    public void setSff(String sff) {
        this.sff = sff;
    }

    public String getSwzm() {
        return swzm;
    }

    public void setSwzm(String swzm) {
        this.swzm = swzm;
    }

    public String getSqtzx() {
        return sqtzx;
    }

    public void setSqtzx(String sqtzx) {
        this.sqtzx = sqtzx;
    }

    public String getSqtzy() {
        return sqtzy;
    }

    public void setSqtzy(String sqtzy) {
        this.sqtzy = sqtzy;
    }

    public String getSqtzd() {
        return sqtzd;
    }

    public void setSqtzd(String sqtzd) {
        this.sqtzd = sqtzd;
    }

    public String getCzsbh() {
        return czsbh;
    }

    public void setCzsbh(String czsbh) {
        this.czsbh = czsbh;
    }

    public String getHzjajl() {
        return hzjajl;
    }

    public void setHzjajl(String hzjajl) {
        this.hzjajl = hzjajl;
    }

    public String getHmztds() {
        return hmztds;
    }

    public void setHmztds(String hmztds) {
        this.hmztds = hmztds;
    }

    public String getHxwms() {
        return hxwms;
    }

    public void setHxwms(String hxwms) {
        this.hxwms = hxwms;
    }

    public String getHhjlxdm() {
        return hhjlxdm;
    }

    public void setHhjlxdm(String hhjlxdm) {
        this.hhjlxdm = hhjlxdm;
    }

    public Date getScjs() {
        return scjs;
    }

    public void setScjs(Date scjs) {
        this.scjs = scjs;
    }

    public String getGczxm() {
        return gczxm;
    }

    public void setGczxm(String gczxm) {
        this.gczxm = gczxm;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }
}
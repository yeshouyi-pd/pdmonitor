package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class AppMonitorDiscoveryDto extends  PageDto{
    /**
     * id
     */
    private String id;


    /**
     * 观察周期主表ID
     */
    private String mid;

    /**
     * 发现江豚时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fxsj;

    /**
     * 观察者与动物间距离(米)
     */
    private String sjl;

    /**
     * 观察者姓名
     */
    private String sgcz;

    /**
     * 方位:船首为0度，左方为负值
     */
    private String sfw;

    /**
     * 方法
     */
    private String sff;

    /**
     * 物种名
     */
    private String swzm;

    /**
     * 群体大小-最少
     */
    private String sqtzx;

    /**
     * 群体大小-最优
     */
    private String sqtzy;

    /**
     * 群体大小-最多
     */
    private String sqtzd;

    /**
     * 注释编码
     */
    private String czsbh;

    /**
     * 动物距最近岸的距离
     */
    private String hzjajl;

    /**
     * 母子豚对数
     */
    private String hmztds;

    /**
     * 行为描述
     */
    private String hxwms;

    /**
     * 环境类型代码
     */
    private String hhjlxdm;

    /**
     * 上传时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date scjs;

    private String gczxm;

    private String deptcode;


    private String gps;

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

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
}
package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class AppMonitorDiscoveryDto extends PageDto {

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
    private String sJl;

    /**
    * 观察者姓名
    */
    private String sGcz;

    /**
    * 方位:船首为0度，左方为负值
    */
    private String sFw;

    /**
    * 方法
    */
    private String sFf;

    /**
    * 物种名
    */
    private String sWzm;

    /**
    * 群体大小-最少
    */
    private String sQtZx;

    /**
    * 群体大小-最优
    */
    private String sQtZy;

    /**
    * 群体大小-最多
    */
    private String sQtZd;

    /**
    * 注释编码
    */
    private String cZsbh;

    /**
    * 动物距最近岸的距离
    */
    private String hZjajl;

    /**
    * 母子豚对数
    */
    private String hMztds;

    /**
    * 行为描述
    */
    private String hXwms;

    /**
    * 环境类型代码
    */
    private String hHjlxdm;

    /**
    * 上传时间
    */
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

    public String getsJl() {
        return sJl;
    }

    public void setsJl(String sJl) {
        this.sJl = sJl;
    }

    public String getsGcz() {
        return sGcz;
    }

    public void setsGcz(String sGcz) {
        this.sGcz = sGcz;
    }

    public String getsFw() {
        return sFw;
    }

    public void setsFw(String sFw) {
        this.sFw = sFw;
    }

    public String getsFf() {
        return sFf;
    }

    public void setsFf(String sFf) {
        this.sFf = sFf;
    }

    public String getsWzm() {
        return sWzm;
    }

    public void setsWzm(String sWzm) {
        this.sWzm = sWzm;
    }

    public String getsQtZx() {
        return sQtZx;
    }

    public void setsQtZx(String sQtZx) {
        this.sQtZx = sQtZx;
    }

    public String getsQtZy() {
        return sQtZy;
    }

    public void setsQtZy(String sQtZy) {
        this.sQtZy = sQtZy;
    }

    public String getsQtZd() {
        return sQtZd;
    }

    public void setsQtZd(String sQtZd) {
        this.sQtZd = sQtZd;
    }

    public String getcZsbh() {
        return cZsbh;
    }

    public void setcZsbh(String cZsbh) {
        this.cZsbh = cZsbh;
    }

    public String gethZjajl() {
        return hZjajl;
    }

    public void sethZjajl(String hZjajl) {
        this.hZjajl = hZjajl;
    }

    public String gethMztds() {
        return hMztds;
    }

    public void sethMztds(String hMztds) {
        this.hMztds = hMztds;
    }

    public String gethXwms() {
        return hXwms;
    }

    public void sethXwms(String hXwms) {
        this.hXwms = hXwms;
    }

    public String gethHjlxdm() {
        return hHjlxdm;
    }

    public void sethHjlxdm(String hHjlxdm) {
        this.hHjlxdm = hHjlxdm;
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
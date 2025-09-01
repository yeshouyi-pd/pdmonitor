package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class AppMonitorDiscoveryDto {

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

    public String getSJl() {
        return sJl;
    }

    public void setSJl(String sJl) {
        this.sJl = sJl;
    }

    public String getSGcz() {
        return sGcz;
    }

    public void setSGcz(String sGcz) {
        this.sGcz = sGcz;
    }

    public String getSFw() {
        return sFw;
    }

    public void setSFw(String sFw) {
        this.sFw = sFw;
    }

    public String getSFf() {
        return sFf;
    }

    public void setSFf(String sFf) {
        this.sFf = sFf;
    }

    public String getSWzm() {
        return sWzm;
    }

    public void setSWzm(String sWzm) {
        this.sWzm = sWzm;
    }

    public String getSQtZx() {
        return sQtZx;
    }

    public void setSQtZx(String sQtZx) {
        this.sQtZx = sQtZx;
    }

    public String getSQtZy() {
        return sQtZy;
    }

    public void setSQtZy(String sQtZy) {
        this.sQtZy = sQtZy;
    }

    public String getSQtZd() {
        return sQtZd;
    }

    public void setSQtZd(String sQtZd) {
        this.sQtZd = sQtZd;
    }

    public String getCZsbh() {
        return cZsbh;
    }

    public void setCZsbh(String cZsbh) {
        this.cZsbh = cZsbh;
    }

    public String getHZjajl() {
        return hZjajl;
    }

    public void setHZjajl(String hZjajl) {
        this.hZjajl = hZjajl;
    }

    public String getHMztds() {
        return hMztds;
    }

    public void setHMztds(String hMztds) {
        this.hMztds = hMztds;
    }

    public String getHXwms() {
        return hXwms;
    }

    public void setHXwms(String hXwms) {
        this.hXwms = hXwms;
    }

    public String getHHjlxdm() {
        return hHjlxdm;
    }

    public void setHHjlxdm(String hHjlxdm) {
        this.hHjlxdm = hHjlxdm;
    }

    public Date getScjs() {
        return scjs;
    }

    public void setScjs(Date scjs) {
        this.scjs = scjs;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", mid=").append(mid);
            sb.append(", fxsj=").append(fxsj);
            sb.append(", sJl=").append(sJl);
            sb.append(", sGcz=").append(sGcz);
            sb.append(", sFw=").append(sFw);
            sb.append(", sFf=").append(sFf);
            sb.append(", sWzm=").append(sWzm);
            sb.append(", sQtZx=").append(sQtZx);
            sb.append(", sQtZy=").append(sQtZy);
            sb.append(", sQtZd=").append(sQtZd);
            sb.append(", cZsbh=").append(cZsbh);
            sb.append(", hZjajl=").append(hZjajl);
            sb.append(", hMztds=").append(hMztds);
            sb.append(", hXwms=").append(hXwms);
            sb.append(", hHjlxdm=").append(hHjlxdm);
            sb.append(", scjs=").append(scjs);
        sb.append("]");
        return sb.toString();
    }

}
package com.pd.server.main.domain;

import java.util.Date;

public class AppMonitorDiscovery {
    private String id;

    private String mid;

    private Date fxsj;

    private String sJl;

    private String sGcz;

    private String sFw;

    private String sFf;

    private String sWzm;

    private String sQtZx;

    private String sQtZy;

    private String sQtZd;

    private String cZsbh;

    private String hZjajl;

    private String hMztds;

    private String hXwms;

    private String hHjlxdm;

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
        sb.append(", gczxm=").append(gczxm);
        sb.append(", deptcode=").append(deptcode);
        sb.append("]");
        return sb.toString();
    }
}
package com.pd.server.main.domain;

public class DeptYwlx {
    private String id;

    private String deptcode;

    private String ywfl;

    private String ywlx;

    private String bslc;

    private String sxzl;

    private String lcto;

    private String lctt;

    private String lcth;

    private String lctf;

    private String lctfi;

    private String bz;

    private String zt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getYwfl() {
        return ywfl;
    }

    public void setYwfl(String ywfl) {
        this.ywfl = ywfl;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getBslc() {
        return bslc;
    }

    public void setBslc(String bslc) {
        this.bslc = bslc;
    }

    public String getSxzl() {
        return sxzl;
    }

    public void setSxzl(String sxzl) {
        this.sxzl = sxzl;
    }

    public String getLcto() {
        return lcto;
    }

    public void setLcto(String lcto) {
        this.lcto = lcto;
    }

    public String getLctt() {
        return lctt;
    }

    public void setLctt(String lctt) {
        this.lctt = lctt;
    }

    public String getLcth() {
        return lcth;
    }

    public void setLcth(String lcth) {
        this.lcth = lcth;
    }

    public String getLctf() {
        return lctf;
    }

    public void setLctf(String lctf) {
        this.lctf = lctf;
    }

    public String getLctfi() {
        return lctfi;
    }

    public void setLctfi(String lctfi) {
        this.lctfi = lctfi;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", ywfl=").append(ywfl);
        sb.append(", ywlx=").append(ywlx);
        sb.append(", bslc=").append(bslc);
        sb.append(", sxzl=").append(sxzl);
        sb.append(", lcto=").append(lcto);
        sb.append(", lctt=").append(lctt);
        sb.append(", lcth=").append(lcth);
        sb.append(", lctf=").append(lctf);
        sb.append(", lctfi=").append(lctfi);
        sb.append(", bz=").append(bz);
        sb.append(", zt=").append(zt);
        sb.append("]");
        return sb.toString();
    }
}
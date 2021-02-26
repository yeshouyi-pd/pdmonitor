package com.pd.server.main.domain;

import java.util.Date;

public class WebchatFileinfo {
    private String id;

    private String zpzl;

    private String zplj;

    private String lsh;

    private String bz;

    private Date cjsj;

    private String zplx;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZpzl() {
        return zpzl;
    }

    public void setZpzl(String zpzl) {
        this.zpzl = zpzl;
    }

    public String getZplj() {
        return zplj;
    }

    public void setZplj(String zplj) {
        this.zplj = zplj;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getZplx() {
        return zplx;
    }

    public void setZplx(String zplx) {
        this.zplx = zplx;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", zpzl=").append(zpzl);
        sb.append(", zplj=").append(zplj);
        sb.append(", lsh=").append(lsh);
        sb.append(", bz=").append(bz);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", zplx=").append(zplx);
        sb.append("]");
        return sb.toString();
    }
}
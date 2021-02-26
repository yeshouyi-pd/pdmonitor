package com.pd.server.main.dto;

import java.util.Date;

public class WebchatVehicleDto {
    private Integer id;

    private String xm;

    private String sfzmhm;

    private String hphm;

    private String hpzl;

    private String clsbdh;

    private String fdjh;

    private Date cjsj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", xm=").append(xm);
        sb.append(", sfzmhm=").append(sfzmhm);
        sb.append(", hphm=").append(hphm);
        sb.append(", hpzl=").append(hpzl);
        sb.append(", clsbdh=").append(clsbdh);
        sb.append(", fdjh=").append(fdjh);
        sb.append(", cjsj=").append(cjsj);
        sb.append("]");
        return sb.toString();
    }
}

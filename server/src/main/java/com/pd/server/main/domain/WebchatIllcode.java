package com.pd.server.main.domain;

import java.util.Date;

public class WebchatIllcode {
    private String id;

    private String wfdm;

    private String wfxw;

    private String wftl;

    private String cfyj;

    private String jf;

    private Float fkje;

    private String qtcf;

    private String cs;

    private String bz;

    private String deptcode;

    private String usercode;

    private Date fbsj;

    private String zt;

    private String lb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWfdm() {
        return wfdm;
    }

    public void setWfdm(String wfdm) {
        this.wfdm = wfdm;
    }

    public String getWfxw() {
        return wfxw;
    }

    public void setWfxw(String wfxw) {
        this.wfxw = wfxw;
    }

    public String getWftl() {
        return wftl;
    }

    public void setWftl(String wftl) {
        this.wftl = wftl;
    }

    public String getCfyj() {
        return cfyj;
    }

    public void setCfyj(String cfyj) {
        this.cfyj = cfyj;
    }

    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    public Float getFkje() {
        return fkje;
    }

    public void setFkje(Float fkje) {
        this.fkje = fkje;
    }

    public String getQtcf() {
        return qtcf;
    }

    public void setQtcf(String qtcf) {
        this.qtcf = qtcf;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public Date getFbsj() {
        return fbsj;
    }

    public void setFbsj(Date fbsj) {
        this.fbsj = fbsj;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", wfdm=").append(wfdm);
        sb.append(", wfxw=").append(wfxw);
        sb.append(", wftl=").append(wftl);
        sb.append(", cfyj=").append(cfyj);
        sb.append(", jf=").append(jf);
        sb.append(", fkje=").append(fkje);
        sb.append(", qtcf=").append(qtcf);
        sb.append(", cs=").append(cs);
        sb.append(", bz=").append(bz);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", usercode=").append(usercode);
        sb.append(", fbsj=").append(fbsj);
        sb.append(", zt=").append(zt);
        sb.append(", lb=").append(lb);
        sb.append("]");
        return sb.toString();
    }
}
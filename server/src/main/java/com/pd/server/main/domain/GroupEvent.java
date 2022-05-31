package com.pd.server.main.domain;

import java.util.Date;

public class GroupEvent {
    private String id;

    private String sbbh;

    private Date kssj;

    private Date jssj;

    private Date cjsj;

    private String sm;

    private String bz;

    private String bz1;

    private String bz2;

    private String bz3;

    private String deptcode;

    private String zcfwj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public Date getKssj() {
        return kssj;
    }

    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }

    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    public String getBz3() {
        return bz3;
    }

    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getZcfwj() {
        return zcfwj;
    }

    public void setZcfwj(String zcfwj) {
        this.zcfwj = zcfwj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sbbh=").append(sbbh);
        sb.append(", kssj=").append(kssj);
        sb.append(", jssj=").append(jssj);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", sm=").append(sm);
        sb.append(", bz=").append(bz);
        sb.append(", bz1=").append(bz1);
        sb.append(", bz2=").append(bz2);
        sb.append(", bz3=").append(bz3);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", zcfwj=").append(zcfwj);
        sb.append("]");
        return sb.toString();
    }
}
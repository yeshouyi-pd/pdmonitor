package com.pd.server.main.domain;

import java.util.Date;

public class EventCameraInfo {
    private String id;

    private String dvrip;

    private Integer tdh;

    private String sxtbh;

    private Date kssj;

    private Date jssj;

    private String zt;

    private String dz;

    private String sjid;

    private String sbsn;

    private String deptcode;

    private String bz;

    private String sm;

    private String sm1;

    private String sm2;

    private String sm3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDvrip() {
        return dvrip;
    }

    public void setDvrip(String dvrip) {
        this.dvrip = dvrip;
    }

    public Integer getTdh() {
        return tdh;
    }

    public void setTdh(Integer tdh) {
        this.tdh = tdh;
    }

    public String getSxtbh() {
        return sxtbh;
    }

    public void setSxtbh(String sxtbh) {
        this.sxtbh = sxtbh;
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

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getSjid() {
        return sjid;
    }

    public void setSjid(String sjid) {
        this.sjid = sjid;
    }

    public String getSbsn() {
        return sbsn;
    }

    public void setSbsn(String sbsn) {
        this.sbsn = sbsn;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getSm1() {
        return sm1;
    }

    public void setSm1(String sm1) {
        this.sm1 = sm1;
    }

    public String getSm2() {
        return sm2;
    }

    public void setSm2(String sm2) {
        this.sm2 = sm2;
    }

    public String getSm3() {
        return sm3;
    }

    public void setSm3(String sm3) {
        this.sm3 = sm3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dvrip=").append(dvrip);
        sb.append(", tdh=").append(tdh);
        sb.append(", sxtbh=").append(sxtbh);
        sb.append(", kssj=").append(kssj);
        sb.append(", jssj=").append(jssj);
        sb.append(", zt=").append(zt);
        sb.append(", dz=").append(dz);
        sb.append(", sjid=").append(sjid);
        sb.append(", sbsn=").append(sbsn);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append(", sm1=").append(sm1);
        sb.append(", sm2=").append(sm2);
        sb.append(", sm3=").append(sm3);
        sb.append("]");
        return sb.toString();
    }
}
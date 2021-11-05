package com.pd.server.main.domain;

import java.util.Date;

public class WaterProject {
    private String id;

    private String xmbh;

    private String xmmc;

    private Date kssj;

    private Date jssj;

    private String fzr;

    private String deptcode;

    private String cjr;

    private Date cjsj;

    private Date gxsj;

    private String xmyt;

    private String sm1;

    private String sm2;

    private String sm3;

    private String sm4;

    private String sm5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
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

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getXmyt() {
        return xmyt;
    }

    public void setXmyt(String xmyt) {
        this.xmyt = xmyt;
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

    public String getSm4() {
        return sm4;
    }

    public void setSm4(String sm4) {
        this.sm4 = sm4;
    }

    public String getSm5() {
        return sm5;
    }

    public void setSm5(String sm5) {
        this.sm5 = sm5;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", xmbh=").append(xmbh);
        sb.append(", xmmc=").append(xmmc);
        sb.append(", kssj=").append(kssj);
        sb.append(", jssj=").append(jssj);
        sb.append(", fzr=").append(fzr);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", cjr=").append(cjr);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", gxsj=").append(gxsj);
        sb.append(", xmyt=").append(xmyt);
        sb.append(", sm1=").append(sm1);
        sb.append(", sm2=").append(sm2);
        sb.append(", sm3=").append(sm3);
        sb.append(", sm4=").append(sm4);
        sb.append(", sm5=").append(sm5);
        sb.append("]");
        return sb.toString();
    }
}
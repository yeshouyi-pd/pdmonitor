package com.pd.server.main.domain;

import java.util.Date;

public class EquipmentFileTy {
    private String id;

    private String sbbh;

    private String tplj;

    private Date cjsj;

    private Date createTime;

    private String nf;

    private String yf;

    private String rq;

    private String xs;

    private String fz;

    private String deptcode;

    private String xmbh;

    private String wjlx;

    private String jczl;

    private String ly;

    private String sm1;

    private String sm2;

    private String sm3;

    private String sm4;

    private String sm5;

    private String sm6;

    private String type;

    private String ts;

    private String txtlx;

    private String wjmc;

    private Integer syncFlag;

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

    public String getTplj() {
        return tplj;
    }

    public void setTplj(String tplj) {
        this.tplj = tplj;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getXs() {
        return xs;
    }

    public void setXs(String xs) {
        this.xs = xs;
    }

    public String getFz() {
        return fz;
    }

    public void setFz(String fz) {
        this.fz = fz;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getWjlx() {
        return wjlx;
    }

    public void setWjlx(String wjlx) {
        this.wjlx = wjlx;
    }

    public String getJczl() {
        return jczl;
    }

    public void setJczl(String jczl) {
        this.jczl = jczl;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
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

    public String getSm6() {
        return sm6;
    }

    public void setSm6(String sm6) {
        this.sm6 = sm6;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getTxtlx() {
        return txtlx;
    }

    public void setTxtlx(String txtlx) {
        this.txtlx = txtlx;
    }

    public String getWjmc() {
        return wjmc;
    }

    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }

    public Integer getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(Integer syncFlag) {
        this.syncFlag = syncFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sbbh=").append(sbbh);
        sb.append(", tplj=").append(tplj);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", createTime=").append(createTime);
        sb.append(", nf=").append(nf);
        sb.append(", yf=").append(yf);
        sb.append(", rq=").append(rq);
        sb.append(", xs=").append(xs);
        sb.append(", fz=").append(fz);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", xmbh=").append(xmbh);
        sb.append(", wjlx=").append(wjlx);
        sb.append(", jczl=").append(jczl);
        sb.append(", ly=").append(ly);
        sb.append(", sm1=").append(sm1);
        sb.append(", sm2=").append(sm2);
        sb.append(", sm3=").append(sm3);
        sb.append(", sm4=").append(sm4);
        sb.append(", sm5=").append(sm5);
        sb.append(", sm6=").append(sm6);
        sb.append(", type=").append(type);
        sb.append(", ts=").append(ts);
        sb.append(", txtlx=").append(txtlx);
        sb.append(", wjmc=").append(wjmc);
        sb.append(", syncFlag=").append(syncFlag);
        sb.append("]");
        return sb.toString();
    }
}
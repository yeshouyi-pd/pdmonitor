package com.pd.server.main.domain;

import java.util.Date;

public class EquipmentFile {
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
        sb.append("]");
        return sb.toString();
    }
}
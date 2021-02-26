package com.pd.server.main.domain;

public class WxYyStatistics {
    private String id;

    private String deptcode;

    private String ywfl;

    private String ywlx;

    private String zt;

    private String nf;

    private String yf;

    private String rq;

    private Integer yysl;

    private String bzzd1;

    private String bzzd2;

    private String bzzd3;

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

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
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

    public Integer getYysl() {
        return yysl;
    }

    public void setYysl(Integer yysl) {
        this.yysl = yysl;
    }

    public String getBzzd1() {
        return bzzd1;
    }

    public void setBzzd1(String bzzd1) {
        this.bzzd1 = bzzd1;
    }

    public String getBzzd2() {
        return bzzd2;
    }

    public void setBzzd2(String bzzd2) {
        this.bzzd2 = bzzd2;
    }

    public String getBzzd3() {
        return bzzd3;
    }

    public void setBzzd3(String bzzd3) {
        this.bzzd3 = bzzd3;
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
        sb.append(", zt=").append(zt);
        sb.append(", nf=").append(nf);
        sb.append(", yf=").append(yf);
        sb.append(", rq=").append(rq);
        sb.append(", yysl=").append(yysl);
        sb.append(", bzzd1=").append(bzzd1);
        sb.append(", bzzd2=").append(bzzd2);
        sb.append(", bzzd3=").append(bzzd3);
        sb.append("]");
        return sb.toString();
    }
}
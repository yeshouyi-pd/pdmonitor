package com.pd.server.main.domain;

public class DrvExamrecord {
    private String id;

    private String kskm;

    private String dtlb;

    private String tmbt;

    private String a;

    private String b;

    private String c;

    private String d;

    private String tp1;

    private String tp2;

    private String zqda;

    private String xzda;

    private String wxid;

    private String dtsm;

    private Integer ktsx;

    private String examlogid;

    private String examid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKskm() {
        return kskm;
    }

    public void setKskm(String kskm) {
        this.kskm = kskm;
    }

    public String getDtlb() {
        return dtlb;
    }

    public void setDtlb(String dtlb) {
        this.dtlb = dtlb;
    }

    public String getTmbt() {
        return tmbt;
    }

    public void setTmbt(String tmbt) {
        this.tmbt = tmbt;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getTp1() {
        return tp1;
    }

    public void setTp1(String tp1) {
        this.tp1 = tp1;
    }

    public String getTp2() {
        return tp2;
    }

    public void setTp2(String tp2) {
        this.tp2 = tp2;
    }

    public String getZqda() {
        return zqda;
    }

    public void setZqda(String zqda) {
        this.zqda = zqda;
    }

    public String getXzda() {
        return xzda;
    }

    public void setXzda(String xzda) {
        this.xzda = xzda;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getDtsm() {
        return dtsm;
    }

    public void setDtsm(String dtsm) {
        this.dtsm = dtsm;
    }

    public Integer getKtsx() {
        return ktsx;
    }

    public void setKtsx(Integer ktsx) {
        this.ktsx = ktsx;
    }

    public String getExamlogid() {
        return examlogid;
    }

    public void setExamlogid(String examlogid) {
        this.examlogid = examlogid;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", kskm=").append(kskm);
        sb.append(", dtlb=").append(dtlb);
        sb.append(", tmbt=").append(tmbt);
        sb.append(", a=").append(a);
        sb.append(", b=").append(b);
        sb.append(", c=").append(c);
        sb.append(", d=").append(d);
        sb.append(", tp1=").append(tp1);
        sb.append(", tp2=").append(tp2);
        sb.append(", zqda=").append(zqda);
        sb.append(", xzda=").append(xzda);
        sb.append(", wxid=").append(wxid);
        sb.append(", dtsm=").append(dtsm);
        sb.append(", ktsx=").append(ktsx);
        sb.append(", examlogid=").append(examlogid);
        sb.append(", examid=").append(examid);
        sb.append("]");
        return sb.toString();
    }
}
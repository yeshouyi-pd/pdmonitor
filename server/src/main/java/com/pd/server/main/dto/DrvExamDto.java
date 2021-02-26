package com.pd.server.main.dto;


public class DrvExamDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 电动车摩托车网上学习
    */
    private String kskm;

    /**
    * 答题类别1单选2多选
    */
    private String dtlb;

    /**
    * 考试题目标题
    */
    private String tmbt;

    /**
    * 答案A
    */
    private String a;

    /**
    * 答案B
    */
    private String b;

    /**
    * 答案C
    */
    private String c;

    /**
    * 答案D
    */
    private String d;

    /**
    * 图片1
    */
    private String tp1;

    /**
    * 图片2
    */
    private String tp2;

    /**
    * 准确答案
    */
    private String zqda;

    /**
    * 答题说明
    */
    private String dtsm;

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

    public String getDtsm() {
    return dtsm;
    }

    public void setDtsm(String dtsm) {
    this.dtsm = dtsm;
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
    sb.append(", dtsm=").append(dtsm);
sb.append("]");
return sb.toString();
}

}
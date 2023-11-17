package com.pd.server.main.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SysLogDto extends  PageDto{

    /**
    * ID
    */
    private String id;

    /**
    * 操作人
    */
    private String czr;

    /**
    * 操作ip
    */
    private String czip;

    /**
    * 操作菜单
    */
    private String czcd;

    /**
    * 操作说明
    */
    private String czsm;

    /**
    * 操作结果 | 1 成功 0 失败
    */
    private String czjg;

    /**
    * 操作失败原因
    */
    private String czsbyy;

    /**
    * 操作时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date czsj;

    /**
    * 操作条件
    */
    private String cztj;

    /**
    * 请求数据
    */
    private String qqsj;

    /**
    * 返回数据
    */
    private String ffsj;

    /**
    * 日志类型
    */
    private String rzlx;

    /**
    * 预警 | 1 正常 0 异常
    */
    private String yj;

    /**
    * 
    */
    private String f1;

    /**
    * 
    */
    private String f2;

    /**
    * j校验位
    */
    private String jyw;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getCzr() {
    return czr;
    }

    public void setCzr(String czr) {
    this.czr = czr;
    }

    public String getCzip() {
    return czip;
    }

    public void setCzip(String czip) {
    this.czip = czip;
    }

    public String getCzcd() {
    return czcd;
    }

    public void setCzcd(String czcd) {
    this.czcd = czcd;
    }

    public String getCzsm() {
    return czsm;
    }

    public void setCzsm(String czsm) {
    this.czsm = czsm;
    }

    public String getCzjg() {
    return czjg;
    }

    public void setCzjg(String czjg) {
    this.czjg = czjg;
    }

    public String getCzsbyy() {
    return czsbyy;
    }

    public void setCzsbyy(String czsbyy) {
    this.czsbyy = czsbyy;
    }


    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public String getCztj() {
    return cztj;
    }

    public void setCztj(String cztj) {
    this.cztj = cztj;
    }

    public String getQqsj() {
    return qqsj;
    }

    public void setQqsj(String qqsj) {
    this.qqsj = qqsj;
    }

    public String getFfsj() {
    return ffsj;
    }

    public void setFfsj(String ffsj) {
    this.ffsj = ffsj;
    }

    public String getRzlx() {
    return rzlx;
    }

    public void setRzlx(String rzlx) {
    this.rzlx = rzlx;
    }

    public String getYj() {
    return yj;
    }

    public void setYj(String yj) {
    this.yj = yj;
    }

    public String getF1() {
    return f1;
    }

    public void setF1(String f1) {
    this.f1 = f1;
    }

    public String getF2() {
    return f2;
    }

    public void setF2(String f2) {
    this.f2 = f2;
    }

    public String getJyw() {
    return jyw;
    }

    public void setJyw(String jyw) {
    this.jyw = jyw;
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", czr=").append(czr);
    sb.append(", czip=").append(czip);
    sb.append(", czcd=").append(czcd);
    sb.append(", czsm=").append(czsm);
    sb.append(", czjg=").append(czjg);
    sb.append(", czsbyy=").append(czsbyy);
    sb.append(", czsj=").append(czsj);
    sb.append(", cztj=").append(cztj);
    sb.append(", qqsj=").append(qqsj);
    sb.append(", ffsj=").append(ffsj);
    sb.append(", rzlx=").append(rzlx);
    sb.append(", yj=").append(yj);
    sb.append(", f1=").append(f1);
    sb.append(", f2=").append(f2);
    sb.append(", jyw=").append(jyw);
sb.append("]");
return sb.toString();
}

}
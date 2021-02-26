package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class JdccxLogDto {

    /**
    * 
    */
    private String id;

    /**
    * 录入人
    */
    private String createBy;

    /**
    * 录入日期
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 请求参数
    */
    private String qqcs;

    /**
    * 返回状态码code
    */
    private String fhztm;

    /**
    * 返回结果result
    */
    private String fhjg;

    /**
    * 返回车架号
    */
    private String cjh;

    /**
    * 返回登记日期
    */
    private String zcrq;

    /**
    * 返回生产厂家
    */
    private String sccj;

    /**
    * 返回登记地点
    */
    private String dw;

    /**
    * 返回手机号码
    */
    private String sjhm;

    /**
    * 返回号牌种类|("01": '蓝牌电动车(合格)', "02": '黄牌电动车(临时)', "03": '残疾人电动')
    */
    private String hpzl;

    /**
    * 返回证件号
    */
    private String zhhm;

    /**
    * 返回车辆号码
    */
    private String hphm;

    /**
    * 返回所有人
    */
    private String syr;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getCreateBy() {
    return createBy;
    }

    public void setCreateBy(String createBy) {
    this.createBy = createBy;
    }

    public Date getCreateTime() {
    return createTime;
    }

    public void setCreateTime(Date createTime) {
    this.createTime = createTime;
    }

    public String getQqcs() {
    return qqcs;
    }

    public void setQqcs(String qqcs) {
    this.qqcs = qqcs;
    }

    public String getFhztm() {
    return fhztm;
    }

    public void setFhztm(String fhztm) {
    this.fhztm = fhztm;
    }

    public String getFhjg() {
    return fhjg;
    }

    public void setFhjg(String fhjg) {
    this.fhjg = fhjg;
    }

    public String getCjh() {
    return cjh;
    }

    public void setCjh(String cjh) {
    this.cjh = cjh;
    }

    public String getZcrq() {
    return zcrq;
    }

    public void setZcrq(String zcrq) {
    this.zcrq = zcrq;
    }

    public String getSccj() {
    return sccj;
    }

    public void setSccj(String sccj) {
    this.sccj = sccj;
    }

    public String getDw() {
    return dw;
    }

    public void setDw(String dw) {
    this.dw = dw;
    }

    public String getSjhm() {
    return sjhm;
    }

    public void setSjhm(String sjhm) {
    this.sjhm = sjhm;
    }

    public String getHpzl() {
    return hpzl;
    }

    public void setHpzl(String hpzl) {
    this.hpzl = hpzl;
    }

    public String getZhhm() {
    return zhhm;
    }

    public void setZhhm(String zhhm) {
    this.zhhm = zhhm;
    }

    public String getHphm() {
    return hphm;
    }

    public void setHphm(String hphm) {
    this.hphm = hphm;
    }

    public String getSyr() {
    return syr;
    }

    public void setSyr(String syr) {
    this.syr = syr;
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", createBy=").append(createBy);
    sb.append(", createTime=").append(createTime);
    sb.append(", qqcs=").append(qqcs);
    sb.append(", fhztm=").append(fhztm);
    sb.append(", fhjg=").append(fhjg);
    sb.append(", cjh=").append(cjh);
    sb.append(", zcrq=").append(zcrq);
    sb.append(", sccj=").append(sccj);
    sb.append(", dw=").append(dw);
    sb.append(", sjhm=").append(sjhm);
    sb.append(", hpzl=").append(hpzl);
    sb.append(", zhhm=").append(zhhm);
    sb.append(", hphm=").append(hphm);
    sb.append(", syr=").append(syr);
sb.append("]");
return sb.toString();
}

}
package com.pd.server.main.dto;


import java.util.List;

public class VehParkLotStatisticsDto {

    /**
    * 
    */
    private String id;

    /**
    * 电动车黄牌
    */
    private Integer ddchp;

    /**
    * 电动车蓝牌
    */
    private Integer ddcnp;

    /**
    * 电动车无牌
    */
    private Integer ddcwp;

    /**
    * 摩托车有牌
    */
    private Integer mtcyp;

    /**
    * 摩托车无牌
    */
    private Integer mtcwp;

    /**
    * 三轮车
    */
    private Integer slc;

    /**
    * 汽车
    */
    private Integer qc;

    /**
    * 农用车
    */
    private Integer nyc;

    /**
    * 其他
    */
    private Integer qt;

    /**
    * 停车场
    */
    private String sysOrgCode;

    /**
    * 状态1入场2出场
    */
    private String zt;

    /**
    * 创建时间
    */
    private String createTime;

    private Integer sumddchp;

    private Integer sumddcnp;

    private Integer sumddcwp;

    private Integer summtcyp;

    private Integer summtcwp;

    private Integer sumslc;

    private Integer sumqc;

    private Integer sumnyc;

    private Integer sumqt;

    private   String chooseType;//统计类型

    private   String  checkdate;//统计时间

    private List<String> sysOrgCodeListStr;  //待统计部门

    public String getChooseType() {
        return chooseType;
    }

    public void setChooseType(String chooseType) {
        this.chooseType = chooseType;
    }

    public String getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(String checkdate) {
        this.checkdate = checkdate;
    }

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public Integer getDdchp() {
    return ddchp;
    }

    public void setDdchp(Integer ddchp) {
    this.ddchp = ddchp;
    }

    public Integer getDdcnp() {
    return ddcnp;
    }

    public void setDdcnp(Integer ddcnp) {
    this.ddcnp = ddcnp;
    }

    public Integer getDdcwp() {
    return ddcwp;
    }

    public void setDdcwp(Integer ddcwp) {
    this.ddcwp = ddcwp;
    }

    public Integer getMtcyp() {
    return mtcyp;
    }

    public void setMtcyp(Integer mtcyp) {
    this.mtcyp = mtcyp;
    }

    public Integer getMtcwp() {
    return mtcwp;
    }

    public void setMtcwp(Integer mtcwp) {
    this.mtcwp = mtcwp;
    }

    public Integer getSlc() {
    return slc;
    }

    public void setSlc(Integer slc) {
    this.slc = slc;
    }

    public Integer getQc() {
    return qc;
    }

    public void setQc(Integer qc) {
    this.qc = qc;
    }

    public Integer getNyc() {
    return nyc;
    }

    public void setNyc(Integer nyc) {
    this.nyc = nyc;
    }

    public Integer getQt() {
    return qt;
    }

    public void setQt(Integer qt) {
    this.qt = qt;
    }

    public String getSysOrgCode() {
    return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
    this.sysOrgCode = sysOrgCode;
    }

    public String getZt() {
    return zt;
    }

    public void setZt(String zt) {
    this.zt = zt;
    }

    public String getCreateTime() {
    return createTime;
    }

    public void setCreateTime(String createTime) {
    this.createTime = createTime;
    }

    public Integer getSumddchp() {
        return sumddchp;
    }

    public void setSumddchp(Integer sumddchp) {
        this.sumddchp = sumddchp;
    }

    public Integer getSumddcnp() {
        return sumddcnp;
    }

    public void setSumddcnp(Integer sumddcnp) {
        this.sumddcnp = sumddcnp;
    }

    public Integer getSumddcwp() {
        return sumddcwp;
    }

    public void setSumddcwp(Integer sumddcwp) {
        this.sumddcwp = sumddcwp;
    }

    public Integer getSummtcyp() {
        return summtcyp;
    }

    public void setSummtcyp(Integer summtcyp) {
        this.summtcyp = summtcyp;
    }

    public Integer getSummtcwp() {
        return summtcwp;
    }

    public void setSummtcwp(Integer summtcwp) {
        this.summtcwp = summtcwp;
    }

    public Integer getSumslc() {
        return sumslc;
    }

    public void setSumslc(Integer sumslc) {
        this.sumslc = sumslc;
    }

    public Integer getSumqc() {
        return sumqc;
    }

    public void setSumqc(Integer sumqc) {
        this.sumqc = sumqc;
    }

    public Integer getSumnyc() {
        return sumnyc;
    }

    public void setSumnyc(Integer sumnyc) {
        this.sumnyc = sumnyc;
    }

    public Integer getSumqt() {
        return sumqt;
    }

    public void setSumqt(Integer sumqt) {
        this.sumqt = sumqt;
    }


    public List<String> getSysOrgCodeListStr() {
        return sysOrgCodeListStr;
    }

    public void setSysOrgCodeListStr(List<String> sysOrgCodeListStr) {
        this.sysOrgCodeListStr = sysOrgCodeListStr;
    }

    @Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", ddchp=").append(ddchp);
    sb.append(", ddcnp=").append(ddcnp);
    sb.append(", ddcwp=").append(ddcwp);
    sb.append(", mtcyp=").append(mtcyp);
    sb.append(", mtcwp=").append(mtcwp);
    sb.append(", slc=").append(slc);
    sb.append(", qc=").append(qc);
    sb.append(", nyc=").append(nyc);
    sb.append(", qt=").append(qt);
    sb.append(", sysOrgCode=").append(sysOrgCode);
    sb.append(", zt=").append(zt);
    sb.append(", createTime=").append(createTime);
sb.append("]");
return sb.toString();
}

}
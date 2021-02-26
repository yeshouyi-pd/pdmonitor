package com.pd.server.main.dto;


import java.util.List;

public class WxYyStatisticsDto {

    /**
    * 
    */
    private String id;

    /**
    * 部门编号
    */
    private String deptcode;

    /**
    * 业务分类
    */
    private String ywfl;

    /**
    * 业务类型
    */
    private String ywlx;

    /**
    * 状态
    */
    private String zt;

    /**
    * 年份
    */
    private String nf;

    /**
    * 月份
    */
    private String yf;

    /**
    * 日期
    */
    private String rq;

    /**
    * 预约数量
    */
    private Integer yysl;

    /**
    * 备注字段1
    */
    private String bzzd1;

    /**
    * 备注字段2
    */
    private String bzzd2;

    /**
    * 备注字段3
    */
    private String bzzd3;

    private List<String> ywflList;

    private List<String> deptcodeList;

    private String chooseType;

    private Integer sumYysl;

    private Integer sumjdcxkcxczc;//机动车-小客车新车注册

    private Integer sumjdcescgh;//机动车-二手车过户

    private Integer sumjdcxkczr;//机动车-小客车转入

    private Integer sumjdcbhljdchp;//机动车-补换领机动车号牌

    private Integer sumjdcbhlxsz;//机动车-补换领行驶证

    private Integer sumjdcdyyw;//机动车-抵押业务

    private Integer sumjdcdzxzcdj;//机动车-大中型注册登记

    private Integer sumjdcdzxzycyw;//机动车-大中型在用车业务

    private Integer sumjdcqtyw;//机动车-其他业务

    private Integer sumjszwdzr;//驾驶证-外地转入业务

    private Integer sumjszqmhz;//驾驶证-期满换证

    private Integer sumjszbhlyw;//驾驶证-补换领业务

    private Integer sumjszjjhz;//驾驶证-军警换证

    private Integer sumjszjwhz;//驾驶证-境外换证

    private Integer sumjszmfyw;//驾驶证-满分业务

    private Integer sumjszsyyw;//驾驶证-审验业务

    private Integer sumjszqtyw;//驾驶证-其他业务

    private Integer sumwfjdc;//违法-机动车违法处理

    private Integer sumwfddc;//违法-电动车

    private Integer sumjdcdcs;//机动车-到场数

    private Integer sumjszdcs;//驾驶证-到场数

    private Integer sumwfdcs;//违法-到场数

    private Integer allYysl;//所有的预约数量

    private Integer allDcs;//所有的到场数

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

    public List<String> getYwflList() {
        return ywflList;
    }

    public void setYwflList(List<String> ywflList) {
        this.ywflList = ywflList;
    }

    public List<String> getDeptcodeList() {
        return deptcodeList;
    }

    public void setDeptcodeList(List<String> deptcodeList) {
        this.deptcodeList = deptcodeList;
    }

    public String getChooseType() {
        return chooseType;
    }

    public void setChooseType(String chooseType) {
        this.chooseType = chooseType;
    }

    public Integer getSumYysl() {
        return sumYysl;
    }

    public void setSumYysl(Integer sumYysl) {
        this.sumYysl = sumYysl;
    }

    public Integer getSumjdcxkcxczc() {
        return sumjdcxkcxczc;
    }

    public void setSumjdcxkcxczc(Integer sumjdcxkcxczc) {
        this.sumjdcxkcxczc = sumjdcxkcxczc;
    }

    public Integer getSumjdcescgh() {
        return sumjdcescgh;
    }

    public void setSumjdcescgh(Integer sumjdcescgh) {
        this.sumjdcescgh = sumjdcescgh;
    }

    public Integer getSumjdcxkczr() {
        return sumjdcxkczr;
    }

    public void setSumjdcxkczr(Integer sumjdcxkczr) {
        this.sumjdcxkczr = sumjdcxkczr;
    }

    public Integer getSumjdcbhljdchp() {
        return sumjdcbhljdchp;
    }

    public void setSumjdcbhljdchp(Integer sumjdcbhljdchp) {
        this.sumjdcbhljdchp = sumjdcbhljdchp;
    }

    public Integer getSumjdcbhlxsz() {
        return sumjdcbhlxsz;
    }

    public void setSumjdcbhlxsz(Integer sumjdcbhlxsz) {
        this.sumjdcbhlxsz = sumjdcbhlxsz;
    }

    public Integer getSumjdcdyyw() {
        return sumjdcdyyw;
    }

    public void setSumjdcdyyw(Integer sumjdcdyyw) {
        this.sumjdcdyyw = sumjdcdyyw;
    }

    public Integer getSumjdcdzxzcdj() {
        return sumjdcdzxzcdj;
    }

    public void setSumjdcdzxzcdj(Integer sumjdcdzxzcdj) {
        this.sumjdcdzxzcdj = sumjdcdzxzcdj;
    }

    public Integer getSumjdcdzxzycyw() {
        return sumjdcdzxzycyw;
    }

    public void setSumjdcdzxzycyw(Integer sumjdcdzxzycyw) {
        this.sumjdcdzxzycyw = sumjdcdzxzycyw;
    }

    public Integer getSumjdcqtyw() {
        return sumjdcqtyw;
    }

    public void setSumjdcqtyw(Integer sumjdcqtyw) {
        this.sumjdcqtyw = sumjdcqtyw;
    }

    public Integer getSumjszwdzr() {
        return sumjszwdzr;
    }

    public void setSumjszwdzr(Integer sumjszwdzr) {
        this.sumjszwdzr = sumjszwdzr;
    }

    public Integer getSumjszqmhz() {
        return sumjszqmhz;
    }

    public void setSumjszqmhz(Integer sumjszqmhz) {
        this.sumjszqmhz = sumjszqmhz;
    }

    public Integer getSumjszbhlyw() {
        return sumjszbhlyw;
    }

    public void setSumjszbhlyw(Integer sumjszbhlyw) {
        this.sumjszbhlyw = sumjszbhlyw;
    }

    public Integer getSumjszjjhz() {
        return sumjszjjhz;
    }

    public void setSumjszjjhz(Integer sumjszjjhz) {
        this.sumjszjjhz = sumjszjjhz;
    }

    public Integer getSumjszjwhz() {
        return sumjszjwhz;
    }

    public void setSumjszjwhz(Integer sumjszjwhz) {
        this.sumjszjwhz = sumjszjwhz;
    }

    public Integer getSumjszmfyw() {
        return sumjszmfyw;
    }

    public void setSumjszmfyw(Integer sumjszmfyw) {
        this.sumjszmfyw = sumjszmfyw;
    }

    public Integer getSumjszsyyw() {
        return sumjszsyyw;
    }

    public void setSumjszsyyw(Integer sumjszsyyw) {
        this.sumjszsyyw = sumjszsyyw;
    }

    public Integer getSumjszqtyw() {
        return sumjszqtyw;
    }

    public void setSumjszqtyw(Integer sumjszqtyw) {
        this.sumjszqtyw = sumjszqtyw;
    }

    public Integer getSumwfjdc() {
        return sumwfjdc;
    }

    public void setSumwfjdc(Integer sumwfjdc) {
        this.sumwfjdc = sumwfjdc;
    }

    public Integer getSumwfddc() {
        return sumwfddc;
    }

    public void setSumwfddc(Integer sumwfddc) {
        this.sumwfddc = sumwfddc;
    }

    public Integer getSumjdcdcs() {
        return sumjdcdcs;
    }

    public void setSumjdcdcs(Integer sumjdcdcs) {
        this.sumjdcdcs = sumjdcdcs;
    }

    public Integer getSumjszdcs() {
        return sumjszdcs;
    }

    public void setSumjszdcs(Integer sumjszdcs) {
        this.sumjszdcs = sumjszdcs;
    }

    public Integer getSumwfdcs() {
        return sumwfdcs;
    }

    public void setSumwfdcs(Integer sumwfdcs) {
        this.sumwfdcs = sumwfdcs;
    }

    public Integer getAllYysl() {
        return allYysl;
    }

    public void setAllYysl(Integer allYysl) {
        this.allYysl = allYysl;
    }

    public Integer getAllDcs() {
        return allDcs;
    }

    public void setAllDcs(Integer allDcs) {
        this.allDcs = allDcs;
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
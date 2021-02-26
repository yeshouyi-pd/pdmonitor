package com.pd.server.main.dto;

import java.util.List;

public class WxYyDto extends PageDto{
    private String id;

    private String deptcode;

    private String yysd;

    private String name;

    private String sjhm;

    private String zjlx;

    private String zjhm;

    private String kclx;

    private String sfxny;

    private String vin;

    private String dwmc;

    private String xydm;

    private String cjsj;

    private String yysj;

    private Integer yysl;

    private String zt;

    private String slsj;

    private String yyrq;

    private String ywfl;

    private String ywlx;

    private String openid;

    private String yytype;

    private String ywm;//一维码

    private int daymax;//临时 多状态  当前部门下当天的最大预约数 || 当前用户的当前部门最大预约数

    private String yelxname ;//前端显示

    private String deptname ;//前端显示

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

    public String getYysd() {
        return yysd;
    }

    public void setYysd(String yysd) {
        this.yysd = yysd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getKclx() {
        return kclx;
    }

    public void setKclx(String kclx) {
        this.kclx = kclx;
    }

    public String getSfxny() {
        return sfxny;
    }

    public void setSfxny(String sfxny) {
        this.sfxny = sfxny;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getYysj() {
        return yysj;
    }

    public void setYysj(String yysj) {
        this.yysj = yysj;
    }

    public Integer getYysl() {
        return yysl;
    }

    public void setYysl(Integer yysl) {
        this.yysl = yysl;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getSlsj() {
        return slsj;
    }

    public void setSlsj(String slsj) {
        this.slsj = slsj;
    }


    public String getYyrq() {
        return yyrq;
    }

    public void setYyrq(String yyrq) {
        this.yyrq = yyrq;
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public String getYytype() {
        return yytype;
    }

    public void setYytype(String yytype) {
        this.yytype = yytype;
    }

    public String getYwm() {
        return ywm;
    }

    public void setYwm(String ywm) {
        this.ywm = ywm;
    }

    public int getDaymax() {
        return daymax;
    }

    public void setDaymax(int daymax) {
        this.daymax = daymax;
    }

    public String getYelxname() {
        return yelxname;
    }

    public void setYelxname(String yelxname) {
        this.yelxname = yelxname;
    }


    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
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
        sb.append(", yysd=").append(yysd);
        sb.append(", name=").append(name);
        sb.append(", sjhm=").append(sjhm);
        sb.append(", zjlx=").append(zjlx);
        sb.append(", zjhm=").append(zjhm);
        sb.append(", kclx=").append(kclx);
        sb.append(", sfxny=").append(sfxny);
        sb.append(", vin=").append(vin);
        sb.append(", dwmc=").append(dwmc);
        sb.append(", xydm=").append(xydm);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", yysj=").append(yysj);
        sb.append(", yysl=").append(yysl);
        sb.append(", zt=").append(zt);
        sb.append(", slsj=").append(slsj);
        sb.append("]");
        return sb.toString();
    }
}
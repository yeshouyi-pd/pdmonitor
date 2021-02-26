package com.pd.server.main.domain;

public class WxYy {
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getYytype() {
        return yytype;
    }

    public void setYytype(String yytype) {
        this.yytype = yytype;
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
        sb.append(", yyrq=").append(yyrq);
        sb.append(", ywfl=").append(ywfl);
        sb.append(", ywlx=").append(ywlx);
        sb.append(", openid=").append(openid);
        sb.append(", yytype=").append(yytype);
        sb.append("]");
        return sb.toString();
    }
}
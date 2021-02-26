package com.pd.server.main.dto;


import java.util.Date;
import java.util.List;

public class DeptDto extends PageDto{

    private String id;

    private String deptcode;

    private String deptname;

    private String upcode;

    private String deptdesc;

    private String linkadd;

    private String linktel;

    private String linkman;

    private String status;

    private String depttype;

    private String zffw;

    private String jd;

    private String wd;

    private String fdr;

    private Integer maxday;

    private Date createtime;

    private String createman;

    private Date updatetime;

    private String updateman;

    private String yyzt;

    private Integer gryymax;

    private Integer qyyymax;


    private List<DeptDto> children;

    private List<String> checkjdcywVal;

    private List<String> checkjsrywVal;

    private List<String> checkfwywVal;


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

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getUpcode() {
        return upcode;
    }

    public void setUpcode(String upcode) {
        this.upcode = upcode;
    }

    public String getDeptdesc() {
        return deptdesc;
    }

    public void setDeptdesc(String deptdesc) {
        this.deptdesc = deptdesc;
    }

    public String getLinkadd() {
        return linkadd;
    }

    public void setLinkadd(String linkadd) {
        this.linkadd = linkadd;
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepttype() {
        return depttype;
    }

    public void setDepttype(String depttype) {
        this.depttype = depttype;
    }

    public String getZffw() {
        return zffw;
    }

    public void setZffw(String zffw) {
        this.zffw = zffw;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getFdr() {
        return fdr;
    }

    public void setFdr(String fdr) {
        this.fdr = fdr;
    }

    public Integer getMaxday() {
        return maxday;
    }

    public void setMaxday(Integer maxday) {
        this.maxday = maxday;
    }

    public List<DeptDto> getChildren() {
        return children;
    }

    public void setChildren(List<DeptDto> children) {
        this.children = children;
    }

    public List<String> getCheckjdcywVal() {
        return checkjdcywVal;
    }

    public void setCheckjdcywVal(List<String> checkjdcywVal) {
        this.checkjdcywVal = checkjdcywVal;
    }

    public List<String> getCheckjsrywVal() {
        return checkjsrywVal;
    }

    public void setCheckjsrywVal(List<String> checkjsrywVal) {
        this.checkjsrywVal = checkjsrywVal;
    }

    public List<String> getCheckfwywVal() {
        return checkfwywVal;
    }

    public void setCheckfwywVal(List<String> checkfwywVal) {
        this.checkfwywVal = checkfwywVal;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateman() {
        return createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateman() {
        return updateman;
    }

    public void setUpdateman(String updateman) {
        this.updateman = updateman;
    }

    public String getYyzt() {
        return yyzt;
    }

    public void setYyzt(String yyzt) {
        this.yyzt = yyzt;
    }


    public Integer getGryymax() {
        return gryymax;
    }

    public void setGryymax(Integer gryymax) {
        this.gryymax = gryymax;
    }

    public Integer getQyyymax() {
        return qyyymax;
    }

    public void setQyyymax(Integer qyyymax) {
        this.qyyymax = qyyymax;
    }

    @Override
    public String toString() {
        return "DeptDto{" +
                "id='" + id + '\'' +
                ", deptcode='" + deptcode + '\'' +
                ", deptname='" + deptname + '\'' +
                ", upcode='" + upcode + '\'' +
                ", deptdesc='" + deptdesc + '\'' +
                ", linkadd='" + linkadd + '\'' +
                ", linktel='" + linktel + '\'' +
                ", linkman='" + linkman + '\'' +
                ", status='" + status + '\'' +
                ", depttype='" + depttype + '\'' +
                ", zffw='" + zffw + '\'' +
                ", jd='" + jd + '\'' +
                ", wd='" + wd + '\'' +
                ", fdr='" + fdr + '\'' +
                ", maxday=" + maxday +
                ", createtime=" + createtime +
                ", createman='" + createman + '\'' +
                ", updatetime=" + updatetime +
                ", updateman='" + updateman + '\'' +
                ", yyzt='" + yyzt + '\'' +
                ", children=" + children +
                ", checkjdcywVal=" + checkjdcywVal +
                ", checkjsrywVal=" + checkjsrywVal +
                ", checkfwywVal=" + checkfwywVal +
                '}';
    }
}
package com.pd.server.main.dto;


import java.util.List;

public class DeptYwlxDto {

    /**
    * id
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
    * 办事流程
    */
    private String bslc;

    /**
    * 所需资料
    */
    private String sxzl;

    /**
    * 流程图一
    */
    private String lcto;

    /**
    * 流程图二
    */
    private String lctt;

    /**
    * 流程图三
    */
    private String lcth;

    /**
    * 流程图四
    */
    private String lctf;

    /**
    * 流程图五
    */
    private String lctfi;

    /**
    * 备注
    */
    private String bz;

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

    public String getBslc() {
    return bslc;
    }

    public void setBslc(String bslc) {
    this.bslc = bslc;
    }

    public String getSxzl() {
    return sxzl;
    }

    public void setSxzl(String sxzl) {
    this.sxzl = sxzl;
    }

    public String getLcto() {
    return lcto;
    }

    public void setLcto(String lcto) {
    this.lcto = lcto;
    }

    public String getLctt() {
    return lctt;
    }

    public void setLctt(String lctt) {
    this.lctt = lctt;
    }

    public String getLcth() {
    return lcth;
    }

    public void setLcth(String lcth) {
    this.lcth = lcth;
    }

    public String getLctf() {
    return lctf;
    }

    public void setLctf(String lctf) {
    this.lctf = lctf;
    }

    public String getLctfi() {
    return lctfi;
    }

    public void setLctfi(String lctfi) {
    this.lctfi = lctfi;
    }

    public String getBz() {
    return bz;
    }

    public void setBz(String bz) {
    this.bz = bz;
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
    sb.append(", bslc=").append(bslc);
    sb.append(", sxzl=").append(sxzl);
    sb.append(", lcto=").append(lcto);
    sb.append(", lctt=").append(lctt);
    sb.append(", lcth=").append(lcth);
    sb.append(", lctf=").append(lctf);
    sb.append(", lctfi=").append(lctfi);
    sb.append(", bz=").append(bz);
sb.append("]");
return sb.toString();
}

}
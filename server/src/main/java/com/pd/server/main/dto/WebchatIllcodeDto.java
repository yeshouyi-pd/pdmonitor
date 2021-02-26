package com.pd.server.main.dto;

        import java.util.Date;
        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonFormat;

public class WebchatIllcodeDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 违法代码
    */
    private String wfdm;

    /**
    * 违法行为
    */
    private String wfxw;

    /**
    * 违法条例
    */
    private String wftl;

    /**
    * 处罚依据
    */
    private String cfyj;

    /**
    * 记分
    */
    private String jf;

    /**
    * 处罚金额
    */
    private Float fkje;;

    /**
    * 其他处罚
    */
    private String qtcf;

    /**
    * 措施
    */
    private String cs;

    /**
    * 备注
    */
    private String bz;

    /**
    * 发布机构
    */
    private String deptcode;

    /**
    * 发布用户
    */
    private String usercode;

    /**
    * 发布时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fbsj;

    /**
    * 状态|1正常0禁用
    */
    private String zt;

    /**
    * 违法代码类别|1机动车2电动车
    */
    private String lb;

    private List<String> idList;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getWfdm() {
    return wfdm;
    }

    public void setWfdm(String wfdm) {
    this.wfdm = wfdm;
    }

    public String getWfxw() {
    return wfxw;
    }

    public void setWfxw(String wfxw) {
    this.wfxw = wfxw;
    }

    public String getWftl() {
    return wftl;
    }

    public void setWftl(String wftl) {
    this.wftl = wftl;
    }

    public String getCfyj() {
    return cfyj;
    }

    public void setCfyj(String cfyj) {
    this.cfyj = cfyj;
    }

    public String getJf() {
    return jf;
    }

    public void setJf(String jf) {
    this.jf = jf;
    }

    public Float getFkje() {
    return fkje;
    }

    public void setFkje(Float fkje) {
    this.fkje = fkje;
    }

    public String getQtcf() {
    return qtcf;
    }

    public void setQtcf(String qtcf) {
    this.qtcf = qtcf;
    }

    public String getCs() {
    return cs;
    }

    public void setCs(String cs) {
    this.cs = cs;
    }

    public String getBz() {
    return bz;
    }

    public void setBz(String bz) {
    this.bz = bz;
    }

    public String getDeptcode() {
    return deptcode;
    }

    public void setDeptcode(String deptcode) {
    this.deptcode = deptcode;
    }

    public String getUsercode() {
    return usercode;
    }

    public void setUsercode(String usercode) {
    this.usercode = usercode;
    }

    public Date getFbsj() {
    return fbsj;
    }

    public void setFbsj(Date fbsj) {
    this.fbsj = fbsj;
    }

    public String getZt() {
    return zt;
    }

    public void setZt(String zt) {
    this.zt = zt;
    }

    public String getLb() {
    return lb;
    }

    public void setLb(String lb) {
    this.lb = lb;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    @Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", wfdm=").append(wfdm);
    sb.append(", wfxw=").append(wfxw);
    sb.append(", wftl=").append(wftl);
    sb.append(", cfyj=").append(cfyj);
    sb.append(", jf=").append(jf);
    sb.append(", fkje=").append(fkje);
    sb.append(", qtcf=").append(qtcf);
    sb.append(", cs=").append(cs);
    sb.append(", bz=").append(bz);
    sb.append(", deptcode=").append(deptcode);
    sb.append(", usercode=").append(usercode);
    sb.append(", fbsj=").append(fbsj);
    sb.append(", zt=").append(zt);
    sb.append(", lb=").append(lb);
sb.append("]");
return sb.toString();
}

}
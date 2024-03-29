package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WaterProjectDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 项目编号
    */
    private String xmbh;

    /**
    * 项目名称
    */
    private String xmmc;

    /**
    * 开始时间
    */
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date kssj;

    /**
    * 结束时间
    */
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date jssj;

    /**
    * 项目负责人
    */
    private String fzr;

    /**
    * 所属部门
    */
    private String deptcode;

    /**
    * 
    */
    private String cjr;

    /**
    * 
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gxsj;

    /**
    * 项目用途
    */
    private String xmyt;

    /**
    * 说明1
    */
    private String sm1;

    /**
    * 说明2
    */
    private String sm2;

    /**
    * 说明3
    */
    private String sm3;

    /**
    * 说明4
    */
    private String sm4;

    /**
    * 说明5
    */
    private String sm5;

    private String userCodes;

    private String sbsnCodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public Date getKssj() {
        return kssj;
    }

    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getXmyt() {
        return xmyt;
    }

    public void setXmyt(String xmyt) {
        this.xmyt = xmyt;
    }

    public String getSm1() {
        return sm1;
    }

    public void setSm1(String sm1) {
        this.sm1 = sm1;
    }

    public String getSm2() {
        return sm2;
    }

    public void setSm2(String sm2) {
        this.sm2 = sm2;
    }

    public String getSm3() {
        return sm3;
    }

    public void setSm3(String sm3) {
        this.sm3 = sm3;
    }

    public String getSm4() {
        return sm4;
    }

    public void setSm4(String sm4) {
        this.sm4 = sm4;
    }

    public String getSm5() {
        return sm5;
    }

    public void setSm5(String sm5) {
        this.sm5 = sm5;
    }

    public String getUserCodes() {
        return userCodes;
    }

    public void setUserCodes(String userCodes) {
        this.userCodes = userCodes;
    }

    public String getSbsnCodes() {
        return sbsnCodes;
    }

    public void setSbsnCodes(String sbsnCodes) {
        this.sbsnCodes = sbsnCodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", xmmc=").append(xmmc);
            sb.append(", kssj=").append(kssj);
            sb.append(", jssj=").append(jssj);
            sb.append(", fzr=").append(fzr);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", cjr=").append(cjr);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", gxsj=").append(gxsj);
            sb.append(", xmyt=").append(xmyt);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
            sb.append(", sm4=").append(sm4);
            sb.append(", sm5=").append(sm5);
        sb.append("]");
        return sb.toString();
    }

}
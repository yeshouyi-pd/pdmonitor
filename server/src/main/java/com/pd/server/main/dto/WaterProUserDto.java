package com.pd.server.main.dto;

import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WaterProUserDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 项目编号
    */
    private String xmbh;

    /**
    * 人员账号
    */
    private String usercode;

    /**
    * 创建人
    */
    private String cjr;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 更新时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gxsj;

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

    /**
    * 是否是负责人
    */
    private String isboss;

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

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
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

    public String getIsboss() {
        return isboss;
    }

    public void setIsboss(String isboss) {
        this.isboss = isboss;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", usercode=").append(usercode);
            sb.append(", cjr=").append(cjr);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", gxsj=").append(gxsj);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
            sb.append(", sm4=").append(sm4);
            sb.append(", sm5=").append(sm5);
            sb.append(", isboss=").append(isboss);
        sb.append("]");
        return sb.toString();
    }

}
package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WaterRawfileDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 项目编号
    */
    private String xmbh;

    /**
    * 设备编号
    */
    private String sbbh;

    /**
    * 文件类型
    */
    private String wjlx;

    /**
    * 文件路径
    */
    private String wjlj;

    /**
    * 上传采集时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 分析次数
    */
    private Integer fxcs;

    /**
    * 状态0未分析1已分析2分析失败
    */
    private String zt;

    /**
    * 
    */
    private String sm1;

    /**
    * 
    */
    private String sm2;

    /**
    * 
    */
    private String sm3;

    /**
    * 
    */
    private String sm4;

    /**
    * 
    */
    private String sm5;

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

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getWjlx() {
        return wjlx;
    }

    public void setWjlx(String wjlx) {
        this.wjlx = wjlx;
    }

    public String getWjlj() {
        return wjlj;
    }

    public void setWjlj(String wjlj) {
        this.wjlj = wjlj;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Integer getFxcs() {
        return fxcs;
    }

    public void setFxcs(Integer fxcs) {
        this.fxcs = fxcs;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", sbbh=").append(sbbh);
            sb.append(", wjlx=").append(wjlx);
            sb.append(", wjlj=").append(wjlj);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", fxcs=").append(fxcs);
            sb.append(", zt=").append(zt);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
            sb.append(", sm4=").append(sm4);
            sb.append(", sm5=").append(sm5);
        sb.append("]");
        return sb.toString();
    }

}
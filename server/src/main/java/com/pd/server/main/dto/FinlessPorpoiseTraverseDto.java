package com.pd.server.main.dto;


public class FinlessPorpoiseTraverseDto extends PageDto{

    /**
    * 主键
    */
    private String id;

    /**
    * 项目编号
    */
    private String xmbh;

    /**
    * 项目编号名称
    */
    private String xmbhmc;

    /**
    * 创建时间
    */
    private String cjsj;

    /**
    * 图片路径
    */
    private String tplj;

    /**
    * 日期
    */
    private String rq;

    /**
    * 备注
    */
    private String bz;

    /**
    * 说明
    */
    private String sm;

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

    public String getXmbhmc() {
        return xmbhmc;
    }

    public void setXmbhmc(String xmbhmc) {
        this.xmbhmc = xmbhmc;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getTplj() {
        return tplj;
    }

    public void setTplj(String tplj) {
        this.tplj = tplj;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", xmbhmc=").append(xmbhmc);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", tplj=").append(tplj);
            sb.append(", rq=").append(rq);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

}
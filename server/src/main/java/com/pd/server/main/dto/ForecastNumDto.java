package com.pd.server.main.dto;


public class ForecastNumDto extends PageDto{

    /**
    * 主键
    */
    private String id;

    /**
    * 设备编号
    */
    private String sbbh;

    /**
    * 出现次数
    */
    private String cxcs;

    /**
    * 出现时间
    */
    private String cxsj;

    /**
    * 出现日期
    */
    private String cxrq;

    /**
    * 部门编号
    */
    private String depcode;

    /**
    * 项目编号
    */
    private String xmbh;

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

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getCxcs() {
        return cxcs;
    }

    public void setCxcs(String cxcs) {
        this.cxcs = cxcs;
    }

    public String getCxsj() {
        return cxsj;
    }

    public void setCxsj(String cxsj) {
        this.cxsj = cxsj;
    }

    public String getCxrq() {
        return cxrq;
    }

    public void setCxrq(String cxrq) {
        this.cxrq = cxrq;
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
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
            sb.append(", sbbh=").append(sbbh);
            sb.append(", cxcs=").append(cxcs);
            sb.append(", cxsj=").append(cxsj);
            sb.append(", cxrq=").append(cxrq);
            sb.append(", depcode=").append(depcode);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

}
package com.pd.server.main.dto;


public class BeconFileStatisticsDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 信标id
    */
    private String xbid;

    /**
    * 设备编号合集
    */
    private String sbbhs;

    /**
    * 日期
    */
    private String rq;

    /**
    * 设备名称合集
    */
    private String sbmcs;

    /**
    * 时间合集
    */
    private String cjsjs;

    /**
     * gps合集
     */
    private String gps;

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

    public String getXbid() {
        return xbid;
    }

    public void setXbid(String xbid) {
        this.xbid = xbid;
    }

    public String getSbbhs() {
        return sbbhs;
    }

    public void setSbbhs(String sbbhs) {
        this.sbbhs = sbbhs;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getSbmcs() {
        return sbmcs;
    }

    public void setSbmcs(String sbmcs) {
        this.sbmcs = sbmcs;
    }

    public String getCjsjs() {
        return cjsjs;
    }

    public void setCjsjs(String cjsjs) {
        this.cjsjs = cjsjs;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
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
            sb.append(", xbid=").append(xbid);
            sb.append(", sbbhs=").append(sbbhs);
            sb.append(", rq=").append(rq);
            sb.append(", sbmcs=").append(sbmcs);
            sb.append(", cjsjs=").append(cjsjs);
            sb.append(", gps=").append(gps);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

}
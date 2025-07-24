package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class BeconFileDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 设备编号
    */
    private String sbbh;

    /**
    * 文件路径
    */
    private String wjlj;

    /**
    * 信标id
    */
    private String xbid;

    /**
    * 采集时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 接收时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date jssj;

    /**
    * 部门编号
    */
    private String deptcode;

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

    private String xmbh;

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

    public String getWjlj() {
        return wjlj;
    }

    public void setWjlj(String wjlj) {
        this.wjlj = wjlj;
    }

    public String getXbid() {
        return xbid;
    }

    public void setXbid(String xbid) {
        this.xbid = xbid;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
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

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", sbbh=").append(sbbh);
            sb.append(", wjlj=").append(wjlj);
            sb.append(", xbid=").append(xbid);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", jssj=").append(jssj);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", rq=").append(rq);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

}
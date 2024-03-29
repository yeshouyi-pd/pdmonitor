package com.pd.server.main.dto;

        import java.util.Date;
        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonFormat;

public class GroupEventDto extends PageDto{

    /**
    * 主键
    */
    private String id;

    /**
    * 设备编号
    */
    private String sbbh;

    /**
    * 开始时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date kssj;

    /**
    * 结束时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date jssj;

    /**
    * 侦测方位角
    */
    private String zcfwj;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
     * 监测点（部门）
     */
    private String deptcode;

    /**
    * 说明
    */
    private String sm;

    /**
    * 备注
    */
    private String bz;

    /**
    * 备注字段
    */
    private String bz1;

    /**
    * 备注字段
    */
    private String bz2;

    /**
    * 备注字段
    */
    private String bz3;

    private String zcts;

    private List<String> zcList;

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

    public String getZcfwj() {
        return zcfwj;
    }

    public void setZcfwj(String zcfwj) {
        this.zcfwj = zcfwj;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }

    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    public String getBz3() {
        return bz3;
    }

    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }

    public String getZcts() {
        return zcts;
    }

    public void setZcts(String zcts) {
        this.zcts = zcts;
    }

    public List<String> getZcList() {
        return zcList;
    }

    public void setZcList(List<String> zcList) {
        this.zcList = zcList;
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
            sb.append(", kssj=").append(kssj);
            sb.append(", jssj=").append(jssj);
            sb.append(", zcfwj=").append(zcfwj);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", sm=").append(sm);
            sb.append(", bz=").append(bz);
            sb.append(", bz1=").append(bz1);
            sb.append(", bz2=").append(bz2);
            sb.append(", bz3=").append(bz3);
        sb.append("]");
        return sb.toString();
    }

}
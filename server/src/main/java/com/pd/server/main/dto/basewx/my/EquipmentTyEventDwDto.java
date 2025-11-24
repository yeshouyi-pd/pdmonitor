package com.pd.server.main.dto.basewx.my;

public class EquipmentTyEventDwDto {

    /**
     *
     */
    private String id;

    /**
     * 设备编号
     */
    private String sbbh;

    /**
     * 开始时间
     */
    private String kssj;

    /**
     * 结束时间
     */
    private String jssj;

    /**
     * 创建时间
     */
    private String rq;

    /**
     * 头数
     */
    private String ts;

    /**
     * 部门编号
     */
    private String deptcode;

    /**
     * 文件路径
     */
    private String tplj;

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

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getTplj() {
        return tplj;
    }

    public void setTplj(String tplj) {
        this.tplj = tplj;
    }
}

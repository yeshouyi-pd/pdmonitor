package com.pd.server.main.dto.basewx.my;

public class EquipmentFileEventDwDto {

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
    private String kssj;

    /**
     * 结束时间
     */
    private String jssj;

    /**
     * 日期
     */
    private String rq;

    /**
     * 头数
     */
    private String ts;

    /**
     * 具体内容
     */
    private String jtnr;

    /**
     * 部门编号
     */
    private String deptcode;

    /**
     * equipment_file表主键，用于下载视频
     */
    private String equipmentFileId;

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

    public String getJtnr() {
        return jtnr;
    }

    public void setJtnr(String jtnr) {
        this.jtnr = jtnr;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getEquipmentFileId() {
        return equipmentFileId;
    }

    public void setEquipmentFileId(String equipmentFileId) {
        this.equipmentFileId = equipmentFileId;
    }
}

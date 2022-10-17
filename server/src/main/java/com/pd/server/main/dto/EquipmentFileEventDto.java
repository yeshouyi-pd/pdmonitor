package com.pd.server.main.dto;


public class EquipmentFileEventDto extends PageDto{

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
            sb.append(", rq=").append(rq);
            sb.append(", ts=").append(ts);
            sb.append(", jtnr=").append(jtnr);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", equipmentFileId=").append(equipmentFileId);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
        sb.append("]");
        return sb.toString();
    }

}
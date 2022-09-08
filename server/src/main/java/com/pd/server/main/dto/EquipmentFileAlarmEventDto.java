package com.pd.server.main.dto;


public class EquipmentFileAlarmEventDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 部门
    */
    private String deptcode;

    /**
    * 设备唯一标识
    */
    private String sbbh;

    /**
    * 事件时间
    */
    private String eventTime;

    /**
    * 出现次数
    */
    private Integer alarmNum;

    /**
    * 出现时间
    */
    private String bjsj;

    /**
    * 出现时间排序
    */
    private Integer xh;

    /**
    * 头数
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

    private int counts;

    private String xmbh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Integer getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(Integer alarmNum) {
        this.alarmNum = alarmNum;
    }

    public String getBjsj() {
        return bjsj;
    }

    public void setBjsj(String bjsj) {
        this.bjsj = bjsj;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
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

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
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
            sb.append(", deptcode=").append(deptcode);
            sb.append(", sbbh=").append(sbbh);
            sb.append(", eventTime=").append(eventTime);
            sb.append(", alarmNum=").append(alarmNum);
            sb.append(", bjsj=").append(bjsj);
            sb.append(", xh=").append(xh);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
        sb.append("]");
        return sb.toString();
    }

}
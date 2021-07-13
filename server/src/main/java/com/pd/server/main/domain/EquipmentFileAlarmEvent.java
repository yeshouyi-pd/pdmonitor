package com.pd.server.main.domain;

public class EquipmentFileAlarmEvent {
    private String id;

    private String deptcode;

    private String sbbh;

    private String eventTime;

    private Integer alarmNum;

    private String bjsj;

    private Integer xh;

    private String sm1;

    private String sm2;

    private String sm3;

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
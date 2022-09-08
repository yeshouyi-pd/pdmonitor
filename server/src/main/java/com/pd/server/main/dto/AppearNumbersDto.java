package com.pd.server.main.dto;


public class AppearNumbersDto {

    /**
    * 
    */
    private String id;

    /**
    * 设备编号
    */
    private String sbbh;

    /**
    * 出现时间(只有年月日)
    */
    private String bjsj;

    /**
    * 出现次数
    */
    private Integer alarmnum;

    /**
    * 小时
    */
    private String xs;

    /**
    * 分钟
    */
    private String fz;

    /**
    * 部门编号
    */
    private String deptcode;

    /**
    * 项目编号
    */
    private String xmbh;

    /**
    * 
    */
    private String sm;

    /**
    * 
    */
    private String bz;

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

    public String getBjsj() {
        return bjsj;
    }

    public void setBjsj(String bjsj) {
        this.bjsj = bjsj;
    }

    public Integer getAlarmnum() {
        return alarmnum;
    }

    public void setAlarmnum(Integer alarmnum) {
        this.alarmnum = alarmnum;
    }

    public String getXs() {
        return xs;
    }

    public void setXs(String xs) {
        this.xs = xs;
    }

    public String getFz() {
        return fz;
    }

    public void setFz(String fz) {
        this.fz = fz;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
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
            sb.append(", bjsj=").append(bjsj);
            sb.append(", alarmnum=").append(alarmnum);
            sb.append(", xs=").append(xs);
            sb.append(", fz=").append(fz);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", sm=").append(sm);
            sb.append(", bz=").append(bz);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
        sb.append("]");
        return sb.toString();
    }

}
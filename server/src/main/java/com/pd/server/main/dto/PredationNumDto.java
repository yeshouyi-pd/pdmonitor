package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class PredationNumDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 设备编号
    */
    private String sbbh;

    /**
    * 采集时间
    */
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 鲸豚出现次数
    */
    private Integer alarmNum;

    /**
    * 鲸豚捕食次数
    */
    private Integer predationNum;

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
    private String sm1;

    /**
    * 
    */
    private String sm2;

    /**
    * 
    */
    private String sm3;

    /**
    * 
    */
    private String sm4;

    /**
    * 
    */
    private String sm5;

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

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Integer getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(Integer alarmNum) {
        this.alarmNum = alarmNum;
    }

    public Integer getPredationNum() {
        return predationNum;
    }

    public void setPredationNum(Integer predationNum) {
        this.predationNum = predationNum;
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

    public String getSm4() {
        return sm4;
    }

    public void setSm4(String sm4) {
        this.sm4 = sm4;
    }

    public String getSm5() {
        return sm5;
    }

    public void setSm5(String sm5) {
        this.sm5 = sm5;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", sbbh=").append(sbbh);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", alarmNum=").append(alarmNum);
            sb.append(", predationNum=").append(predationNum);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
            sb.append(", sm4=").append(sm4);
            sb.append(", sm5=").append(sm5);
        sb.append("]");
        return sb.toString();
    }

}
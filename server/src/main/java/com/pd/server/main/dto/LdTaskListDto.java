package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class LdTaskListDto {

    /**
    * 
    */
    private Integer id;

    /**
    * SIM卡卡号
    */
    private String iccid;

    /**
    * cmd：201 状态返回 ;202 CH1开 ;203 CH1关 ;204 CH2开 ;205 CH2关 ;206 CH3开 ;207 CH3关 ;208 CH4开 ;209 CH4关 ;211 开充电 ;212 关充电
    */
    private String task;

    /**
    * 0：等待执行；1:执行完毕
    */
    private Integer state;

    /**
    * 任务发生时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fsdate;

    /**
    * 任务完成时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date jsdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getFsdate() {
        return fsdate;
    }

    public void setFsdate(Date fsdate) {
        this.fsdate = fsdate;
    }

    public Date getJsdate() {
        return jsdate;
    }

    public void setJsdate(Date jsdate) {
        this.jsdate = jsdate;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", iccid=").append(iccid);
            sb.append(", task=").append(task);
            sb.append(", state=").append(state);
            sb.append(", fsdate=").append(fsdate);
            sb.append(", jsdate=").append(jsdate);
        sb.append("]");
        return sb.toString();
    }

}
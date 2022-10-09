package com.pd.server.main.domain;

import java.util.Date;

public class LdTaskList {
    private Integer id;

    private String iccid;

    private String task;

    private Integer state;

    private Date fsdate;

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
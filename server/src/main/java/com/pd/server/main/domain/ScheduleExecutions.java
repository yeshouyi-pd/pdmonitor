package com.pd.server.main.domain;

import java.util.Date;

public class ScheduleExecutions {
    private Integer id;

    private Integer scheduleId;

    private Integer deviceId;

    private Date plannedTime;

    private Date actualTime;

    private String executionStatus;

    private Date createdAt;

    private String errorMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Date getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(Date plannedTime) {
        this.plannedTime = plannedTime;
    }

    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", scheduleId=").append(scheduleId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", plannedTime=").append(plannedTime);
        sb.append(", actualTime=").append(actualTime);
        sb.append(", executionStatus=").append(executionStatus);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", errorMessage=").append(errorMessage);
        sb.append("]");
        return sb.toString();
    }
}
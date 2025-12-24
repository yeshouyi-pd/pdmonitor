package com.pd.server.main.dto;


public class ScheduleExecutionsDto {

    /**
    * 执行记录ID
    */
    private Integer id;

    /**
    * 计划ID
    */
    private Integer scheduleId;

    /**
    * 设备ID
    */
    private String deviceId;

    /**
    * 计划执行时间
    */
    private String plannedTime;

    /**
    * 实际执行时间
    */
    private String actualTime;

    /**
    * 执行状态
    */
    private String executionStatus;

    /**
    * 错误信息
    */
    private String errorMessage;

    /**
    * 实际执行时间
    */
    private String createdAt;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(String plannedTime) {
        this.plannedTime = plannedTime;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
            sb.append(", errorMessage=").append(errorMessage);
            sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }

}
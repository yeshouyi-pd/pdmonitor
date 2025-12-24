package com.pd.server.main.dto;


public class DeviceSchedulesDto {

    /**
    * 计划ID
    */
    private Integer id;

    /**
    * 计划名称
    */
    private String scheduleName;

    /**
    * 设备ID
    */
    private String deviceId;

    /**
    * 开始时间
    */
    private String startTime;

    /**
    * 结束时间(用于关闭)
    */
    private String endTime;

    /**
    * 持续时长(分钟)
    */
    private Integer durationMinutes;

    /**
    * 重复类型
    */
    private String repeatType;

    /**
    * 周一
    */
    private Integer monday;

    /**
    * 周二
    */
    private Integer tuesday;

    /**
    * 周三
    */
    private Integer wednesday;

    /**
    * 周四
    */
    private Integer thursday;

    /**
    * 周五
    */
    private Integer friday;

    /**
    * 周六
    */
    private Integer saturday;

    /**
    * 周日
    */
    private Integer sunday;

    /**
    * 是否启用
    */
    private Integer isActive;

    /**
    * 是否正在执行
    */
    private Integer isRunning;

    /**
    * 上次执行时间
    */
    private String lastRunTime;

    /**
    * 下次执行时间
    */
    private String nextRunTime;

    /**
    * 创建事件
    */
    private String createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    public Integer getMonday() {
        return monday;
    }

    public void setMonday(Integer monday) {
        this.monday = monday;
    }

    public Integer getTuesday() {
        return tuesday;
    }

    public void setTuesday(Integer tuesday) {
        this.tuesday = tuesday;
    }

    public Integer getWednesday() {
        return wednesday;
    }

    public void setWednesday(Integer wednesday) {
        this.wednesday = wednesday;
    }

    public Integer getThursday() {
        return thursday;
    }

    public void setThursday(Integer thursday) {
        this.thursday = thursday;
    }

    public Integer getFriday() {
        return friday;
    }

    public void setFriday(Integer friday) {
        this.friday = friday;
    }

    public Integer getSaturday() {
        return saturday;
    }

    public void setSaturday(Integer saturday) {
        this.saturday = saturday;
    }

    public Integer getSunday() {
        return sunday;
    }

    public void setSunday(Integer sunday) {
        this.sunday = sunday;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(Integer isRunning) {
        this.isRunning = isRunning;
    }

    public String getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(String lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    public String getNextRunTime() {
        return nextRunTime;
    }

    public void setNextRunTime(String nextRunTime) {
        this.nextRunTime = nextRunTime;
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
            sb.append(", scheduleName=").append(scheduleName);
            sb.append(", deviceId=").append(deviceId);
            sb.append(", startTime=").append(startTime);
            sb.append(", endTime=").append(endTime);
            sb.append(", durationMinutes=").append(durationMinutes);
            sb.append(", repeatType=").append(repeatType);
            sb.append(", monday=").append(monday);
            sb.append(", tuesday=").append(tuesday);
            sb.append(", wednesday=").append(wednesday);
            sb.append(", thursday=").append(thursday);
            sb.append(", friday=").append(friday);
            sb.append(", saturday=").append(saturday);
            sb.append(", sunday=").append(sunday);
            sb.append(", isActive=").append(isActive);
            sb.append(", isRunning=").append(isRunning);
            sb.append(", lastRunTime=").append(lastRunTime);
            sb.append(", nextRunTime=").append(nextRunTime);
            sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }

}
package com.pd.server.main.dto;

/**
 * 执行记录分页查询DTO
 */
public class ScheduleExecutionPageDto extends PageDto<ScheduleExecutionWithScheduleDto> {

    /**
     * 设备ID
     */
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}


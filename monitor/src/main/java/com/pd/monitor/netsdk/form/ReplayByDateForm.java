package com.pd.monitor.netsdk.form;

import lombok.Data;

@Data
public class ReplayByDateForm {

    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 通道号
     */
    private int channelNum;
    /**
     * 日期 yymmddhh
     */
    private String date;
}

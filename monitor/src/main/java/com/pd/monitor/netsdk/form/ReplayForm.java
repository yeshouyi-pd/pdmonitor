package com.pd.monitor.netsdk.form;

import lombok.Data;

@Data
public class ReplayForm {

    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 通道号
     */
    private int channelNum;
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 类型 参考  NET_RealPlayType
     */
    private int rType;
}

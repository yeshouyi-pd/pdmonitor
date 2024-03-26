package com.pd.monitor.netsdk.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 播放信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealPlayInfo {
    /**
     * 登录句柄
     */
    private long loginHandler;
    /**
     * 码流类型
     */
    private int emDataType;
    /**
     * 通道号
     */
    private int channel;
    /**
     * 预览类型
     */
    private int rType;
    /**
     * 设备编号
     */
    private String deviceId;
    /**
     * 类别 1预览 2是回放
     */
    private String type;
    /**
     * 预览句柄
     */
    private long videoHandler;
    /**
     * 时间段，yymmddhh 用于回放
     */
    private String odate;

}

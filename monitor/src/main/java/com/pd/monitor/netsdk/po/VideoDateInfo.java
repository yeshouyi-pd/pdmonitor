package com.pd.monitor.netsdk.po;

import lombok.Data;


/**
 * 下载视频信息
 */
@Data
public class VideoDateInfo {
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 通道号
     */
    private int channelNum;
}

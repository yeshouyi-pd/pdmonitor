package com.pd.monitor.netsdk.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfo {

    /**
     * 设备ID
     */
    private String did;

    /**
     *设备IP
     */
    private String dIp;

    /**
     *设备端口
     */
    private int dPort;

    /**
     *设备用户名
     */
    private String dUser;

    /**
     *设备密码
     */
    private String dPassword;

}

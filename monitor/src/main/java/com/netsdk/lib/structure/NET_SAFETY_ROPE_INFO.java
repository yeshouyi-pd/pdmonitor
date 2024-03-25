package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/**
 * @author 260611
 * @description 安全绳相关属性状态信息
 * @date 2023/04/21 16:09:59
 */
public class NET_SAFETY_ROPE_INFO extends NetSDKLib.SdkStructure {
    /**
     * 佩戴带安全绳是否合规, 0-不合规 1-合规 2-未知
     */
    public int nCompliantType;
    /**
     * 预留字节
     */
    public byte[] szReserved = new byte[28];

    public NET_SAFETY_ROPE_INFO() {
    }
}
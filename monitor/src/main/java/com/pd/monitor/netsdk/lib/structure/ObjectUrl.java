package com.pd.monitor.netsdk.lib.structure;

import com.pd.monitor.netsdk.lib.NetSDKLib;

import static com.pd.monitor.netsdk.lib.NetSDKLib.MAX_PATH;

/**
 * @author 47081
 * @version 1.0
 * @description
 * @date 2021/2/22
 */
public class ObjectUrl extends NetSDKLib.SdkStructure {
    public byte[] objectUrl=new byte[MAX_PATH];
}

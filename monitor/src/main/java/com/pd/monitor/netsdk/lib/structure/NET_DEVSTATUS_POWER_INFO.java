package com.pd.monitor.netsdk.lib.structure;


import com.pd.monitor.netsdk.lib.NetSDKLib;

/** 
* @author 291189
* @description   电源电池相关信息 
* @date 2022/09/01 15:11:24
*/
public class NET_DEVSTATUS_POWER_INFO extends NetSDKLib.SdkStructure {
/** 
电池电量百分比,0~100
*/
public			int					nBatteryPercent;
/** 
供电类型 {@link com.pd.monitor.netsdk.lib.enumeration.NET_EM_POWER_TYPE}
*/
public			int					emPowerType;

public NET_DEVSTATUS_POWER_INFO(){
}
}
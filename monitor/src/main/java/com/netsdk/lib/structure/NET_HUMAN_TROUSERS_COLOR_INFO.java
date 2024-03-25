package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/** 
* @author 291189
* @description  裤子颜色过滤条件 
* @origin autoTool
* @date 2023/05/29 11:40:42
*/
public class NET_HUMAN_TROUSERS_COLOR_INFO extends NetSDKLib.SdkStructure {
/** 
是否过滤启用: TRUE-下发该配置  FALSE-不下发该配置
*/
public			int					bEnable;
/** 
需要过滤的裤子颜色颜色类型的个数,最大值是32
*/
public			int					nShowListNums;
/** 
需要过滤的裤子颜色类型
*/
public			int[]					emHumanTrousersColorShowList=new int[32];
/** 
保留字节
*/
public			byte[]					szReserved=new byte[256];

public			NET_HUMAN_TROUSERS_COLOR_INFO(){
}
}
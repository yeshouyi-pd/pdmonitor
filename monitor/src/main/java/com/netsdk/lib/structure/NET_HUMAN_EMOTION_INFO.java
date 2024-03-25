package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/** 
* @author 291189
* @description  表情过滤条件 
* @origin autoTool
* @date 2023/05/29 11:40:41
*/
public class NET_HUMAN_EMOTION_INFO extends NetSDKLib.SdkStructure {
/** 
是否过滤启用: TRUE-下发该配置  FALSE-不下发该配置
*/
public			int					bEnable;
/** 
过滤显示的表情的个数,最大值是32
*/
public			int					nShowListNums;
/** 
过滤显示的表情
*/
public			int[]					emHumanEmotionShowList=new int[32];
/** 
保留字节
*/
public			byte[]					szReserved=new byte[256];

public			NET_HUMAN_EMOTION_INFO(){
}
}
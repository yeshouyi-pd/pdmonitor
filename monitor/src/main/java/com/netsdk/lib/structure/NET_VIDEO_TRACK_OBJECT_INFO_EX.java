package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/** 
* @author 291189
* @description  视频目标信息扩展 
* @origin autoTool
* @date 2023/09/22 13:56:25
*/
public class NET_VIDEO_TRACK_OBJECT_INFO_EX extends NetSDKLib.SdkStructure {
/** 
/ 人脸抓拍角度, 三个角度依次分别是Pitch抬头低头,仰俯角;yaw是左右转头,偏航角;Roll是头在平面内左偏右偏,翻滚角,角度范围是-90~90
*/
public			int[]					nAngle=new int[3];
/** 
/ 保留字节
*/
public			byte[]					szReserved=new byte[1012];

public NET_VIDEO_TRACK_OBJECT_INFO_EX(){
}
}
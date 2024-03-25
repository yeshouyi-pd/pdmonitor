package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

/**
 * @author 291189
 * @description 事件类型 EVENT_IVS_TRAFFIC_OCCUPYING_THEPATH (占道事件)对应的数据块描述信息
 * @date 2022/10/21 09:31:02
 */
public class NET_EVENT_TRAFFIC_OCCUPYING_THEPATH_INFO extends NetSDKLib.SdkStructure {
	/**
	 * 通道号
	 */
	public int nChannelID;
	/**
	 * 0:脉冲,1:开始, 2:停止
	 */
	public int nAction;
	/**
	 * 事件名称
	 */
	public byte[] szName = new byte[128];
	/**
	 * 智能事件所属大类
	 */
	public byte[] szClass = new byte[16];
	/**
	 * GroupID事件组ID，同一物体抓拍过程内GroupID相同
	 */
	public int nGroupID;
	/**
	 * CountInGroup一个事件组内的抓拍张数
	 */
	public int nCountInGroup;
	/**
	 * IndexInGroup一个事件组内的抓拍序号，从1开始
	 */
	public int nIndexInGroup;
	/**
	 * 事件时间毫秒数
	 */
	public int nUTCMS;
	/**
	 * 相对事件时间戳,(单位是毫秒)
	 */
	public double dbPTS;
	/**
	 * 事件发生的时间
	 */
	public NET_TIME_EX stuUTC = new NET_TIME_EX();
	/**
	 * 事件ID
	 */
	public int nEventID;
	/**
	 * 智能事件规则编号
	 */
	public int nRuleID;
	/**
	 * 对应车道号
	 */
	public int nLane;
	/**
	 * 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
	 */
	public int nSequence;
	/**
	 * 车牌信息
	 */
	public NetSDKLib.NET_MSG_OBJECT stuObject = new NetSDKLib.NET_MSG_OBJECT();
	/**
	 * 车身信息
	 */
	public NetSDKLib.NET_MSG_OBJECT stuVehicle = new NetSDKLib.NET_MSG_OBJECT();
	/**
	 * 交通车辆信息
	 */
	public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar = new DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO();
	/**
	 * 公共信息
	 */
	public NetSDKLib.EVENT_COMM_INFO stuCommInfo = new NetSDKLib.EVENT_COMM_INFO();
	/**
	 * 事件对应文件信息
	 */
	public NetSDKLib.NET_EVENT_FILE_INFO stuFileInfo = new NetSDKLib.NET_EVENT_FILE_INFO();
	/**
	 * 抓图标志(按位),具体见NET_RESERVED_COMMON
	 */
	public int dwSnapFlagMask;
	/**
	 * 车速，单位km/h
	 */
	public int nSpeed;
	/**
	 * 预留字节
	 */
	public byte[] szReserved = new byte[1020];

	public NET_EVENT_TRAFFIC_OCCUPYING_THEPATH_INFO() {
	}
}
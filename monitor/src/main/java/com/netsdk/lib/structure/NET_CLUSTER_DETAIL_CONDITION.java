package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

/**
 * @author 251823
 * @description 查询条件
 * @date 2022/10/09 14:06:13
 */
public class NET_CLUSTER_DETAIL_CONDITION extends NetSDKLib.SdkStructure {
	/**
	 * 聚档周期Key, 一次只能查询一个聚档周期内的数据,必填.与开始结束时间取交集
	 */
	public int nClusterDate;
	/**
	 * 是否需要过滤ProfileID
	 */
	public int bIsNeedProfileID;
	/**
	 * 聚档结果ID，如果不带该字段或为空，则默认不过滤ProfileID
	 */
	public int nProfileID;
	/**
	 * 保留字节
	 */
	public byte[] szReserved = new byte[256];

	public NET_CLUSTER_DETAIL_CONDITION() {
	}
}
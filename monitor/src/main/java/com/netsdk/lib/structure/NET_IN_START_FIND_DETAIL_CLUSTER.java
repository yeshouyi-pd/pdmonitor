package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

/**
 * @author 251823
 * @description 接口 CLIENT_StartFindDetailNumberStatCluster 的输入参数
 * @date 2022/10/09 14:06:13
 */
public class NET_IN_START_FIND_DETAIL_CLUSTER extends NetSDKLib.SdkStructure {
	/**
	 * 此结构体大小,必须赋值
	 */
	public int dwSize;
	/**
	 * 查询条件
	 */
	public NET_CLUSTER_DETAIL_CONDITION stuCondition = new NET_CLUSTER_DETAIL_CONDITION();

	public NET_IN_START_FIND_DETAIL_CLUSTER() {
		this.dwSize = this.size();
	}
}
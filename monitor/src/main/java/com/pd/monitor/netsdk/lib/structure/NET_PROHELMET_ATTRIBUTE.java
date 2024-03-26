package com.pd.monitor.netsdk.lib.structure;

import com.pd.monitor.netsdk.lib.NetSDKLib;

/**
 * @author 251823
 * @description 防护面罩相关属性状态信息
 * @date 2022/07/22 10:58:03
 */
public class NET_PROHELMET_ATTRIBUTE extends NetSDKLib.SdkStructure {
	/**
	 * 是否有戴防护面罩 {@link com.pd.monitor.netsdk.lib.enumeration.EM_WEARING_STATE}
	 */
	public int emHasHat;
	/**
	 * 帽子颜色 {@link com.pd.monitor.netsdk.lib.enumeration.EM_CLOTHES_COLOR}
	 */
	public int emHatColor;
	/**
	 * 预留字节
	 */
	public byte[] szReserved = new byte[128];

	public NET_PROHELMET_ATTRIBUTE() {
	}
}
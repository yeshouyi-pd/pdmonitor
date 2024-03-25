package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

/**
 * @author 251823
 * @description 测温规则扩展
 * @date 2023/03/15 10:25:00
 */
public class CFG_RADIOMETRY_RULE_EX extends NetSDKLib.SdkStructure {
	/**
	 * 标定黑体温度，精度0.1，实际值扩大了10倍；比如显示是38度，实际该值获取的是380；如果需要设置39度，如该值需要传入390。
	 */
	public int nBlackBodyTemp;
	/**
	 * 保留字节
	 */
	public byte[] byReserved = new byte[252];

	public CFG_RADIOMETRY_RULE_EX() {
	}
}
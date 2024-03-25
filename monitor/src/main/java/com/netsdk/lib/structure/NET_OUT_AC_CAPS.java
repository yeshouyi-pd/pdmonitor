package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

/**
 * @author 291189
 * @description 门禁AC服务协议与设备能力获取出参
 * @origin autoTool
 * @date 2023/02/10 14:34:21
 */
public class NET_OUT_AC_CAPS extends NetSDKLib.SdkStructure {
	/**
	 * 结构体大小
	 */
	public int dwSize;
	/**
	 * ACCaps能力集
	 */
	public NET_AC_CAPS stuACCaps = new NET_AC_CAPS();
	/**
	 * user操作能力集
	 */
	public NET_ACCESS_USER_CAPS stuUserCaps = new NET_ACCESS_USER_CAPS();
	/**
	 * card操作能力集
	 */
	public NET_ACCESS_CARD_CAPS stuCardCaps = new NET_ACCESS_CARD_CAPS();
	/**
	 * 信息操作能力集
	 */
	public NET_ACCESS_FINGERPRINT_CAPS stuFingerprintCaps = new NET_ACCESS_FINGERPRINT_CAPS();
	/**
	 * 目标操作能力集
	 */
	public NET_ACCESS_FACE_CAPS stuFaceCaps = new NET_ACCESS_FACE_CAPS();
	/**
	 * 眼睛相关能力集
	 */
	public NET_ACCESS_IRIS_CAPS stuIrisCaps = new NET_ACCESS_IRIS_CAPS();
	/**
	 * 反潜回能力集
	 */
	public NET_ANTI_SUBMARINE_CAPS stuAntisubmarineCaps = new NET_ANTI_SUBMARINE_CAPS();
	/**
	 * 门禁互锁相关能力
	 */
	public NET_INTER_LOCK_CAPS stuInterLockCaps = new NET_INTER_LOCK_CAPS();

	public NET_OUT_AC_CAPS() {
		this.dwSize = this.size();
	}
}
package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

/**
 * @author 251823
 * @description CLIENT_DownloadPieceFile 接口输出参数（文件下载）
 * @origin autoTool
 * @date 2023/11/30 19:38:56
 */
public class NET_OUT_DOWNLOAD extends NetSDKLib.SdkStructure {
	/**
	 * / 结构体大小：赋值为结构体大小
	 */
	public int dwSize;

	public NET_OUT_DOWNLOAD() {
		this.dwSize = this.size();
	}
}
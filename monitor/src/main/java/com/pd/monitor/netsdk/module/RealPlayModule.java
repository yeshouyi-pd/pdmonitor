package com.pd.monitor.netsdk.module;

import java.awt.Panel;

import com.pd.monitor.controller.WaterQualityController;
import com.pd.monitor.netsdk.frame.RealplayEx;
import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.lib.ToolKits;
import com.pd.monitor.netsdk.websocketServer.WebSocketServerDh;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;

/**
 * 实时预览接口实现
 * 主要有 ：开始拉流、停止拉流功能
 */
public class RealPlayModule {

	private static final Logger LOG = LoggerFactory.getLogger(RealPlayModule.class);

	/**
	 * 开始实时预览
	 * @param m_hLoginHandle 登录句柄
	 * @param nChannelID 通道ID
	 * @param rType 码流类型 ，参考  NET_RealPlayType
	 *              0 		// 实时预览
	 *              3 		// 实时预览-从码流1
	 *              7 		// 多画面预览－4画面
	 *              9 		// 多画面预览－9画面
	 *              10 	    // 多画面预览－16画面
	 * @param emDataType 回调的数据类型,详见 EM_REAL_DATA_TYPE
	 *              0;          // 私有码流
	 *              1;          // 国标PS码流
	 *              2;          // TS码流
	 *              3;          // MP4文件
	 *              4;          // 裸H264码流
	 *              5;	        // 流式FLV`
	 * @return 预览句柄
	 */
	public static NetSDKLib.LLong startRealPlay(NetSDKLib.LLong m_hLoginHandle,
												int nChannelID, int rType, int emDataType, Session session) {
		NetSDKLib.NET_IN_REALPLAY_BY_DATA_TYPE inParam = new NetSDKLib.NET_IN_REALPLAY_BY_DATA_TYPE();
		NetSDKLib.NET_OUT_REALPLAY_BY_DATA_TYPE outParam = new NetSDKLib.NET_OUT_REALPLAY_BY_DATA_TYPE();
		inParam.nChannelID = nChannelID;
		inParam.rType = rType;
		inParam.emDataType = emDataType;
		NetSDKLib.LLong lRealHandle = LoginModule.netSdk.CLIENT_RealPlayByDataType(m_hLoginHandle,
				inParam, outParam, 3000);
		if (lRealHandle.longValue() != 0) {
			LOG.error("拉取预览成功 success" + lRealHandle);

			WebSocketServerDh.sessionsALLong.put(session,lRealHandle);
			//设置回调的接收
			//lRealHandle ：拉流成功的句柄
			//你的接收回调的类里的方法 注：这里使用的是官方demo中的RealplayEx类下的接口
			//源码发现 可以自定义参数channlID  放在  Pointer  的public 构造函数里面
			//直接通过本地方法申请4位的真是内存空间  int站4位
	/*		Pointer dwUser = new Memory(4);
			dwUser.setInt(0,nChannelID); //全部用掉*/
			RealplayEx.netSdk.CLIENT_SetRealDataCallBackEx(lRealHandle, RealplayEx.CbfRealDataCallBackEx.getInstance(),
					null, 31);
			return lRealHandle;
		} else {
			return null;
		}
	}

	/**
	 * \if ENGLISH_LANG
	 * Start RealPlay
	 * \else
	 * 开始预览
	 * \endif
	 */
	public static NetSDKLib.LLong startRealPlay(int channel, int stream, Panel realPlayWindow) {
		NetSDKLib.LLong m_hPlayHandle = LoginModule.netSdk.CLIENT_RealPlayEx(LoginModule.m_hLoginHandle, channel, Native.getComponentPointer(realPlayWindow), stream);

		if(m_hPlayHandle.longValue() == 0) {
			System.err.println("开始实时预览失败，错误码" + ToolKits.getErrorCodePrint());
		} else {
			System.out.println("Success to start realplay");
		}

		return m_hPlayHandle;
	}

	/**
	 * \if ENGLISH_LANG
	 * Start RealPlay
	 * \else
	 * 停止预览
	 * \endif
	 */
	public static void stopRealPlay(NetSDKLib.LLong m_hPlayHandle) {
		if(m_hPlayHandle.longValue() == 0) {
			return;
		}

		boolean bRet = LoginModule.netSdk.CLIENT_StopRealPlayEx(m_hPlayHandle);
		if(bRet) {
			m_hPlayHandle.setValue(0);
		}
	}
}

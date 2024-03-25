package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib.SdkStructure;
/**
 * @description 任务全局配置
 * @author 119178
 * @date 2021/3/16
 */
public class NET_ANALYSE_TASK_GLOBAL extends SdkStructure{
	/**
	 * 车道信息数量
	 */
	public int										nLanesNum;                      
	/**
	 * 车道信息 每个车道两条边界线
	 */
	public	NET_ANALYSE_TASK_GLOBAL_LANES[]			stuLanes=new NET_ANALYSE_TASK_GLOBAL_LANES[8];
	/**
	 * 标定区域个数
	 */
	public int										nCalibrateArea;					
	/**
	 * 标定区域
	 */
	public	NET_ANALYSE_TASK_GLOBAL_CALIBRATEAREA[]	stuCalibrateArea = new NET_ANALYSE_TASK_GLOBAL_CALIBRATEAREA[32];
	/**
	 * 保留字节
	 */
	public	byte[]									byReserved=new byte[1024];

	public  NET_ANALYSE_TASK_GLOBAL(){
		for(int i=0;i<stuLanes.length;i++){
			stuLanes[i]=new NET_ANALYSE_TASK_GLOBAL_LANES();
		}

		for(int i=0;i<stuCalibrateArea.length;i++){
			stuCalibrateArea[i]=new NET_ANALYSE_TASK_GLOBAL_CALIBRATEAREA();
		}

	}



}

package com.pd.monitor.netsdk.service;

import com.pd.monitor.netsdk.callback.FDownloadDataCallBack;
import com.pd.monitor.netsdk.callback.FRealDataCallback;
import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.module.AutoRegisterModule;
import com.pd.monitor.netsdk.module.DevicesModule;
import com.pd.monitor.netsdk.po.ChannelInfo;
import com.pd.monitor.netsdk.po.VideoDateInfo;
import com.pd.monitor.netsdk.utils.DeviceListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.pd.monitor.netsdk.module.OperationModule.getRealPlayHandleByDeviceId;


/**
 * 视频监控服务
 * @author xutongxue
 * @date 2023/8/23 16:08
 */
@Service
@Slf4j
public class VideoMonitorService {

    @Resource
    private FDownloadDataCallBack fDownLoadPosCallBack;
    @Resource
    private FRealDataCallback FRealDataCallback;


    /**
     * 启动视频监控服务监听端口
     * @param serverIP
     * @param serverPort
     */
    public void startMonitorServer(String serverIP, int serverPort){
        if( !AutoRegisterModule.getServerState() ){
            AutoRegisterModule.startServer(serverIP, serverPort);
        }else{
            log.error("服务已存在!");
        }
    }

    /**
     * 关闭视频监控服务
     */
    public void stopMonitorServer(){
        AutoRegisterModule.stopServer();
    }


    /**
     *  视频预览
     */
    public long startRealPlay(String deviceId,int channelNum,int rType){
        DevicesModule devicesModule = DeviceListUtil.getDeviceModuleByDeviceId(deviceId);
        if(devicesModule != null){
            return devicesModule.startRealPlay(channelNum, FRealDataCallback,deviceId,rType);}
        return 0;
    }

    /**
     * 视频回放
     */
    public long startReplay(String deviceId,int channelNum,String beginDate,String endDate,int rType){
        DevicesModule devicesModule = DeviceListUtil.getDeviceModuleByDeviceId(deviceId);
        if(devicesModule != null)
            return devicesModule.startReplay(channelNum,beginDate,endDate,fDownLoadPosCallBack,deviceId,rType);
        return 0;
    }

    /**
     * 获取当天回放记录
     */
    public List<VideoDateInfo> findReplayDate(String deviceId, int channelNum, String odate){
        DevicesModule devicesModule = DeviceListUtil.getDeviceModuleByDeviceId(deviceId);
        if(devicesModule != null)
            return devicesModule.findReplayByDate(channelNum,odate);
        return null;
    }

    /**
     * 获取指定设备所有可用通道号
     */

    public List<ChannelInfo> findChannelNum(String deviceId){
        DevicesModule devicesModule = DeviceListUtil.getDeviceModuleByDeviceId(deviceId);
        if(devicesModule != null)
            return devicesModule.findChannelNum();
        return null;
    }

    public boolean stopPlay(String deviceId){
        DevicesModule devicesModule = DeviceListUtil.getDeviceModuleByDeviceId(deviceId);
        if(devicesModule != null) {
            long handle = getRealPlayHandleByDeviceId(deviceId);
            return devicesModule.stopPlay(new NetSDKLib.LLong(handle));
        }
        return false;
    }


    public boolean stopReport(NetSDKLib.LLong lLong, String deviceId) {
        DevicesModule devicesModule = DeviceListUtil.getDeviceModuleByDeviceId(deviceId);
        if(devicesModule != null)
            return devicesModule.stopReport(lLong);
        return false;
    }


    /**
     * 返回websocket ip和地址
     * @return
     */
    public String getDeviceIpUrl(HttpServletRequest request){
        String ipUrl = null;
        if(String.valueOf(request.getRequestURL()).indexOf("443")!=-1){
            ipUrl="wss://" + request.getServerName() + ":" + request.getServerPort();//https请求版本
        }else{
            ipUrl="ws://" + request.getServerName() + ":" + request.getServerPort();//http请求版本
        }
        return ipUrl;
    }
}

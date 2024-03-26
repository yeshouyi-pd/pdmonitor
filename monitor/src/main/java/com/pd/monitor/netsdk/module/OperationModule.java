package com.pd.monitor.netsdk.module;

import com.pd.monitor.netsdk.callback.FRealDataCallback;
import com.pd.monitor.netsdk.callback.FDownloadDataCallBack;
import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.lib.ToolKits;
import com.pd.monitor.netsdk.lib.enumeration.EM_AUDIO_DATA_TYPE;
import com.pd.monitor.netsdk.lib.enumeration.ENUMERROR;
import com.pd.monitor.netsdk.po.ChannelInfo;
import com.pd.monitor.netsdk.po.RealPlayInfo;
import com.pd.monitor.netsdk.po.VideoDateInfo;
import com.pd.monitor.netsdk.websocket.WebSocketServerDh;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.pd.monitor.netsdk.utils.DateUtil.getOdate;

/**
 * 操作模块
 * @author xutongxue
 * @date 2023/8/23 17:09
 */
@Slf4j
public class OperationModule {

    public static final NetSDKLib DHNetSdkLib = NetSDKLib.NETSDK_INSTANCE;

    @Autowired
    private static WebSocketServerDh server;

    //预览信息保存hash表,realPlayHandler-RealPlayInfo类
    public static Map<Long, RealPlayInfo> realPlayHandlers = new ConcurrentHashMap<>();

    private boolean m_bRecordStatus = false;
    /**
     * 开始预览
     * @param m_hLoginHandle 登陆句柄
     * @param FRealDataCallback 实时预览回调
     * @param deviceId
     * @param rType
     * @return
     */
    public static long startRealPlay(NetSDKLib.LLong m_hLoginHandle, int channelNum, FRealDataCallback FRealDataCallback, String deviceId, int rType) {
        System.out.println("开始预览："+deviceId+"，登录句柄："+m_hLoginHandle+"通道："+channelNum);
        //入参对象
        int emDataType = NetSDKLib.EM_REAL_DATA_TYPE.EM_REAL_DATA_TYPE_FLV_STREAM;
        NetSDKLib.NET_IN_REALPLAY_BY_DATA_TYPE inParam = new NetSDKLib.NET_IN_REALPLAY_BY_DATA_TYPE();
        inParam.emDataType = emDataType;
        inParam.nChannelID = channelNum;
        inParam.emAudioType = EM_AUDIO_DATA_TYPE.EM_AUDIO_DATA_TYPE_AAC.ordinal();
        inParam.rType = rType;
        inParam.cbRealData = FRealDataCallback;
        inParam.dwUser = null;

        //返回对象
        NetSDKLib.NET_OUT_REALPLAY_BY_DATA_TYPE stOut = new NetSDKLib.NET_OUT_REALPLAY_BY_DATA_TYPE();
        //获取预览句柄
        NetSDKLib.LLong lRealHandle = DHNetSdkLib.CLIENT_RealPlayByDataType(m_hLoginHandle, inParam, stOut, 5000);
        //开启实时监控
        if(lRealHandle.longValue() != 0) {
            RealPlayInfo info = new RealPlayInfo(m_hLoginHandle.longValue(), emDataType, channelNum, NetSDKLib.NET_RealPlayType.NET_RType_Realplay,deviceId,"1",lRealHandle.longValue(),null);
            realPlayHandlers.put(lRealHandle.longValue(), info);
        } else {
            System.err.printf("RealPlayByDataType Failed!Last Error[0x%x]\n", DHNetSdkLib.CLIENT_GetLastError());
        }
        return lRealHandle.longValue();
    }

    /**
     * 开启视频回放
     * @param m_hLoginHandle
     * @param channelNum
     * @param beginDate
     * @param endDate
     * @param fDownLoadPosCallBack
     * @param deviceId
     * @param rType
     * @return
     */
    public static long startReplay(NetSDKLib.LLong m_hLoginHandle, int channelNum, String beginDate, String endDate, FDownloadDataCallBack fDownLoadPosCallBack, String deviceId, int rType){
        NetSDKLib.NET_IN_PLAYBACK_BY_DATA_TYPE inParam = new NetSDKLib.NET_IN_PLAYBACK_BY_DATA_TYPE();
        NetSDKLib.NET_OUT_PLAYBACK_BY_DATA_TYPE outParam = new NetSDKLib.NET_OUT_PLAYBACK_BY_DATA_TYPE();
        inParam.nChannelID = channelNum;

        String[] begin = beginDate.split(" ");
        NetSDKLib.NET_TIME start_time = handleDate(begin[0],begin[1]);

        String[] end = endDate.split(" ");
        NetSDKLib.NET_TIME end_time = handleDate(end[0],end[1]);

        inParam.stStartTime = start_time;
        inParam.stStopTime = end_time;
        inParam.fDownLoadDataCallBack = fDownLoadPosCallBack;
        inParam.dwPosUser = null;
        inParam.emDataType = NetSDKLib.EM_REAL_DATA_TYPE.EM_REAL_DATA_TYPE_FLV_STREAM;
        inParam.nPlayDirection = 0;
        inParam.hWnd = null;

        //获取yymmddhh
        String odate = getOdate(begin[0],begin[1]);
        IntByReference steamType = new IntByReference(rType);
        boolean a = DHNetSdkLib.CLIENT_SetDeviceMode(m_hLoginHandle, NetSDKLib.EM_USEDEV_MODE.NET_RECORD_STREAM_TYPE, steamType.getPointer());
        NetSDKLib.LLong lLong = DHNetSdkLib.CLIENT_PlayBackByDataType(m_hLoginHandle, inParam, outParam, 1000);
        if (lLong.longValue() != 0) {
            RealPlayInfo info = new RealPlayInfo(m_hLoginHandle.longValue(), NetSDKLib.EM_REAL_DATA_TYPE.EM_REAL_DATA_TYPE_FLV_STREAM, channelNum, NetSDKLib.NET_RealPlayType.NET_RType_Realplay,deviceId,"2",lLong.longValue(),odate);
            realPlayHandlers.put(lLong.longValue(), info);
        } else {
            log.error("realplay failed.error is " + ENUMERROR.getErrorMessage());
        }
        return lLong.longValue();
    }

    /**
     * 查询指定日期以及通道号的回放数据
     * @param m_hLoginHandle
     * @param channelNum
     * @param odate
     * @return
     */
    public static List<VideoDateInfo> findReplayByDate(NetSDKLib.LLong m_hLoginHandle, int channelNum, String odate){
        NetSDKLib.NET_TIME startTime = handleDate(odate, "0:0:0");
        NetSDKLib.NET_TIME endTime = handleDate(odate, "23:59:59");
        int nFileCount = 50; //每次查询的最大文件个数
        NetSDKLib.NET_RECORDFILE_INFO[] numberFile = (NetSDKLib.NET_RECORDFILE_INFO[])new NetSDKLib.NET_RECORDFILE_INFO().toArray(nFileCount);
        int maxlen = nFileCount * numberFile[0].size();
        IntByReference outFileCoutReference = new IntByReference(0);
        boolean ibt = DHNetSdkLib.CLIENT_QueryRecordFile(m_hLoginHandle, channelNum, 0, startTime, endTime, null, numberFile, maxlen, outFileCoutReference, 1000, false);
        List<VideoDateInfo> list = new ArrayList<>();
        if(ibt) {
            for(int i=0; i<outFileCoutReference.getValue(); i++) {
                VideoDateInfo video = new VideoDateInfo();
                NetSDKLib.NET_TIME starttime = numberFile[i].starttime;
                NetSDKLib.NET_TIME endtime = numberFile[i].endtime;
                video.setBeginTime(starttime.dwYear + "-" + starttime.dwMonth + "-" + starttime.dwDay + " " +  starttime.dwHour + ":" +  starttime.dwMinute + ":" +  starttime.dwSecond);
                video.setEndTime(endtime.dwYear + "-" + endtime.dwMonth + "-" + endtime.dwDay + " " +  endtime.dwHour + ":" +  endtime.dwMinute + ":" +  endtime.dwSecond);
                video.setChannelNum(numberFile[i].ch);
                list.add(video);
            }
            return list;
        } else {
            return list;
        }
    }

    /**
     * 查询可用通道号
     * @param m_hLoginHandle
     * @return
     */
    public static List<ChannelInfo> findChannelNum(NetSDKLib.LLong m_hLoginHandle){
        int cameraCount = 16;  // 通道号个数
        NetSDKLib.NET_MATRIX_CAMERA_INFO[]  cameraInfo = new NetSDKLib.NET_MATRIX_CAMERA_INFO[cameraCount];
        for(int i = 0; i < cameraCount; i++) {
            cameraInfo[i] = new NetSDKLib.NET_MATRIX_CAMERA_INFO();
        }
        // 入参
        NetSDKLib.NET_IN_MATRIX_GET_CAMERAS inMatrix = new NetSDKLib.NET_IN_MATRIX_GET_CAMERAS();
        // 出参
        NetSDKLib.NET_OUT_MATRIX_GET_CAMERAS outMatrix = new NetSDKLib.NET_OUT_MATRIX_GET_CAMERAS();
        outMatrix.nMaxCameraCount = cameraCount;
        outMatrix.pstuCameras = new Memory(cameraInfo[0].size() * cameraCount);
        outMatrix.pstuCameras.clear(cameraInfo[0].size() * cameraCount);

        ToolKits.SetStructArrToPointerData(cameraInfo, outMatrix.pstuCameras);  // 将数组内存拷贝到Pointer
        List<ChannelInfo> list = new ArrayList<>();

        if(DHNetSdkLib.CLIENT_MatrixGetCameras(m_hLoginHandle, inMatrix, outMatrix, 5000)) {
            ToolKits.GetPointerDataToStructArr(outMatrix.pstuCameras, cameraInfo);  // 将 Pointer 的内容 输出到   数组
            for(int j = 0; j < outMatrix.nRetCameraCount; j++) {
                if(cameraInfo[j].bRemoteDevice == 0) {   // 过滤远程设备
                    continue;
                }
                String ip = new String(cameraInfo[j].stuRemoteDevice.szIp).trim();
                if("192.168.0.0".equals(ip)){
                    continue;
                }
                ChannelInfo channelInfo = new ChannelInfo();
                channelInfo.setIp(ip);
                channelInfo.setNChannelID(cameraInfo[j].nUniqueChannel);
                channelInfo.setPort(cameraInfo[j].stuRemoteDevice.nPort);
                channelInfo.setDeviceName(new String(cameraInfo[j].stuRemoteDevice.szDevName).trim());
                list.add(channelInfo);
            }
        } else {
        }
        return list;
    }

    /**
     * 停止预览
     * @param m_hPlayHandle 实时预览句柄
     * @return
     */
    public static boolean stopRealPlay(NetSDKLib.LLong m_hPlayHandle) {
        boolean bRet = false;
        if(m_hPlayHandle.longValue() != 0) {
            bRet = DHNetSdkLib.CLIENT_StopRealPlayEx(m_hPlayHandle);
            m_hPlayHandle.setValue(0);
        }

        return bRet;
    }

    /**
     * 停止回放
     * @param lLong
     * @return
     */
    public static boolean stopReport(NetSDKLib.LLong lLong) {
        boolean bRet = false;
        if(lLong.longValue() != 0) {
            bRet = DHNetSdkLib.CLIENT_StopPlayBack(lLong);
            lLong.setValue(0);
        }
        return bRet;
    }
    /**
     * 远程抓图
     * @param m_hLoginHandle 登陆句柄
     * @param chn  通道号
     * @return
     */
    public static boolean snapPicture(NetSDKLib.LLong m_hLoginHandle, int chn) {
        // 发送抓图命令给前端设备，抓图的信息
        NetSDKLib.SNAP_PARAMS msg = new NetSDKLib.SNAP_PARAMS();
        msg.Channel = chn;  			// 抓图通道
        msg.mode = 0;    			    // 抓图模式
        msg.Quality = 3;				// 画质
        msg.InterSnap = 0; 	            // 定时抓图时间间隔
        msg.CmdSerial = 0;  			// 请求序列号，有效值范围 0~65535，超过范围会被截断为

        IntByReference reserved = new IntByReference(0);

        if (!DHNetSdkLib.CLIENT_SnapPictureEx(m_hLoginHandle, msg, reserved)) {
            System.err.printf("SnapPictureEx Failed!" + ToolKits.getErrorCodePrint());
            return false;
        } else {
            System.out.println("SnapPictureEx success");
        }
        return true;
    }

    /**
     *设置抓图回调函数， 图片主要在m_SnapReceiveCB中返回
     * @param m_SnapReceiveCB
     */
    public void setSnapRevCallBack(NetSDKLib.fSnapRev m_SnapReceiveCB){
        DHNetSdkLib.CLIENT_SetSnapRevCallBack(m_SnapReceiveCB, null);
    }

    /**
     * 获取网络协议
     * @param m_hLoginHandle 登录句柄
     * @return
     */
    public NetSDKLib.CFG_DVRIP_INFO getDVRIPConfig(NetSDKLib.LLong m_hLoginHandle) {
        NetSDKLib.CFG_DVRIP_INFO msg = new NetSDKLib.CFG_DVRIP_INFO();

        if(!ToolKits.GetDevConfig(m_hLoginHandle, -1, NetSDKLib.CFG_CMD_DVRIP, msg)) {
            return null;
        }

        return msg;
    }

    /**
     * 网络协议配置
     * @param m_hLoginHandle 登陆句柄
     * @param enable  使能
     * @param address 服务器地址
     * @param nPort  服务器端口号
     * @param deviceId  设备ID
     * @param info 获取到的网络协议配置
     * @return
     */
    public boolean setDVRIPConfig(NetSDKLib.LLong m_hLoginHandle, boolean enable, String address, int nPort, byte[] deviceId, NetSDKLib.CFG_DVRIP_INFO info) {
        NetSDKLib.CFG_DVRIP_INFO msg = info;
        // 主动注册配置个数
        msg.nRegistersNum = 1;

        // 主动注册使能
        msg.stuRegisters[0].bEnable = enable? 1:0;

        // 服务器个数
        msg.stuRegisters[0].nServersNum = 1;

        // 服务器地址
        ToolKits.StringToByteArray(address, msg.stuRegisters[0].stuServers[0].szAddress);

        // 服务器端口号
        msg.stuRegisters[0].stuServers[0].nPort = nPort;

        // 设备ID
        ToolKits.ByteArrayToByteArray(deviceId, msg.stuRegisters[0].szDeviceID);

        return ToolKits.SetDevConfig(m_hLoginHandle, -1, NetSDKLib.CFG_CMD_DVRIP, msg);
    }


    /**
     * 新的视频预览回调函数
     */
    @Component
    public static class RealDattaCallBackEx implements NetSDKLib.fRealDataCallBackEx2 {
        private RealDattaCallBackEx(){}

        private static class RealDataCallBackExHolder {
            private static final RealDattaCallBackEx realDataEx= new RealDattaCallBackEx();
        }

        public static final RealDattaCallBackEx getInstance() {
            return RealDataCallBackExHolder.realDataEx;
        }

        @Override
        public void invoke(NetSDKLib.LLong lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, NetSDKLib.LLong param, Pointer dwUser) {
            //RealPlayInfo info = DeviceApi.realPlayHandlers.get(lRealHandle.longValue());
            RealPlayInfo info = realPlayHandlers.get(lRealHandle.longValue());
            if (info != null && info.getLoginHandler() != 0) {
                //过滤码流
                byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
                if (info.getEmDataType() == 0 || info.getEmDataType() == 3) {
                    //选择私有码流或mp4码流,拉流出的码流都是私有码流
                    if (dwDataType == 0) {
                        sendBuffer(buffer, lRealHandle.longValue());
                    }
                } else if ((dwDataType - 1000) == info.getEmDataType()) {
                    if(param.longValue() ==0){
                        sendBuffer(buffer, lRealHandle.longValue());
                    }
                }
            }
        }
    }

    // 回调建议写成单例模式, 回调里处理数据，需要另开线程
    @Component
    public static class RealDataCallBack implements NetSDKLib.fRealDataCallBackEx {
        private RealDataCallBack() {}

        private static class RealDataCallBackHolder {
            private static final RealDataCallBack realDataCB = new RealDataCallBack();
        }

        public static final RealDataCallBack getInstance() {
            return RealDataCallBackHolder.realDataCB;
        }

        @Override
        public void invoke(NetSDKLib.LLong lRealHandle, int dwDataType,
                           Pointer pBuffer, int dwBufSize, int param, Pointer dwUser) {
            //RealPlayInfo info = DeviceApi.realPlayHandlers.get(lRealHandle.longValue());
            RealPlayInfo info = realPlayHandlers.get(lRealHandle.longValue());
            if (info != null && info.getLoginHandler() != 0) {
                //过滤码流
                byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
                if (info.getEmDataType() == 0 || info.getEmDataType() == 3) {
                    //选择私有码流或mp4码流,拉流出的码流都是私有码流
                    if (dwDataType == 0) {
                        log.info(dwDataType + ",length:" + buffer.length + " " + Arrays.toString(buffer), RealDataCallBack.class);
                        sendBuffer(buffer, lRealHandle.longValue());
                    }
                } else if ((dwDataType - 1000) == info.getEmDataType()) {
                    log.info(dwDataType + ",length: " + buffer.length + Arrays.toString(buffer), RealDataCallBack.class);
                    sendBuffer(pBuffer.getByteArray(0, dwBufSize), lRealHandle.longValue());
                }
            }
        }
    }

    /**
     * 发送数据
     * @param bytes
     * @param realPlayHandler
     */
    private static void sendBuffer(byte[] bytes, long realPlayHandler) {
        /**
         * 发送流数据
         * 使用pBuffer.getByteBuffer(0,dwBufSize)得到的是一个指向native pointer的ByteBuffer对象,其数据存储在native,
         * 而webSocket发送的数据需要存储在ByteBuffer的成员变量hb，使用pBuffer的getByteBuffer得到的ByteBuffer其hb为null
         * 所以，需要先得到pBuffer的字节数组,手动创建一个ByteBuffer
         */
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        server.forwardDataFlow(realPlayHandler, buffer);
    }


    public static NetSDKLib.NET_TIME handleDate(String odate,String otime){
        NetSDKLib.NET_TIME net_time = new NetSDKLib.NET_TIME();
        String[] odates = odate.split("-");
        int year = Integer.parseInt(odates[0]);
        int month = Integer.parseInt(odates[1]);
        int day = Integer.parseInt(odates[2]);

        String[] otbegins = otime.split(":");
        net_time.dwYear = year;
        net_time.dwMonth = month;
        net_time.dwDay = day;
        net_time.dwHour = Integer.parseInt(otbegins[0]);
        net_time.dwMinute = Integer.parseInt(otbegins[1]);
        net_time.dwSecond = Integer.parseInt(otbegins[2]);
        return net_time;
    }


    /**
     * 根据预览句柄获取对应数据
     * @param handle
     * @return
     */
    public static RealPlayInfo findRealPlayInfo(long handle){
        return realPlayHandlers.get(handle);
    }

    public static long getRealPlayHandleByDeviceId(String deviceId) {
        for (Map.Entry<Long, RealPlayInfo> entry : realPlayHandlers.entrySet()) {
            if (entry.getValue().getDeviceId().equals(deviceId)) {
                return entry.getKey();
            }
        }
        return 0L;
    }

    /**
     * 根据句柄删除数据
     * @param handle
     * @return
     */
    public static boolean removeRealPlayInfo(long handle){
        realPlayHandlers.remove(handle);
        return true;
    }

    /**
     * 根据设备编号，播放时间段，通道号组成的字符串查询句柄并返回句柄
     * @param str
     * @return
     */
    public static long findRealPlayInfoByDeviceIdAndChannelNum(String str){
        for (long key:realPlayHandlers.keySet()) {
            RealPlayInfo info = realPlayHandlers.get(key);
            if("1".equals(info.getType())){
                if((info.getDeviceId()+info.getChannel()).equals(str)){
                    return key;
                }
            }else{
                if((info.getDeviceId() + info.getChannel() +  info.getOdate()).equals(str)){
                    return key;
                }
            }
        }
        return 0;
    }
}

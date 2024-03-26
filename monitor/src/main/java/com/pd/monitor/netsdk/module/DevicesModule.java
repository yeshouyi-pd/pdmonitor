package com.pd.monitor.netsdk.module;

import com.pd.monitor.netsdk.callback.FDownloadDataCallBack;
import com.pd.monitor.netsdk.callback.FRealDataCallback;
import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.lib.ToolKits;
import com.pd.monitor.netsdk.lib.Utils;
import com.pd.monitor.netsdk.po.ChannelInfo;
import com.pd.monitor.netsdk.po.DeviceInfo;
import com.pd.monitor.netsdk.po.VideoDateInfo;
import com.pd.monitor.netsdk.utils.CopyUtil;
import com.sun.jna.Pointer;
import lombok.Data;

import java.nio.charset.Charset;
import java.util.List;

import static com.pd.monitor.netsdk.module.LoginModule.m_stDeviceInfo;
import static com.pd.monitor.netsdk.module.LoginModule.netSdk;


/**
 * 设备模块
 * @author xutongxue
 * @date 2023/8/23 19:01
 */
@Data
public class DevicesModule {

    private NetSDKLib.LLong loginHandler;

    private DeviceInfo deviceInfo;

    private OperationModule operationModule;

    public DevicesModule DevicesModule(DeviceInfo deviceInfo) {
        return CopyUtil.transfer(deviceInfo,DevicesModule.class);
    }

    public DevicesModule(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public boolean stopReport(NetSDKLib.LLong lLong) {
        return operationModule.stopReport(lLong);
    }

    public boolean stopPlay(NetSDKLib.LLong playHandle) {
        return operationModule.stopRealPlay(playHandle);
    }

    public long startReplay(int channelNum, String beginDate, String endDate, FDownloadDataCallBack fDownLoadPosCallBack, String deviceId, int rType) {
        return operationModule.startReplay(loginHandler,channelNum,beginDate,endDate,fDownLoadPosCallBack,deviceId,rType);
    }

    public long startRealPlay(int channelNum, FRealDataCallback FRealDataCallback, String deviceId, int rType) {
        return operationModule.startRealPlay(loginHandler,channelNum, FRealDataCallback,deviceId,rType);
    }

    public boolean login() {

        Pointer deviceId = ToolKits.GetGBKStringToPointer(deviceInfo.getDid());
        //入参
        NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY inParam =
                new NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY();
        inParam.szIP = deviceInfo.getDIp().getBytes(Charset.forName(Utils.getPlatformEncode()));
        inParam.nPort = deviceInfo.getDPort();
        inParam.szUserName = "admin".getBytes(Charset.forName(Utils.getPlatformEncode()));
        inParam.szPassword = "admin123".getBytes(Charset.forName(Utils.getPlatformEncode()));
        inParam.emSpecCap = 2;
        inParam.pCapParam = deviceId;
        //出参
        NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY pstOutParam=new NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY();
        pstOutParam.stuDeviceInfo=m_stDeviceInfo;

        loginHandler = netSdk.CLIENT_LoginWithHighLevelSecurity(inParam, pstOutParam);
        if(loginHandler.longValue() == 0) {
            System.err.printf("Login Device[%s] Port[%d]Failed. %s\n", inParam.szIP, inParam.nPort, ToolKits.getErrorCodePrint());
        } else {
            System.out.println("登录句柄："+loginHandler);
            System.out.println("Login Success [ " + inParam.szIP + " ]");
        }

        return loginHandler.longValue() == 0? false:true;
    }

    public boolean logout() {
        boolean bRet = false;
        if(loginHandler.longValue() != 0) {
            bRet = LoginModule.netSdk.CLIENT_Logout(loginHandler);
            if (bRet){
                System.out.println("Logout Success" + "[" + loginHandler + "]");
            }
            loginHandler.setValue(0);
        }
        return bRet;
    }



    public List<ChannelInfo> findChannelNum() {
        return operationModule.findChannelNum(loginHandler);
    }

    public List<VideoDateInfo> findReplayByDate(int channelNum, String odate) {
        return operationModule.findReplayByDate(loginHandler, channelNum, odate);
    }
}

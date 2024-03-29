package com.pd.monitor.netsdk.callback;

import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.module.AutoRegisterModule;
import com.pd.monitor.netsdk.module.DevicesModule;
import com.pd.monitor.netsdk.utils.DeviceListUtil;
import com.sun.jna.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 主动注册回调函数
 * @author xutongxue
 */
@Component
public class AutoRegisterServiceCallback implements NetSDKLib.fServiceCallBack {
    private static final Logger log = LoggerFactory.getLogger(AutoRegisterModule.class);

    @Override
    public int invoke(NetSDKLib.LLong lHandle, final String pIp, final int wPort,
                      int lCommand, Pointer pParam, int dwParamLen,
                      Pointer dwUserData) {

        // 将 pParam 转化为序列号
        byte[] buffer = new byte[dwParamLen];
        pParam.read(0, buffer, 0, dwParamLen);
        String deviceId = "";
        try {
            deviceId = new String(buffer, "GBK").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.error("Register Device Info:"+"address:"+pIp+",port:"+wPort+",DeviceID:"+deviceId);
        System.out.printf("Register Device Info [Device address %s][port %s][DeviceID %s] \n", pIp, wPort, deviceId);
        switch(lCommand) {
            case NetSDKLib.EM_LISTEN_TYPE.NET_DVR_DISCONNECT: {  // 验证期间设备断线回调
                System.out.println("验证期间设备断线回调" + new Date());
                List<DevicesModule> deviceList = DeviceListUtil.getDeviceList();
                for (DevicesModule deviceModule : deviceList) {
                    if (deviceModule.getDeviceInfo().getDid().equals(deviceId)) {
                        deviceModule.getDeviceInfo().setDIp(pIp);
                        deviceModule.getDeviceInfo().setDPort(0);
                    }
                }
                break;
            }
            case NetSDKLib.EM_LISTEN_TYPE.NET_DVR_SERIAL_RETURN: {// 设备注册携带序列号
                System.out.println("设备注册携带序列号" + new Date());
                List<DevicesModule> deviceList = DeviceListUtil.getDeviceList();
                for (DevicesModule deviceModule : deviceList) {
                    if (deviceModule.getDeviceInfo().getDid().equals(deviceId)) {
                        //设置为虚拟pIp,wPort
                        deviceModule.getDeviceInfo().setDIp(pIp);
                        deviceModule.getDeviceInfo().setDPort(wPort);
                        Executors.newSingleThreadExecutor().submit(new Runnable() {
                            @Override
                            public void run() {
                                //设备登陆，标记状态
                                boolean login = deviceModule.login();
                                if (login){
                                    System.out.println("获取登录句柄成功："+deviceModule.getDeviceInfo().getDid());
                                }
                            }
                        });
                    }
                }
                break;
            }
            default:
                break;
        }
        return 0;
    }
}

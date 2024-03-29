package com.pd.monitor.netsdk.module;

import com.pd.monitor.netsdk.callback.AutoRegisterServiceCallback;
import com.pd.monitor.netsdk.callback.FSnapReceiveCallback;
import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.lib.ToolKits;
import com.pd.monitor.netsdk.utils.DeviceListUtil;
import com.sun.jna.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * 主动注册服务
 * @author xutongxue
 * @date 2023/8/23 12:18
 */
public class AutoRegisterModule {

    public static final NetSDKLib DHNetSdkLib = NetSDKLib.NETSDK_INSTANCE;
    // 监听服务句柄
    public static NetSDKLib.LLong mServerHandler = new NetSDKLib.LLong(0);
    // 主动注册监听回调
    private static AutoRegisterServiceCallback serviceCallback = new AutoRegisterServiceCallback();
    // 设备断线回调
    private static DisConnect disConnectCallback = new DisConnect();
    // 设备重连通知回调
    private static HaveReConnect haveReConnect = new HaveReConnect();
    // 抓图回调
    public static FSnapReceiveCallback captureCallback = new FSnapReceiveCallback();

    private static final Logger log = LoggerFactory.getLogger(AutoRegisterModule.class);

    /**
     * 开启服务
     * @param address 本地IP地址
     * @param port 本地端口, 建议9500
     */
    public static boolean startServer(String address, int port) {
        //SDK初始化，并设置回调
        boolean flag = LoginModule.init(disConnectCallback, haveReConnect);
        log.error(flag+"");
        LoginModule.netSdk.CLIENT_SetSnapRevCallBack(captureCallback, null);
        if (flag){
            System.out.println("服务启动成功");
            log.error("服务启动成功");
        }
        mServerHandler = DHNetSdkLib.CLIENT_ListenServer(address, port, 1000, serviceCallback, null);
        if (0 == mServerHandler.longValue()) {
            System.err.println("Failed to start server." + ToolKits.getErrorCodePrint());
            log.error("Failed to start server." + ToolKits.getErrorCodePrint());
        } else {
            System.out.printf("Start server, [Server address %s][Server port %d]\n", address, port);
            log.error("Start server "+ address + "---" + port);
        }
        return mServerHandler.longValue() != 0;
    }

    /**
     * 结束服务
     */
    public static boolean stopServer() {
        boolean flag = false;
        if(mServerHandler.longValue() != 0) {
            flag = DHNetSdkLib.CLIENT_StopListenServer(mServerHandler);
            mServerHandler.setValue(0);
            System.out.println("Stop server!");
        }
        return flag;
    }

    /**
     * 获取服务状态
     * @return
     */
    public static boolean getServerState() {
        return mServerHandler.longValue() != 0;
    }

    /**
     * 设备断线回调: 通过 CLIENT_Init 设置该回调函数，当设备出现断线时，SDK会调用该函数
     */
    private static class DisConnect implements NetSDKLib.fDisConnect {
        public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
            System.out.printf("Device[%s] Port[%d] DisConnect!\n", pchDVRIP, nDVRPort);
        }
    }

    /**
     * 设备重连回调: 通过 CLIENT_Init 设置该回调函数，当设备出现断线时，SDK会调用该函数
     */
    private static class HaveReConnect implements NetSDKLib.fHaveReConnect {
        public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
            System.out.printf("ReConnect Device[%s] Port[%d]\n", pchDVRIP, nDVRPort);
        }
    }

    /**
     * 获取本地地址
     * @return
     */
    private String getHostAddress() {
        String address = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            address = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address;
    }

    public static List<DevicesModule> getDeviceList() {
        return DeviceListUtil.getDeviceList();
    }

}

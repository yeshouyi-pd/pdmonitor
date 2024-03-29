package com.pd.monitor.netsdk.module;

import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.lib.NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY;
import com.pd.monitor.netsdk.lib.ToolKits;
import com.sun.jna.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author xutongxue
 */
public class LoginModule {

    public static NetSDKLib netSdk 	= NetSDKLib.NETSDK_INSTANCE;

    // 设备信息
    public static NetSDKLib.NET_DEVICEINFO_Ex m_stDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();

    // 登陆句柄
    public static NetSDKLib.LLong m_hLoginHandle = new NetSDKLib.LLong(0);

    // 网络断线处理
    public static DisConnect disConnect = new DisConnect();

    // 设备连接恢复，实现设备连接恢复接口
    public static HaveReConnect haveReConnect = new HaveReConnect();

    private static boolean bInit    = false;
    private static boolean bLogopen = false;

    private static final Logger LOG = LoggerFactory.getLogger(RealPlayModule.class);

    /**
     * 初始化
     */
    public static boolean init(NetSDKLib.fDisConnect disConnect, NetSDKLib.fHaveReConnect haveReConnect) {
        bInit = netSdk.CLIENT_Init(disConnect, null);
        if(!bInit) {
            LOG.error("Initialize SDK failed");
            return false;
        }

        //打开日志，可选
        NetSDKLib.LOG_SET_PRINT_INFO setLog = new NetSDKLib.LOG_SET_PRINT_INFO();
        File path = new File("./sdklog/");
        if (!path.exists()) {
            path.mkdir();
        }
        String logPath = path.getAbsoluteFile().getParent() + "\\sdklog\\" + ToolKits.getDate() + ".log";
        setLog.nPrintStrategy = 0;
        setLog.bSetFilePath = 1;
        System.arraycopy(logPath.getBytes(), 0, setLog.szLogFilePath, 0, logPath.getBytes().length);
        System.out.println(logPath);
        setLog.bSetPrintStrategy = 1;
        bLogopen = netSdk.CLIENT_LogOpen(setLog);
        if(!bLogopen ) {
            LOG.error("Failed to open NetSDK log");
        }

        // 设置断线重连回调接口，设置过断线重连成功回调函数后，当设备出现断线情况，SDK内部会自动进行重连操作
        // 此操作为可选操作，但建议用户进行设置
        netSdk.CLIENT_SetAutoReconnect(haveReConnect, null);

        //设置登录超时时间和尝试次数，可选
        int waitTime = 5000; //登录请求响应超时时间设置为5S
        int tryTimes = 1;    //登录时尝试建立链接1次
        netSdk.CLIENT_SetConnectTime(waitTime, tryTimes);


        // 设置更多网络参数，NET_PARAM的nWaittime，nConnectTryNum成员与CLIENT_SetConnectTime
        // 接口设置的登录设备超时时间和尝试次数意义相同,可选
        NetSDKLib.NET_PARAM netParam = new NetSDKLib.NET_PARAM();
        netParam.nConnectTime = 10000;      // 登录时尝试建立链接的超时时间
        netParam.nGetConnInfoTime = 3000;   // 设置子连接的超时时间
        netParam.nGetDevInfoTime = 3000;//获取设备信息超时时间，为0默认1000ms
        netSdk.CLIENT_SetNetworkParam(netParam);

        return true;
    }

    /**
     * 清除环境
     */
    public static void cleanup() {
        if(bLogopen) {
            netSdk.CLIENT_LogClose();
        }

        if(bInit) {
            netSdk.CLIENT_Cleanup();
        }
    }

    /**
     * 登录设备
     */
    public static boolean login(String m_strIp, int m_nPort, String m_strUser, String m_strPassword) {
        //IntByReference nError = new IntByReference(0);
        //入参
        NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY pstInParam=new NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY();
        pstInParam.nPort=m_nPort;
        pstInParam.szIP=m_strIp.getBytes();
        pstInParam.szPassword=m_strPassword.getBytes();
        pstInParam.szUserName=m_strUser.getBytes();
        //出参
        NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY pstOutParam=new NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY();
        pstOutParam.stuDeviceInfo=m_stDeviceInfo;

        m_hLoginHandle = netSdk.CLIENT_LoginWithHighLevelSecurity(pstInParam, pstOutParam);
        if(m_hLoginHandle.longValue() == 0) {
            LOG.error("Login Device["+m_strIp+"] Port["+m_nPort+"]Failed."+ToolKits.getErrorCodePrint());
        } else {
            LOG.error("Login Success [ " + m_strIp + " ]");
        }

        return m_hLoginHandle.longValue() == 0? false:true;
    }

    /**
     * 登出设备
     */
    public static boolean logout() {
        if(m_hLoginHandle.longValue() == 0) {
            return false;
        }

        boolean bRet = netSdk.CLIENT_Logout(m_hLoginHandle);
        if(bRet) {
            System.out.println("Logout Success [ " + m_hLoginHandle + " ]");
            m_hLoginHandle.setValue(0);
        }

        return bRet;
    }

    // 设备断线回调: 通过 CLIENT_Init 设置该回调函数，当设备出现断线时，SDK会调用该函数
    private static class DisConnect implements NetSDKLib.fDisConnect
    {
        public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser)
        {
            System.out.printf("Device[%s] Port[%d] DisConnect!\n", pchDVRIP, nDVRPort);
        }
    }

    // 网络连接恢复，设备重连成功回调
    // 通过 CLIENT_SetAutoReconnect 设置该回调函数，当已断线的设备重连成功时，SDK会调用该函数
    private static class HaveReConnect implements NetSDKLib.fHaveReConnect {
        public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser)
        {
            System.out.printf("ReConnect Device[%s] Port[%d]\n", pchDVRIP, nDVRPort);
        }
    }
}

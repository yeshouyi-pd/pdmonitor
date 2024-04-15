package com.pd.monitor.netsdk.controller;

import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.module.LoginModule;
import com.pd.monitor.netsdk.module.RealPlayModule;
import com.pd.monitor.netsdk.utils.Result;
import com.pd.monitor.netsdk.utils.ResultUtils;
import com.pd.monitor.netsdk.websocketServer.WebSocketServerDh;
import com.pd.server.config.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

@Slf4j
@RequestMapping("/realPlayManager")
@RestController
public class RealPlayController {


    // 初始化sdk
    public static NetSDKLib netsdk = NetSDKLib.NETSDK_INSTANCE;

    // 设备信息
    private static NetSDKLib.NET_DEVICEINFO_Ex m_stDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();



    @PostMapping("/startPlay/{nChannelID}")
    public Result startPlay(@PathVariable  int nChannelID){
/*        try{
            // 初始化
            LoginModule.init(LoginModule.disConnect, LoginModule.haveReConnect);
            // 若未登录，先登录。
            if (LoginModule.m_hLoginHandle.longValue() == 0){
                LoginModule.login("49.239.193.148", 43489, "admin", "admin@bht2023");
            }
            //
           if(WebSocketServerDh.channlAndlRealHandles .size() == 0 ){
                RealPlayModule.startRealPlay(LoginModule.m_hLoginHandle,nChannelID, 0, 5);
            }else{
                if(!WebSocketServerDh.channlAndlRealHandles.containsKey(nChannelID)){
                    RealPlayModule.startRealPlay(LoginModule.m_hLoginHandle,nChannelID, 0, 5);
                }else{
                    if ( WebSocketServerDh.channlAndlRealHandles.get(nChannelID).longValue() == 0){
                        RealPlayModule.startRealPlay(LoginModule.m_hLoginHandle,nChannelID, 0, 5);
                    }
                }

            }


            return ResultUtils.getSuccessResult("登录成功");
        }catch (Exception e){
            return ResultUtils.getFailResult("登录失败");
        }*/
        return ResultUtils.getSuccessResult("登录成功");
    }



}

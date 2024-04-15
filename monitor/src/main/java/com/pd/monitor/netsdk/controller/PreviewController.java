package com.pd.monitor.netsdk.controller;

import com.pd.monitor.netsdk.utils.Result;
import com.pd.monitor.netsdk.utils.ResultUtils;
import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.module.DevicesModule;
import com.pd.monitor.netsdk.po.DeviceInfo;
import com.pd.monitor.netsdk.service.VideoMonitorService;
import com.pd.monitor.netsdk.websocketServer.WebSocketServerDh;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.pd.monitor.netsdk.utils.DeviceListUtil.getDeviceModuleByDeviceId;

/**
 * 预览管理
 * @author xutongxue
 * @date 2023/8/23 16:05
 */
@Slf4j
@RequestMapping("/previewManager")
@RestController
public class PreviewController {
    @Resource
    private VideoMonitorService videoMonitorService;


    @PostMapping("/start")
    public Result startReal(@RequestBody DeviceInfo deviceInfo) {
        String device = deviceInfo.getDid();
        DevicesModule devicesModule = getDeviceModuleByDeviceId(deviceInfo.getDid());
        if (devicesModule.getDeviceInfo().getDPort() == 0){
            log.error("设备未自动注册成功，请等待1分钟再试");
            return ResultUtils.getFailResult(-99, "设备未自动注册成功，请等待1分钟再试");
        }
        try {
            boolean b =false;
            if (!b) {
                log.error("未获取到WebSocket服务，请先建立通道");
                return ResultUtils.getFailResult(-99, "视频连接错误,请重新打开页面");
            }
            long realPlayHandle = videoMonitorService.startRealPlay(device, 0, 0);
            if (realPlayHandle == 0) {
                log.error("获取播放句柄发生错误");
                return ResultUtils.getFailResult(-99, "视频连接错误,请重新打开页面");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("realPlayHandler", realPlayHandle);
            return ResultUtils.getSuccessResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getFailResult(-99, "视频连接错误");
        }
    }

    @PostMapping("/stop")
    public Result stopReal(@RequestBody DeviceInfo deviceInfo) {
        String device = deviceInfo.getDid();
        DevicesModule devicesModule = getDeviceModuleByDeviceId(deviceInfo.getDid());
        if (devicesModule.getDeviceInfo().getDPort() == 0){
            log.error("设备未自动注册成功，无需关闭预览");
            return ResultUtils.getFailResult(-99, "设备未自动注册成功，无需关闭预览");
        }
        if (StringUtils.isEmpty(device)) {
            log.error("关闭预览参数错误");
            return ResultUtils.getFailResult(-99, "参数错误");
        }
        try {
            boolean b = videoMonitorService.stopPlay(device);
            if (!b) {
                log.error("关闭预览失败");
                return ResultUtils.getFailResult(-99, "关闭窗口失败");
            }
            return ResultUtils.getSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("关闭预览发生错误");
            return ResultUtils.getFailResult(-99, "关闭窗口失败");
        }
    }

    @PostMapping("/closeOpen")
    public Result closeOpen(@RequestParam("device") String device, @RequestParam("handler") long handler, @RequestParam("channelNum") int channelNum, HttpServletRequest request, @RequestParam("type") String type, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate, @RequestParam("rType") int rType) {
        ResultUtils result = new ResultUtils();
        if (StringUtils.isEmpty(device)) {
            return ResultUtils.getFailResult(-99, "参数错误");
        }
        try {
            boolean b = false;
            if ("1".equals(type)) {
                b = videoMonitorService.stopPlay(device);
            } else if ("2".equals(type)) {
                b = videoMonitorService.stopReport(new NetSDKLib.LLong(handler), device);
            }
            long realPlayHandle = 0;
            if ("1".equals(type)) {
                realPlayHandle = videoMonitorService.startRealPlay(device, channelNum, rType);
            } else if ("2".equals(type)) {
                realPlayHandle = videoMonitorService.startReplay(device, channelNum, beginDate, endDate, rType);
            }
            if (realPlayHandle == 0) {
                return ResultUtils.getFailResult(-99, "视频连接错误,请重试");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("realPlayHandler", realPlayHandle);
            return ResultUtils.getSuccessResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getFailResult(-99, "关闭实时监控错误");
        }
    }

}
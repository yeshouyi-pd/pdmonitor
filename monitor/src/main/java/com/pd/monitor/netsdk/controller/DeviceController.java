package com.pd.monitor.netsdk.controller;

import com.pd.monitor.netsdk.utils.Result;
import com.pd.monitor.netsdk.utils.ResultUtils;
import com.pd.monitor.netsdk.module.DevicesModule;
import com.pd.monitor.netsdk.po.ChannelInfo;
import com.pd.monitor.netsdk.po.DeviceInfo;
import com.pd.monitor.netsdk.service.VideoMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pd.monitor.netsdk.utils.DeviceListUtil.getDeviceList;
import static com.pd.monitor.netsdk.utils.DeviceListUtil.getDeviceModuleByDeviceId;

/**
 * 设备管理
 * @author xutongxue
 * @date 2023/8/23 12:58
 */
@Slf4j
@RequestMapping("/deviceManager")
@RestController
public class DeviceController {

    @Autowired
    private VideoMonitorService videoMonitorService;

    @PostMapping("/add")
    public Result add(@RequestBody DeviceInfo deviceInfo) {
        List<DevicesModule> deviceList = getDeviceList();
        DevicesModule deviceModuleByDeviceId = getDeviceModuleByDeviceId(deviceInfo.getDid());
        if (deviceModuleByDeviceId == null){
            DevicesModule devicesModule = new DevicesModule(deviceInfo);
            deviceList.add(devicesModule);
            System.out.println("成功添加设备："+deviceInfo.getDid());
            return ResultUtils.getSuccessResult("成功添加设备："+deviceInfo.getDid());
        }else {
            System.out.println("设备已存在："+deviceInfo.getDid());
            return ResultUtils.getSuccessResult("设备已存在："+deviceInfo.getDid());
        }
    }

    @PostMapping("/list")
    public Result list() {
        List<DevicesModule> deviceList = getDeviceList();
        return ResultUtils.getSuccessResult(deviceList);
    }

    @PostMapping("/channel")
    public Result channel(@RequestBody DeviceInfo deviceInfo) {
        List<ChannelInfo> channelInfoList = videoMonitorService.findChannelNum(deviceInfo.getDid());
        return ResultUtils.getSuccessResult(channelInfoList);
    }
}

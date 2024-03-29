package com.pd.monitor.netsdk.controller;

import com.pd.monitor.netsdk.utils.Result;
import com.pd.monitor.netsdk.utils.ResultUtils;
import com.pd.monitor.netsdk.module.DevicesModule;
import com.pd.monitor.netsdk.form.MonitorServerParam;
import com.pd.monitor.netsdk.service.VideoMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.pd.monitor.netsdk.utils.DeviceListUtil.getDeviceList;

/**
 * 监视器管理
 * @author xutongxue
 * @date 2023/8/26 12:50
 */

@Slf4j
@RequestMapping("/monitorManager")
@RestController
public class MonitorController {

    @Resource
    private VideoMonitorService videoMonitorService;

    @PostMapping("/start")
    public Result startMonitor(@RequestBody MonitorServerParam param) {
        videoMonitorService.startMonitorServer(param.getServerIp(),param.getPort());
        return ResultUtils.getSuccessResult();
    }

    @PostMapping("/stop")
    public Result stopMonitor() {
        //登出所有设备
        List<DevicesModule> deviceList = getDeviceList();
        for (DevicesModule deviceModule : deviceList) {
            if (deviceModule.getDeviceInfo().getDPort() != 0) {
                deviceModule.logout();
                deviceList.remove(deviceModule);
            }
        }

        //停止监听服务
        videoMonitorService.stopMonitorServer();
        return ResultUtils.getSuccessResult();
    }
}

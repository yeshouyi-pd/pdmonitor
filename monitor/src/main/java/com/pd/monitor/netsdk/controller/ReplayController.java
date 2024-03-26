package com.pd.monitor.netsdk.controller;

import com.pd.monitor.netsdk.form.ReplayByDateForm;
import com.pd.monitor.netsdk.form.ReplayForm;
import com.pd.monitor.netsdk.po.ChannelInfo;
import com.pd.monitor.netsdk.po.VideoDateInfo;
import com.pd.monitor.netsdk.service.VideoMonitorService;
import com.pd.monitor.netsdk.utils.Result;
import com.pd.monitor.netsdk.utils.ResultUtils;
import com.pd.monitor.netsdk.websocket.WebSocketServerDh;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pd.monitor.netsdk.utils.DateUtil.getOdate;

/**
 * 回放控制器
 * @author xutongxue
 * @date 2023/8/23 16:05
 */
@Slf4j
@RequestMapping("/replayManager")
@RestController
public class ReplayController {

    @Autowired
    private VideoMonitorService videoMonitorService;

    @PostMapping("/play")
    public Result replay(@RequestBody ReplayForm form) {

        try{
            String replayDate = getOdate(form.getBeginTime().split(" ")[0],form.getBeginTime().split(" ")[1]);
            if(!WebSocketServerDh.currentSession(form.getDeviceId()+form.getChannelNum()+replayDate)){
                return ResultUtils.getFailResult(-99, "视频连接错误,请重新打开页面");
            }
            long realPlayHandle = videoMonitorService.startReplay(form.getDeviceId(), form.getChannelNum(), form.getBeginTime(), form.getEndTime(), form.getRType());
            if (realPlayHandle == 0) {
                return ResultUtils.getFailResult(-99, "播放错误，请重试");
            }
            Map<String, Long> map = new HashMap<>();
            map.put("realPlayHandler", realPlayHandle);
            return ResultUtils.getSuccessResult(map);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.getFailResult(-10, "播放回放异常");
        }
    }

    @PostMapping("/queryDate")
    public Result queryDate(@RequestBody ReplayByDateForm form){
        //先登录
        try{
            List<VideoDateInfo> list = videoMonitorService.findReplayDate(form.getDeviceId(), form.getChannelNum(), form.getDate());
            if(list.size() == 0){
                return ResultUtils.getFailResult(-10, "当天无保存录像");
            }
            return ResultUtils.getSuccessResult(list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.getFailResult(-10, "查询回放视频列表失败");
        }
    }
    @PostMapping("/findChannelNum")
    public Result findChannelNum(@RequestBody String deviceId){
        try{
            List<ChannelInfo> list = videoMonitorService.findChannelNum(deviceId);
            return ResultUtils.getSuccessResult(list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.getFailResult(-10, "查询通道号列表失败");
        }
    }
}

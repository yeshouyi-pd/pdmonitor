package com.pd.monitor.controller;

import com.pd.server.main.dto.EventCameraInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EventCameraInfoService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/eventCameraInfo")
public class EventCameraInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(EventCameraInfoController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private EventCameraInfoService eventCameraInfoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        eventCameraInfoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EventCameraInfoDto eventCameraInfoDto) {
        // 保存校验
                ValidatorUtil.length(eventCameraInfoDto.getDvrip(), "所属DVR的IP", 1, 255);
                ValidatorUtil.length(eventCameraInfoDto.getSxtbh(), "摄像头编号", 1, 255);
                ValidatorUtil.length(eventCameraInfoDto.getZt(), "状态", 1, 10);
                ValidatorUtil.length(eventCameraInfoDto.getDz(), "播放地址", 1, 255);
                ValidatorUtil.length(eventCameraInfoDto.getSjid(), "事件id(equipment_file_alarm_event表中的id)", 1, 50);
                ValidatorUtil.length(eventCameraInfoDto.getSbsn(), "设备编号", 1, 255);
                ValidatorUtil.length(eventCameraInfoDto.getDeptcode(), "部门编号", 1, 255);
                ValidatorUtil.length(eventCameraInfoDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(eventCameraInfoDto.getSm(), "说明", 1, 255);
                ValidatorUtil.length(eventCameraInfoDto.getSm1(), "说明1", 1, 255);
                ValidatorUtil.length(eventCameraInfoDto.getSm2(), "说明2", 1, 255);
                ValidatorUtil.length(eventCameraInfoDto.getSm3(), "说明3", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        eventCameraInfoService.save(eventCameraInfoDto);
        responseDto.setContent(eventCameraInfoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        eventCameraInfoService.delete(id);
        return responseDto;
    }

}

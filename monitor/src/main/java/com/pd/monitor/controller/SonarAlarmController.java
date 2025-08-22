package com.pd.monitor.controller;

import com.pd.server.main.dto.SonarAlarmDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SonarAlarmService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/sonarAlarm")
public class SonarAlarmController {

    private static final Logger LOG = LoggerFactory.getLogger(SonarAlarmController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private SonarAlarmService sonarAlarmService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        sonarAlarmService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SonarAlarmDto sonarAlarmDto) {
        // 保存校验
                ValidatorUtil.length(sonarAlarmDto.getDeviceCode(), "声呐换能器的编号", 1, 50);
                ValidatorUtil.length(sonarAlarmDto.getAlarmDesc(), "报警描述", 1, 255);
                ValidatorUtil.length(sonarAlarmDto.getAlarmTime(), "报警时间", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        sonarAlarmService.save(sonarAlarmDto);
        responseDto.setContent(sonarAlarmDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        sonarAlarmService.delete(id);
        return responseDto;
    }

}

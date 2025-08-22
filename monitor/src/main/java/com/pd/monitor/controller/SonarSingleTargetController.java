package com.pd.monitor.controller;

import com.pd.server.main.dto.SonarSingleTargetDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SonarSingleTargetService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/sonarSingleTarget")
public class SonarSingleTargetController {

    private static final Logger LOG = LoggerFactory.getLogger(SonarSingleTargetController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private SonarSingleTargetService sonarSingleTargetService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        sonarSingleTargetService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SonarSingleTargetDto sonarSingleTargetDto) {
        // 保存校验
                ValidatorUtil.length(sonarSingleTargetDto.getDeviceCode(), "声呐换能器的编号", 1, 50);
                ValidatorUtil.length(sonarSingleTargetDto.getAcTime(), "采集时间", 1, 20);

        ResponseDto responseDto = new ResponseDto();
        sonarSingleTargetService.save(sonarSingleTargetDto);
        responseDto.setContent(sonarSingleTargetDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        sonarSingleTargetService.delete(id);
        return responseDto;
    }

}

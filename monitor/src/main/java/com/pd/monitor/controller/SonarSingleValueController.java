package com.pd.monitor.controller;

import com.pd.server.main.dto.SonarSingleValueDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SonarSingleValueService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/sonarSingleValue")
public class SonarSingleValueController {

    private static final Logger LOG = LoggerFactory.getLogger(SonarSingleValueController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private SonarSingleValueService sonarSingleValueService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        sonarSingleValueService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SonarSingleValueDto sonarSingleValueDto) {
        // 保存校验
                ValidatorUtil.length(sonarSingleValueDto.getSingleTargetId(), "sonar_single_target表id", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        sonarSingleValueService.save(sonarSingleValueDto);
        responseDto.setContent(sonarSingleValueDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        sonarSingleValueService.delete(id);
        return responseDto;
    }

}

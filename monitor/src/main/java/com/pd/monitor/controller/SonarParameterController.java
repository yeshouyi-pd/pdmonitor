package com.pd.monitor.controller;

import com.pd.server.main.dto.SonarParameterDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SonarParameterService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/sonarParameter")
public class SonarParameterController {

    private static final Logger LOG = LoggerFactory.getLogger(SonarParameterController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private SonarParameterService sonarParameterService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        sonarParameterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SonarParameterDto sonarParameterDto) {
        // 保存校验
                ValidatorUtil.length(sonarParameterDto.getDeviceCode(), "声呐换能器的编号", 1, 50);
                ValidatorUtil.length(sonarParameterDto.getMapParaValue(), "报警阈值", 1, 500);
                ValidatorUtil.length(sonarParameterDto.getChangeTime(), "更改时间", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        sonarParameterService.save(sonarParameterDto);
        responseDto.setContent(sonarParameterDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        sonarParameterService.delete(id);
        return responseDto;
    }

}

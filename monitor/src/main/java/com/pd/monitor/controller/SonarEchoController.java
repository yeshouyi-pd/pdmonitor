package com.pd.monitor.controller;

import com.pd.server.main.dto.SonarEchoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SonarEchoService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/sonarEcho")
public class SonarEchoController {

    private static final Logger LOG = LoggerFactory.getLogger(SonarEchoController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private SonarEchoService sonarEchoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        sonarEchoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SonarEchoDto sonarEchoDto) {
        // 保存校验
                ValidatorUtil.length(sonarEchoDto.getDeviceCode(), "声呐换能器的编号", 1, 50);
                ValidatorUtil.length(sonarEchoDto.getAcTime(), "采集时间", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        sonarEchoService.save(sonarEchoDto);
        responseDto.setContent(sonarEchoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        sonarEchoService.delete(id);
        return responseDto;
    }

}

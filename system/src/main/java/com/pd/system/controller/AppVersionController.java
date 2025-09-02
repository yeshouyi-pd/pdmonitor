package com.pd.system.controller;

import com.pd.server.main.dto.AppVersionDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppVersionService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/appVersion")
public class AppVersionController {

    private static final Logger LOG = LoggerFactory.getLogger(AppVersionController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private AppVersionService appVersionService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        appVersionService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppVersionDto appVersionDto) {
        // 保存校验

        ResponseDto responseDto = new ResponseDto();
        appVersionService.save(appVersionDto);
        responseDto.setContent(appVersionDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        appVersionService.delete(id);
        return responseDto;
    }

}

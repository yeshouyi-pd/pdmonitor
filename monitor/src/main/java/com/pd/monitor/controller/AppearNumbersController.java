package com.pd.monitor.controller;

import com.pd.server.main.dto.AppearNumbersDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppearNumbersService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/appearNumbers")
public class AppearNumbersController {

    private static final Logger LOG = LoggerFactory.getLogger(AppearNumbersController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private AppearNumbersService appearNumbersService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        appearNumbersService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppearNumbersDto appearNumbersDto) {
        // 保存校验
                ValidatorUtil.length(appearNumbersDto.getSbbh(), "设备编号", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getBjsj(), "出现时间(只有年月日)", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getXs(), "小时", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getFz(), "分钟", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getDeptcode(), "部门编号", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getXmbh(), "项目编号", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getSm(), "", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getBz(), "", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getSm1(), "", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(appearNumbersDto.getSm3(), "", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        appearNumbersService.save(appearNumbersDto);
        responseDto.setContent(appearNumbersDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        appearNumbersService.delete(id);
        return responseDto;
    }

}

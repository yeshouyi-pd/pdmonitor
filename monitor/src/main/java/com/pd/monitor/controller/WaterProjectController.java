package com.pd.monitor.controller;

import com.pd.server.main.dto.WaterProjectDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WaterProjectService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/waterProject")
public class WaterProjectController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterProjectController.class);
    public static final String BUSINESS_NAME = "项目管理";

    @Resource
    private WaterProjectService waterProjectService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaterProjectDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        waterProjectService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterProjectDto waterProjectDto) {
        // 保存校验
                ValidatorUtil.length(waterProjectDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(waterProjectDto.getXmmc(), "项目名称", 1, 512);
                ValidatorUtil.length(waterProjectDto.getFzr(), "项目负责人", 1, 128);
                ValidatorUtil.length(waterProjectDto.getDeptcode(), "所属部门", 1, 36);
                ValidatorUtil.length(waterProjectDto.getCjr(), "", 1, 128);
                ValidatorUtil.length(waterProjectDto.getXmyt(), "项目用途", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm1(), "说明1", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm2(), "说明2", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm3(), "说明3", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm4(), "说明4", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm5(), "说明5", 1, 1024);

        ResponseDto responseDto = new ResponseDto();
        waterProjectService.save(waterProjectDto);
        responseDto.setContent(waterProjectDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterProjectService.delete(id);
        return responseDto;
    }

}

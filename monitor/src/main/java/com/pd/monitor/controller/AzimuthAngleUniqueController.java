package com.pd.monitor.controller;

import com.pd.server.main.dto.AzimuthAngleUniqueDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AzimuthAngleUniqueService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/azimuthAngleUnique")
public class AzimuthAngleUniqueController {

    private static final Logger LOG = LoggerFactory.getLogger(AzimuthAngleUniqueController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private AzimuthAngleUniqueService azimuthAngleUniqueService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        azimuthAngleUniqueService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AzimuthAngleUniqueDto azimuthAngleUniqueDto) {
        // 保存校验
                ValidatorUtil.require(azimuthAngleUniqueDto.getSbbh(), "设备编号");
                ValidatorUtil.length(azimuthAngleUniqueDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(azimuthAngleUniqueDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(azimuthAngleUniqueDto.getXs(), "小时", 1, 13);
                ValidatorUtil.require(azimuthAngleUniqueDto.getFz(), "分钟");
                ValidatorUtil.length(azimuthAngleUniqueDto.getFz(), "分钟", 1, 16);
                ValidatorUtil.length(azimuthAngleUniqueDto.getDeptcode(), "部门编号", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        azimuthAngleUniqueService.save(azimuthAngleUniqueDto);
        responseDto.setContent(azimuthAngleUniqueDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        azimuthAngleUniqueService.delete(id);
        return responseDto;
    }

}

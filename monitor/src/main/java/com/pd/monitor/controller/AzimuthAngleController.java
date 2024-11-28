package com.pd.monitor.controller;

import com.pd.server.main.dto.AzimuthAngleDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AzimuthAngleService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/azimuthAngle")
public class AzimuthAngleController {

    private static final Logger LOG = LoggerFactory.getLogger(AzimuthAngleController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private AzimuthAngleService azimuthAngleService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        azimuthAngleService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AzimuthAngleDto azimuthAngleDto) {
        // 保存校验
                ValidatorUtil.length(azimuthAngleDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(azimuthAngleDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(azimuthAngleDto.getXs(), "小时", 1, 13);
                ValidatorUtil.length(azimuthAngleDto.getFz(), "分钟", 1, 16);
                ValidatorUtil.length(azimuthAngleDto.getTs(), "头数", 1, 10);
                ValidatorUtil.length(azimuthAngleDto.getDeptcode(), "部门编号", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        azimuthAngleService.save(azimuthAngleDto);
        responseDto.setContent(azimuthAngleDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        azimuthAngleService.delete(id);
        return responseDto;
    }

}

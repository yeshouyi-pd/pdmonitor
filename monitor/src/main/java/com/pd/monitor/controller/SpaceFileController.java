package com.pd.monitor.controller;

import com.pd.server.main.dto.SpaceFileDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SpaceFileService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/spaceFile")
public class SpaceFileController {

    private static final Logger LOG = LoggerFactory.getLogger(SpaceFileController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private SpaceFileService spaceFileService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        spaceFileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SpaceFileDto spaceFileDto) {
        // 保存校验
                ValidatorUtil.length(spaceFileDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(spaceFileDto.getWjlj(), "文件路径", 1, 255);
                ValidatorUtil.length(spaceFileDto.getYjz(), "预警值", 1, 50);
                ValidatorUtil.length(spaceFileDto.getDeptcode(), "部门编号", 1, 50);
                ValidatorUtil.length(spaceFileDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(spaceFileDto.getBz(), "备注", 1, 100);
                ValidatorUtil.length(spaceFileDto.getSm(), "说明", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        spaceFileService.save(spaceFileDto);
        responseDto.setContent(spaceFileDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        spaceFileService.delete(id);
        return responseDto;
    }

}

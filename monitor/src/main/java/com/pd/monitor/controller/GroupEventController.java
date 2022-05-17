package com.pd.monitor.controller;

import com.pd.server.main.dto.GroupEventDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.GroupEventService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/groupEvent")
public class GroupEventController {

    private static final Logger LOG = LoggerFactory.getLogger(GroupEventController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private GroupEventService groupEventService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        groupEventService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody GroupEventDto groupEventDto) {
        // 保存校验
                ValidatorUtil.length(groupEventDto.getSbbh(), "设备编号", 1, 255);
                ValidatorUtil.length(groupEventDto.getSm(), "说明", 1, 500);
                ValidatorUtil.length(groupEventDto.getBz(), "备注", 1, 500);
                ValidatorUtil.length(groupEventDto.getBz1(), "备注字段", 1, 500);
                ValidatorUtil.length(groupEventDto.getBz2(), "备注字段", 1, 500);
                ValidatorUtil.length(groupEventDto.getBz3(), "备注字段", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        groupEventService.save(groupEventDto);
        responseDto.setContent(groupEventDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        groupEventService.delete(id);
        return responseDto;
    }

}

package com.pd.monitor.controller;

import com.pd.server.main.dto.SendCommandDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SendCommandService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/sendCommand")
public class SendCommandController {

    private static final Logger LOG = LoggerFactory.getLogger(SendCommandController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private SendCommandService sendCommandService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        sendCommandService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SendCommandDto sendCommandDto) {
        // 保存校验
                ValidatorUtil.length(sendCommandDto.getNodeId(), "基站编号", 1, 100);
                ValidatorUtil.length(sendCommandDto.getLoraId(), "信标编号", 1, 255);
                ValidatorUtil.length(sendCommandDto.getSerialNumber(), "序号", 1, 255);
                ValidatorUtil.length(sendCommandDto.getWholeSec(), "时间整数部分", 1, 255);
                ValidatorUtil.length(sendCommandDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(sendCommandDto.getSm(), "说明", 1, 255);
                ValidatorUtil.length(sendCommandDto.getRemark(), "标注", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        sendCommandService.save(sendCommandDto);
        responseDto.setContent(sendCommandDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        sendCommandService.delete(id);
        return responseDto;
    }

}

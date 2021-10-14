package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.dto.InterfaceLogDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.InterfaceLogService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/interfaceLog")
public class InterfaceLogController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(InterfaceLogController.class);
    public static final String BUSINESS_NAME = "接口日志表";

    @Resource
    private InterfaceLogService interfaceLogService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        interfaceLogService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody InterfaceLogDto interfaceLogDto) {
        // 保存校验
                ValidatorUtil.length(interfaceLogDto.getIp(), "请求ip", 1, 255);
                ValidatorUtil.length(interfaceLogDto.getQqry(), "请求人员", 1, 255);
                ValidatorUtil.length(interfaceLogDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(interfaceLogDto.getBz1(), "备注字段1", 1, 255);
                ValidatorUtil.length(interfaceLogDto.getBz2(), "备注字段2", 1, 255);
                ValidatorUtil.length(interfaceLogDto.getBz3(), "备注字段3", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        interfaceLogService.save(interfaceLogDto);
        responseDto.setContent(interfaceLogDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        interfaceLogService.delete(id);
        return responseDto;
    }

}

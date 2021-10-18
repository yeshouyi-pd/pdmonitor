package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.dto.AuthorizeInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AuthorizeInfoService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/authorizeInfo")
public class AuthorizeInfoController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizeInfoController.class);
    public static final String BUSINESS_NAME = "设备授权信息表";

    @Resource
    private AuthorizeInfoService authorizeInfoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody AuthorizeInfoDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        authorizeInfoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AuthorizeInfoDto authorizeInfoDto) {
        // 保存校验
                ValidatorUtil.length(authorizeInfoDto.getDwmc(), "单位名称", 1, 255);
                ValidatorUtil.length(authorizeInfoDto.getSbsn(), "设备编号", 1, 255);
                ValidatorUtil.length(authorizeInfoDto.getSqm(), "授权码", 1, 255);
                ValidatorUtil.length(authorizeInfoDto.getIp(), "客户ip", 1, 255);
                ValidatorUtil.length(authorizeInfoDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(authorizeInfoDto.getBz1(), "备注字段1", 1, 255);
                ValidatorUtil.length(authorizeInfoDto.getBz2(), "备注字段2", 1, 255);
                ValidatorUtil.length(authorizeInfoDto.getBz3(), "备注字段3", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        authorizeInfoService.save(authorizeInfoDto);
        responseDto.setContent(authorizeInfoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        authorizeInfoService.delete(id);
        return responseDto;
    }

}

package com.pd.monitor.controller;

import com.pd.server.main.dto.LdListDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.LdListService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/ldList")
public class LdListController {

    private static final Logger LOG = LoggerFactory.getLogger(LdListController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private LdListService ldListService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        ldListService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody LdListDto ldListDto) {
        // 保存校验
                ValidatorUtil.require(ldListDto.getIccid(), "SIM卡卡号");
                ValidatorUtil.length(ldListDto.getIccid(), "SIM卡卡号", 1, 32);
                ValidatorUtil.length(ldListDto.getJd(), "经度", 1, 32);
                ValidatorUtil.length(ldListDto.getWd(), "纬度", 1, 32);
                ValidatorUtil.length(ldListDto.getSbms(), "设备描述", 1, 256);
                ValidatorUtil.length(ldListDto.getIpport(), "设备IP:端口", 1, 32);
                ValidatorUtil.length(ldListDto.getState(), "状态0:离线;1:在线", 1, 32);
                ValidatorUtil.length(ldListDto.getDeptcode(), "部门代码", 1, 32);

        ResponseDto responseDto = new ResponseDto();
        ldListService.save(ldListDto);
        responseDto.setContent(ldListDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        ldListService.delete(id);
        return responseDto;
    }

}

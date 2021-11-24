package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.WaterProEquip;
import com.pd.server.main.domain.WaterProEquipExample;
import com.pd.server.main.domain.WaterProUser;
import com.pd.server.main.domain.WaterProUserExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.WaterProEquipService;
import com.pd.server.main.service.WaterProUserService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/waterProEquip")
public class WaterProEquipController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterProEquipController.class);
    public static final String BUSINESS_NAME = "项目设备表";

    @Resource
    private WaterProEquipService waterProEquipService;
    @Resource
    private WaterProUserService waterProUserService;

    /**
     * 通过项目编号查询
     */
    @PostMapping("/findAllByExample")
    public ResponseDto findAllByExample(@RequestBody WaterProEquipDto waterProEquipDto) {
        ResponseDto responseDto = new ResponseDto();
        WaterProEquipExample example = new WaterProEquipExample();
        WaterProEquipExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(waterProEquipDto.getXmbh())){
            ca.andXmbhEqualTo(waterProEquipDto.getXmbh());
        }
        List<WaterProEquip> lists = waterProEquipService.selectByExample(example);
        responseDto.setContent(lists);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaterProEquipDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        List<String> xmbhs = new ArrayList<>();
        if(!"460100".equals(loginUserDto.getDeptcode())){
            xmbhs = waterProUserService.findXmbhByUsercode(loginUserDto.getLoginName());
        }
        waterProEquipService.list(pageDto, xmbhs);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterProEquipDto waterProEquipDto) {
        // 保存校验
        ValidatorUtil.length(waterProEquipDto.getXmbh(), "", 1, 36);
        ValidatorUtil.length(waterProEquipDto.getSbsn(), "", 1, 128);
        ValidatorUtil.length(waterProEquipDto.getCjr(), "创建人", 1, 128);
        ValidatorUtil.length(waterProEquipDto.getSm1(), "说明1", 1, 1024);
        ValidatorUtil.length(waterProEquipDto.getSm2(), "说明2", 1, 1024);
        ValidatorUtil.length(waterProEquipDto.getSm3(), "说明3", 1, 1024);
        ValidatorUtil.length(waterProEquipDto.getSm4(), "说明4", 1, 1024);
        ValidatorUtil.length(waterProEquipDto.getSm5(), "说明4", 1, 1024);
        ValidatorUtil.length(waterProEquipDto.getSm6(), "", 1, 1024);

        ResponseDto responseDto = new ResponseDto();
        waterProEquipService.save(waterProEquipDto);
        responseDto.setContent(waterProEquipDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterProEquipService.delete(id);
        return responseDto;
    }

}

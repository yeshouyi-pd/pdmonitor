package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.EquipmentTyEvent;
import com.pd.server.main.domain.EquipmentTyEventExample;
import com.pd.server.main.dto.EquipmentTyEventDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentTyEventService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/equipmentTyEvent")
public class EquipmentTyEventController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentTyEventController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private EquipmentTyEventService equipmentTyEventService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EquipmentTyEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        List<String> deptList = getUpdeptcode(userDto.getDeptcode());
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentTyEventExample example = new EquipmentTyEventExample();
        EquipmentTyEventExample.Criteria ca = example.createCriteria();
        if(!CollectionUtils.isEmpty(deptList)){
            ca.andDeptcodeIn(deptList);
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(userDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(userDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        List<EquipmentTyEvent> equipmentTyEventList = equipmentTyEventService.list(example);
        PageInfo<EquipmentTyEvent> pageInfo = new PageInfo<>(equipmentTyEventList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentTyEventDto> equipmentTyEventDtoList = CopyUtil.copyList(equipmentTyEventList, EquipmentTyEventDto.class);
        pageDto.setList(equipmentTyEventDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentTyEventDto equipmentTyEventDto) {
        // 保存校验
                ValidatorUtil.length(equipmentTyEventDto.getSbbh(), "设备编号", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getTs(), "头数", 1, 20);
                ValidatorUtil.length(equipmentTyEventDto.getDeptcode(), "部门编号", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getSm(), "说明", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getSm1(), "", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getSm3(), "", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        equipmentTyEventService.save(equipmentTyEventDto);
        responseDto.setContent(equipmentTyEventDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentTyEventService.delete(id);
        return responseDto;
    }

}

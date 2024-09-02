package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.utils.SendSmsTool;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.EquipmentTyEventDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFileTyService;
import com.pd.server.main.service.EquipmentFileTyTodayService;
import com.pd.server.main.service.EquipmentTyEventService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateUtils;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/equipmentTyEvent")
public class EquipmentTyEventController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentTyEventController.class);
    public static final String BUSINESS_NAME = "聚类事件";

    @Resource
    private EquipmentTyEventService equipmentTyEventService;
    @Resource
    private EquipmentFileTyService equipmentFileTyService;
    @Resource
    private EquipmentFileTyTodayService equipmentFileTyTodayService;

    @GetMapping("/sendMessage")
    public ResponseDto sendMessage(){
        ResponseDto responseDto = new ResponseDto();
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        String tyPhone = attrMap.get("tyPhone");
        Boolean falge = SendSmsTool.sendSms("2037275","",tyPhone);
        if(falge){
            responseDto.setSuccess(true);
        }else{
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

    @GetMapping("/getTodayEvent/{sbbh}")
    public ResponseDto getTodayEvent(@PathVariable String sbbh){
        ResponseDto responseDto = new ResponseDto();
//        LoginUserDto userDto = getRequestHeader();
//        List<String> depts = getUpdeptcode(userDto.getDeptcode());
        EquipmentTyEventExample example = new EquipmentTyEventExample();
        EquipmentTyEventExample.Criteria ca = example.createCriteria();
        ca.andRqEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"));
//        if(!CollectionUtils.isEmpty(depts)){
//            ca.andDeptcodeIn(depts);
//        }
        ca.andSbbhEqualTo(sbbh);
        example.setOrderByClause(" kssj desc ");
        List<EquipmentTyEvent> lists = equipmentTyEventService.list(example);
        responseDto.setContent(lists);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EquipmentTyEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentTyEventExample example = new EquipmentTyEventExample();
        EquipmentTyEventExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        example.setOrderByClause(" jssj desc ");
        List<EquipmentTyEvent> equipmentTyEventList = new ArrayList<>();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            equipmentTyEventList = equipmentTyEventService.selectByExampleSpecial(pageDto);
        }else{
            equipmentTyEventList = equipmentTyEventService.list(example);
        }
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
                ValidatorUtil.length(equipmentTyEventDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(equipmentTyEventDto.getKssj(), "开始时间", 1, 100);
                ValidatorUtil.length(equipmentTyEventDto.getJssj(), "结束时间", 1, 100);
                ValidatorUtil.length(equipmentTyEventDto.getRq(), "创建时间", 1, 50);
                ValidatorUtil.length(equipmentTyEventDto.getTs(), "头数", 1, 20);
                ValidatorUtil.length(equipmentTyEventDto.getDeptcode(), "部门编号", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getXmbh(), "项目编号", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getSm(), "说明", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getSm1(), "", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentTyEventDto.getSm3(), "", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        equipmentTyEventService.save(equipmentTyEventDto);
        EquipmentFileTy equipmentFileTy = equipmentFileTyService.selectByPrimaryKey(equipmentTyEventDto.getBz());
        if(!StringUtils.isEmpty(equipmentFileTy) && !StringUtils.isEmpty(equipmentFileTy.getTs())){
            equipmentFileTy.setTs(equipmentTyEventDto.getTs());
            equipmentFileTyService.update(equipmentFileTy);
            EquipmentFileTyToday equipmentFileTyToday = CopyUtil.copy(equipmentFileTy,EquipmentFileTyToday.class);
            equipmentFileTyTodayService.update(equipmentFileTyToday);
        }
        responseDto.setContent(equipmentTyEventDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        EquipmentTyEvent equipmentFileEvent = equipmentTyEventService.selectByPrimaryKey(id);
        equipmentTyEventService.delete(id);
        equipmentFileTyService.delete(equipmentFileEvent.getBz());
        equipmentFileTyTodayService.delete(equipmentFileEvent.getBz());
        return responseDto;
    }

}

package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.EquipmentFileAlarmEvent;
import com.pd.server.main.domain.EquipmentFileAlarmEventExample;
import com.pd.server.main.dto.EquipmentFileAlarmEventDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.EquipmentFileAlarmEventService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/equipmentFileAlarmEvent")
public class EquipmentFileAlarmEventController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileAlarmEventController.class);
    public static final String BUSINESS_NAME = "按事件统计";

    @Resource
    private EquipmentFileAlarmEventService equipmentFileAlarmEventService;

    /**
     * 图表查询
     */
    @PostMapping("/echartsAlarmData")
    public ResponseDto echartsAlarmData(@RequestBody EquipmentFileAlarmEventDto entityDto){
        ResponseDto responseDto = new ResponseDto();
        List<EquipmentFileAlarmEventDto> listall = equipmentFileAlarmEventService.listStatisticsAll(entityDto);
        List<String> xAixsData = listall.stream().filter(Objects::nonNull).map(u->u.getBjsj()).collect(Collectors.toList());
        List<Integer> yAixsData = listall.stream().filter(Objects::nonNull).map(u->u.getCounts()).collect(Collectors.toList());
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("xAixsData",xAixsData);
        resultMap.put("yAixsData",yAixsData);
        responseDto.setContent(resultMap);
        return responseDto;
    }

    /**
     * 图表查询
     */
    @PostMapping("/echartsAlarmDataByDp")
    public ResponseDto echartsAlarmDataByDp(@RequestBody EquipmentFileAlarmEventDto entityDto){
        ResponseDto responseDto = new ResponseDto();
        List<EquipmentFileAlarmEventDto> listall = equipmentFileAlarmEventService.listStatisticsAllByDp(entityDto);
        List<String> xAixsData = listall.stream().filter(Objects::nonNull).map(u->u.getBjsj()).collect(Collectors.toList());
        List<Integer> yAixsData = listall.stream().filter(Objects::nonNull).map(u->u.getCounts()).collect(Collectors.toList());
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("xAixsData",xAixsData);
        resultMap.put("yAixsData",yAixsData);
        responseDto.setContent(resultMap);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/detailByParam")
    public ResponseDto detailByParam(@RequestBody EquipmentFileAlarmEvent entity) {
        ResponseDto responseDto = new ResponseDto();
        List<EquipmentFileAlarmEvent> list = equipmentFileAlarmEventService.detailByParam(entity);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EquipmentFileAlarmEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileAlarmEventExample example = new EquipmentFileAlarmEventExample();
        EquipmentFileAlarmEventExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andBjsjGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andBjsjLessThanOrEqualTo(pageDto.getEtime());
        }
        example.setOrderByClause(" bjsj desc,xh desc ");
        List<EquipmentFileAlarmEvent> equipmentFileAlarmEventList = new ArrayList<>();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            equipmentFileAlarmEventList = equipmentFileAlarmEventService.selectByExampleSpecial(pageDto);
        }else{
            equipmentFileAlarmEventList = equipmentFileAlarmEventService.list(example);
        }
        PageInfo<EquipmentFileAlarmEvent> pageInfo = new PageInfo<>(equipmentFileAlarmEventList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileAlarmEventDto> equipmentFileAlarmEventDtoList = CopyUtil.copyList(equipmentFileAlarmEventList, EquipmentFileAlarmEventDto.class);
        pageDto.setList(equipmentFileAlarmEventDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/statistics")
    public ResponseDto statistics(@RequestBody EquipmentFileAlarmEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        List<String> list = getUpdeptcode(userDto.getDeptcode());
        List<String> sbsns = new ArrayList<>();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            sbsns = userDto.getXmbhsbsns().get(pageDto.getXmbh());
        }
        equipmentFileAlarmEventService.listStatistics(pageDto, list, sbsns);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileAlarmEventDto equipmentFileAlarmEventDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileAlarmEventDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getEventTime(), "事件时间", 1, 100);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getBjsj(), "出现时间", 1, 50);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getSm1(), "", 1, 50);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getSm2(), "", 1, 50);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getSm3(), "", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileAlarmEventService.save(equipmentFileAlarmEventDto);
        responseDto.setContent(equipmentFileAlarmEventDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileAlarmEventService.delete(id);
        return responseDto;
    }

}

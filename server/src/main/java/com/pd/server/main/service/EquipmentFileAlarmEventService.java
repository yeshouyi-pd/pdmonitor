package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileAlarmEvent;
import com.pd.server.main.domain.EquipmentFileAlarmEventExample;
import com.pd.server.main.dto.EquipmentFileAlarmEventDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFileAlarmEventMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EquipmentFileAlarmEventService {

    @Resource
    private EquipmentFileAlarmEventMapper equipmentFileAlarmEventMapper;

    public List<EquipmentFileAlarmEvent> detailByParam(EquipmentFileAlarmEvent entity){
        EquipmentFileAlarmEventExample equipmentFileAlarmEventExample = new EquipmentFileAlarmEventExample();
        EquipmentFileAlarmEventExample.Criteria ca = equipmentFileAlarmEventExample.createCriteria();
        if(!StringUtils.isEmpty(entity.getBjsj())){
            ca.andBjsjEqualTo(entity.getBjsj());
        }
        if(!StringUtils.isEmpty(entity.getDeptcode())){
            ca.andDeptcodeEqualTo(entity.getDeptcode());
        }
        if(!StringUtils.isEmpty(entity.getSbbh())){
            ca.andSbbhEqualTo(entity.getSbbh());
        }
        equipmentFileAlarmEventExample.setOrderByClause(" xh ");
        List<EquipmentFileAlarmEvent> equipmentFileAlarmEventList = equipmentFileAlarmEventMapper.selectByExample(equipmentFileAlarmEventExample);
        return  equipmentFileAlarmEventList;
    }

    /**
    * 列表查询
    */
    public void list(EquipmentFileAlarmEventDto pageDto, List<String> list) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileAlarmEventExample equipmentFileAlarmEventExample = new EquipmentFileAlarmEventExample();
        EquipmentFileAlarmEventExample.Criteria ca = equipmentFileAlarmEventExample.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        List<EquipmentFileAlarmEvent> equipmentFileAlarmEventList = equipmentFileAlarmEventMapper.selectByExample(equipmentFileAlarmEventExample);
        PageInfo<EquipmentFileAlarmEvent> pageInfo = new PageInfo<>(equipmentFileAlarmEventList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileAlarmEventDto> equipmentFileAlarmEventDtoList = CopyUtil.copyList(equipmentFileAlarmEventList, EquipmentFileAlarmEventDto.class);
        pageDto.setList(equipmentFileAlarmEventDtoList);
    }

    /**
     * 列表查询
     */
    public void listStatistics(EquipmentFileAlarmEventDto pageDto, List<String> list) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileAlarmEventExample equipmentFileAlarmEventExample = new EquipmentFileAlarmEventExample();
        EquipmentFileAlarmEventExample.Criteria ca = equipmentFileAlarmEventExample.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andBjsjGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andBjsjLessThanOrEqualTo(pageDto.getEtime());
        }
        List<EquipmentFileAlarmEvent> equipmentFileAlarmEventList = equipmentFileAlarmEventMapper.statisticsByExample(equipmentFileAlarmEventExample);
        PageInfo<EquipmentFileAlarmEvent> pageInfo = new PageInfo<>(equipmentFileAlarmEventList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileAlarmEventDto> equipmentFileAlarmEventDtoList = CopyUtil.copyList(equipmentFileAlarmEventList, EquipmentFileAlarmEventDto.class);
        pageDto.setList(equipmentFileAlarmEventDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileAlarmEventDto equipmentFileAlarmEventDto) {
        EquipmentFileAlarmEvent equipmentFileAlarmEvent = CopyUtil.copy(equipmentFileAlarmEventDto, EquipmentFileAlarmEvent.class);
        if (StringUtils.isEmpty(equipmentFileAlarmEventDto.getId())) {
            this.insert(equipmentFileAlarmEvent);
        } else {
            this.update(equipmentFileAlarmEvent);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileAlarmEvent equipmentFileAlarmEvent) {
        equipmentFileAlarmEvent.setId(UuidUtil.getShortUuid());
        equipmentFileAlarmEventMapper.insert(equipmentFileAlarmEvent);
    }

    /**
    * 更新
    */
    private void update(EquipmentFileAlarmEvent equipmentFileAlarmEvent) {
        equipmentFileAlarmEventMapper.updateByPrimaryKey(equipmentFileAlarmEvent);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        equipmentFileAlarmEventMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentTyEvent;
import com.pd.server.main.domain.EquipmentTyEventExample;
import com.pd.server.main.dto.EquipmentTyEventDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentTyEventMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EquipmentTyEventService {

    @Resource
    private EquipmentTyEventMapper equipmentTyEventMapper;

    /**
    * 列表查询
    */
    public List<EquipmentTyEvent> list(EquipmentTyEventExample example) {
        return equipmentTyEventMapper.selectByExample(example);
    }

    public List<EquipmentTyEvent> selectByExampleSpecial(EquipmentTyEventDto record) {
        return equipmentTyEventMapper.selectByExampleSpecial(record);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentTyEventDto equipmentTyEventDto) {
        EquipmentTyEvent equipmentTyEvent = CopyUtil.copy(equipmentTyEventDto, EquipmentTyEvent.class);
        if (StringUtils.isEmpty(equipmentTyEventDto.getId())) {
            this.insert(equipmentTyEvent);
        } else {
            this.update(equipmentTyEvent);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentTyEvent equipmentTyEvent) {
        equipmentTyEvent.setId(UuidUtil.getShortUuid());
        equipmentTyEventMapper.insert(equipmentTyEvent);
    }

    /**
    * 更新
    */
    private void update(EquipmentTyEvent equipmentTyEvent) {
        equipmentTyEventMapper.updateByPrimaryKey(equipmentTyEvent);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        equipmentTyEventMapper.deleteByPrimaryKey(id);
    }

}
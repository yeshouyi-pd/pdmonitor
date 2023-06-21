package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileEvent;
import com.pd.server.main.domain.EquipmentFileEventExample;
import com.pd.server.main.dto.EquipmentFileEventDto;
import com.pd.server.main.dto.EventDto;
import com.pd.server.main.dto.KvIntDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFileEventMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EquipmentFileEventService {

    @Resource
    private EquipmentFileEventMapper equipmentFileEventMapper;

    public EquipmentFileEvent selectByDp(){
        return equipmentFileEventMapper.selectByDp();
    }

    /**
    * 列表查询
    */
    public List<EquipmentFileEvent> list(EquipmentFileEventExample example) {
        return equipmentFileEventMapper.selectByExample(example);
    }

    public List<EquipmentFileEvent> selectByExampleSpecial(EquipmentFileEventDto record){
        return equipmentFileEventMapper.selectByExampleSpecial(record);
    }

    public EquipmentFileEvent selectByPrimaryKey(String id){
        return equipmentFileEventMapper.selectByPrimaryKey(id);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileEventDto equipmentFileEventDto) {
        EquipmentFileEvent equipmentFileEvent = CopyUtil.copy(equipmentFileEventDto, EquipmentFileEvent.class);
        if (StringUtils.isEmpty(equipmentFileEventDto.getId())) {
            this.insert(equipmentFileEvent);
        } else {
            this.update(equipmentFileEvent);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileEvent equipmentFileEvent) {
        equipmentFileEvent.setId(UuidUtil.getShortUuid());
        equipmentFileEventMapper.insert(equipmentFileEvent);
    }

    /**
    * 更新
    */
    private void update(EquipmentFileEvent equipmentFileEvent) {
        equipmentFileEventMapper.updateByPrimaryKey(equipmentFileEvent);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        equipmentFileEventMapper.deleteByPrimaryKey(id);
    }

    public List<EventDto> getEquipmentEventByDept(EquipmentFileEventExample equipmentFileEventExample) {
        return equipmentFileEventMapper.getEquipmentEventByDept(equipmentFileEventExample);
    }

    public int selectTsBySms(EquipmentFileEventExample equipmentFileEventExample){
        return equipmentFileEventMapper.selectTsBySms(equipmentFileEventExample);
    }
}
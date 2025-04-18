package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileEvent;
import com.pd.server.main.domain.EquipmentFileEventExample;
import java.util.List;

import com.pd.server.main.dto.EquipmentFileEventDto;
import com.pd.server.main.dto.EventDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileEventMapper {
    long countByExample(EquipmentFileEventExample example);

    int deleteByExample(EquipmentFileEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFileEvent record);

    int insertSelective(EquipmentFileEvent record);

    List<EquipmentFileEvent> selectByExampleExport(@Param("record") EquipmentFileEventDto record);

    List<EquipmentFileEvent> selectByDw(EquipmentFileEventExample example);

    List<EquipmentFileEvent> selectByExample(EquipmentFileEventExample example);

    List<EquipmentFileEvent> selectByExampleSpecial(@Param("record") EquipmentFileEventDto record);

    EquipmentFileEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFileEvent record, @Param("example") EquipmentFileEventExample example);

    int updateByExample(@Param("record") EquipmentFileEvent record, @Param("example") EquipmentFileEventExample example);

    int updateByPrimaryKeySelective(EquipmentFileEvent record);

    int updateByPrimaryKey(EquipmentFileEvent record);

    List<EventDto> getEquipmentEventByDept(EquipmentFileEventExample equipmentFileEventExample);

    EquipmentFileEvent selectByDp();

    Integer selectTsBySms(EquipmentFileEventExample equipmentFileEventExample);

    List<EquipmentFileEvent> getEquipmentEventByDeptdate(EquipmentFileEventExample example);

}
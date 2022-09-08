package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileAlarmEvent;
import com.pd.server.main.domain.EquipmentFileAlarmEventExample;
import java.util.List;

import com.pd.server.main.dto.EquipmentFileAlarmEventDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileAlarmEventDwDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileAlarmEventMapper {

    int countTsByExample(EquipmentFileAlarmEventExample example);

    long countByExample(EquipmentFileAlarmEventExample example);

    int deleteByExample(EquipmentFileAlarmEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFileAlarmEvent record);

    int insertSelective(EquipmentFileAlarmEvent record);

    List<EquipmentFileAlarmEvent> selectByExample(EquipmentFileAlarmEventExample example);

    List<EquipmentFileAlarmEventDwDto> selectDwByExample(EquipmentFileAlarmEventExample example);

    EquipmentFileAlarmEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFileAlarmEvent record, @Param("example") EquipmentFileAlarmEventExample example);

    int updateByExample(@Param("record") EquipmentFileAlarmEvent record, @Param("example") EquipmentFileAlarmEventExample example);

    int updateByPrimaryKeySelective(EquipmentFileAlarmEvent record);

    int updateByPrimaryKey(EquipmentFileAlarmEvent record);

    List<EquipmentFileAlarmEventDto> statisticsByExample(EquipmentFileAlarmEventExample example);

    List<EquipmentFileAlarmEventDto> statisticsByExampleASC(EquipmentFileAlarmEventExample example);

    List<EquipmentFileAlarmEventDto> statisticsByExampleOnlyDept(EquipmentFileAlarmEventExample example);

}
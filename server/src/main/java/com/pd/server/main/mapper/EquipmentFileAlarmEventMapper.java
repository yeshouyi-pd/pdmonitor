package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileAlarmEvent;
import com.pd.server.main.domain.EquipmentFileAlarmEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileAlarmEventMapper {
    long countByExample(EquipmentFileAlarmEventExample example);

    int deleteByExample(EquipmentFileAlarmEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFileAlarmEvent record);

    int insertSelective(EquipmentFileAlarmEvent record);

    List<EquipmentFileAlarmEvent> selectByExample(EquipmentFileAlarmEventExample example);

    EquipmentFileAlarmEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFileAlarmEvent record, @Param("example") EquipmentFileAlarmEventExample example);

    int updateByExample(@Param("record") EquipmentFileAlarmEvent record, @Param("example") EquipmentFileAlarmEventExample example);

    int updateByPrimaryKeySelective(EquipmentFileAlarmEvent record);

    int updateByPrimaryKey(EquipmentFileAlarmEvent record);

    List<EquipmentFileAlarmEvent> statisticsByExample(EquipmentFileAlarmEventExample example);
}
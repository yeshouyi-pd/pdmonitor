package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentTyEvent;
import com.pd.server.main.domain.EquipmentTyEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentTyEventMapper {
    long countByExample(EquipmentTyEventExample example);

    int deleteByExample(EquipmentTyEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentTyEvent record);

    int insertSelective(EquipmentTyEvent record);

    List<EquipmentTyEvent> selectByExample(EquipmentTyEventExample example);

    EquipmentTyEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentTyEvent record, @Param("example") EquipmentTyEventExample example);

    int updateByExample(@Param("record") EquipmentTyEvent record, @Param("example") EquipmentTyEventExample example);

    int updateByPrimaryKeySelective(EquipmentTyEvent record);

    int updateByPrimaryKey(EquipmentTyEvent record);
}
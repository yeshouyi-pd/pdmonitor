package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentRecord;
import com.pd.server.main.domain.EquipmentRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentRecordMapper {
    long countByExample(EquipmentRecordExample example);

    int deleteByExample(EquipmentRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentRecord record);

    int insertSelective(EquipmentRecord record);

    List<EquipmentRecord> selectByExample(EquipmentRecordExample example);

    EquipmentRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentRecord record, @Param("example") EquipmentRecordExample example);

    int updateByExample(@Param("record") EquipmentRecord record, @Param("example") EquipmentRecordExample example);

    int updateByPrimaryKeySelective(EquipmentRecord record);

    int updateByPrimaryKey(EquipmentRecord record);
}
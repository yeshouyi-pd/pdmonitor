package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileToday;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileTodayMapper {
    long countByExample(EquipmentFileTodayExample example);

    int deleteByExample(EquipmentFileTodayExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFile record);

    int insertSelective(EquipmentFileToday record);

    List<EquipmentFileToday> selectByExample(EquipmentFileTodayExample example);

    EquipmentFileToday selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFileToday record, @Param("example") EquipmentFileTodayExample example);

    int updateByExample(@Param("record") EquipmentFileToday record, @Param("example") EquipmentFileTodayExample example);

    int updateByPrimaryKeySelective(EquipmentFileToday record);

    int updateByPrimaryKey(EquipmentFileToday record);
}
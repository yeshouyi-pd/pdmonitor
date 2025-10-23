package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileTTxt;
import com.pd.server.main.domain.EquipmentFileTTxtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileTTxtMapper {
    long countByExample(EquipmentFileTTxtExample example);

    int deleteByExample(EquipmentFileTTxtExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFileTTxt record);

    int insertSelective(EquipmentFileTTxt record);

    List<EquipmentFileTTxt> selectByExample(EquipmentFileTTxtExample example);

    EquipmentFileTTxt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFileTTxt record, @Param("example") EquipmentFileTTxtExample example);

    int updateByExample(@Param("record") EquipmentFileTTxt record, @Param("example") EquipmentFileTTxtExample example);

    int updateByPrimaryKeySelective(EquipmentFileTTxt record);

    int updateByPrimaryKey(EquipmentFileTTxt record);
}
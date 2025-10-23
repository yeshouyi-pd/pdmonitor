package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFilePTxt;
import com.pd.server.main.domain.EquipmentFilePTxtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFilePTxtMapper {
    long countByExample(EquipmentFilePTxtExample example);

    int deleteByExample(EquipmentFilePTxtExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFilePTxt record);

    int insertSelective(EquipmentFilePTxt record);

    List<EquipmentFilePTxt> selectByExample(EquipmentFilePTxtExample example);

    EquipmentFilePTxt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFilePTxt record, @Param("example") EquipmentFilePTxtExample example);

    int updateByExample(@Param("record") EquipmentFilePTxt record, @Param("example") EquipmentFilePTxtExample example);

    int updateByPrimaryKeySelective(EquipmentFilePTxt record);

    int updateByPrimaryKey(EquipmentFilePTxt record);
}
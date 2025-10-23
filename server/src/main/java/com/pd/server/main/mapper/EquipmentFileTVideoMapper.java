package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileTVideo;
import com.pd.server.main.domain.EquipmentFileTVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileTVideoMapper {
    long countByExample(EquipmentFileTVideoExample example);

    int deleteByExample(EquipmentFileTVideoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFileTVideo record);

    int insertSelective(EquipmentFileTVideo record);

    List<EquipmentFileTVideo> selectByExample(EquipmentFileTVideoExample example);

    EquipmentFileTVideo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFileTVideo record, @Param("example") EquipmentFileTVideoExample example);

    int updateByExample(@Param("record") EquipmentFileTVideo record, @Param("example") EquipmentFileTVideoExample example);

    int updateByPrimaryKeySelective(EquipmentFileTVideo record);

    int updateByPrimaryKey(EquipmentFileTVideo record);
}
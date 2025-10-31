package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFilePVideo;
import com.pd.server.main.domain.EquipmentFilePVideoExample;
import java.util.List;

import com.pd.server.main.dto.basewx.my.EquipmentFileDwjkDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFilePVideoMapper {
    long countByExample(EquipmentFilePVideoExample example);

    int deleteByExample(EquipmentFilePVideoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFilePVideo record);

    int insertSelective(EquipmentFilePVideo record);

    List<EquipmentFilePVideo> selectByExample(EquipmentFilePVideoExample example);

    EquipmentFilePVideo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFilePVideo record, @Param("example") EquipmentFilePVideoExample example);

    int updateByExample(@Param("record") EquipmentFilePVideo record, @Param("example") EquipmentFilePVideoExample example);

    int updateByPrimaryKeySelective(EquipmentFilePVideo record);

    int updateByPrimaryKey(EquipmentFilePVideo record);

    List<EquipmentFileDwjkDto> listAllDw(EquipmentFilePVideoExample example);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileTWav;
import com.pd.server.main.domain.EquipmentFileTWavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileTWavMapper {
    long countByExample(EquipmentFileTWavExample example);

    int deleteByExample(EquipmentFileTWavExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFileTWav record);

    int insertSelective(EquipmentFileTWav record);

    List<EquipmentFileTWav> selectByExample(EquipmentFileTWavExample example);

    EquipmentFileTWav selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFileTWav record, @Param("example") EquipmentFileTWavExample example);

    int updateByExample(@Param("record") EquipmentFileTWav record, @Param("example") EquipmentFileTWavExample example);

    int updateByPrimaryKeySelective(EquipmentFileTWav record);

    int updateByPrimaryKey(EquipmentFileTWav record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFilePWav;
import com.pd.server.main.domain.EquipmentFilePWavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFilePWavMapper {
    long countByExample(EquipmentFilePWavExample example);

    int deleteByExample(EquipmentFilePWavExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFilePWav record);

    int insertSelective(EquipmentFilePWav record);

    List<EquipmentFilePWav> selectByExample(EquipmentFilePWavExample example);

    EquipmentFilePWav selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFilePWav record, @Param("example") EquipmentFilePWavExample example);

    int updateByExample(@Param("record") EquipmentFilePWav record, @Param("example") EquipmentFilePWavExample example);

    int updateByPrimaryKeySelective(EquipmentFilePWav record);

    int updateByPrimaryKey(EquipmentFilePWav record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileMapper {
    long countByExample(EquipmentFileExample example);

    int deleteByExample(EquipmentFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFile record);

    int insertSelective(EquipmentFile record);

    List<EquipmentFile> selectByExample(EquipmentFileExample example);

    EquipmentFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFile record, @Param("example") EquipmentFileExample example);

    int updateByExample(@Param("record") EquipmentFile record, @Param("example") EquipmentFileExample example);

    int updateByPrimaryKeySelective(EquipmentFile record);

    int updateByPrimaryKey(EquipmentFile record);
}
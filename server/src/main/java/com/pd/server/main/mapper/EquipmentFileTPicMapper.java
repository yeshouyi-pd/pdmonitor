package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileTPic;
import com.pd.server.main.domain.EquipmentFileTPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileTPicMapper {
    long countByExample(EquipmentFileTPicExample example);

    int deleteByExample(EquipmentFileTPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFileTPic record);

    int insertSelective(EquipmentFileTPic record);

    List<EquipmentFileTPic> selectByExample(EquipmentFileTPicExample example);

    List<EquipmentFileTPic> selectByExampleOnlyId(EquipmentFileTPicExample example);

    EquipmentFileTPic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFileTPic record, @Param("example") EquipmentFileTPicExample example);

    int updateByExample(@Param("record") EquipmentFileTPic record, @Param("example") EquipmentFileTPicExample example);

    int updateByPrimaryKeySelective(EquipmentFileTPic record);

    int updateByPrimaryKey(EquipmentFileTPic record);
}
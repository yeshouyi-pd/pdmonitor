package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterProEquip;
import com.pd.server.main.domain.WaterProEquipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterProEquipMapper {
    long countByExample(WaterProEquipExample example);

    int deleteByExample(WaterProEquipExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterProEquip record);

    int insertSelective(WaterProEquip record);

    List<WaterProEquip> selectByExample(WaterProEquipExample example);

    WaterProEquip selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterProEquip record, @Param("example") WaterProEquipExample example);

    int updateByExample(@Param("record") WaterProEquip record, @Param("example") WaterProEquipExample example);

    int updateByPrimaryKeySelective(WaterProEquip record);

    int updateByPrimaryKey(WaterProEquip record);

    List<String> findSbsnByXmbh(String xmbh);
}
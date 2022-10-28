package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterEquiplog;
import com.pd.server.main.domain.WaterEquiplogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterEquiplogMapper {
    long countByExample(WaterEquiplogExample example);

    int deleteByExample(WaterEquiplogExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterEquiplog record);

    int insertSelective(WaterEquiplog record);

    List<WaterEquiplog> selectByExample(WaterEquiplogExample example);

    WaterEquiplog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterEquiplog record, @Param("example") WaterEquiplogExample example);

    int updateByExample(@Param("record") WaterEquiplog record, @Param("example") WaterEquiplogExample example);

    int updateByPrimaryKeySelective(WaterEquiplog record);

    int updateBySbbhSelective(WaterEquiplog record);

    int updateByPrimaryKey(WaterEquiplog record);
}
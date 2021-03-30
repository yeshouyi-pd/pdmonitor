package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterQuality;
import com.pd.server.main.domain.WaterQualityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterQualityMapper {
    long countByExample(WaterQualityExample example);

    int deleteByExample(WaterQualityExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterQuality record);

    int insertSelective(WaterQuality record);

    List<WaterQuality> selectByExample(WaterQualityExample example);

    WaterQuality selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterQuality record, @Param("example") WaterQualityExample example);

    int updateByExample(@Param("record") WaterQuality record, @Param("example") WaterQualityExample example);

    int updateByPrimaryKeySelective(WaterQuality record);

    int updateByPrimaryKey(WaterQuality record);
}
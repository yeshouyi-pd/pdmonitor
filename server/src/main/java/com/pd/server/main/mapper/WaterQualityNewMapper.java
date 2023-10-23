package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterQualityNew;
import com.pd.server.main.domain.WaterQualityNewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterQualityNewMapper {
    long countByExample(WaterQualityNewExample example);

    int deleteByExample(WaterQualityNewExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterQualityNew record);

    int insertSelective(WaterQualityNew record);

    List<WaterQualityNew> selectByExample(WaterQualityNewExample example);

    WaterQualityNew selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterQualityNew record, @Param("example") WaterQualityNewExample example);

    int updateByExample(@Param("record") WaterQualityNew record, @Param("example") WaterQualityNewExample example);

    int updateByPrimaryKeySelective(WaterQualityNew record);

    int updateByPrimaryKey(WaterQualityNew record);
}
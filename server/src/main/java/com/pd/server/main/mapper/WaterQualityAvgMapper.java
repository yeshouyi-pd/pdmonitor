package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterQualityAvg;
import com.pd.server.main.domain.WaterQualityAvgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterQualityAvgMapper {
    long countByExample(WaterQualityAvgExample example);

    int deleteByExample(WaterQualityAvgExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterQualityAvg record);

    int insertSelective(WaterQualityAvg record);

    List<WaterQualityAvg> selectByExample(WaterQualityAvgExample example);

    WaterQualityAvg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterQualityAvg record, @Param("example") WaterQualityAvgExample example);

    int updateByExample(@Param("record") WaterQualityAvg record, @Param("example") WaterQualityAvgExample example);

    int updateByPrimaryKeySelective(WaterQualityAvg record);

    int updateByPrimaryKey(WaterQualityAvg record);
}
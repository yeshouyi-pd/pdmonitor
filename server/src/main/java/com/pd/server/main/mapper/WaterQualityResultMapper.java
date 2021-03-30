package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterQualityResult;
import com.pd.server.main.domain.WaterQualityResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterQualityResultMapper {
    long countByExample(WaterQualityResultExample example);

    int deleteByExample(WaterQualityResultExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterQualityResult record);

    int insertSelective(WaterQualityResult record);

    List<WaterQualityResult> selectByExample(WaterQualityResultExample example);

    WaterQualityResult selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterQualityResult record, @Param("example") WaterQualityResultExample example);

    int updateByExample(@Param("record") WaterQualityResult record, @Param("example") WaterQualityResultExample example);

    int updateByPrimaryKeySelective(WaterQualityResult record);

    int updateByPrimaryKey(WaterQualityResult record);
}
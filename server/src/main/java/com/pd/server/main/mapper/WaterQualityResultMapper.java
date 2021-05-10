package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterQualityAvg;
import com.pd.server.main.domain.WaterQualityResult;
import com.pd.server.main.domain.WaterQualityResultExample;
import java.util.List;

import com.pd.server.main.dto.KvIntDto;
import com.pd.server.main.dto.WaterQualityAvgDto;

import com.pd.server.main.dto.KvMapDto;
import com.pd.server.main.dto.WaterQualityResultDto;
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

    List<WaterQualityAvgDto> selectAvgByExample(WaterQualityResultExample example);

    List<WaterQualityResult> getLatestDate();

    List<KvIntDto> getAlljcsjByDept(WaterQualityResultExample example);



}
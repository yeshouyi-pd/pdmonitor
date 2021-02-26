package com.pd.server.main.mapper;

import com.pd.server.main.domain.ReportWater;
import com.pd.server.main.domain.ReportWaterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportWaterMapper {
    long countByExample(ReportWaterExample example);

    int deleteByExample(ReportWaterExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportWater record);

    int insertSelective(ReportWater record);

    List<ReportWater> selectByExample(ReportWaterExample example);

    ReportWater selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportWater record, @Param("example") ReportWaterExample example);

    int updateByExample(@Param("record") ReportWater record, @Param("example") ReportWaterExample example);

    int updateByPrimaryKeySelective(ReportWater record);

    int updateByPrimaryKey(ReportWater record);
}
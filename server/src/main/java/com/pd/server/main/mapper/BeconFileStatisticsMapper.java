package com.pd.server.main.mapper;

import com.pd.server.main.domain.BeconFileStatistics;
import com.pd.server.main.domain.BeconFileStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BeconFileStatisticsMapper {
    long countByExample(BeconFileStatisticsExample example);

    int deleteByExample(BeconFileStatisticsExample example);

    int deleteByPrimaryKey(String id);

    int insert(BeconFileStatistics record);

    int insertSelective(BeconFileStatistics record);

    List<BeconFileStatistics> selectByExample(BeconFileStatisticsExample example);

    BeconFileStatistics selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BeconFileStatistics record, @Param("example") BeconFileStatisticsExample example);

    int updateByExample(@Param("record") BeconFileStatistics record, @Param("example") BeconFileStatisticsExample example);

    int updateByPrimaryKeySelective(BeconFileStatistics record);

    int updateByPrimaryKey(BeconFileStatistics record);
}
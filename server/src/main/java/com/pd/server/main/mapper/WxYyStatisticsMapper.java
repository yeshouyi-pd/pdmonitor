package com.pd.server.main.mapper;

import com.pd.server.main.domain.WxYyStatistics;
import com.pd.server.main.domain.WxYyStatisticsExample;
import java.util.List;

import com.pd.server.main.dto.VehParkLotStatisticsDto;
import com.pd.server.main.dto.WxYyStatisticsDto;
import org.apache.ibatis.annotations.Param;

public interface WxYyStatisticsMapper {
    long countByExample(WxYyStatisticsExample example);

    int deleteByExample(WxYyStatisticsExample example);

    int deleteByPrimaryKey(String id);

    int insert(WxYyStatistics record);

    int insertSelective(WxYyStatistics record);

    List<WxYyStatistics> selectByExample(WxYyStatisticsExample example);

    WxYyStatistics selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WxYyStatistics record, @Param("example") WxYyStatisticsExample example);

    int updateByExample(@Param("record") WxYyStatistics record, @Param("example") WxYyStatisticsExample example);

    int updateByPrimaryKeySelective(WxYyStatistics record);

    int updateByPrimaryKey(WxYyStatistics record);

    List<WxYyStatisticsDto> getStatistics(WxYyStatisticsExample example);

    List<WxYyStatisticsDto> getStatisticsByDeptCode(WxYyStatisticsExample example);
}
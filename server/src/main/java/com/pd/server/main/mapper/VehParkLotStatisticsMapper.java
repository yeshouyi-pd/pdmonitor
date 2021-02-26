package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehParkLotStatistics;
import com.pd.server.main.domain.VehParkLotStatisticsExample;
import java.util.List;

import com.pd.server.main.dto.VehParkLotStatisticsDto;
import org.apache.ibatis.annotations.Param;

public interface VehParkLotStatisticsMapper {
    long countByExample(VehParkLotStatisticsExample example);

    int deleteByExample(VehParkLotStatisticsExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehParkLotStatistics record);

    int insertSelective(VehParkLotStatistics record);

    List<VehParkLotStatistics> selectByExample(VehParkLotStatisticsExample example);

    VehParkLotStatistics selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehParkLotStatistics record, @Param("example") VehParkLotStatisticsExample example);

    int updateByExample(@Param("record") VehParkLotStatistics record, @Param("example") VehParkLotStatisticsExample example);

    int updateByPrimaryKeySelective(VehParkLotStatistics record);

    int updateByPrimaryKey(VehParkLotStatistics record);


    List<VehParkLotStatisticsDto> getStatistics(VehParkLotStatisticsDto dto);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.MeteorologicalData;
import com.pd.server.main.domain.MeteorologicalDataExample;
import java.util.List;

import com.pd.server.main.dto.basewx.my.MeteorologicalDataSum;
import org.apache.ibatis.annotations.Param;

public interface MeteorologicalDataMapper {
    long countByExample(MeteorologicalDataExample example);

    int deleteByExample(MeteorologicalDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(MeteorologicalData record);

    int insertSelective(MeteorologicalData record);

    List<MeteorologicalData> selectByExample(MeteorologicalDataExample example);

    MeteorologicalData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MeteorologicalData record, @Param("example") MeteorologicalDataExample example);

    int updateByExample(@Param("record") MeteorologicalData record, @Param("example") MeteorologicalDataExample example);

    int updateByPrimaryKeySelective(MeteorologicalData record);

    int updateByPrimaryKey(MeteorologicalData record);

    List<MeteorologicalDataSum> sumByExample(MeteorologicalDataExample example);
}
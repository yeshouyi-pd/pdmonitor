package com.pd.server.main.mapper;

import com.pd.server.main.domain.ForecastNum;
import com.pd.server.main.domain.ForecastNumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForecastNumMapper {
    long countByExample(ForecastNumExample example);

    int deleteByExample(ForecastNumExample example);

    int deleteByPrimaryKey(String id);

    int insert(ForecastNum record);

    int insertSelective(ForecastNum record);

    List<ForecastNum> selectByExample(ForecastNumExample example);

    ForecastNum selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ForecastNum record, @Param("example") ForecastNumExample example);

    int updateByExample(@Param("record") ForecastNum record, @Param("example") ForecastNumExample example);

    int updateByPrimaryKeySelective(ForecastNum record);

    int updateByPrimaryKey(ForecastNum record);
}
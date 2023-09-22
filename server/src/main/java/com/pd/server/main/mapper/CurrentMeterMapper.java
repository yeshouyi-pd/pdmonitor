package com.pd.server.main.mapper;

import com.pd.server.main.domain.CurrentMeter;
import com.pd.server.main.domain.CurrentMeterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CurrentMeterMapper {
    long countByExample(CurrentMeterExample example);

    int deleteByExample(CurrentMeterExample example);

    int deleteByPrimaryKey(String id);

    int insert(CurrentMeter record);

    int insertSelective(CurrentMeter record);

    List<CurrentMeter> selectByExample(CurrentMeterExample example);

    CurrentMeter selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CurrentMeter record, @Param("example") CurrentMeterExample example);

    int updateByExample(@Param("record") CurrentMeter record, @Param("example") CurrentMeterExample example);

    int updateByPrimaryKeySelective(CurrentMeter record);

    int updateByPrimaryKey(CurrentMeter record);
}
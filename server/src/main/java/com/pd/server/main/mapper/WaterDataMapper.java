package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterData;
import com.pd.server.main.domain.WaterDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterDataMapper {
    long countByExample(WaterDataExample example);

    int deleteByExample(WaterDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterData record);

    int insertSelective(WaterData record);

    List<WaterData> selectByExample(WaterDataExample example);

    WaterData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterData record, @Param("example") WaterDataExample example);

    int updateByExample(@Param("record") WaterData record, @Param("example") WaterDataExample example);

    int updateByPrimaryKeySelective(WaterData record);

    int updateByPrimaryKey(WaterData record);
}
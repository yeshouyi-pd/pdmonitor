package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaveData;
import com.pd.server.main.domain.WaveDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaveDataMapper {
    long countByExample(WaveDataExample example);

    int deleteByExample(WaveDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaveData record);

    int insertSelective(WaveData record);

    List<WaveData> selectByExample(WaveDataExample example);

    WaveData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaveData record, @Param("example") WaveDataExample example);

    int updateByExample(@Param("record") WaveData record, @Param("example") WaveDataExample example);

    int updateByPrimaryKeySelective(WaveData record);

    int updateByPrimaryKey(WaveData record);
}
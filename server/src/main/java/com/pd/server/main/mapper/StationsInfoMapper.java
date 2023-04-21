package com.pd.server.main.mapper;

import com.pd.server.main.domain.StationsInfo;
import com.pd.server.main.domain.StationsInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StationsInfoMapper {
    long countByExample(StationsInfoExample example);

    int deleteByExample(StationsInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(StationsInfo record);

    int insertSelective(StationsInfo record);

    List<StationsInfo> selectByExample(StationsInfoExample example);

    StationsInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StationsInfo record, @Param("example") StationsInfoExample example);

    int updateByExample(@Param("record") StationsInfo record, @Param("example") StationsInfoExample example);

    int updateByPrimaryKeySelective(StationsInfo record);

    int updateByPrimaryKey(StationsInfo record);
}
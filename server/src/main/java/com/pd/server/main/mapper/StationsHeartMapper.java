package com.pd.server.main.mapper;

import com.pd.server.main.domain.StationsHeart;
import com.pd.server.main.domain.StationsHeartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StationsHeartMapper {
    long countByExample(StationsHeartExample example);

    int deleteByExample(StationsHeartExample example);

    int deleteByPrimaryKey(String id);

    int insert(StationsHeart record);

    int insertSelective(StationsHeart record);

    List<StationsHeart> selectByExample(StationsHeartExample example);

    StationsHeart selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StationsHeart record, @Param("example") StationsHeartExample example);

    int updateByExample(@Param("record") StationsHeart record, @Param("example") StationsHeartExample example);

    int updateByPrimaryKeySelective(StationsHeart record);

    int updateByPrimaryKey(StationsHeart record);
}
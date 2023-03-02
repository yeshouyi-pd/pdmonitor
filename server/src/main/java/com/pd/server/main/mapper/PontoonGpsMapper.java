package com.pd.server.main.mapper;

import com.pd.server.main.domain.PontoonGps;
import com.pd.server.main.domain.PontoonGpsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PontoonGpsMapper {
    long countByExample(PontoonGpsExample example);

    int deleteByExample(PontoonGpsExample example);

    int deleteByPrimaryKey(String id);

    int insert(PontoonGps record);

    int insertSelective(PontoonGps record);

    List<PontoonGps> selectByExample(PontoonGpsExample example);

    PontoonGps selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PontoonGps record, @Param("example") PontoonGpsExample example);

    int updateByExample(@Param("record") PontoonGps record, @Param("example") PontoonGpsExample example);

    int updateByPrimaryKeySelective(PontoonGps record);

    int updateByPrimaryKey(PontoonGps record);

    List<String> selectGpsByExample(PontoonGpsExample example);
}
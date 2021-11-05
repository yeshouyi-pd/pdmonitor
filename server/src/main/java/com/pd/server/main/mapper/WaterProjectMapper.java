package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterProject;
import com.pd.server.main.domain.WaterProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterProjectMapper {
    long countByExample(WaterProjectExample example);

    int deleteByExample(WaterProjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterProject record);

    int insertSelective(WaterProject record);

    List<WaterProject> selectByExample(WaterProjectExample example);

    WaterProject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterProject record, @Param("example") WaterProjectExample example);

    int updateByExample(@Param("record") WaterProject record, @Param("example") WaterProjectExample example);

    int updateByPrimaryKeySelective(WaterProject record);

    int updateByPrimaryKey(WaterProject record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterRawfile;
import com.pd.server.main.domain.WaterRawfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterRawfileMapper {
    long countByExample(WaterRawfileExample example);

    int deleteByExample(WaterRawfileExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterRawfile record);

    int insertSelective(WaterRawfile record);

    List<WaterRawfile> selectByExample(WaterRawfileExample example);

    WaterRawfile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterRawfile record, @Param("example") WaterRawfileExample example);

    int updateByExample(@Param("record") WaterRawfile record, @Param("example") WaterRawfileExample example);

    int updateByPrimaryKeySelective(WaterRawfile record);

    int updateByPrimaryKey(WaterRawfile record);
}
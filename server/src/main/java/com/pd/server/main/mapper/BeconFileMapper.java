package com.pd.server.main.mapper;

import com.pd.server.main.domain.BeconFile;
import com.pd.server.main.domain.BeconFileExample;
import java.util.List;

import com.pd.server.main.domain.BeconFileStatistics;
import com.pd.server.main.dto.BeconFileDto;
import org.apache.ibatis.annotations.Param;

public interface BeconFileMapper {
    long countByExample(BeconFileExample example);

    int deleteByExample(BeconFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(BeconFile record);

    int insertSelective(BeconFile record);

    List<BeconFile> selectByExample(BeconFileExample example);

    List<BeconFile> selectByExampleSpecial(@Param("record") BeconFileDto record);

    BeconFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BeconFile record, @Param("example") BeconFileExample example);

    int updateByExample(@Param("record") BeconFile record, @Param("example") BeconFileExample example);

    int updateByPrimaryKeySelective(BeconFile record);

    int updateByPrimaryKey(BeconFile record);

    List<BeconFileStatistics> statisticsByExample(BeconFileExample example);
}
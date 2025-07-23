package com.pd.server.main.mapper;

import com.pd.server.main.domain.BeconFileTyToday;
import com.pd.server.main.domain.BeconFileTyTodayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BeconFileTyTodayMapper {
    long countByExample(BeconFileTyTodayExample example);

    int deleteByExample(BeconFileTyTodayExample example);

    int deleteByPrimaryKey(String id);

    int insert(BeconFileTyToday record);

    int insertSelective(BeconFileTyToday record);

    List<BeconFileTyToday> selectByExample(BeconFileTyTodayExample example);

    BeconFileTyToday selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BeconFileTyToday record, @Param("example") BeconFileTyTodayExample example);

    int updateByExample(@Param("record") BeconFileTyToday record, @Param("example") BeconFileTyTodayExample example);

    int updateByPrimaryKeySelective(BeconFileTyToday record);

    int updateByPrimaryKey(BeconFileTyToday record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.BeconFileToday;
import com.pd.server.main.domain.BeconFileTodayExample;
import java.util.List;

import com.pd.server.main.dto.basewx.my.NoTodaySbbhDto;
import org.apache.ibatis.annotations.Param;

public interface BeconFileTodayMapper {
    long countByExample(BeconFileTodayExample example);

    int deleteByExample(BeconFileTodayExample example);

    int deleteByPrimaryKey(String id);

    int insert(BeconFileToday record);

    int insertSelective(BeconFileToday record);

    List<BeconFileToday> selectByExample(BeconFileTodayExample example);

    BeconFileToday selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BeconFileToday record, @Param("example") BeconFileTodayExample example);

    int updateByExample(@Param("record") BeconFileToday record, @Param("example") BeconFileTodayExample example);

    int updateByPrimaryKeySelective(BeconFileToday record);

    int updateByPrimaryKey(BeconFileToday record);

    List<NoTodaySbbhDto> noTodaySbbhDtoList(BeconFileTodayExample example);
}
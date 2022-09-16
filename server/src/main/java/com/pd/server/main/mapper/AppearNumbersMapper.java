package com.pd.server.main.mapper;

import com.pd.server.main.domain.AppearNumbers;
import com.pd.server.main.domain.AppearNumbersExample;
import java.util.List;

import com.pd.server.main.dto.AppearNumbersDto;
import org.apache.ibatis.annotations.Param;

public interface AppearNumbersMapper {
    long countByExample(AppearNumbersExample example);

    int deleteByExample(AppearNumbersExample example);

    int deleteByPrimaryKey(String id);

    int insert(AppearNumbers record);

    int insertSelective(AppearNumbers record);

    List<AppearNumbers> selectByExample(AppearNumbersExample example);

    List<AppearNumbers> selectByExampleSpecial(@Param("record") AppearNumbersDto record);

    AppearNumbers selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppearNumbers record, @Param("example") AppearNumbersExample example);

    int updateByExample(@Param("record") AppearNumbers record, @Param("example") AppearNumbersExample example);

    int updateByPrimaryKeySelective(AppearNumbers record);

    int updateByPrimaryKey(AppearNumbers record);
}
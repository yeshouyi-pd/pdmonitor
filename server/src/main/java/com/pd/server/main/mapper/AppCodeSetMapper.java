package com.pd.server.main.mapper;

import com.pd.server.main.domain.AppCodeSet;
import com.pd.server.main.domain.AppCodeSetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppCodeSetMapper {
    long countByExample(AppCodeSetExample example);

    int deleteByExample(AppCodeSetExample example);

    int deleteByPrimaryKey(String id);

    int insert(AppCodeSet record);

    int insertSelective(AppCodeSet record);

    List<AppCodeSet> selectByExample(AppCodeSetExample example);

    AppCodeSet selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppCodeSet record, @Param("example") AppCodeSetExample example);

    int updateByExample(@Param("record") AppCodeSet record, @Param("example") AppCodeSetExample example);

    int updateByPrimaryKeySelective(AppCodeSet record);

    int updateByPrimaryKey(AppCodeSet record);
}
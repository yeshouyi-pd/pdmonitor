package com.pd.server.main.mapper;

import com.pd.server.main.domain.AppCodeType;
import com.pd.server.main.domain.AppCodeTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppCodeTypeMapper {
    long countByExample(AppCodeTypeExample example);

    int deleteByExample(AppCodeTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(AppCodeType record);

    int insertSelective(AppCodeType record);

    List<AppCodeType> selectByExample(AppCodeTypeExample example);

    AppCodeType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppCodeType record, @Param("example") AppCodeTypeExample example);

    int updateByExample(@Param("record") AppCodeType record, @Param("example") AppCodeTypeExample example);

    int updateByPrimaryKeySelective(AppCodeType record);

    int updateByPrimaryKey(AppCodeType record);
}
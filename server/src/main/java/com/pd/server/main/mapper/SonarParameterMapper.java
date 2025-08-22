package com.pd.server.main.mapper;

import com.pd.server.main.domain.SonarParameter;
import com.pd.server.main.domain.SonarParameterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SonarParameterMapper {
    long countByExample(SonarParameterExample example);

    int deleteByExample(SonarParameterExample example);

    int deleteByPrimaryKey(String id);

    int insert(SonarParameter record);

    int insertSelective(SonarParameter record);

    List<SonarParameter> selectByExample(SonarParameterExample example);

    SonarParameter selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SonarParameter record, @Param("example") SonarParameterExample example);

    int updateByExample(@Param("record") SonarParameter record, @Param("example") SonarParameterExample example);

    int updateByPrimaryKeySelective(SonarParameter record);

    int updateByPrimaryKey(SonarParameter record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.SonarSingleValue;
import com.pd.server.main.domain.SonarSingleValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SonarSingleValueMapper {
    long countByExample(SonarSingleValueExample example);

    int deleteByExample(SonarSingleValueExample example);

    int deleteByPrimaryKey(String id);

    int insert(SonarSingleValue record);

    int insertSelective(SonarSingleValue record);

    List<SonarSingleValue> selectByExampleWithBLOBs(SonarSingleValueExample example);

    List<SonarSingleValue> selectByExample(SonarSingleValueExample example);

    SonarSingleValue selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SonarSingleValue record, @Param("example") SonarSingleValueExample example);

    int updateByExampleWithBLOBs(@Param("record") SonarSingleValue record, @Param("example") SonarSingleValueExample example);

    int updateByExample(@Param("record") SonarSingleValue record, @Param("example") SonarSingleValueExample example);

    int updateByPrimaryKeySelective(SonarSingleValue record);

    int updateByPrimaryKeyWithBLOBs(SonarSingleValue record);

    int updateByPrimaryKey(SonarSingleValue record);
}
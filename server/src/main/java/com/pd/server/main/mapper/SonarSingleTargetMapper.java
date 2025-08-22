package com.pd.server.main.mapper;

import com.pd.server.main.domain.SonarSingleTarget;
import com.pd.server.main.domain.SonarSingleTargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SonarSingleTargetMapper {
    long countByExample(SonarSingleTargetExample example);

    int deleteByExample(SonarSingleTargetExample example);

    int deleteByPrimaryKey(String id);

    int insert(SonarSingleTarget record);

    int insertSelective(SonarSingleTarget record);

    List<SonarSingleTarget> selectByExample(SonarSingleTargetExample example);

    SonarSingleTarget selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SonarSingleTarget record, @Param("example") SonarSingleTargetExample example);

    int updateByExample(@Param("record") SonarSingleTarget record, @Param("example") SonarSingleTargetExample example);

    int updateByPrimaryKeySelective(SonarSingleTarget record);

    int updateByPrimaryKey(SonarSingleTarget record);
}
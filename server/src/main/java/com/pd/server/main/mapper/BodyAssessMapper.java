package com.pd.server.main.mapper;

import com.pd.server.main.domain.BodyAssess;
import com.pd.server.main.domain.BodyAssessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BodyAssessMapper {
    long countByExample(BodyAssessExample example);

    int deleteByExample(BodyAssessExample example);

    int deleteByPrimaryKey(String id);

    int insert(BodyAssess record);

    int insertSelective(BodyAssess record);

    List<BodyAssess> selectByExample(BodyAssessExample example);

    BodyAssess selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BodyAssess record, @Param("example") BodyAssessExample example);

    int updateByExample(@Param("record") BodyAssess record, @Param("example") BodyAssessExample example);

    int updateByPrimaryKeySelective(BodyAssess record);

    int updateByPrimaryKey(BodyAssess record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.SonarEcho;
import com.pd.server.main.domain.SonarEchoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SonarEchoMapper {
    long countByExample(SonarEchoExample example);

    int deleteByExample(SonarEchoExample example);

    int deleteByPrimaryKey(String id);

    int insert(SonarEcho record);

    int insertSelective(SonarEcho record);

    List<SonarEcho> selectByExampleWithBLOBs(SonarEchoExample example);

    List<SonarEcho> selectByExample(SonarEchoExample example);

    SonarEcho selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SonarEcho record, @Param("example") SonarEchoExample example);

    int updateByExampleWithBLOBs(@Param("record") SonarEcho record, @Param("example") SonarEchoExample example);

    int updateByExample(@Param("record") SonarEcho record, @Param("example") SonarEchoExample example);

    int updateByPrimaryKeySelective(SonarEcho record);

    int updateByPrimaryKeyWithBLOBs(SonarEcho record);

    int updateByPrimaryKey(SonarEcho record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.SonarAlarm;
import com.pd.server.main.domain.SonarAlarmExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SonarAlarmMapper {
    long countByExample(SonarAlarmExample example);

    int deleteByExample(SonarAlarmExample example);

    int deleteByPrimaryKey(String id);

    int insert(SonarAlarm record);

    int insertSelective(SonarAlarm record);

    List<SonarAlarm> selectByExample(SonarAlarmExample example);

    SonarAlarm selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SonarAlarm record, @Param("example") SonarAlarmExample example);

    int updateByExample(@Param("record") SonarAlarm record, @Param("example") SonarAlarmExample example);

    int updateByPrimaryKeySelective(SonarAlarm record);

    int updateByPrimaryKey(SonarAlarm record);
}
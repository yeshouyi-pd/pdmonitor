package com.pd.server.main.mapper;

import com.pd.server.main.domain.ScheduleExecutions;
import com.pd.server.main.domain.ScheduleExecutionsExample;
import com.pd.server.main.dto.ScheduleExecutionWithScheduleDto;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleExecutionsMapper {
    long countByExample(ScheduleExecutionsExample example);

    int deleteByExample(ScheduleExecutionsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleExecutions record);

    int insertSelective(ScheduleExecutions record);

    List<ScheduleExecutions> selectByExampleWithBLOBs(ScheduleExecutionsExample example);

    List<ScheduleExecutions> selectByExample(ScheduleExecutionsExample example);

    ScheduleExecutions selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScheduleExecutions record, @Param("example") ScheduleExecutionsExample example);

    int updateByExampleWithBLOBs(@Param("record") ScheduleExecutions record, @Param("example") ScheduleExecutionsExample example);

    int updateByExample(@Param("record") ScheduleExecutions record, @Param("example") ScheduleExecutionsExample example);

    int updateByPrimaryKeySelective(ScheduleExecutions record);

    int updateByPrimaryKeyWithBLOBs(ScheduleExecutions record);

    int updateByPrimaryKey(ScheduleExecutions record);

    /**
     * 根据设备ID查询执行记录，左连接计划表
     * @param deviceId 设备ID
     * @return 执行记录列表（包含计划信息）
     */
    List<ScheduleExecutionWithScheduleDto> selectByDeviceIdWithSchedule(@Param("deviceId") String deviceId);
}
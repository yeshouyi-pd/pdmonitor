package com.pd.server.main.mapper;

import com.pd.server.main.domain.DeviceSchedules;
import com.pd.server.main.domain.DeviceSchedulesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceSchedulesMapper {
    long countByExample(DeviceSchedulesExample example);

    int deleteByExample(DeviceSchedulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceSchedules record);

    int insertSelective(DeviceSchedules record);

    List<DeviceSchedules> selectByExample(DeviceSchedulesExample example);

    DeviceSchedules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceSchedules record, @Param("example") DeviceSchedulesExample example);

    int updateByExample(@Param("record") DeviceSchedules record, @Param("example") DeviceSchedulesExample example);

    int updateByPrimaryKeySelective(DeviceSchedules record);

    int updateByPrimaryKey(DeviceSchedules record);
}
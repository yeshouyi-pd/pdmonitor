package com.pd.server.main.mapper;

import com.pd.server.main.domain.DeviceMonitorData;
import com.pd.server.main.domain.DeviceMonitorDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceMonitorDataMapper {
    long countByExample(DeviceMonitorDataExample example);

    int deleteByExample(DeviceMonitorDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(DeviceMonitorData record);

    int insertSelective(DeviceMonitorData record);

    List<DeviceMonitorData> selectByExample(DeviceMonitorDataExample example);

    DeviceMonitorData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DeviceMonitorData record, @Param("example") DeviceMonitorDataExample example);

    int updateByExample(@Param("record") DeviceMonitorData record, @Param("example") DeviceMonitorDataExample example);

    int updateByPrimaryKeySelective(DeviceMonitorData record);

    int updateByPrimaryKey(DeviceMonitorData record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.DeviceStateLog;
import com.pd.server.main.domain.DeviceStateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceStateLogMapper {
    long countByExample(DeviceStateLogExample example);

    int deleteByExample(DeviceStateLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(DeviceStateLog record);

    int insertSelective(DeviceStateLog record);

    List<DeviceStateLog> selectByExample(DeviceStateLogExample example);

    DeviceStateLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DeviceStateLog record, @Param("example") DeviceStateLogExample example);

    int updateByExample(@Param("record") DeviceStateLog record, @Param("example") DeviceStateLogExample example);

    int updateByPrimaryKeySelective(DeviceStateLog record);

    int updateByPrimaryKey(DeviceStateLog record);
}
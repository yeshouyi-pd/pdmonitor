package com.pd.server.main.mapper;

import com.pd.server.main.domain.VoicePowerDevice;
import com.pd.server.main.domain.VoicePowerDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoicePowerDeviceMapper {
    long countByExample(VoicePowerDeviceExample example);

    int deleteByExample(VoicePowerDeviceExample example);

    int deleteByPrimaryKey(String id);

    int insert(VoicePowerDevice record);

    int insertSelective(VoicePowerDevice record);

    List<VoicePowerDevice> selectByExample(VoicePowerDeviceExample example);

    VoicePowerDevice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VoicePowerDevice record, @Param("example") VoicePowerDeviceExample example);

    int updateByExample(@Param("record") VoicePowerDevice record, @Param("example") VoicePowerDeviceExample example);

    int updateByPrimaryKeySelective(VoicePowerDevice record);

    int updateByPrimaryKey(VoicePowerDevice record);
}
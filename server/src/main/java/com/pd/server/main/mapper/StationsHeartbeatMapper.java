package com.pd.server.main.mapper;

import com.pd.server.main.domain.StationsHeartbeat;
import com.pd.server.main.domain.StationsHeartbeatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StationsHeartbeatMapper {
    long countByExample(StationsHeartbeatExample example);

    int deleteByExample(StationsHeartbeatExample example);

    int deleteByPrimaryKey(String id);

    int insert(StationsHeartbeat record);

    int insertSelective(StationsHeartbeat record);

    List<StationsHeartbeat> selectByExample(StationsHeartbeatExample example);

    StationsHeartbeat selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StationsHeartbeat record, @Param("example") StationsHeartbeatExample example);

    int updateByExample(@Param("record") StationsHeartbeat record, @Param("example") StationsHeartbeatExample example);

    int updateByPrimaryKeySelective(StationsHeartbeat record);

    int updateByPrimaryKey(StationsHeartbeat record);
}
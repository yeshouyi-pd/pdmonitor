package com.pd.server.main.mapper;

import com.pd.server.main.domain.AppMonitorDiscovery;
import com.pd.server.main.domain.AppMonitorDiscoveryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMonitorDiscoveryMapper {
    long countByExample(AppMonitorDiscoveryExample example);

    int deleteByExample(AppMonitorDiscoveryExample example);

    int deleteByPrimaryKey(String id);

    int insert(AppMonitorDiscovery record);

    int insertSelective(AppMonitorDiscovery record);

    List<AppMonitorDiscovery> selectByExample(AppMonitorDiscoveryExample example);

    AppMonitorDiscovery selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppMonitorDiscovery record, @Param("example") AppMonitorDiscoveryExample example);

    int updateByExample(@Param("record") AppMonitorDiscovery record, @Param("example") AppMonitorDiscoveryExample example);

    int updateByPrimaryKeySelective(AppMonitorDiscovery record);

    int updateByPrimaryKey(AppMonitorDiscovery record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.AppMonitorInfo;
import com.pd.server.main.domain.AppMonitorInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMonitorInfoMapper {
    long countByExample(AppMonitorInfoExample example);

    int deleteByExample(AppMonitorInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(AppMonitorInfo record);

    int insertSelective(AppMonitorInfo record);

    List<AppMonitorInfo> selectByExample(AppMonitorInfoExample example);

    AppMonitorInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppMonitorInfo record, @Param("example") AppMonitorInfoExample example);

    int updateByExample(@Param("record") AppMonitorInfo record, @Param("example") AppMonitorInfoExample example);

    int updateByPrimaryKeySelective(AppMonitorInfo record);

    int updateByPrimaryKey(AppMonitorInfo record);
}
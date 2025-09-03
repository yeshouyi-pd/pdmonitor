package com.pd.server.main.mapper;

import com.pd.server.main.domain.AppMonitorExp;
import com.pd.server.main.domain.AppMonitorExpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMonitorExpMapper {
    long countByExample(AppMonitorExpExample example);

    int deleteByExample(AppMonitorExpExample example);

    int deleteByPrimaryKey(String id);

    int insert(AppMonitorExp record);

    int insertSelective(AppMonitorExp record);

    List<AppMonitorExp> selectByExample(AppMonitorExpExample example);

    AppMonitorExp selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppMonitorExp record, @Param("example") AppMonitorExpExample example);

    int updateByExample(@Param("record") AppMonitorExp record, @Param("example") AppMonitorExpExample example);

    int updateByPrimaryKeySelective(AppMonitorExp record);

    int updateByPrimaryKey(AppMonitorExp record);
}
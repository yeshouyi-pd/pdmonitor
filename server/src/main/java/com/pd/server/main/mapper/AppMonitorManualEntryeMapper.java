package com.pd.server.main.mapper;

import com.pd.server.main.domain.AppMonitorManualEntrye;
import com.pd.server.main.domain.AppMonitorManualEntryeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMonitorManualEntryeMapper {
    long countByExample(AppMonitorManualEntryeExample example);

    int deleteByExample(AppMonitorManualEntryeExample example);

    int deleteByPrimaryKey(String id);

    int insert(AppMonitorManualEntrye record);

    int insertSelective(AppMonitorManualEntrye record);

    List<AppMonitorManualEntrye> selectByExample(AppMonitorManualEntryeExample example);

    AppMonitorManualEntrye selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AppMonitorManualEntrye record, @Param("example") AppMonitorManualEntryeExample example);

    int updateByExample(@Param("record") AppMonitorManualEntrye record, @Param("example") AppMonitorManualEntryeExample example);

    int updateByPrimaryKeySelective(AppMonitorManualEntrye record);

    int updateByPrimaryKey(AppMonitorManualEntrye record);
}
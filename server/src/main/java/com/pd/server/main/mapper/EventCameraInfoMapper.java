package com.pd.server.main.mapper;

import com.pd.server.main.domain.EventCameraInfo;
import com.pd.server.main.domain.EventCameraInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventCameraInfoMapper {
    long countByExample(EventCameraInfoExample example);

    int deleteByExample(EventCameraInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(EventCameraInfo record);

    int insertSelective(EventCameraInfo record);

    List<EventCameraInfo> selectByExample(EventCameraInfoExample example);

    EventCameraInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EventCameraInfo record, @Param("example") EventCameraInfoExample example);

    int updateByExample(@Param("record") EventCameraInfo record, @Param("example") EventCameraInfoExample example);

    int updateByPrimaryKeySelective(EventCameraInfo record);

    int updateByPrimaryKey(EventCameraInfo record);

    List<EventCameraInfo> selectByExamplerow();

}
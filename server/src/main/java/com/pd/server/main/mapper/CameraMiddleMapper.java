package com.pd.server.main.mapper;

import com.pd.server.main.domain.CameraMiddle;
import com.pd.server.main.domain.CameraMiddleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CameraMiddleMapper {
    long countByExample(CameraMiddleExample example);

    int deleteByExample(CameraMiddleExample example);

    int deleteByPrimaryKey(String id);

    int insert(CameraMiddle record);

    int insertSelective(CameraMiddle record);

    List<CameraMiddle> selectByExample(CameraMiddleExample example);

    CameraMiddle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CameraMiddle record, @Param("example") CameraMiddleExample example);

    int updateByExample(@Param("record") CameraMiddle record, @Param("example") CameraMiddleExample example);

    int updateByPrimaryKeySelective(CameraMiddle record);

    int updateByPrimaryKey(CameraMiddle record);
}
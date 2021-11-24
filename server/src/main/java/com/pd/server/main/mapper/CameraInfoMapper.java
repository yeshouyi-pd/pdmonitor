package com.pd.server.main.mapper;

import com.pd.server.main.domain.CameraInfo;
import com.pd.server.main.domain.CameraInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CameraInfoMapper {
    long countByExample(CameraInfoExample example);

    int deleteByExample(CameraInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(CameraInfo record);

    int insertSelective(CameraInfo record);

    List<CameraInfo> selectByExample(CameraInfoExample example);

    CameraInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CameraInfo record, @Param("example") CameraInfoExample example);

    int updateByExample(@Param("record") CameraInfo record, @Param("example") CameraInfoExample example);

    int updateByPrimaryKeySelective(CameraInfo record);

    int updateByPrimaryKey(CameraInfo record);
}
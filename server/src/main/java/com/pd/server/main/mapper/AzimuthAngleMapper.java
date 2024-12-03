package com.pd.server.main.mapper;

import com.pd.server.main.domain.AzimuthAngle;
import com.pd.server.main.domain.AzimuthAngleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AzimuthAngleMapper {
    long countByExample(AzimuthAngleExample example);

    int deleteByExample(AzimuthAngleExample example);

    int deleteByPrimaryKey(String id);

    int insert(AzimuthAngle record);

    int insertSelective(AzimuthAngle record);

    List<AzimuthAngle> selectByExample(AzimuthAngleExample example);

    AzimuthAngle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AzimuthAngle record, @Param("example") AzimuthAngleExample example);

    int updateByExample(@Param("record") AzimuthAngle record, @Param("example") AzimuthAngleExample example);

    int updateByPrimaryKeySelective(AzimuthAngle record);

    int updateByPrimaryKey(AzimuthAngle record);

    List<AzimuthAngle> selectByQuartz(AzimuthAngleExample example);
}
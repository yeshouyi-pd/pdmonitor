package com.pd.server.main.mapper;

import com.pd.server.main.domain.AzimuthAngleRq;
import com.pd.server.main.domain.AzimuthAngleRqExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AzimuthAngleRqMapper {
    long countByExample(AzimuthAngleRqExample example);

    int deleteByExample(AzimuthAngleRqExample example);

    int deleteByPrimaryKey(String id);

    int insert(AzimuthAngleRq record);

    int insertSelective(AzimuthAngleRq record);

    List<AzimuthAngleRq> selectByExample(AzimuthAngleRqExample example);

    AzimuthAngleRq selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AzimuthAngleRq record, @Param("example") AzimuthAngleRqExample example);

    int updateByExample(@Param("record") AzimuthAngleRq record, @Param("example") AzimuthAngleRqExample example);

    int updateByPrimaryKeySelective(AzimuthAngleRq record);

    int updateByPrimaryKey(AzimuthAngleRq record);

    List<AzimuthAngleRq> selectByQuartz(AzimuthAngleRqExample example);
}
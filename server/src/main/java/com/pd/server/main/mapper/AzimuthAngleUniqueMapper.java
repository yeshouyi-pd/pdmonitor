package com.pd.server.main.mapper;

import com.pd.server.main.domain.AzimuthAngleUnique;
import com.pd.server.main.domain.AzimuthAngleUniqueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AzimuthAngleUniqueMapper {
    long countByExample(AzimuthAngleUniqueExample example);

    int deleteByExample(AzimuthAngleUniqueExample example);

    int deleteByPrimaryKey(@Param("sbbh") String sbbh, @Param("fz") String fz);

    int insert(AzimuthAngleUnique record);

    int insertSelective(AzimuthAngleUnique record);

    List<AzimuthAngleUnique> selectByExample(AzimuthAngleUniqueExample example);

    AzimuthAngleUnique selectByPrimaryKey(@Param("sbbh") String sbbh, @Param("fz") String fz);

    int updateByExampleSelective(@Param("record") AzimuthAngleUnique record, @Param("example") AzimuthAngleUniqueExample example);

    int updateByExample(@Param("record") AzimuthAngleUnique record, @Param("example") AzimuthAngleUniqueExample example);

    int updateByPrimaryKeySelective(AzimuthAngleUnique record);

    int updateByPrimaryKey(AzimuthAngleUnique record);
}
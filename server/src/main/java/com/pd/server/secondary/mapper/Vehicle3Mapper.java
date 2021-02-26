package com.pd.server.secondary.mapper;

import com.pd.server.secondary.domain.Vehicle3;
import com.pd.server.secondary.domain.Vehicle3Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Vehicle3Mapper {
    long countByExample(Vehicle3Example example);

    int deleteByExample(Vehicle3Example example);

    int deleteByPrimaryKey(String sfzmhm);

    int insert(Vehicle3 record);

    int insertSelective(Vehicle3 record);

    List<Vehicle3> selectByExample(Vehicle3Example example);

    Vehicle3 selectByPrimaryKey(String sfzmhm);

    int updateByExampleSelective(@Param("record") Vehicle3 record, @Param("example") Vehicle3Example example);

    int updateByExample(@Param("record") Vehicle3 record, @Param("example") Vehicle3Example example);

    int updateByPrimaryKeySelective(Vehicle3 record);

    int updateByPrimaryKey(Vehicle3 record);
}
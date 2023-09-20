package com.pd.server.main.mapper;

import com.pd.server.main.domain.Turbidity;
import com.pd.server.main.domain.TurbidityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TurbidityMapper {
    long countByExample(TurbidityExample example);

    int deleteByExample(TurbidityExample example);

    int deleteByPrimaryKey(String id);

    int insert(Turbidity record);

    int insertSelective(Turbidity record);

    List<Turbidity> selectByExample(TurbidityExample example);

    Turbidity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Turbidity record, @Param("example") TurbidityExample example);

    int updateByExample(@Param("record") Turbidity record, @Param("example") TurbidityExample example);

    int updateByPrimaryKeySelective(Turbidity record);

    int updateByPrimaryKey(Turbidity record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.SeaSurfaceSalinity;
import com.pd.server.main.domain.SeaSurfaceSalinityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SeaSurfaceSalinityMapper {
    long countByExample(SeaSurfaceSalinityExample example);

    int deleteByExample(SeaSurfaceSalinityExample example);

    int deleteByPrimaryKey(String id);

    int insert(SeaSurfaceSalinity record);

    int insertSelective(SeaSurfaceSalinity record);

    List<SeaSurfaceSalinity> selectByExample(SeaSurfaceSalinityExample example);

    SeaSurfaceSalinity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SeaSurfaceSalinity record, @Param("example") SeaSurfaceSalinityExample example);

    int updateByExample(@Param("record") SeaSurfaceSalinity record, @Param("example") SeaSurfaceSalinityExample example);

    int updateByPrimaryKeySelective(SeaSurfaceSalinity record);

    int updateByPrimaryKey(SeaSurfaceSalinity record);
}
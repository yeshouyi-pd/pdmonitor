package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehRobbery;
import com.pd.server.main.domain.VehRobberyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehRobberyMapper {
    long countByExample(VehRobberyExample example);

    int deleteByExample(VehRobberyExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehRobbery record);

    int insertSelective(VehRobbery record);

    List<VehRobbery> selectByExample(VehRobberyExample example);

    VehRobbery selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehRobbery record, @Param("example") VehRobberyExample example);

    int updateByExample(@Param("record") VehRobbery record, @Param("example") VehRobberyExample example);

    int updateByPrimaryKeySelective(VehRobbery record);

    int updateByPrimaryKey(VehRobbery record);
}
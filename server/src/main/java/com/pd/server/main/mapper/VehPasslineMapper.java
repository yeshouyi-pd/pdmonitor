package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehPassline;
import com.pd.server.main.domain.VehPasslineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehPasslineMapper {
    long countByExample(VehPasslineExample example);

    int deleteByExample(VehPasslineExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehPassline record);

    int insertSelective(VehPassline record);

    List<VehPassline> selectByExample(VehPasslineExample example);

    VehPassline selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehPassline record, @Param("example") VehPasslineExample example);

    int updateByExample(@Param("record") VehPassline record, @Param("example") VehPasslineExample example);

    int updateByPrimaryKeySelective(VehPassline record);

    int updateByPrimaryKey(VehPassline record);
}
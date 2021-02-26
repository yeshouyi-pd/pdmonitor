package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehAccident;
import com.pd.server.main.domain.VehAccidentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehAccidentMapper {
    long countByExample(VehAccidentExample example);

    int deleteByExample(VehAccidentExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehAccident record);

    int insertSelective(VehAccident record);

    List<VehAccident> selectByExample(VehAccidentExample example);

    VehAccident selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehAccident record, @Param("example") VehAccidentExample example);

    int updateByExample(@Param("record") VehAccident record, @Param("example") VehAccidentExample example);

    int updateByPrimaryKeySelective(VehAccident record);

    int updateByPrimaryKey(VehAccident record);
}
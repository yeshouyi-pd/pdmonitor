package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehImpawn;
import com.pd.server.main.domain.VehImpawnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehImpawnMapper {
    long countByExample(VehImpawnExample example);

    int deleteByExample(VehImpawnExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehImpawn record);

    int insertSelective(VehImpawn record);

    List<VehImpawn> selectByExample(VehImpawnExample example);

    VehImpawn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehImpawn record, @Param("example") VehImpawnExample example);

    int updateByExample(@Param("record") VehImpawn record, @Param("example") VehImpawnExample example);

    int updateByPrimaryKeySelective(VehImpawn record);

    int updateByPrimaryKey(VehImpawn record);
}
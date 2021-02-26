package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehImpawnCheck;
import com.pd.server.main.domain.VehImpawnCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehImpawnCheckMapper {
    long countByExample(VehImpawnCheckExample example);

    int deleteByExample(VehImpawnCheckExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehImpawnCheck record);

    int insertSelective(VehImpawnCheck record);

    List<VehImpawnCheck> selectByExample(VehImpawnCheckExample example);

    VehImpawnCheck selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehImpawnCheck record, @Param("example") VehImpawnCheckExample example);

    int updateByExample(@Param("record") VehImpawnCheck record, @Param("example") VehImpawnCheckExample example);

    int updateByPrimaryKeySelective(VehImpawnCheck record);

    int updateByPrimaryKey(VehImpawnCheck record);
}
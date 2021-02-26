package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehImpawnQuery;
import com.pd.server.main.domain.VehImpawnQueryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehImpawnQueryMapper {
    long countByExample(VehImpawnQueryExample example);

    int deleteByExample(VehImpawnQueryExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehImpawnQuery record);

    int insertSelective(VehImpawnQuery record);

    List<VehImpawnQuery> selectByExample(VehImpawnQueryExample example);

    VehImpawnQuery selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehImpawnQuery record, @Param("example") VehImpawnQueryExample example);

    int updateByExample(@Param("record") VehImpawnQuery record, @Param("example") VehImpawnQueryExample example);

    int updateByPrimaryKeySelective(VehImpawnQuery record);

    int updateByPrimaryKey(VehImpawnQuery record);
}
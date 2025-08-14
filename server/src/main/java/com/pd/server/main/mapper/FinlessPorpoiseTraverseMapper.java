package com.pd.server.main.mapper;

import com.pd.server.main.domain.FinlessPorpoiseTraverse;
import com.pd.server.main.domain.FinlessPorpoiseTraverseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FinlessPorpoiseTraverseMapper {
    long countByExample(FinlessPorpoiseTraverseExample example);

    int deleteByExample(FinlessPorpoiseTraverseExample example);

    int deleteByPrimaryKey(String id);

    int insert(FinlessPorpoiseTraverse record);

    int insertSelective(FinlessPorpoiseTraverse record);

    List<FinlessPorpoiseTraverse> selectByExample(FinlessPorpoiseTraverseExample example);

    FinlessPorpoiseTraverse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FinlessPorpoiseTraverse record, @Param("example") FinlessPorpoiseTraverseExample example);

    int updateByExample(@Param("record") FinlessPorpoiseTraverse record, @Param("example") FinlessPorpoiseTraverseExample example);

    int updateByPrimaryKeySelective(FinlessPorpoiseTraverse record);

    int updateByPrimaryKey(FinlessPorpoiseTraverse record);
}
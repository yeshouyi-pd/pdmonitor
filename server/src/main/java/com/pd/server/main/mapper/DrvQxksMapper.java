package com.pd.server.main.mapper;

import com.pd.server.main.domain.DrvQxks;
import com.pd.server.main.domain.DrvQxksExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrvQxksMapper {
    long countByExample(DrvQxksExample example);

    int deleteByExample(DrvQxksExample example);

    int deleteByPrimaryKey(String id);

    int insert(DrvQxks record);

    int insertSelective(DrvQxks record);

    List<DrvQxks> selectByExample(DrvQxksExample example);

    DrvQxks selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DrvQxks record, @Param("example") DrvQxksExample example);

    int updateByExample(@Param("record") DrvQxks record, @Param("example") DrvQxksExample example);

    int updateByPrimaryKeySelective(DrvQxks record);

    int updateByPrimaryKey(DrvQxks record);
}
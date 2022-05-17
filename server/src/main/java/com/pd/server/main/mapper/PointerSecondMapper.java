package com.pd.server.main.mapper;

import com.pd.server.main.domain.PointerSecond;
import com.pd.server.main.domain.PointerSecondExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointerSecondMapper {
    long countByExample(PointerSecondExample example);

    int deleteByExample(PointerSecondExample example);

    int deleteByPrimaryKey(String id);

    int insert(PointerSecond record);

    int insertSelective(PointerSecond record);

    List<PointerSecond> selectByExample(PointerSecondExample example);

    PointerSecond selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PointerSecond record, @Param("example") PointerSecondExample example);

    int updateByExample(@Param("record") PointerSecond record, @Param("example") PointerSecondExample example);

    int updateByPrimaryKeySelective(PointerSecond record);

    int updateByPrimaryKey(PointerSecond record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.PointerDay;
import com.pd.server.main.domain.PointerDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointerDayMapper {
    long countByExample(PointerDayExample example);

    int deleteByExample(PointerDayExample example);

    int deleteByPrimaryKey(String id);

    int insert(PointerDay record);

    int insertSelective(PointerDay record);

    List<PointerDay> selectByExample(PointerDayExample example);

    PointerDay selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PointerDay record, @Param("example") PointerDayExample example);

    int updateByExample(@Param("record") PointerDay record, @Param("example") PointerDayExample example);

    int updateByPrimaryKeySelective(PointerDay record);

    int updateByPrimaryKey(PointerDay record);
}
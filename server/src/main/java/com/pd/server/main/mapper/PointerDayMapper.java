package com.pd.server.main.mapper;

import com.pd.server.main.domain.PointerDay;
import com.pd.server.main.domain.PointerDayExample;
import java.util.List;

import com.pd.server.main.dto.PointerDayDto;
import com.pd.server.main.dto.basewx.my.PointerCommenDto;
import org.apache.ibatis.annotations.Param;

public interface PointerDayMapper {
    long countByExample(PointerDayExample example);

    int deleteByExample(PointerDayExample example);

    int deleteByPrimaryKey(String id);

    int insert(PointerDay record);

    int insertSelective(PointerDay record);

    List<PointerDay> selectByExample(PointerDayExample example);

    List<PointerCommenDto> selectAll(PointerDayExample example);

    List<PointerCommenDto> selectAllSpecial(@Param("record") PointerDayDto record);

    PointerDay selectByPrimaryKey(String id);

    PointerDay selectByDp();

    int updateByExampleSelective(@Param("record") PointerDay record, @Param("example") PointerDayExample example);

    int updateByExample(@Param("record") PointerDay record, @Param("example") PointerDayExample example);

    int updateByPrimaryKeySelective(PointerDay record);

    int updateByPrimaryKey(PointerDay record);
}
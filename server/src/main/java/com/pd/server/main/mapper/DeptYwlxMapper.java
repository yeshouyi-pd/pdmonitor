package com.pd.server.main.mapper;

import com.pd.server.main.domain.DeptYwlx;
import com.pd.server.main.domain.DeptYwlxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptYwlxMapper {
    long countByExample(DeptYwlxExample example);

    int deleteByExample(DeptYwlxExample example);

    int deleteByPrimaryKey(String id);

    int insert(DeptYwlx record);

    int insertSelective(DeptYwlx record);

    List<DeptYwlx> selectByExample(DeptYwlxExample example);

    DeptYwlx selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DeptYwlx record, @Param("example") DeptYwlxExample example);

    int updateByExample(@Param("record") DeptYwlx record, @Param("example") DeptYwlxExample example);

    int updateByPrimaryKeySelective(DeptYwlx record);

    int updateByPrimaryKey(DeptYwlx record);
}
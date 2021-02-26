package com.pd.server.main.mapper;

import com.pd.server.main.domain.DeptYysj;
import com.pd.server.main.domain.DeptYysjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptYysjMapper {
    long countByExample(DeptYysjExample example);

    int deleteByExample(DeptYysjExample example);

    int deleteByPrimaryKey(String id);

    int insert(DeptYysj record);

    int insertSelective(DeptYysj record);

    List<DeptYysj> selectByExample(DeptYysjExample example);

    DeptYysj selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DeptYysj record, @Param("example") DeptYysjExample example);

    int updateByExample(@Param("record") DeptYysj record, @Param("example") DeptYysjExample example);

    int updateByPrimaryKeySelective(DeptYysj record);

    int updateByPrimaryKey(DeptYysj record);
}
package com.pd.server.secondary.mapper;

import com.pd.server.secondary.domain.LdTaskListSec;
import com.pd.server.secondary.domain.LdTaskListSecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LdTaskListSecMapper {
    long countByExample(LdTaskListSecExample example);

    int deleteByExample(LdTaskListSecExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LdTaskListSec record);

    int insertSelective(LdTaskListSec record);

    List<LdTaskListSec> selectByExample(LdTaskListSecExample example);

    LdTaskListSec selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LdTaskListSec record, @Param("example") LdTaskListSecExample example);

    int updateByExample(@Param("record") LdTaskListSec record, @Param("example") LdTaskListSecExample example);

    int updateByPrimaryKeySelective(LdTaskListSec record);

    int updateByPrimaryKey(LdTaskListSec record);
}
package com.pd.power.main.mapper;

import com.pd.power.main.domain.LdTaskListSec;
import com.pd.power.main.domain.LdTaskListSecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
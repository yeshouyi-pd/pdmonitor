package com.pd.power.main.mapper;

import com.pd.power.main.domain.TdDataSec;
import com.pd.power.main.domain.TdDataSecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TdDataSecMapper {
    long countByExample(TdDataSecExample example);

    int deleteByExample(TdDataSecExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TdDataSec record);

    int insertSelective(TdDataSec record);

    List<TdDataSec> selectByExample(TdDataSecExample example);

    TdDataSec selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TdDataSec record, @Param("example") TdDataSecExample example);

    int updateByExample(@Param("record") TdDataSec record, @Param("example") TdDataSecExample example);

    int updateByPrimaryKeySelective(TdDataSec record);

    int updateByPrimaryKey(TdDataSec record);
}
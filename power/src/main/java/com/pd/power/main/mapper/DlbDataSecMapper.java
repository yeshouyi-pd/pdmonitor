package com.pd.power.main.mapper;

import com.pd.power.main.domain.DlbDataSec;
import com.pd.power.main.domain.DlbDataSecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DlbDataSecMapper {
    long countByExample(DlbDataSecExample example);

    int deleteByExample(DlbDataSecExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DlbDataSec record);

    int insertSelective(DlbDataSec record);

    List<DlbDataSec> selectByExample(DlbDataSecExample example);

    DlbDataSec selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DlbDataSec record, @Param("example") DlbDataSecExample example);

    int updateByExample(@Param("record") DlbDataSec record, @Param("example") DlbDataSecExample example);

    int updateByPrimaryKeySelective(DlbDataSec record);

    int updateByPrimaryKey(DlbDataSec record);
}
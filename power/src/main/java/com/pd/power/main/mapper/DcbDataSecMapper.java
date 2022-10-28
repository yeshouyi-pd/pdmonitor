package com.pd.power.main.mapper;

import com.pd.power.main.domain.DcbDataSec;
import com.pd.power.main.domain.DcbDataSecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DcbDataSecMapper {
    long countByExample(DcbDataSecExample example);

    int deleteByExample(DcbDataSecExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DcbDataSec record);

    int insertSelective(DcbDataSec record);

    List<DcbDataSec> selectByExample(DcbDataSecExample example);

    DcbDataSec selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DcbDataSec record, @Param("example") DcbDataSecExample example);

    int updateByExample(@Param("record") DcbDataSec record, @Param("example") DcbDataSecExample example);

    int updateByPrimaryKeySelective(DcbDataSec record);

    int updateByPrimaryKey(DcbDataSec record);
}
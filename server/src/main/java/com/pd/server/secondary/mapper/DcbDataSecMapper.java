package com.pd.server.secondary.mapper;

import com.pd.server.secondary.domain.DcbDataSec;
import com.pd.server.secondary.domain.DcbDataSecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
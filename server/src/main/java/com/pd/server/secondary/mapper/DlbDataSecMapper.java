package com.pd.server.secondary.mapper;

import com.pd.server.secondary.domain.DlbDataSec;
import com.pd.server.secondary.domain.DlbDataSecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
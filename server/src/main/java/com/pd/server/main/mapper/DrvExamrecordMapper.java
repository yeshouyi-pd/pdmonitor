package com.pd.server.main.mapper;

import com.pd.server.main.domain.DrvExamrecord;
import com.pd.server.main.domain.DrvExamrecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrvExamrecordMapper {
    long countByExample(DrvExamrecordExample example);

    int deleteByExample(DrvExamrecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(DrvExamrecord record);

    int insertSelective(DrvExamrecord record);

    List<DrvExamrecord> selectByExample(DrvExamrecordExample example);

    DrvExamrecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DrvExamrecord record, @Param("example") DrvExamrecordExample example);

    int updateByExample(@Param("record") DrvExamrecord record, @Param("example") DrvExamrecordExample example);

    int updateByPrimaryKeySelective(DrvExamrecord record);

    int updateByPrimaryKey(DrvExamrecord record);

    int deleteByLogid(String logid);
}
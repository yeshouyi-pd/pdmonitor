package com.pd.server.main.mapper;

import com.pd.server.main.domain.DrvExam;
import com.pd.server.main.domain.DrvExamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrvExamMapper {
    long countByExample(DrvExamExample example);

    int deleteByExample(DrvExamExample example);

    int deleteByPrimaryKey(String id);

    int insert(DrvExam record);

    int insertSelective(DrvExam record);

    List<DrvExam> selectByExample(DrvExamExample example);

    DrvExam selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DrvExam record, @Param("example") DrvExamExample example);

    int updateByExample(@Param("record") DrvExam record, @Param("example") DrvExamExample example);

    int updateByPrimaryKeySelective(DrvExam record);

    int updateByPrimaryKey(DrvExam record);

    List<DrvExam> selectByLimit();
}
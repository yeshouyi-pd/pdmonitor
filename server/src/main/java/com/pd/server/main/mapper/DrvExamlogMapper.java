package com.pd.server.main.mapper;

import com.pd.server.main.domain.DrvExamlog;
import com.pd.server.main.domain.DrvExamlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrvExamlogMapper {
    long countByExample(DrvExamlogExample example);

    int deleteByExample(DrvExamlogExample example);

    int deleteByPrimaryKey(@Param("id") String id);

    int insert(DrvExamlog record);

    int insertSelective(DrvExamlog record);

    List<DrvExamlog> selectByExample(DrvExamlogExample example);

    DrvExamlog selectByPrimaryKey(@Param("id") String id, @Param("xm") String xm);

    int updateByExampleSelective(@Param("record") DrvExamlog record, @Param("example") DrvExamlogExample example);

    int updateByExample(@Param("record") DrvExamlog record, @Param("example") DrvExamlogExample example);

    int updateByPrimaryKeySelective(DrvExamlog record);

    int updateByPrimaryKey(DrvExamlog record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.PredationNum;
import com.pd.server.main.domain.PredationNumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PredationNumMapper {
    long countByExample(PredationNumExample example);

    int deleteByExample(PredationNumExample example);

    int deleteByPrimaryKey(String id);

    int insert(PredationNum record);

    int insertSelective(PredationNum record);

    List<PredationNum> selectByExample(PredationNumExample example);

    PredationNum selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PredationNum record, @Param("example") PredationNumExample example);

    int updateByExample(@Param("record") PredationNum record, @Param("example") PredationNumExample example);

    int updateByPrimaryKeySelective(PredationNum record);

    int updateByPrimaryKey(PredationNum record);
}
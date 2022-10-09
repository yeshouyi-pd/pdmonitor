package com.pd.server.main.mapper;

import com.pd.server.main.domain.TdData;
import com.pd.server.main.domain.TdDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdDataMapper {
    long countByExample(TdDataExample example);

    int deleteByExample(TdDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TdData record);

    int insertSelective(TdData record);

    List<TdData> selectByExample(TdDataExample example);

    TdData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TdData record, @Param("example") TdDataExample example);

    int updateByExample(@Param("record") TdData record, @Param("example") TdDataExample example);

    int updateByPrimaryKeySelective(TdData record);

    int updateByPrimaryKey(TdData record);
}
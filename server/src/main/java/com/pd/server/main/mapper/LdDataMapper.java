package com.pd.server.main.mapper;

import com.pd.server.main.domain.LdData;
import com.pd.server.main.domain.LdDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LdDataMapper {
    long countByExample(LdDataExample example);

    int deleteByExample(LdDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LdData record);

    int insertSelective(LdData record);

    List<LdData> selectByExample(LdDataExample example);

    LdData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LdData record, @Param("example") LdDataExample example);

    int updateByExample(@Param("record") LdData record, @Param("example") LdDataExample example);

    int updateByPrimaryKeySelective(LdData record);

    int updateByPrimaryKey(LdData record);
}
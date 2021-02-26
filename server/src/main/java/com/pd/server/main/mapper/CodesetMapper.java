package com.pd.server.main.mapper;

import com.pd.server.main.domain.Codeset;
import com.pd.server.main.domain.CodesetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodesetMapper {
    long countByExample(CodesetExample example);

    int deleteByExample(CodesetExample example);

    int deleteByPrimaryKey(String id);

    int insert(Codeset record);

    int insertSelective(Codeset record);

    List<Codeset> selectByExample(CodesetExample example);

    Codeset selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Codeset record, @Param("example") CodesetExample example);

    int updateByExample(@Param("record") Codeset record, @Param("example") CodesetExample example);

    int updateByPrimaryKeySelective(Codeset record);

    int updateByPrimaryKey(Codeset record);
}
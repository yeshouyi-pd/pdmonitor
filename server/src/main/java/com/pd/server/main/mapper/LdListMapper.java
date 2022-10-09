package com.pd.server.main.mapper;

import com.pd.server.main.domain.LdList;
import com.pd.server.main.domain.LdListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LdListMapper {
    long countByExample(LdListExample example);

    int deleteByExample(LdListExample example);

    int deleteByPrimaryKey(String iccid);

    int insert(LdList record);

    int insertSelective(LdList record);

    List<LdList> selectByExample(LdListExample example);

    LdList selectByPrimaryKey(String iccid);

    int updateByExampleSelective(@Param("record") LdList record, @Param("example") LdListExample example);

    int updateByExample(@Param("record") LdList record, @Param("example") LdListExample example);

    int updateByPrimaryKeySelective(LdList record);

    int updateByPrimaryKey(LdList record);
}
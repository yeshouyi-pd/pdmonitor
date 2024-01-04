package com.pd.server.main.mapper;

import com.pd.server.main.domain.UserVideo;
import com.pd.server.main.domain.UserVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserVideoMapper {
    long countByExample(UserVideoExample example);

    int deleteByExample(UserVideoExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserVideo record);

    int insertSelective(UserVideo record);

    List<UserVideo> selectByExample(UserVideoExample example);

    UserVideo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserVideo record, @Param("example") UserVideoExample example);

    int updateByExample(@Param("record") UserVideo record, @Param("example") UserVideoExample example);

    int updateByPrimaryKeySelective(UserVideo record);

    int updateByPrimaryKey(UserVideo record);

    List<String> glyCode();
}
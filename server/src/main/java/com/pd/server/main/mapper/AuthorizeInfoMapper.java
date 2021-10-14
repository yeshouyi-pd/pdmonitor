package com.pd.server.main.mapper;

import com.pd.server.main.domain.AuthorizeInfo;
import com.pd.server.main.domain.AuthorizeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorizeInfoMapper {
    long countByExample(AuthorizeInfoExample example);

    int deleteByExample(AuthorizeInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(AuthorizeInfo record);

    int insertSelective(AuthorizeInfo record);

    List<AuthorizeInfo> selectByExample(AuthorizeInfoExample example);

    AuthorizeInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AuthorizeInfo record, @Param("example") AuthorizeInfoExample example);

    int updateByExample(@Param("record") AuthorizeInfo record, @Param("example") AuthorizeInfoExample example);

    int updateByPrimaryKeySelective(AuthorizeInfo record);

    int updateByPrimaryKey(AuthorizeInfo record);
}
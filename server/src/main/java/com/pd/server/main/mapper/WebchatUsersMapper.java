package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatUsers;
import com.pd.server.main.domain.WebchatUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatUsersMapper {
    long countByExample(WebchatUsersExample example);

    int deleteByExample(WebchatUsersExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatUsers record);

    int insertSelective(WebchatUsers record);

    List<WebchatUsers> selectByExampleWithBLOBs(WebchatUsersExample example);

    List<WebchatUsers> selectByExample(WebchatUsersExample example);

    WebchatUsers selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebchatUsers record, @Param("example") WebchatUsersExample example);

    int updateByExampleWithBLOBs(@Param("record") WebchatUsers record, @Param("example") WebchatUsersExample example);

    int updateByExample(@Param("record") WebchatUsers record, @Param("example") WebchatUsersExample example);

    int updateByPrimaryKeySelective(WebchatUsers record);

    int updateByPrimaryKeyWithBLOBs(WebchatUsers record);

    int updateByPrimaryKey(WebchatUsers record);
}
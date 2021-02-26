package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatFileinfo;
import com.pd.server.main.domain.WebchatFileinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatFileinfoMapper {
    long countByExample(WebchatFileinfoExample example);

    int deleteByExample(WebchatFileinfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatFileinfo record);

    int insertSelective(WebchatFileinfo record);

    List<WebchatFileinfo> selectByExample(WebchatFileinfoExample example);

    WebchatFileinfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebchatFileinfo record, @Param("example") WebchatFileinfoExample example);

    int updateByExample(@Param("record") WebchatFileinfo record, @Param("example") WebchatFileinfoExample example);

    int updateByPrimaryKeySelective(WebchatFileinfo record);

    int updateByPrimaryKey(WebchatFileinfo record);
}
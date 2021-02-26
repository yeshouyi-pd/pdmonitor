package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatPostinfo;
import com.pd.server.main.domain.WebchatPostinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatPostinfoMapper {
    long countByExample(WebchatPostinfoExample example);

    int deleteByExample(WebchatPostinfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatPostinfo record);

    int insertSelective(WebchatPostinfo record);

    List<WebchatPostinfo> selectByExample(WebchatPostinfoExample example);

    WebchatPostinfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebchatPostinfo record, @Param("example") WebchatPostinfoExample example);

    int updateByExample(@Param("record") WebchatPostinfo record, @Param("example") WebchatPostinfoExample example);

    int updateByPrimaryKeySelective(WebchatPostinfo record);

    int updateByPrimaryKey(WebchatPostinfo record);
}
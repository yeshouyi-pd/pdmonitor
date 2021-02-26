package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatBlack;
import com.pd.server.main.domain.WebchatBlackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatBlackMapper {
    long countByExample(WebchatBlackExample example);

    int deleteByExample(WebchatBlackExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatBlack record);

    int insertSelective(WebchatBlack record);

    List<WebchatBlack> selectByExample(WebchatBlackExample example);

    WebchatBlack selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebchatBlack record, @Param("example") WebchatBlackExample example);

    int updateByExample(@Param("record") WebchatBlack record, @Param("example") WebchatBlackExample example);

    int updateByPrimaryKeySelective(WebchatBlack record);

    int updateByPrimaryKey(WebchatBlack record);
}
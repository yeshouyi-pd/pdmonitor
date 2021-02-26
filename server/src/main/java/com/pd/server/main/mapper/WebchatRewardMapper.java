package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatReward;
import com.pd.server.main.domain.WebchatRewardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatRewardMapper {
    long countByExample(WebchatRewardExample example);

    int deleteByExample(WebchatRewardExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatReward record);

    int insertSelective(WebchatReward record);

    List<WebchatReward> selectByExample(WebchatRewardExample example);

    WebchatReward selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebchatReward record, @Param("example") WebchatRewardExample example);

    int updateByExample(@Param("record") WebchatReward record, @Param("example") WebchatRewardExample example);

    int updateByPrimaryKeySelective(WebchatReward record);

    int updateByPrimaryKey(WebchatReward record);

    int updateById(WebchatReward record);
}
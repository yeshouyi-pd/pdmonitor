package com.pd.server.main.mapper;

import com.pd.server.main.domain.RewardCheck;
import com.pd.server.main.domain.RewardCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RewardCheckMapper {
    long countByExample(RewardCheckExample example);

    int deleteByExample(RewardCheckExample example);

    int deleteByPrimaryKey(String id);

    int insert(RewardCheck record);

    int insertSelective(RewardCheck record);

    List<RewardCheck> selectByExample(RewardCheckExample example);

    RewardCheck selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RewardCheck record, @Param("example") RewardCheckExample example);

    int updateByExample(@Param("record") RewardCheck record, @Param("example") RewardCheckExample example);

    int updateByPrimaryKeySelective(RewardCheck record);

    int updateByPrimaryKey(RewardCheck record);
}
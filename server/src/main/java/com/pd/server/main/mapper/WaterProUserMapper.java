package com.pd.server.main.mapper;

import com.pd.server.main.domain.WaterProUser;
import com.pd.server.main.domain.WaterProUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterProUserMapper {
    long countByExample(WaterProUserExample example);

    int deleteByExample(WaterProUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterProUser record);

    int insertSelective(WaterProUser record);

    List<WaterProUser> selectByExample(WaterProUserExample example);

    WaterProUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterProUser record, @Param("example") WaterProUserExample example);

    int updateByExample(@Param("record") WaterProUser record, @Param("example") WaterProUserExample example);

    int updateByPrimaryKeySelective(WaterProUser record);

    int updateByPrimaryKey(WaterProUser record);

    List<String> findXmbhByUsercode(String usercode);

    List<String> findXmbhByExample(WaterProUserExample example);
}
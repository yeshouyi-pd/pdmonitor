package com.pd.server.main.mapper;

import com.pd.server.main.domain.UavFlyVideo;
import com.pd.server.main.domain.UavFlyVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UavFlyVideoMapper {
    long countByExample(UavFlyVideoExample example);

    int deleteByExample(UavFlyVideoExample example);

    int deleteByPrimaryKey(String id);

    int insert(UavFlyVideo record);

    int insertSelective(UavFlyVideo record);

    List<UavFlyVideo> selectByExample(UavFlyVideoExample example);

    UavFlyVideo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UavFlyVideo record, @Param("example") UavFlyVideoExample example);

    int updateByExample(@Param("record") UavFlyVideo record, @Param("example") UavFlyVideoExample example);

    int updateByPrimaryKeySelective(UavFlyVideo record);

    int updateByPrimaryKey(UavFlyVideo record);
}
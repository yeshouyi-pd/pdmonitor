package com.pd.server.main.mapper;

import com.pd.server.main.domain.VideoEvent;
import com.pd.server.main.domain.VideoEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoEventMapper {
    long countByExample(VideoEventExample example);

    int deleteByExample(VideoEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(VideoEvent record);

    int insertSelective(VideoEvent record);

    List<VideoEvent> selectByExample(VideoEventExample example);

    VideoEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VideoEvent record, @Param("example") VideoEventExample example);

    int updateByExample(@Param("record") VideoEvent record, @Param("example") VideoEventExample example);

    int updateByPrimaryKeySelective(VideoEvent record);

    int updateByPrimaryKey(VideoEvent record);
}
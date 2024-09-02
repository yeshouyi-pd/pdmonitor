package com.pd.server.main.mapper;

import com.pd.server.main.domain.VideoEvent;
import com.pd.server.main.domain.VideoEventExample;
import java.util.List;

import com.pd.server.main.dto.VideoEventDto;
import com.pd.server.main.dto.basewx.my.VideoEventDpDto;
import org.apache.ibatis.annotations.Param;

public interface VideoEventMapper {
    long countByExample(VideoEventExample example);

    int deleteByExample(VideoEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(VideoEvent record);

    int insertSelective(VideoEvent record);

    List<VideoEvent> selectByExample(VideoEventExample example);

    List<VideoEvent> selectByDp(@Param("example") VideoEventExample example,@Param("limitNum") Integer limitNum);

    List<VideoEvent> selectByPage(@Param("record") VideoEventDto record);

    VideoEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VideoEvent record, @Param("example") VideoEventExample example);

    int updateByExample(@Param("record") VideoEvent record, @Param("example") VideoEventExample example);

    int updateByPrimaryKeySelective(VideoEvent record);

    int updateByPrimaryKey(VideoEvent record);

    List<VideoEventDpDto> selectCountByRq(VideoEventExample example);

    List<VideoEventDpDto> selectCountBySbbh(VideoEventExample example);

    List<VideoEventDpDto> selectStaticsBySbbh(VideoEventExample example);

    List<VideoEventDpDto> selectStaticsByRq(VideoEventExample example);
}
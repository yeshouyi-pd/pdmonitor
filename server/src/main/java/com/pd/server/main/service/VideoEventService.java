package com.pd.server.main.service;

import com.pd.server.main.domain.VideoEvent;
import com.pd.server.main.domain.VideoEventExample;
import com.pd.server.main.dto.VideoEventDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.VideoEventMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoEventService {

    @Resource
    private VideoEventMapper videoEventMapper;

    /**
    * 列表查询
    */
    public List<VideoEvent> selectByExample(VideoEventExample example) {
        return videoEventMapper.selectByExample(example);
    }

    public List<VideoEvent> selectByPage(VideoEventDto videoEventDto){
        return videoEventMapper.selectByPage(videoEventDto);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VideoEventDto videoEventDto) {
        VideoEvent videoEvent = CopyUtil.copy(videoEventDto, VideoEvent.class);
        if (StringUtils.isEmpty(videoEventDto.getId())) {
            this.insert(videoEvent);
        } else {
            this.update(videoEvent);
        }
    }

    /**
    * 新增
    */
    private void insert(VideoEvent videoEvent) {
        videoEvent.setId(UuidUtil.getShortUuid());
        videoEventMapper.insert(videoEvent);
    }

    /**
    * 更新
    */
    private void update(VideoEvent videoEvent) {
        videoEventMapper.updateByPrimaryKey(videoEvent);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        videoEventMapper.deleteByPrimaryKey(id);
    }

}
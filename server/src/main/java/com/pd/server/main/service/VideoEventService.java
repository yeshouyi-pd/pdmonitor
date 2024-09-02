package com.pd.server.main.service;

import com.pd.server.main.domain.VideoEvent;
import com.pd.server.main.domain.VideoEventExample;
import com.pd.server.main.dto.VideoEventDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.VideoEventDpDto;
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

    public VideoEvent selectById(String id){
        return videoEventMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据日期分组查询已审核和未审核的联动视频数据量，实时视频数据量
     */
    public List<VideoEventDpDto> selectStaticsByRq(VideoEventExample example){
        return videoEventMapper.selectStaticsByRq(example);
    }

    /**
     * 根据设备编号分组查询已审核和未审核的联动视频数据量，实时视频数据量
     */
    public List<VideoEventDpDto> selectStaticsBySbbh(VideoEventExample example){
        return videoEventMapper.selectStaticsBySbbh(example);
    }

    /**
     * 根据日期分组查询已审核和未审核的数据量
     */
    public List<VideoEventDpDto> selectCountByRq(VideoEventExample example){
        return videoEventMapper.selectCountByRq(example);
    }

    /**
     * 根据设备编号分钟查询已审核和未审核的数据量
     */
    public List<VideoEventDpDto> selectCountBySbbh(VideoEventExample example){
        return videoEventMapper.selectCountBySbbh(example);
    }

    /**
    * 列表查询
    */
    public List<VideoEvent> selectByExample(VideoEventExample example) {
        return videoEventMapper.selectByExample(example);
    }

    public List<VideoEvent> selectByPage(VideoEventDto videoEventDto){
        return videoEventMapper.selectByPage(videoEventDto);
    }

    public List<VideoEvent> selectByDp(VideoEventExample example,Integer limitNum) {
        return videoEventMapper.selectByDp(example, limitNum);
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
        videoEventMapper.updateByPrimaryKeySelective(videoEvent);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        videoEventMapper.deleteByPrimaryKey(id);
    }

    public void updateItem(VideoEvent videoEvent){
        videoEventMapper.updateByPrimaryKeySelective(videoEvent);
    }

    public void saveItem(VideoEvent videoEvent){
        videoEventMapper.insert(videoEvent);
    }
}
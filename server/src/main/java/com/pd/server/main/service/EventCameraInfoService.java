package com.pd.server.main.service;

import com.pd.server.main.domain.EventCameraInfo;
import com.pd.server.main.domain.EventCameraInfoExample;
import com.pd.server.main.dto.EventCameraInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EventCameraInfoMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class EventCameraInfoService {

    @Resource
    private EventCameraInfoMapper eventCameraInfoMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EventCameraInfoExample eventCameraInfoExample = new EventCameraInfoExample();
        List<EventCameraInfo> eventCameraInfoList = eventCameraInfoMapper.selectByExample(eventCameraInfoExample);
        PageInfo<EventCameraInfo> pageInfo = new PageInfo<>(eventCameraInfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EventCameraInfoDto> eventCameraInfoDtoList = CopyUtil.copyList(eventCameraInfoList, EventCameraInfoDto.class);
        pageDto.setList(eventCameraInfoDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EventCameraInfoDto eventCameraInfoDto) {
        EventCameraInfo eventCameraInfo = CopyUtil.copy(eventCameraInfoDto, EventCameraInfo.class);
        if (StringUtils.isEmpty(eventCameraInfoDto.getId())) {
            this.insert(eventCameraInfo);
        } else {
            this.update(eventCameraInfo);
        }
    }

    /**
    * 新增
    */
    private void insert(EventCameraInfo eventCameraInfo) {
                Date now = new Date();
        eventCameraInfo.setId(UuidUtil.getShortUuid());
        eventCameraInfoMapper.insert(eventCameraInfo);
    }

    /**
    * 更新
    */
    private void update(EventCameraInfo eventCameraInfo) {
        eventCameraInfoMapper.updateByPrimaryKey(eventCameraInfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        eventCameraInfoMapper.deleteByPrimaryKey(id);
    }

}
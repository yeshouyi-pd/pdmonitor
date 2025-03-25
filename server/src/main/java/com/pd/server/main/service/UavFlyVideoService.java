package com.pd.server.main.service;

import com.pd.server.main.domain.UavFlyVideo;
import com.pd.server.main.domain.UavFlyVideoExample;
import com.pd.server.main.dto.UavFlyVideoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.UavFlyVideoMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UavFlyVideoService {

    @Resource
    private UavFlyVideoMapper uavFlyVideoMapper;

    /**
    * 列表查询
    */
    public List<UavFlyVideo> selectByExample(UavFlyVideoExample example) {
        return uavFlyVideoMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(UavFlyVideoDto uavFlyVideoDto) {
        UavFlyVideo uavFlyVideo = CopyUtil.copy(uavFlyVideoDto, UavFlyVideo.class);
        if (StringUtils.isEmpty(uavFlyVideoDto.getId())) {
            this.insert(uavFlyVideo);
        } else {
            this.update(uavFlyVideo);
        }
    }

    /**
    * 新增
    */
    private void insert(UavFlyVideo uavFlyVideo) {
        uavFlyVideo.setId(UuidUtil.getShortUuid());
        uavFlyVideoMapper.insert(uavFlyVideo);
    }

    /**
    * 更新
    */
    private void update(UavFlyVideo uavFlyVideo) {
        uavFlyVideoMapper.updateByPrimaryKey(uavFlyVideo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        uavFlyVideoMapper.deleteByPrimaryKey(id);
    }

}
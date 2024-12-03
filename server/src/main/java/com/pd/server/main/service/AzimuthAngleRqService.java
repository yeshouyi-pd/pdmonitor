package com.pd.server.main.service;

import com.pd.server.main.domain.AzimuthAngleRq;
import com.pd.server.main.domain.AzimuthAngleRqExample;
import com.pd.server.main.dto.AzimuthAngleRqDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AzimuthAngleRqMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AzimuthAngleRqService {

    @Resource
    private AzimuthAngleRqMapper azimuthAngleRqMapper;

    public List<AzimuthAngleRq> selectByQuartz(AzimuthAngleRqExample example) {
        return azimuthAngleRqMapper.selectByQuartz(example);
    }

    /**
    * 列表查询
    */
    public List<AzimuthAngleRq> list(AzimuthAngleRqExample example) {
        return azimuthAngleRqMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AzimuthAngleRqDto azimuthAngleRqDto) {
        AzimuthAngleRq azimuthAngleRq = CopyUtil.copy(azimuthAngleRqDto, AzimuthAngleRq.class);
        if (StringUtils.isEmpty(azimuthAngleRqDto.getId())) {
            this.insert(azimuthAngleRq);
        } else {
            this.update(azimuthAngleRq);
        }
    }

    /**
    * 新增
    */
    private void insert(AzimuthAngleRq azimuthAngleRq) {
        azimuthAngleRq.setId(UuidUtil.getShortUuid());
        azimuthAngleRqMapper.insert(azimuthAngleRq);
    }

    /**
    * 更新
    */
    private void update(AzimuthAngleRq azimuthAngleRq) {
        azimuthAngleRqMapper.updateByPrimaryKey(azimuthAngleRq);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        azimuthAngleRqMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除
     */
    public void deleteByExample(AzimuthAngleRqExample example) {
        azimuthAngleRqMapper.deleteByExample(example);
    }

}
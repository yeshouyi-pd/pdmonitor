package com.pd.server.main.service;

import com.pd.server.main.domain.AzimuthAngle;
import com.pd.server.main.domain.AzimuthAngleExample;
import com.pd.server.main.dto.AzimuthAngleDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AzimuthAngleMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AzimuthAngleService {

    @Resource
    private AzimuthAngleMapper azimuthAngleMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AzimuthAngleExample azimuthAngleExample = new AzimuthAngleExample();
        List<AzimuthAngle> azimuthAngleList = azimuthAngleMapper.selectByExample(azimuthAngleExample);
        PageInfo<AzimuthAngle> pageInfo = new PageInfo<>(azimuthAngleList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AzimuthAngleDto> azimuthAngleDtoList = CopyUtil.copyList(azimuthAngleList, AzimuthAngleDto.class);
        pageDto.setList(azimuthAngleDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AzimuthAngleDto azimuthAngleDto) {
        AzimuthAngle azimuthAngle = CopyUtil.copy(azimuthAngleDto, AzimuthAngle.class);
        if (StringUtils.isEmpty(azimuthAngleDto.getId())) {
            this.insert(azimuthAngle);
        } else {
            this.update(azimuthAngle);
        }
    }

    /**
    * 新增
    */
    private void insert(AzimuthAngle azimuthAngle) {
        azimuthAngle.setId(UuidUtil.getShortUuid());
        azimuthAngleMapper.insert(azimuthAngle);
    }

    /**
    * 更新
    */
    private void update(AzimuthAngle azimuthAngle) {
        azimuthAngleMapper.updateByPrimaryKey(azimuthAngle);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        azimuthAngleMapper.deleteByPrimaryKey(id);
    }

}
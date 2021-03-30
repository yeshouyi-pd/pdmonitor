package com.pd.server.main.service;

import com.pd.server.main.domain.WaterQuality;
import com.pd.server.main.domain.WaterQualityExample;
import com.pd.server.main.dto.WaterQualityDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WaterQualityMapper;
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
public class WaterQualityService {

    @Resource
    private WaterQualityMapper waterQualityMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaterQualityExample waterQualityExample = new WaterQualityExample();
        List<WaterQuality> waterQualityList = waterQualityMapper.selectByExample(waterQualityExample);
        PageInfo<WaterQuality> pageInfo = new PageInfo<>(waterQualityList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterQualityDto> waterQualityDtoList = CopyUtil.copyList(waterQualityList, WaterQualityDto.class);
        pageDto.setList(waterQualityDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterQualityDto waterQualityDto) {
        WaterQuality waterQuality = CopyUtil.copy(waterQualityDto, WaterQuality.class);
        if (StringUtils.isEmpty(waterQualityDto.getId())) {
            this.insert(waterQuality);
        } else {
            this.update(waterQuality);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterQuality waterQuality) {
                Date now = new Date();
        waterQuality.setId(UuidUtil.getShortUuid());
        waterQualityMapper.insert(waterQuality);
    }

    /**
    * 更新
    */
    private void update(WaterQuality waterQuality) {
        waterQualityMapper.updateByPrimaryKey(waterQuality);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterQualityMapper.deleteByPrimaryKey(id);
    }

}
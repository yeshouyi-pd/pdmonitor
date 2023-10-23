package com.pd.server.main.service;

import com.pd.server.main.domain.WaterQualityNew;
import com.pd.server.main.domain.WaterQualityNewExample;
import com.pd.server.main.dto.WaterQualityNewDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WaterQualityNewMapper;
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
public class WaterQualityNewService {

    @Resource
    private WaterQualityNewMapper waterQualityNewMapper;

    public List<WaterQualityNew> selectByExample(WaterQualityNewExample example){
        return waterQualityNewMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterQualityNewDto waterQualityNewDto) {
        WaterQualityNew waterQualityNew = CopyUtil.copy(waterQualityNewDto, WaterQualityNew.class);
        if (StringUtils.isEmpty(waterQualityNewDto.getId())) {
            this.insert(waterQualityNew);
        } else {
            this.update(waterQualityNew);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterQualityNew waterQualityNew) {
                Date now = new Date();
        waterQualityNew.setId(UuidUtil.getShortUuid());
        waterQualityNewMapper.insert(waterQualityNew);
    }

    /**
    * 更新
    */
    private void update(WaterQualityNew waterQualityNew) {
        waterQualityNewMapper.updateByPrimaryKey(waterQualityNew);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterQualityNewMapper.deleteByPrimaryKey(id);
    }

}
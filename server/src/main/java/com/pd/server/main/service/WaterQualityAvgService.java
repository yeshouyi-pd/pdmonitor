package com.pd.server.main.service;

import com.pd.server.main.domain.WaterQualityAvg;
import com.pd.server.main.domain.WaterQualityAvgExample;
import com.pd.server.main.domain.WaterQualityResult;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.WaterQualityAvgDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WaterQualityAvgMapper;
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
public class WaterQualityAvgService {

    @Resource
    private WaterQualityAvgMapper waterQualityAvgMapper;

    public List<WaterQualityAvg> findByExample(WaterQualityAvgExample example){
        List<WaterQualityAvg> waterQualityAvgList = waterQualityAvgMapper.selectByExample(example);
        return waterQualityAvgList;
    }

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaterQualityAvgExample waterQualityAvgExample = new WaterQualityAvgExample();
        List<WaterQualityAvg> waterQualityAvgList = waterQualityAvgMapper.selectByExample(waterQualityAvgExample);
        PageInfo<WaterQualityAvg> pageInfo = new PageInfo<>(waterQualityAvgList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterQualityAvgDto> waterQualityAvgDtoList = CopyUtil.copyList(waterQualityAvgList, WaterQualityAvgDto.class);
        pageDto.setList(waterQualityAvgDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterQualityAvgDto waterQualityAvgDto) {
        WaterQualityAvg waterQualityAvg = CopyUtil.copy(waterQualityAvgDto, WaterQualityAvg.class);
        if (StringUtils.isEmpty(waterQualityAvgDto.getId())) {
            this.insert(waterQualityAvg);
        } else {
            this.update(waterQualityAvg);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterQualityAvg waterQualityAvg) {
                Date now = new Date();
        waterQualityAvg.setId(UuidUtil.getShortUuid());
        waterQualityAvgMapper.insert(waterQualityAvg);
    }

    /**
    * 更新
    */
    private void update(WaterQualityAvg waterQualityAvg) {
        waterQualityAvgMapper.updateByPrimaryKey(waterQualityAvg);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterQualityAvgMapper.deleteByPrimaryKey(id);
    }

}
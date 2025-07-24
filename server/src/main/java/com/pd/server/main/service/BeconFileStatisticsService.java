package com.pd.server.main.service;

import com.pd.server.main.domain.BeconFileStatistics;
import com.pd.server.main.domain.BeconFileStatisticsExample;
import com.pd.server.main.dto.BeconFileStatisticsDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.BeconFileStatisticsMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BeconFileStatisticsService {

    @Resource
    private BeconFileStatisticsMapper beconFileStatisticsMapper;

    /**
    * 列表查询
    */
    public List<BeconFileStatistics> selectByExample(BeconFileStatisticsExample example) {
        return beconFileStatisticsMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(BeconFileStatisticsDto beconFileStatisticsDto) {
        BeconFileStatistics beconFileStatistics = CopyUtil.copy(beconFileStatisticsDto, BeconFileStatistics.class);
        if (StringUtils.isEmpty(beconFileStatisticsDto.getId())) {
            this.insert(beconFileStatistics);
        } else {
            this.update(beconFileStatistics);
        }
    }

    /**
    * 新增
    */
    public void insert(BeconFileStatistics beconFileStatistics) {
        beconFileStatistics.setId(UuidUtil.getShortUuid());
        beconFileStatisticsMapper.insert(beconFileStatistics);
    }

    /**
    * 更新
    */
    private void update(BeconFileStatistics beconFileStatistics) {
        beconFileStatisticsMapper.updateByPrimaryKey(beconFileStatistics);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        beconFileStatisticsMapper.deleteByPrimaryKey(id);
    }

    public void deleteByExample(BeconFileStatisticsExample example) {
        beconFileStatisticsMapper.deleteByExample(example);
    }

}
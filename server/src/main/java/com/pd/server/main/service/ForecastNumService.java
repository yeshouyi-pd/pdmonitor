package com.pd.server.main.service;

import com.pd.server.main.domain.ForecastNum;
import com.pd.server.main.domain.ForecastNumExample;
import com.pd.server.main.dto.ForecastNumDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.ForecastNumMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ForecastNumService {

    @Resource
    private ForecastNumMapper forecastNumMapper;

    /**
    * 列表查询
    */
    public List<ForecastNum> selectByExample(ForecastNumExample example) {
        return forecastNumMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(ForecastNumDto forecastNumDto) {
        ForecastNum forecastNum = CopyUtil.copy(forecastNumDto, ForecastNum.class);
        if (StringUtils.isEmpty(forecastNumDto.getId())) {
            this.insert(forecastNum);
        } else {
            this.update(forecastNum);
        }
    }

    /**
    * 新增
    */
    private void insert(ForecastNum forecastNum) {
        forecastNum.setId(UuidUtil.getShortUuid());
        forecastNumMapper.insert(forecastNum);
    }

    /**
    * 更新
    */
    private void update(ForecastNum forecastNum) {
        forecastNumMapper.updateByPrimaryKey(forecastNum);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        forecastNumMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.SonarSingleValue;
import com.pd.server.main.domain.SonarSingleValueExample;
import com.pd.server.main.dto.SonarSingleValueDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SonarSingleValueMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SonarSingleValueService {

    @Resource
    private SonarSingleValueMapper sonarSingleValueMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SonarSingleValueExample sonarSingleValueExample = new SonarSingleValueExample();
        List<SonarSingleValue> sonarSingleValueList = sonarSingleValueMapper.selectByExample(sonarSingleValueExample);
        PageInfo<SonarSingleValue> pageInfo = new PageInfo<>(sonarSingleValueList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SonarSingleValueDto> sonarSingleValueDtoList = CopyUtil.copyList(sonarSingleValueList, SonarSingleValueDto.class);
        pageDto.setList(sonarSingleValueDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SonarSingleValueDto sonarSingleValueDto) {
        SonarSingleValue sonarSingleValue = CopyUtil.copy(sonarSingleValueDto, SonarSingleValue.class);
        if (StringUtils.isEmpty(sonarSingleValueDto.getId())) {
            this.insert(sonarSingleValue);
        } else {
            this.update(sonarSingleValue);
        }
    }

    /**
    * 新增
    */
    private void insert(SonarSingleValue sonarSingleValue) {
        sonarSingleValue.setId(UuidUtil.getShortUuid());
        sonarSingleValueMapper.insert(sonarSingleValue);
    }

    /**
    * 更新
    */
    private void update(SonarSingleValue sonarSingleValue) {
        sonarSingleValueMapper.updateByPrimaryKey(sonarSingleValue);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        sonarSingleValueMapper.deleteByPrimaryKey(id);
    }

}
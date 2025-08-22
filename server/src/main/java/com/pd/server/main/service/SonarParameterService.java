package com.pd.server.main.service;

import com.pd.server.main.domain.SonarParameter;
import com.pd.server.main.domain.SonarParameterExample;
import com.pd.server.main.dto.SonarParameterDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SonarParameterMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SonarParameterService {

    @Resource
    private SonarParameterMapper sonarParameterMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SonarParameterExample sonarParameterExample = new SonarParameterExample();
        List<SonarParameter> sonarParameterList = sonarParameterMapper.selectByExample(sonarParameterExample);
        PageInfo<SonarParameter> pageInfo = new PageInfo<>(sonarParameterList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SonarParameterDto> sonarParameterDtoList = CopyUtil.copyList(sonarParameterList, SonarParameterDto.class);
        pageDto.setList(sonarParameterDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SonarParameterDto sonarParameterDto) {
        SonarParameter sonarParameter = CopyUtil.copy(sonarParameterDto, SonarParameter.class);
        if (StringUtils.isEmpty(sonarParameterDto.getId())) {
            this.insert(sonarParameter);
        } else {
            this.update(sonarParameter);
        }
    }

    /**
    * 新增
    */
    private void insert(SonarParameter sonarParameter) {
        sonarParameter.setId(UuidUtil.getShortUuid());
        sonarParameterMapper.insert(sonarParameter);
    }

    /**
    * 更新
    */
    private void update(SonarParameter sonarParameter) {
        sonarParameterMapper.updateByPrimaryKey(sonarParameter);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        sonarParameterMapper.deleteByPrimaryKey(id);
    }

}
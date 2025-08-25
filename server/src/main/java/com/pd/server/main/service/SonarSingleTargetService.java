package com.pd.server.main.service;

import com.pd.server.main.domain.SonarSingleTarget;
import com.pd.server.main.domain.SonarSingleTargetExample;
import com.pd.server.main.dto.SonarSingleTargetDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SonarSingleTargetMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SonarSingleTargetService {

    @Resource
    private SonarSingleTargetMapper sonarSingleTargetMapper;

    /**
    * 列表查询
    */
    public List<SonarSingleTarget> selectByExample(SonarSingleTargetExample example) {
        return sonarSingleTargetMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SonarSingleTargetDto sonarSingleTargetDto) {
        SonarSingleTarget sonarSingleTarget = CopyUtil.copy(sonarSingleTargetDto, SonarSingleTarget.class);
        if (StringUtils.isEmpty(sonarSingleTargetDto.getId())) {
            this.insert(sonarSingleTarget);
        } else {
            this.update(sonarSingleTarget);
        }
    }

    public void save(SonarSingleTarget sonarSingleTarget) {
        sonarSingleTargetMapper.insert(sonarSingleTarget);
    }

    /**
    * 新增
    */
    private void insert(SonarSingleTarget sonarSingleTarget) {
        sonarSingleTarget.setId(UuidUtil.getShortUuid());
        sonarSingleTargetMapper.insert(sonarSingleTarget);
    }

    /**
    * 更新
    */
    private void update(SonarSingleTarget sonarSingleTarget) {
        sonarSingleTargetMapper.updateByPrimaryKey(sonarSingleTarget);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        sonarSingleTargetMapper.deleteByPrimaryKey(id);
    }

}
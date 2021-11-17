package com.pd.server.main.service;

import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.domain.WaterProject;
import com.pd.server.main.domain.WaterProjectExample;
import com.pd.server.main.dto.WaterProjectDto;
import com.pd.server.main.mapper.WaterProjectMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class WaterProjectService {

    @Resource
    private WaterProjectMapper waterProjectMapper;

    /**
    * 列表查询
    */
    public void list(WaterProjectDto pageDto, List<String> xmbhs) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaterProjectExample waterProjectExample = new WaterProjectExample();
        WaterProjectExample.Criteria ca = waterProjectExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            ca.andXmbhEqualTo(pageDto.getXmbh());
        }
        if(!StringUtils.isEmpty(pageDto.getXmmc())){
            ca.andXmmcEqualTo(pageDto.getXmmc());
        }
        if(!CollectionUtils.isEmpty(xmbhs)){
            ca.andXmbhIn(xmbhs);
        }
        waterProjectExample.setOrderByClause(" cjsj desc ");
        List<WaterProject> waterProjectList = waterProjectMapper.selectByExample(waterProjectExample);
        PageInfo<WaterProject> pageInfo = new PageInfo<>(waterProjectList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterProjectDto> waterProjectDtoList = CopyUtil.copyList(waterProjectList, WaterProjectDto.class);
        pageDto.setList(waterProjectDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterProjectDto waterProjectDto) {
        WaterProject waterProject = CopyUtil.copy(waterProjectDto, WaterProject.class);
        if (StringUtils.isEmpty(waterProjectDto.getId())) {
            this.insert(waterProject);
        } else {
            this.update(waterProject);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterProject waterProject) {
        Date now = new Date();
        WaterProjectExample example = new WaterProjectExample();
        WaterProjectExample.Criteria ca = example.createCriteria();
        ca.andXmbhEqualTo(waterProject.getXmbh());
        if(!CollectionUtils.isEmpty(waterProjectMapper.selectByExample(example))){
            throw  new BusinessException(BusinessExceptionCode.PROJECT_CODE_EXIST);
        }
        waterProject.setId(UuidUtil.getShortUuid());
        waterProjectMapper.insert(waterProject);
    }

    /**
    * 更新
    */
    private void update(WaterProject waterProject) {
        waterProjectMapper.updateByPrimaryKey(waterProject);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterProjectMapper.deleteByPrimaryKey(id);
    }

}
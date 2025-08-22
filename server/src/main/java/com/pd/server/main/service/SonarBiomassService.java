package com.pd.server.main.service;

import com.pd.server.main.domain.SonarBiomass;
import com.pd.server.main.domain.SonarBiomassExample;
import com.pd.server.main.dto.SonarBiomassDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SonarBiomassMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SonarBiomassService {

    @Resource
    private SonarBiomassMapper sonarBiomassMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SonarBiomassExample sonarBiomassExample = new SonarBiomassExample();
        List<SonarBiomass> sonarBiomassList = sonarBiomassMapper.selectByExample(sonarBiomassExample);
        PageInfo<SonarBiomass> pageInfo = new PageInfo<>(sonarBiomassList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SonarBiomassDto> sonarBiomassDtoList = CopyUtil.copyList(sonarBiomassList, SonarBiomassDto.class);
        pageDto.setList(sonarBiomassDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SonarBiomassDto sonarBiomassDto) {
        SonarBiomass sonarBiomass = CopyUtil.copy(sonarBiomassDto, SonarBiomass.class);
        if (StringUtils.isEmpty(sonarBiomassDto.getId())) {
            this.insert(sonarBiomass);
        } else {
            this.update(sonarBiomass);
        }
    }

    /**
    * 新增
    */
    private void insert(SonarBiomass sonarBiomass) {
        sonarBiomass.setId(UuidUtil.getShortUuid());
        sonarBiomassMapper.insert(sonarBiomass);
    }

    /**
    * 更新
    */
    private void update(SonarBiomass sonarBiomass) {
        sonarBiomassMapper.updateByPrimaryKey(sonarBiomass);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        sonarBiomassMapper.deleteByPrimaryKey(id);
    }

}
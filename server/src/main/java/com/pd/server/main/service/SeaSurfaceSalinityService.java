package com.pd.server.main.service;

import com.pd.server.main.domain.SeaSurfaceSalinity;
import com.pd.server.main.domain.SeaSurfaceSalinityExample;
import com.pd.server.main.dto.SeaSurfaceSalinityDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SeaSurfaceSalinityMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SeaSurfaceSalinityService {

    @Resource
    private SeaSurfaceSalinityMapper seaSurfaceSalinityMapper;


    public SeaSurfaceSalinity selectLastOne() {
        return seaSurfaceSalinityMapper.selectLastOne();
    }

    /**
    * 列表查询
    */
    public List<SeaSurfaceSalinity> selectByExample(SeaSurfaceSalinityExample example) {
        return seaSurfaceSalinityMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SeaSurfaceSalinityDto seaSurfaceSalinityDto) {
        SeaSurfaceSalinity seaSurfaceSalinity = CopyUtil.copy(seaSurfaceSalinityDto, SeaSurfaceSalinity.class);
        if (StringUtils.isEmpty(seaSurfaceSalinityDto.getId())) {
            this.insert(seaSurfaceSalinity);
        } else {
            this.update(seaSurfaceSalinity);
        }
    }

    /**
    * 新增
    */
    private void insert(SeaSurfaceSalinity seaSurfaceSalinity) {
        seaSurfaceSalinity.setId(UuidUtil.getShortUuid());
        seaSurfaceSalinityMapper.insert(seaSurfaceSalinity);
    }

    /**
    * 更新
    */
    private void update(SeaSurfaceSalinity seaSurfaceSalinity) {
        seaSurfaceSalinityMapper.updateByPrimaryKey(seaSurfaceSalinity);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        seaSurfaceSalinityMapper.deleteByPrimaryKey(id);
    }

}
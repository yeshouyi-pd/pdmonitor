package com.pd.server.main.service;

import com.pd.server.main.domain.FinlessPorpoiseTraverse;
import com.pd.server.main.domain.FinlessPorpoiseTraverseExample;
import com.pd.server.main.dto.FinlessPorpoiseTraverseDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.FinlessPorpoiseTraverseMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FinlessPorpoiseTraverseService {

    @Resource
    private FinlessPorpoiseTraverseMapper finlessPorpoiseTraverseMapper;

    /**
    * 列表查询
    */
    public List<FinlessPorpoiseTraverse> selectByExample(FinlessPorpoiseTraverseExample example) {
        return finlessPorpoiseTraverseMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(FinlessPorpoiseTraverseDto finlessPorpoiseTraverseDto) {
        FinlessPorpoiseTraverse finlessPorpoiseTraverse = CopyUtil.copy(finlessPorpoiseTraverseDto, FinlessPorpoiseTraverse.class);
        if (StringUtils.isEmpty(finlessPorpoiseTraverseDto.getId())) {
            this.insert(finlessPorpoiseTraverse);
        } else {
            this.update(finlessPorpoiseTraverse);
        }
    }

    /**
    * 新增
    */
    private void insert(FinlessPorpoiseTraverse finlessPorpoiseTraverse) {
        finlessPorpoiseTraverse.setId(UuidUtil.getShortUuid());
        finlessPorpoiseTraverseMapper.insert(finlessPorpoiseTraverse);
    }

    /**
    * 更新
    */
    private void update(FinlessPorpoiseTraverse finlessPorpoiseTraverse) {
        finlessPorpoiseTraverseMapper.updateByPrimaryKey(finlessPorpoiseTraverse);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        finlessPorpoiseTraverseMapper.deleteByPrimaryKey(id);
    }

}
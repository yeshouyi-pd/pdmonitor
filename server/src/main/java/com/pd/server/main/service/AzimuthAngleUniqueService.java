package com.pd.server.main.service;

import com.pd.server.main.domain.AzimuthAngleUnique;
import com.pd.server.main.domain.AzimuthAngleUniqueExample;
import com.pd.server.main.dto.AzimuthAngleUniqueDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AzimuthAngleUniqueMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AzimuthAngleUniqueService {

    @Resource
    private AzimuthAngleUniqueMapper azimuthAngleUniqueMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AzimuthAngleUniqueExample azimuthAngleUniqueExample = new AzimuthAngleUniqueExample();
        List<AzimuthAngleUnique> azimuthAngleUniqueList = azimuthAngleUniqueMapper.selectByExample(azimuthAngleUniqueExample);
        PageInfo<AzimuthAngleUnique> pageInfo = new PageInfo<>(azimuthAngleUniqueList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AzimuthAngleUniqueDto> azimuthAngleUniqueDtoList = CopyUtil.copyList(azimuthAngleUniqueList, AzimuthAngleUniqueDto.class);
        pageDto.setList(azimuthAngleUniqueDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AzimuthAngleUniqueDto azimuthAngleUniqueDto) {
        AzimuthAngleUnique azimuthAngleUnique = CopyUtil.copy(azimuthAngleUniqueDto, AzimuthAngleUnique.class);
        if (StringUtils.isEmpty(azimuthAngleUniqueDto.getId())) {
            this.insert(azimuthAngleUnique);
        } else {
            this.update(azimuthAngleUnique);
        }
    }

    /**
    * 新增
    */
    private void insert(AzimuthAngleUnique azimuthAngleUnique) {
        azimuthAngleUnique.setId(UuidUtil.getShortUuid());
        azimuthAngleUniqueMapper.insert(azimuthAngleUnique);
    }

    /**
    * 更新
    */
    private void update(AzimuthAngleUnique azimuthAngleUnique) {
        azimuthAngleUniqueMapper.updateByPrimaryKey(azimuthAngleUnique);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        //azimuthAngleUniqueMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除
     */
    public void deleteByExample(AzimuthAngleUniqueExample example) {
        azimuthAngleUniqueMapper.deleteByExample(example);
    }

}
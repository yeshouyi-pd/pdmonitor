package com.pd.server.main.service;

import com.pd.server.main.domain.AppCodeType;
import com.pd.server.main.domain.AppCodeTypeExample;
import com.pd.server.main.dto.AppCodeTypeDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AppCodeTypeMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class AppCodeTypeService {

    @Resource
    private AppCodeTypeMapper appCodeTypeMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppCodeTypeExample appCodeTypeExample = new AppCodeTypeExample();
        AppCodeTypeExample.Criteria criteria = appCodeTypeExample.createCriteria();
        AppCodeType appCodeType = CopyUtil.copy(pageDto, AppCodeType.class);
        if(org.apache.commons.lang.StringUtils.isNotBlank(appCodeType.getTypeName())){
            criteria.andTypeNameLike("%" + appCodeType.getTypeName().trim() + "%");
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(appCodeType.getTypeValue())){
            criteria.andTypeValueEqualTo(appCodeType.getTypeValue().trim());
        }
        
        // 如果有查询参数，可以在这里添加查询条件
        // 目前先实现基础的分页查询
        
        appCodeTypeExample.setOrderByClause("create_time asc");
        List<AppCodeType> appCodeTypeList = appCodeTypeMapper.selectByExample(appCodeTypeExample);
        PageInfo<AppCodeType> pageInfo = new PageInfo<>(appCodeTypeList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppCodeTypeDto> appCodeTypeDtoList = CopyUtil.copyList(appCodeTypeList, AppCodeTypeDto.class);
        pageDto.setList(appCodeTypeDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppCodeTypeDto appCodeTypeDto) {
        AppCodeType appCodeType = CopyUtil.copy(appCodeTypeDto, AppCodeType.class);
        if (StringUtils.isEmpty(appCodeTypeDto.getId())) {
            // 新增时检查类型代码是否已存在
            this.checkTypeValueExists(appCodeTypeDto.getTypeValue());
            this.insert(appCodeType);
        } else {
            this.update(appCodeType);
        }
    }

    /**
     * 检查类型代码是否已存在
     */
    private void checkTypeValueExists(String typeValue) {
        AppCodeTypeExample example = new AppCodeTypeExample();
        AppCodeTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeValueEqualTo(typeValue);
        
        List<AppCodeType> existingList = appCodeTypeMapper.selectByExample(example);
        if (!existingList.isEmpty()) {
            throw new RuntimeException("类型代码已存在，请使用其他代码");
        }
    }

    /**
    * 新增
    */
    private void insert(AppCodeType appCodeType) {
        Date now = new Date();
        appCodeType.setId(UuidUtil.getShortUuid());
        appCodeType.setCreateTime(now);
        appCodeTypeMapper.insert(appCodeType);
    }

    /**
    * 更新
    */
    private void update(AppCodeType appCodeType) {
        appCodeTypeMapper.updateByPrimaryKey(appCodeType);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appCodeTypeMapper.deleteByPrimaryKey(id);
    }

    public List<AppCodeType> selectByExample() {
        return appCodeTypeMapper.selectByExample(null);
    }
}
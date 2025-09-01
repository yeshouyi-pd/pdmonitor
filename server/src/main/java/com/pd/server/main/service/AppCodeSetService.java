package com.pd.server.main.service;

import com.pd.server.main.domain.AppCodeSet;
import com.pd.server.main.domain.AppCodeSetExample;
import com.pd.server.main.dto.AppCodeSetDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AppCodeSetMapper;
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
public class AppCodeSetService {

    @Resource
    private AppCodeSetMapper appCodeSetMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppCodeSetExample appCodeSetExample = new AppCodeSetExample();
        AppCodeSet appCodeSet = CopyUtil.copy(pageDto, AppCodeSet.class);
        if(org.apache.commons.lang.StringUtils.isNotBlank(appCodeSet.getCodeName())){
            appCodeSetExample.createCriteria().andCodeNameLike("%" + appCodeSet.getCodeName().trim() + "%");
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(appCodeSet.getCodeValue())){
            appCodeSetExample.createCriteria().andCodeValueEqualTo(appCodeSet.getCodeValue().trim());
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(appCodeSet.getTypeValue())){
            appCodeSetExample.createCriteria().andTypeValueEqualTo(appCodeSet.getTypeValue().trim());
        }

        // 如果有查询参数，可以在这里添加查询条件
        // 目前先实现基础的分页查询
        
        appCodeSetExample.setOrderByClause("create_time asc");
        List<AppCodeSet> appCodeSetList = appCodeSetMapper.selectByExample(appCodeSetExample);
        PageInfo<AppCodeSet> pageInfo = new PageInfo<>(appCodeSetList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppCodeSetDto> appCodeSetDtoList = CopyUtil.copyList(appCodeSetList, AppCodeSetDto.class);
        pageDto.setList(appCodeSetDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppCodeSetDto appCodeSetDto) {
        AppCodeSet appCodeSet = CopyUtil.copy(appCodeSetDto, AppCodeSet.class);
        if (StringUtils.isEmpty(appCodeSetDto.getId())) {
            // 新增时检查typeValue和codeValue组合是否已存在
            this.checkTypeValueAndCodeValueExists(appCodeSetDto.getTypeValue(), appCodeSetDto.getCodeValue());
            this.insert(appCodeSet);
        } else {
            this.update(appCodeSet);
        }
    }

    /**
     * 检查typeValue和codeValue组合是否已存在
     */
    private void checkTypeValueAndCodeValueExists(String typeValue, String codeValue) {
        AppCodeSetExample example = new AppCodeSetExample();
        AppCodeSetExample.Criteria criteria = example.createCriteria();
        criteria.andTypeValueEqualTo(typeValue);
        criteria.andCodeValueEqualTo(codeValue);
        
        List<AppCodeSet> existingList = appCodeSetMapper.selectByExample(example);
        if (!existingList.isEmpty()) {
            throw new RuntimeException("该代码类型下已存在相同的代码值，请使用其他代码值");
        }
    }

    /**
    * 新增
    */
    private void insert(AppCodeSet appCodeSet) {
        Date now = new Date();
        appCodeSet.setId(UuidUtil.getShortUuid());
        appCodeSet.setCreateTime(now);
        appCodeSetMapper.insert(appCodeSet);
    }

    /**
    * 更新
    */
    private void update(AppCodeSet appCodeSet) {
        appCodeSetMapper.updateByPrimaryKey(appCodeSet);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appCodeSetMapper.deleteByPrimaryKey(id);
    }

}
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
            this.insert(appCodeType);
        } else {
            this.update(appCodeType);
        }
    }

    /**
    * 新增
    */
    private void insert(AppCodeType appCodeType) {
                Date now = new Date();
        appCodeType.setId(UuidUtil.getShortUuid());
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

}
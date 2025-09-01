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
            this.insert(appCodeSet);
        } else {
            this.update(appCodeSet);
        }
    }

    /**
    * 新增
    */
    private void insert(AppCodeSet appCodeSet) {
                Date now = new Date();
        appCodeSet.setId(UuidUtil.getShortUuid());
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
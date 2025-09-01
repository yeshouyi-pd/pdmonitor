package com.pd.server.main.service;

import com.pd.server.main.domain.AppVersion;
import com.pd.server.main.domain.AppVersionExample;
import com.pd.server.main.dto.AppVersionDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AppVersionMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppVersionService {

    @Resource
    private AppVersionMapper appVersionMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppVersionExample appVersionExample = new AppVersionExample();
        List<AppVersion> appVersionList = appVersionMapper.selectByExample(appVersionExample);
        PageInfo<AppVersion> pageInfo = new PageInfo<>(appVersionList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppVersionDto> appVersionDtoList = CopyUtil.copyList(appVersionList, AppVersionDto.class);
        pageDto.setList(appVersionDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppVersionDto appVersionDto) {
        AppVersion appVersion = CopyUtil.copy(appVersionDto, AppVersion.class);
        if (StringUtils.isEmpty(appVersionDto.getId())) {
            this.insert(appVersion);
        } else {
            this.update(appVersion);
        }
    }

    /**
    * 新增
    */
    private void insert(AppVersion appVersion) {
        appVersion.setId(UuidUtil.getShortUuid());
        appVersionMapper.insert(appVersion);
    }

    /**
    * 更新
    */
    private void update(AppVersion appVersion) {
        appVersionMapper.updateByPrimaryKey(appVersion);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appVersionMapper.deleteByPrimaryKey(id);
    }

}
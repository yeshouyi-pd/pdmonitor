package com.pd.server.main.service;

import com.pd.server.main.domain.AppMonitorInfo;
import com.pd.server.main.domain.AppMonitorInfoExample;
import com.pd.server.main.dto.AppMonitorInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AppMonitorInfoMapper;
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
public class AppMonitorInfoService {

    @Resource
    private AppMonitorInfoMapper appMonitorInfoMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppMonitorInfoExample appMonitorInfoExample = new AppMonitorInfoExample();
        List<AppMonitorInfo> appMonitorInfoList = appMonitorInfoMapper.selectByExample(appMonitorInfoExample);
        PageInfo<AppMonitorInfo> pageInfo = new PageInfo<>(appMonitorInfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppMonitorInfoDto> appMonitorInfoDtoList = CopyUtil.copyList(appMonitorInfoList, AppMonitorInfoDto.class);
        pageDto.setList(appMonitorInfoDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppMonitorInfoDto appMonitorInfoDto) {
        AppMonitorInfo appMonitorInfo = CopyUtil.copy(appMonitorInfoDto, AppMonitorInfo.class);
        if (StringUtils.isEmpty(appMonitorInfoDto.getId())) {
            this.insert(appMonitorInfo);
        } else {
            this.update(appMonitorInfo);
        }
    }

    /**
    * 新增
    */
    private void insert(AppMonitorInfo appMonitorInfo) {
                Date now = new Date();
        appMonitorInfo.setId(UuidUtil.getShortUuid());
        appMonitorInfoMapper.insert(appMonitorInfo);
    }

    /**
    * 更新
    */
    private void update(AppMonitorInfo appMonitorInfo) {
        appMonitorInfoMapper.updateByPrimaryKey(appMonitorInfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appMonitorInfoMapper.deleteByPrimaryKey(id);
    }

}
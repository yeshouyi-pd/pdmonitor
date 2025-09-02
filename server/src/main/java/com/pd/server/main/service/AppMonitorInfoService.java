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
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

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
    public PageDto list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppMonitorInfoExample appMonitorInfoExample = new AppMonitorInfoExample();
        AppMonitorInfoExample.Criteria ca = appMonitorInfoExample.createCriteria();
        AppMonitorInfoDto appMonitorInfoDto = CopyUtil.copy(pageDto, AppMonitorInfoDto.class);
        if(StringUtils.isNotBlank(appMonitorInfoDto.getDeptcode())){
            ca.andDeptcodeEqualTo(appMonitorInfoDto.getDeptcode());
        }
        if(StringUtils.isNotBlank(appMonitorInfoDto.getGczxm())){
            ca.andDeptcodeEqualTo(appMonitorInfoDto.getGczxm());
        }
        appMonitorInfoExample.setOrderByClause(" ksgcsj desc");
        List<AppMonitorInfo> appMonitorInfoList = appMonitorInfoMapper.selectByExample(appMonitorInfoExample);
        PageInfo<AppMonitorInfo> pageInfo = new PageInfo<>(appMonitorInfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppMonitorInfoDto> appMonitorInfoDtoList = CopyUtil.copyList(appMonitorInfoList, AppMonitorInfoDto.class);
        pageDto.setList(appMonitorInfoDtoList);
        return pageDto;
    }

    public List<AppMonitorInfo> selectByExample( AppMonitorInfoDto appMonitorInfoDto) {
        AppMonitorInfoExample appMonitorInfoExample = new AppMonitorInfoExample();
        AppMonitorInfoExample.Criteria ca = appMonitorInfoExample.createCriteria();
        ca.andDeptcodeEqualTo(appMonitorInfoDto.getDeptcode());
        appMonitorInfoExample.setOrderByClause(" ksgcsj desc");
        return appMonitorInfoMapper.selectByExample(appMonitorInfoExample);

    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppMonitorInfoDto appMonitorInfoDto) {
        AppMonitorInfo appMonitorInfo = CopyUtil.copy(appMonitorInfoDto, AppMonitorInfo.class);
        try {
            appMonitorInfo.setScsj(new Date());
            this.insert(appMonitorInfo);
        } catch (Exception e) {
            appMonitorInfo.setGxsj(new Date());
            this.update(appMonitorInfo);
        }
    }

    /**
    * 新增
    */
    private void insert(AppMonitorInfo appMonitorInfo) {
                Date now = new Date();
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

    public AppMonitorInfo findById(String id) {
        return appMonitorInfoMapper.selectByPrimaryKey(id);
    }
}
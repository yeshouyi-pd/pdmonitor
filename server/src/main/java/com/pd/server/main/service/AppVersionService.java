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
        // 设置一个初始版本号，从1开始
        appVersion.setId(1L);
        appVersionMapper.insert(appVersion);
    }

    /**
    * 更新
    */
    private void update(AppVersion appVersion) {
        AppVersionExample example = new AppVersionExample();
        example.createCriteria().andIdEqualTo(appVersion.getId());
        appVersionMapper.updateByExampleSelective(appVersion, example);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appVersionMapper.deleteByPrimaryKey(Long.valueOf(id));
    }

    public  List<AppVersion> selectByExample(){
       return appVersionMapper.selectByExample(null);
    }

    /**
     * 原子性增加版本号
     */
    public void incrementVersion() {
        try {
            List<AppVersion> versions = appVersionMapper.selectByExample(null);
            if (!versions.isEmpty()) {
                AppVersion version = versions.get(0);
                Long currentId = version.getId();
                
                // 创建更新条件
                AppVersionExample example = new AppVersionExample();
                example.createCriteria().andIdEqualTo(currentId);
                
                // 设置新的版本号
                version.setId(currentId + 1);
                
                // 执行原子性更新
                appVersionMapper.updateByExampleSelective(version, example);
            }
        } catch (Exception e) {
            // 版本更新失败不影响主业务，只记录日志
            System.err.println("版本更新失败: " + e.getMessage());
        }
    }
}
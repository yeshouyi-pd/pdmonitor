package com.pd.server.main.service;

import com.pd.server.main.domain.AppMonitorDiscovery;
import com.pd.server.main.domain.AppMonitorDiscoveryExample;
import com.pd.server.main.dto.AppMonitorDiscoveryDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AppMonitorDiscoveryMapper;
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
public class AppMonitorDiscoveryService {

    @Resource
    private AppMonitorDiscoveryMapper appMonitorDiscoveryMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppMonitorDiscoveryExample appMonitorDiscoveryExample = new AppMonitorDiscoveryExample();
        List<AppMonitorDiscovery> appMonitorDiscoveryList = appMonitorDiscoveryMapper.selectByExample(appMonitorDiscoveryExample);
        PageInfo<AppMonitorDiscovery> pageInfo = new PageInfo<>(appMonitorDiscoveryList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppMonitorDiscoveryDto> appMonitorDiscoveryDtoList = CopyUtil.copyList(appMonitorDiscoveryList, AppMonitorDiscoveryDto.class);
        pageDto.setList(appMonitorDiscoveryDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        AppMonitorDiscovery appMonitorDiscovery = CopyUtil.copy(appMonitorDiscoveryDto, AppMonitorDiscovery.class);
        if (StringUtils.isEmpty(appMonitorDiscoveryDto.getId())) {
            this.insert(appMonitorDiscovery);
        } else {
            this.update(appMonitorDiscovery);
        }
    }

    /**
    * 新增
    */
    private void insert(AppMonitorDiscovery appMonitorDiscovery) {
                Date now = new Date();
        appMonitorDiscovery.setId(UuidUtil.getShortUuid());
        appMonitorDiscoveryMapper.insert(appMonitorDiscovery);
    }

    /**
    * 更新
    */
    private void update(AppMonitorDiscovery appMonitorDiscovery) {
        appMonitorDiscoveryMapper.updateByPrimaryKey(appMonitorDiscovery);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appMonitorDiscoveryMapper.deleteByPrimaryKey(id);
    }

}
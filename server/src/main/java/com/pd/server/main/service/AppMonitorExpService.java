package com.pd.server.main.service;

import com.pd.server.main.domain.AppMonitorExp;
import com.pd.server.main.domain.AppMonitorExpExample;
import com.pd.server.main.dto.AppMonitorExpDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AppMonitorExpMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppMonitorExpService {

    @Resource
    private AppMonitorExpMapper appMonitorExpMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppMonitorExpExample appMonitorExpExample = new AppMonitorExpExample();
        List<AppMonitorExp> appMonitorExpList = appMonitorExpMapper.selectByExample(appMonitorExpExample);
        PageInfo<AppMonitorExp> pageInfo = new PageInfo<>(appMonitorExpList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppMonitorExpDto> appMonitorExpDtoList = CopyUtil.copyList(appMonitorExpList, AppMonitorExpDto.class);
        pageDto.setList(appMonitorExpDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppMonitorExpDto appMonitorExpDto) {
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        if (StringUtils.isEmpty(appMonitorExpDto.getId())) {
            this.insert(appMonitorExp);
        } else {
            this.update(appMonitorExp);
        }
    }

    /**
    * 新增
    */
    private void insert(AppMonitorExp appMonitorExp) {
        appMonitorExp.setId(UuidUtil.getShortUuid());
        appMonitorExpMapper.insert(appMonitorExp);
    }

    /**
    * 更新
    */
    private void update(AppMonitorExp appMonitorExp) {
        appMonitorExpMapper.updateByPrimaryKey(appMonitorExp);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appMonitorExpMapper.deleteByPrimaryKey(id);
    }

}
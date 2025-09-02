package com.pd.server.main.service;

import com.pd.server.main.domain.AppMonitorManualEntrye;
import com.pd.server.main.domain.AppMonitorManualEntryeExample;
import com.pd.server.main.dto.AppMonitorManualEntryeDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AppMonitorManualEntryeMapper;
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
public class AppMonitorManualEntryeService {

    @Resource
    private AppMonitorManualEntryeMapper appMonitorManualEntryeMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppMonitorManualEntryeExample appMonitorManualEntryeExample = new AppMonitorManualEntryeExample();
        List<AppMonitorManualEntrye> appMonitorManualEntryeList = appMonitorManualEntryeMapper.selectByExample(appMonitorManualEntryeExample);
        PageInfo<AppMonitorManualEntrye> pageInfo = new PageInfo<>(appMonitorManualEntryeList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppMonitorManualEntryeDto> appMonitorManualEntryeDtoList = CopyUtil.copyList(appMonitorManualEntryeList, AppMonitorManualEntryeDto.class);
        pageDto.setList(appMonitorManualEntryeDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppMonitorManualEntryeDto appMonitorManualEntryeDto) {
        AppMonitorManualEntrye appMonitorManualEntrye = CopyUtil.copy(appMonitorManualEntryeDto, AppMonitorManualEntrye.class);
          try {
              appMonitorManualEntrye.setScsj(new Date());
              this.insert(appMonitorManualEntrye);
          } catch (Exception e) {
              appMonitorManualEntrye.setGxsj(new Date());
              this.update(appMonitorManualEntrye);
          }


    }

    /**
    * 新增
    */
    private void insert(AppMonitorManualEntrye appMonitorManualEntrye) {
                Date now = new Date();
        appMonitorManualEntrye.setId(UuidUtil.getShortUuid());
        appMonitorManualEntryeMapper.insert(appMonitorManualEntrye);
    }

    /**
    * 更新
    */
    private void update(AppMonitorManualEntrye appMonitorManualEntrye) {
        appMonitorManualEntryeMapper.updateByPrimaryKey(appMonitorManualEntrye);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appMonitorManualEntryeMapper.deleteByPrimaryKey(id);
    }

    public List<AppMonitorManualEntrye> selectByExample(AppMonitorManualEntryeDto appMonitorManualEntryeDto) {
        AppMonitorManualEntryeExample appMonitorManualEntryeExample = new AppMonitorManualEntryeExample();
        AppMonitorManualEntryeExample.Criteria criteria = appMonitorManualEntryeExample.createCriteria();
        criteria.andDeptcodeEqualTo(appMonitorManualEntryeDto.getDeptcode());
        criteria.andGczxmEqualTo(appMonitorManualEntryeDto.getGczxm());
        return appMonitorManualEntryeMapper.selectByExample(appMonitorManualEntryeExample);

    }

    public List<AppMonitorManualEntrye> selectByMid(String mid) {
        AppMonitorManualEntryeExample appMonitorManualEntryeExample = new AppMonitorManualEntryeExample();
        AppMonitorManualEntryeExample.Criteria criteria = appMonitorManualEntryeExample.createCriteria();
        criteria.andMidEqualTo(mid);
        return appMonitorManualEntryeMapper.selectByExample(appMonitorManualEntryeExample);
    }
}
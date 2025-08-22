package com.pd.server.main.service;

import com.pd.server.main.domain.SonarAlarm;
import com.pd.server.main.domain.SonarAlarmExample;
import com.pd.server.main.dto.SonarAlarmDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SonarAlarmMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SonarAlarmService {

    @Resource
    private SonarAlarmMapper sonarAlarmMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SonarAlarmExample sonarAlarmExample = new SonarAlarmExample();
        List<SonarAlarm> sonarAlarmList = sonarAlarmMapper.selectByExample(sonarAlarmExample);
        PageInfo<SonarAlarm> pageInfo = new PageInfo<>(sonarAlarmList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SonarAlarmDto> sonarAlarmDtoList = CopyUtil.copyList(sonarAlarmList, SonarAlarmDto.class);
        pageDto.setList(sonarAlarmDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SonarAlarmDto sonarAlarmDto) {
        SonarAlarm sonarAlarm = CopyUtil.copy(sonarAlarmDto, SonarAlarm.class);
        if (StringUtils.isEmpty(sonarAlarmDto.getId())) {
            this.insert(sonarAlarm);
        } else {
            this.update(sonarAlarm);
        }
    }

    /**
    * 新增
    */
    private void insert(SonarAlarm sonarAlarm) {
        sonarAlarm.setId(UuidUtil.getShortUuid());
        sonarAlarmMapper.insert(sonarAlarm);
    }

    /**
    * 更新
    */
    private void update(SonarAlarm sonarAlarm) {
        sonarAlarmMapper.updateByPrimaryKey(sonarAlarm);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        sonarAlarmMapper.deleteByPrimaryKey(id);
    }

}
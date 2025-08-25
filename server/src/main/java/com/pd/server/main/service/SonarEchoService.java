package com.pd.server.main.service;

import com.pd.server.main.domain.SonarEcho;
import com.pd.server.main.domain.SonarEchoExample;
import com.pd.server.main.dto.SonarEchoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SonarEchoMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SonarEchoService {

    @Resource
    private SonarEchoMapper sonarEchoMapper;

    /**
    * 列表查询
    */
    public List<SonarEcho> selectByExample(SonarEchoExample example) {
        return sonarEchoMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SonarEchoDto sonarEchoDto) {
        SonarEcho sonarEcho = CopyUtil.copy(sonarEchoDto, SonarEcho.class);
        if (StringUtils.isEmpty(sonarEchoDto.getId())) {
            this.insert(sonarEcho);
        } else {
            this.update(sonarEcho);
        }
    }

    /**
    * 新增
    */
    private void insert(SonarEcho sonarEcho) {
        sonarEcho.setId(UuidUtil.getShortUuid());
        sonarEchoMapper.insert(sonarEcho);
    }

    /**
    * 更新
    */
    private void update(SonarEcho sonarEcho) {
        sonarEchoMapper.updateByPrimaryKey(sonarEcho);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        sonarEchoMapper.deleteByPrimaryKey(id);
    }

}
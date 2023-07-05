package com.pd.server.main.service;

import com.pd.server.main.domain.DeviceStateLog;
import com.pd.server.main.domain.DeviceStateLogExample;
import com.pd.server.main.dto.DeviceStateLogDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.DeviceStateLogMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceStateLogService {

    @Resource
    private DeviceStateLogMapper deviceStateLogMapper;

    /**
    * 列表查询
    */
    public List<DeviceStateLog> selectByExample(DeviceStateLogExample example){
        return deviceStateLogMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DeviceStateLogDto deviceStateLogDto) {
        DeviceStateLog deviceStateLog = CopyUtil.copy(deviceStateLogDto, DeviceStateLog.class);
        if (StringUtils.isEmpty(deviceStateLogDto.getId())) {
            this.insert(deviceStateLog);
        } else {
            this.update(deviceStateLog);
        }
    }

    /**
    * 新增
    */
    private void insert(DeviceStateLog deviceStateLog) {
        deviceStateLog.setId(UuidUtil.getShortUuid());
        deviceStateLogMapper.insert(deviceStateLog);
    }

    /**
    * 更新
    */
    private void update(DeviceStateLog deviceStateLog) {
        deviceStateLogMapper.updateByPrimaryKey(deviceStateLog);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        deviceStateLogMapper.deleteByPrimaryKey(id);
    }

}
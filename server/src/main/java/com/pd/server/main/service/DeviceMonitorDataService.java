package com.pd.server.main.service;

import com.pd.server.main.domain.DeviceMonitorData;
import com.pd.server.main.domain.DeviceMonitorDataExample;
import com.pd.server.main.dto.DeviceMonitorDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.DeviceMonitorDataMapper;
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
public class DeviceMonitorDataService {

    @Resource
    private DeviceMonitorDataMapper deviceMonitorDataMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DeviceMonitorDataExample deviceMonitorDataExample = new DeviceMonitorDataExample();
        List<DeviceMonitorData> deviceMonitorDataList = deviceMonitorDataMapper.selectByExample(deviceMonitorDataExample);
        PageInfo<DeviceMonitorData> pageInfo = new PageInfo<>(deviceMonitorDataList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DeviceMonitorDataDto> deviceMonitorDataDtoList = CopyUtil.copyList(deviceMonitorDataList, DeviceMonitorDataDto.class);
        pageDto.setList(deviceMonitorDataDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DeviceMonitorDataDto deviceMonitorDataDto) {
        DeviceMonitorData deviceMonitorData = CopyUtil.copy(deviceMonitorDataDto, DeviceMonitorData.class);
        if (StringUtils.isEmpty(deviceMonitorDataDto.getId())) {
            this.insert(deviceMonitorData);
        } else {
            this.update(deviceMonitorData);
        }
    }

    /**
    * 新增
    */
    private void insert(DeviceMonitorData deviceMonitorData) {
                Date now = new Date();
        deviceMonitorData.setId(UuidUtil.getShortUuid());
        deviceMonitorDataMapper.insert(deviceMonitorData);
    }

    /**
    * 更新
    */
    private void update(DeviceMonitorData deviceMonitorData) {
        deviceMonitorDataMapper.updateByPrimaryKey(deviceMonitorData);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        deviceMonitorDataMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.DeviceSchedules;
import com.pd.server.main.domain.DeviceSchedulesExample;
import com.pd.server.main.dto.DeviceSchedulesDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.DeviceSchedulesMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DeviceSchedulesService {

    @Resource
    private DeviceSchedulesMapper deviceSchedulesMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DeviceSchedulesExample deviceSchedulesExample = new DeviceSchedulesExample();
        List<DeviceSchedules> deviceSchedulesList = deviceSchedulesMapper.selectByExample(deviceSchedulesExample);
        PageInfo<DeviceSchedules> pageInfo = new PageInfo<>(deviceSchedulesList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DeviceSchedulesDto> deviceSchedulesDtoList = CopyUtil.copyList(deviceSchedulesList, DeviceSchedulesDto.class);
        pageDto.setList(deviceSchedulesDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(DeviceSchedulesDto deviceSchedulesDto) {
        DeviceSchedules deviceSchedules = CopyUtil.copy(deviceSchedulesDto, DeviceSchedules.class);
        if (StringUtils.isEmpty(deviceSchedulesDto.getId())) {
            deviceSchedules.setIsRunning(0);
            this.insert(deviceSchedules);
        } else {
            this.update(deviceSchedules);
        }
    }


    /**
     * 根据设备ID查询计划列表
     * @param deviceId 设备ID
     * @return 设备计划列表
     */
    public List<DeviceSchedulesDto> getByDeviceId(String deviceId) {
        DeviceSchedulesExample example = new DeviceSchedulesExample();
        DeviceSchedulesExample.Criteria criteria = example.createCriteria();
        criteria.andDeviceIdEqualTo(deviceId);
        example.setOrderByClause("created_at desc");
        List<DeviceSchedules> list = deviceSchedulesMapper.selectByExample(example);
        List<DeviceSchedulesDto> dtoList = CopyUtil.copyList(list, DeviceSchedulesDto.class);
        return dtoList;
    }

    /**
     * 查询所有启用的计划
     * @return 启用的计划列表
     */
    public List<DeviceSchedules> getActiveSchedules() {
        DeviceSchedulesExample example = new DeviceSchedulesExample();
        DeviceSchedulesExample.Criteria criteria = example.createCriteria();
        criteria.andIsActiveEqualTo(1);
        return deviceSchedulesMapper.selectByExample(example);
    }

    /**
     * 更新计划的执行状态
     * @param schedule 计划对象
     */
    public void updateScheduleStatus(DeviceSchedules schedule) {
        deviceSchedulesMapper.updateByPrimaryKeySelective(schedule);
    }

    /**
     * 根据ID查询计划
     * @param id 计划ID
     * @return 计划对象
     */
    public DeviceSchedules getById(Integer id) {
        return deviceSchedulesMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     */
    private void insert(DeviceSchedules deviceSchedules) {
        deviceSchedules.setCreatedAt(new Date());
        deviceSchedulesMapper.insert(deviceSchedules);
    }

    /**
     * 更新
     */
    private void update(DeviceSchedules deviceSchedules) {
        deviceSchedulesMapper.updateByPrimaryKeySelective(deviceSchedules);
    }

    /**
     * 删除
     */
    public void delete(int id) {
        deviceSchedulesMapper.deleteByPrimaryKey(id);
    }

}
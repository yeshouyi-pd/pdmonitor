package com.pd.server.main.service;

import com.pd.server.main.domain.ScheduleExecutions;
import com.pd.server.main.domain.ScheduleExecutionsExample;
import com.pd.server.main.dto.ScheduleExecutionsDto;
import com.pd.server.main.dto.ScheduleExecutionWithScheduleDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.ScheduleExecutionsMapper;
import com.pd.server.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleExecutionsService {

    @Resource
    private ScheduleExecutionsMapper scheduleExecutionsMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ScheduleExecutionsExample scheduleExecutionsExample = new ScheduleExecutionsExample();
        List<ScheduleExecutions> scheduleExecutionsList = scheduleExecutionsMapper.selectByExample(scheduleExecutionsExample);
        PageInfo<ScheduleExecutions> pageInfo = new PageInfo<>(scheduleExecutionsList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ScheduleExecutionsDto> scheduleExecutionsDtoList = CopyUtil.copyList(scheduleExecutionsList, ScheduleExecutionsDto.class);
        pageDto.setList(scheduleExecutionsDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(ScheduleExecutionsDto scheduleExecutionsDto) {
        ScheduleExecutions scheduleExecutions = CopyUtil.copy(scheduleExecutionsDto, ScheduleExecutions.class);
        if (StringUtils.isEmpty(scheduleExecutionsDto.getId())) {
            this.insert(scheduleExecutions);
        } else {
            this.update(scheduleExecutions);
        }
    }

    /**
     * 新增
     */
    private void insert(ScheduleExecutions scheduleExecutions) {
        scheduleExecutions.setCreatedAt(new Date());
        scheduleExecutionsMapper.insert(scheduleExecutions);
    }

    /**
     * 直接插入执行记录（用于定时任务）
     * @param scheduleExecutions 执行记录对象
     * @return 执行记录ID
     */
    public Integer insertExecution(ScheduleExecutions scheduleExecutions) {
        scheduleExecutions.setCreatedAt(new Date());
        scheduleExecutionsMapper.insert(scheduleExecutions);
        return scheduleExecutions.getId();
    }

    /**
     * 根据ID查询执行记录
     * @param id 执行记录ID
     * @return 执行记录对象
     */
    public ScheduleExecutions getById(Integer id) {
        return scheduleExecutionsMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新执行记录
     * @param scheduleExecutions 执行记录对象
     */
    public void updateExecution(ScheduleExecutions scheduleExecutions) {
        scheduleExecutionsMapper.updateByPrimaryKeySelective(scheduleExecutions);
    }

    /**
     * 更新
     */
    private void update(ScheduleExecutions scheduleExecutions) {
        scheduleExecutionsMapper.updateByPrimaryKeySelective(scheduleExecutions);
    }

    /**
     * 删除
     */
    public void delete(int id) {
        scheduleExecutionsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据设备ID查询执行记录，左连接计划表
     * @param deviceId 设备ID
     * @return 执行记录列表（包含计划信息）
     */
    public List<ScheduleExecutionWithScheduleDto> getByDeviceIdWithSchedule(String deviceId) {
        List<ScheduleExecutionWithScheduleDto> list = scheduleExecutionsMapper.selectByDeviceIdWithSchedule(deviceId);
        // 日期字段在 Mapper XML 中已经映射为 String 类型，直接返回
        return list;
    }

    /**
     * 根据设备ID查询执行记录，左连接计划表（分页）
     * @param pageDto 分页对象，包含deviceId、page、size
     */
    public void getByDeviceIdWithSchedule(PageDto<ScheduleExecutionWithScheduleDto> pageDto, String deviceId) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<ScheduleExecutionWithScheduleDto> list = scheduleExecutionsMapper.selectByDeviceIdWithSchedule(deviceId);
        PageInfo<ScheduleExecutionWithScheduleDto> pageInfo = new PageInfo<>(list);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

}
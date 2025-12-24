package com.pd.server.main.service;

import com.pd.server.main.domain.ScheduleExecutions;
import com.pd.server.main.domain.ScheduleExecutionsExample;
import com.pd.server.main.dto.ScheduleExecutionsDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.ScheduleExecutionsMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
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
        scheduleExecutionsMapper.updateByPrimaryKey(scheduleExecutions);
    }

    /**
     * 删除
     */
    public void delete(int id) {
        scheduleExecutionsMapper.deleteByPrimaryKey(id);
    }

}
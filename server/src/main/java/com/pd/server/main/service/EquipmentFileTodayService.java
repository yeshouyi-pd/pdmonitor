package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.EquipmentFileToday;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.mapper.EquipmentFileTodayMapper;
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
public class EquipmentFileTodayService {

    @Resource
    private EquipmentFileTodayMapper equipmentFileTodayMapper;

    public List<KvIntDto> getAlljcsjByDept(EquipmentFileTodayExample example){
        return equipmentFileTodayMapper.getAlljcsjByDept(example);
    }

    public int countTsByExample(EquipmentFileTodayExample example){
        return equipmentFileTodayMapper.countTsByExample(example);
    }

    public  List<WelcomeKvDto> getWarningDate(EquipmentFileTodayExample equipmentFileTodayExample){
        return equipmentFileTodayMapper.getWarningDate(equipmentFileTodayExample);
    }

    public List<AlarmNumbersDto> groupByRq(EquipmentFileTodayExample example){
        return equipmentFileTodayMapper.groupByRq(example);
    }

    public List<AlarmNumbersDto> statisticsAlarmNums(EquipmentFileTodayExample example){
        return equipmentFileTodayMapper.statisticsAlarmNums(example);
    }

    public List<EquipmentFileToday> listAll(EquipmentFileTodayExample equipmentFileTodayExample){
        return equipmentFileTodayMapper.selectByExample(equipmentFileTodayExample);
    }

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileTodayExample equipmentFileTodayExample = new EquipmentFileTodayExample();
        List<EquipmentFileToday> equipmentFileTodayList = equipmentFileTodayMapper.selectByExample(equipmentFileTodayExample);
        PageInfo<EquipmentFileToday> pageInfo = new PageInfo<>(equipmentFileTodayList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileTodayDto> equipmentFileTodayDtoList = CopyUtil.copyList(equipmentFileTodayList, EquipmentFileTodayDto.class);
        pageDto.setList(equipmentFileTodayDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileTodayDto equipmentFileTodayDto) {
        EquipmentFileToday equipmentFileToday = CopyUtil.copy(equipmentFileTodayDto, EquipmentFileToday.class);
        if (StringUtils.isEmpty(equipmentFileTodayDto.getId())) {
            this.insert(equipmentFileToday);
        } else {
            this.update(equipmentFileToday);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileToday equipmentFileToday) {
                Date now = new Date();
        equipmentFileToday.setId(UuidUtil.getShortUuid());
        equipmentFileTodayMapper.insert(equipmentFileToday);
    }

    /**
    * 更新
    */
    public void update(EquipmentFileToday equipmentFileToday) {
        equipmentFileTodayMapper.updateByPrimaryKeySelective(equipmentFileToday);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        equipmentFileTodayMapper.deleteByPrimaryKey(id);
    }

}
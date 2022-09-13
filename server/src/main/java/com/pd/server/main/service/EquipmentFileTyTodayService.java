package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileTyToday;
import com.pd.server.main.domain.EquipmentFileTyTodayExample;
import com.pd.server.main.dto.EquipmentFileTyTodayDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFileTyTodayMapper;
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
public class EquipmentFileTyTodayService {

    @Resource
    private EquipmentFileTyTodayMapper equipmentFileTyTodayMapper;

    public List<String> selectTodayGps(String rq,String sbbh){
        return equipmentFileTyTodayMapper.selectTodayGps(rq,sbbh);
    }

    public List<EquipmentFileTyToday> selectByExample(EquipmentFileTyTodayExample example){
        return equipmentFileTyTodayMapper.selectByExample(example);
    }

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileTyTodayExample equipmentFileTyTodayExample = new EquipmentFileTyTodayExample();
        List<EquipmentFileTyToday> equipmentFileTyTodayList = equipmentFileTyTodayMapper.selectByExample(equipmentFileTyTodayExample);
        PageInfo<EquipmentFileTyToday> pageInfo = new PageInfo<>(equipmentFileTyTodayList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileTyTodayDto> equipmentFileTyTodayDtoList = CopyUtil.copyList(equipmentFileTyTodayList, EquipmentFileTyTodayDto.class);
        pageDto.setList(equipmentFileTyTodayDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileTyTodayDto equipmentFileTyTodayDto) {
        EquipmentFileTyToday equipmentFileTyToday = CopyUtil.copy(equipmentFileTyTodayDto, EquipmentFileTyToday.class);
        if (StringUtils.isEmpty(equipmentFileTyTodayDto.getId())) {
            this.insert(equipmentFileTyToday);
        } else {
            this.update(equipmentFileTyToday);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileTyToday equipmentFileTyToday) {
                Date now = new Date();
        equipmentFileTyToday.setId(UuidUtil.getShortUuid());
        equipmentFileTyTodayMapper.insert(equipmentFileTyToday);
    }

    /**
    * 更新
    */
    private void update(EquipmentFileTyToday equipmentFileTyToday) {
        equipmentFileTyTodayMapper.updateByPrimaryKey(equipmentFileTyToday);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        equipmentFileTyTodayMapper.deleteByPrimaryKey(id);
    }

}
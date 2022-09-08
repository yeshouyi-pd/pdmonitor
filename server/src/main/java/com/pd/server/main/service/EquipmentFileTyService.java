package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileTy;
import com.pd.server.main.domain.EquipmentFileTyExample;
import com.pd.server.main.dto.EquipmentFileTyDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFileTyMapper;
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
public class EquipmentFileTyService {

    @Resource
    private EquipmentFileTyMapper equipmentFileTyMapper;

    public List<String> selectTodayGps(String rq,String sbbh){
        return equipmentFileTyMapper.selectTodayGps(rq,sbbh);
    }

    public List<EquipmentFileTy> lists(EquipmentFileTyExample example){
        return equipmentFileTyMapper.lists(example);
    }

    public List<EquipmentFileTy> listAll(EquipmentFileTyExample example){
        return equipmentFileTyMapper.selectByExample(example);
    }

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileTyExample equipmentFileTyExample = new EquipmentFileTyExample();
        List<EquipmentFileTy> equipmentFileTyList = equipmentFileTyMapper.selectByExample(equipmentFileTyExample);
        PageInfo<EquipmentFileTy> pageInfo = new PageInfo<>(equipmentFileTyList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileTyDto> equipmentFileTyDtoList = CopyUtil.copyList(equipmentFileTyList, EquipmentFileTyDto.class);
        pageDto.setList(equipmentFileTyDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileTyDto equipmentFileTyDto) {
        EquipmentFileTy equipmentFileTy = CopyUtil.copy(equipmentFileTyDto, EquipmentFileTy.class);
        if (StringUtils.isEmpty(equipmentFileTyDto.getId())) {
            this.insert(equipmentFileTy);
        } else {
            this.update(equipmentFileTy);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileTy equipmentFileTy) {
                Date now = new Date();
        equipmentFileTy.setId(UuidUtil.getShortUuid());
        equipmentFileTyMapper.insert(equipmentFileTy);
    }

    /**
    * 更新
    */
    private void update(EquipmentFileTy equipmentFileTy) {
        equipmentFileTyMapper.updateByPrimaryKey(equipmentFileTy);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        equipmentFileTyMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileTWav;
import com.pd.server.main.domain.EquipmentFileTWavExample;
import com.pd.server.main.dto.EquipmentFileTWavDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFileTWavMapper;
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
public class EquipmentFileTWavService {

    @Resource
    private EquipmentFileTWavMapper equipmentFileTWavMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileTWavExample equipmentFileTWavExample = new EquipmentFileTWavExample();
        List<EquipmentFileTWav> equipmentFileTWavList = equipmentFileTWavMapper.selectByExample(equipmentFileTWavExample);
        PageInfo<EquipmentFileTWav> pageInfo = new PageInfo<>(equipmentFileTWavList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileTWavDto> equipmentFileTWavDtoList = CopyUtil.copyList(equipmentFileTWavList, EquipmentFileTWavDto.class);
        pageDto.setList(equipmentFileTWavDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileTWavDto equipmentFileTWavDto) {
        EquipmentFileTWav equipmentFileTWav = CopyUtil.copy(equipmentFileTWavDto, EquipmentFileTWav.class);
        if (StringUtils.isEmpty(equipmentFileTWavDto.getId())) {
            this.insert(equipmentFileTWav);
        } else {
            this.update(equipmentFileTWav);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileTWav equipmentFileTWav) {
        equipmentFileTWavMapper.insert(equipmentFileTWav);
    }

    /**
    * 更新
    */
    private void update(EquipmentFileTWav equipmentFileTWav) {
        equipmentFileTWavMapper.updateByPrimaryKey(equipmentFileTWav);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFileTWavMapper.deleteByPrimaryKey(id);
    }

    public List<EquipmentFileTWav> selectByExample(EquipmentFileTWavExample example){
        return equipmentFileTWavMapper.selectByExample(example);
    }

}
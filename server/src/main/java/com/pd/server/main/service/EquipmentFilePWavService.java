package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFilePWav;
import com.pd.server.main.domain.EquipmentFilePWavExample;
import com.pd.server.main.dto.EquipmentFilePWavDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFilePWavMapper;
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
public class EquipmentFilePWavService {

    @Resource
    private EquipmentFilePWavMapper equipmentFilePWavMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFilePWavExample equipmentFilePWavExample = new EquipmentFilePWavExample();
        List<EquipmentFilePWav> equipmentFilePWavList = equipmentFilePWavMapper.selectByExample(equipmentFilePWavExample);
        PageInfo<EquipmentFilePWav> pageInfo = new PageInfo<>(equipmentFilePWavList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFilePWavDto> equipmentFilePWavDtoList = CopyUtil.copyList(equipmentFilePWavList, EquipmentFilePWavDto.class);
        pageDto.setList(equipmentFilePWavDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFilePWavDto equipmentFilePWavDto) {
        EquipmentFilePWav equipmentFilePWav = CopyUtil.copy(equipmentFilePWavDto, EquipmentFilePWav.class);
        if (StringUtils.isEmpty(equipmentFilePWavDto.getId())) {
            this.insert(equipmentFilePWav);
        } else {
            this.update(equipmentFilePWav);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFilePWav equipmentFilePWav) {
        equipmentFilePWavMapper.insert(equipmentFilePWav);
    }

    /**
    * 更新
    */
    private void update(EquipmentFilePWav equipmentFilePWav) {
        equipmentFilePWavMapper.updateByPrimaryKey(equipmentFilePWav);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFilePWavMapper.deleteByPrimaryKey(id);
    }

}
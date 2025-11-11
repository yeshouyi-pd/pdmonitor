package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileTPic;
import com.pd.server.main.domain.EquipmentFileTPicExample;
import com.pd.server.main.dto.EquipmentFileTPicDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFileTPicMapper;
import com.pd.server.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EquipmentFileTPicService {

    @Resource
    private EquipmentFileTPicMapper equipmentFileTPicMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileTPicExample equipmentFileTPicExample = new EquipmentFileTPicExample();
        List<EquipmentFileTPic> equipmentFileTPicList = equipmentFileTPicMapper.selectByExample(equipmentFileTPicExample);
        PageInfo<EquipmentFileTPic> pageInfo = new PageInfo<>(equipmentFileTPicList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileTPicDto> equipmentFileTPicDtoList = CopyUtil.copyList(equipmentFileTPicList, EquipmentFileTPicDto.class);
        pageDto.setList(equipmentFileTPicDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileTPicDto equipmentFileTPicDto) {
        EquipmentFileTPic equipmentFileTPic = CopyUtil.copy(equipmentFileTPicDto, EquipmentFileTPic.class);
        if (StringUtils.isEmpty(equipmentFileTPicDto.getId())) {
            this.insert(equipmentFileTPic);
        } else {
            this.update(equipmentFileTPic);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileTPic equipmentFileTPic) {
        equipmentFileTPicMapper.insert(equipmentFileTPic);
    }

    /**
    * 更新
    */
    private void update(EquipmentFileTPic equipmentFileTPic) {
        equipmentFileTPicMapper.updateByPrimaryKey(equipmentFileTPic);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFileTPicMapper.deleteByPrimaryKey(id);
    }

    public List<EquipmentFileTPic> selectByExample(EquipmentFileTPicExample example) {
        return equipmentFileTPicMapper.selectByExample(example);
    }

    public List<EquipmentFileTPic> selectByExampleOnlyId(EquipmentFileTPicExample example) {
        return equipmentFileTPicMapper.selectByExampleOnlyId(example);
    }


}
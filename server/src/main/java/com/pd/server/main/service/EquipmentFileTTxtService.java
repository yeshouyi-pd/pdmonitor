package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileTTxt;
import com.pd.server.main.domain.EquipmentFileTTxtExample;
import com.pd.server.main.dto.EquipmentFileTTxtDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFileTTxtMapper;
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
public class EquipmentFileTTxtService {

    @Resource
    private EquipmentFileTTxtMapper equipmentFileTTxtMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileTTxtExample equipmentFileTTxtExample = new EquipmentFileTTxtExample();
        List<EquipmentFileTTxt> equipmentFileTTxtList = equipmentFileTTxtMapper.selectByExample(equipmentFileTTxtExample);
        PageInfo<EquipmentFileTTxt> pageInfo = new PageInfo<>(equipmentFileTTxtList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileTTxtDto> equipmentFileTTxtDtoList = CopyUtil.copyList(equipmentFileTTxtList, EquipmentFileTTxtDto.class);
        pageDto.setList(equipmentFileTTxtDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileTTxtDto equipmentFileTTxtDto) {
        EquipmentFileTTxt equipmentFileTTxt = CopyUtil.copy(equipmentFileTTxtDto, EquipmentFileTTxt.class);
        if (StringUtils.isEmpty(equipmentFileTTxtDto.getId())) {
            this.insert(equipmentFileTTxt);
        } else {
            this.update(equipmentFileTTxt);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileTTxt equipmentFileTTxt) {
        equipmentFileTTxtMapper.insert(equipmentFileTTxt);
    }

    /**
    * 更新
    */
    private void update(EquipmentFileTTxt equipmentFileTTxt) {
        equipmentFileTTxtMapper.updateByPrimaryKey(equipmentFileTTxt);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFileTTxtMapper.deleteByPrimaryKey(id);
    }

}
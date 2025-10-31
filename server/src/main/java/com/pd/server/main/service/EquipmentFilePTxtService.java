package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFilePTxt;
import com.pd.server.main.domain.EquipmentFilePTxtExample;
import com.pd.server.main.dto.EquipmentFilePTxtDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileDwjkDto;
import com.pd.server.main.mapper.EquipmentFilePTxtMapper;
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
public class EquipmentFilePTxtService {

    @Resource
    private EquipmentFilePTxtMapper equipmentFilePTxtMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFilePTxtExample equipmentFilePTxtExample = new EquipmentFilePTxtExample();
        List<EquipmentFilePTxt> equipmentFilePTxtList = equipmentFilePTxtMapper.selectByExample(equipmentFilePTxtExample);
        PageInfo<EquipmentFilePTxt> pageInfo = new PageInfo<>(equipmentFilePTxtList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFilePTxtDto> equipmentFilePTxtDtoList = CopyUtil.copyList(equipmentFilePTxtList, EquipmentFilePTxtDto.class);
        pageDto.setList(equipmentFilePTxtDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFilePTxtDto equipmentFilePTxtDto) {
        EquipmentFilePTxt equipmentFilePTxt = CopyUtil.copy(equipmentFilePTxtDto, EquipmentFilePTxt.class);
        if (StringUtils.isEmpty(equipmentFilePTxtDto.getId())) {
            this.insert(equipmentFilePTxt);
        } else {
            this.update(equipmentFilePTxt);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFilePTxt equipmentFilePTxt) {
        equipmentFilePTxtMapper.insert(equipmentFilePTxt);
    }

    /**
    * 更新
    */
    private void update(EquipmentFilePTxt equipmentFilePTxt) {
        equipmentFilePTxtMapper.updateByPrimaryKey(equipmentFilePTxt);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFilePTxtMapper.deleteByPrimaryKey(id);
    }


    public List<EquipmentFileDwjkDto> listAllDw(EquipmentFilePTxtExample example){
        return equipmentFilePTxtMapper.listAllDw(example);
    }
}
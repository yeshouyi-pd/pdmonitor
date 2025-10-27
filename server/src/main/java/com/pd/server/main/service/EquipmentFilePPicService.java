package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFilePPic;
import com.pd.server.main.domain.EquipmentFilePPicExample;
import com.pd.server.main.dto.EquipmentFilePPicDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFilePPicMapper;
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
public class EquipmentFilePPicService {

    @Resource
    private EquipmentFilePPicMapper equipmentFilePPicMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFilePPicExample equipmentFilePPicExample = new EquipmentFilePPicExample();
        List<EquipmentFilePPic> equipmentFilePPicList = equipmentFilePPicMapper.selectByExample(equipmentFilePPicExample);
        PageInfo<EquipmentFilePPic> pageInfo = new PageInfo<>(equipmentFilePPicList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFilePPicDto> equipmentFilePPicDtoList = CopyUtil.copyList(equipmentFilePPicList, EquipmentFilePPicDto.class);
        pageDto.setList(equipmentFilePPicDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFilePPicDto equipmentFilePPicDto) {
        EquipmentFilePPic equipmentFilePPic = CopyUtil.copy(equipmentFilePPicDto, EquipmentFilePPic.class);
        if (StringUtils.isEmpty(equipmentFilePPicDto.getId())) {
            this.insert(equipmentFilePPic);
        } else {
            this.update(equipmentFilePPic);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFilePPic equipmentFilePPic) {
        equipmentFilePPicMapper.insert(equipmentFilePPic);
    }

    /**
    * 更新
    */
    private void update(EquipmentFilePPic equipmentFilePPic) {
        equipmentFilePPicMapper.updateByPrimaryKey(equipmentFilePPic);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFilePPicMapper.deleteByPrimaryKey(id);
    }

}
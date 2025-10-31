package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFilePVideo;
import com.pd.server.main.domain.EquipmentFilePVideoExample;
import com.pd.server.main.dto.EquipmentFilePVideoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileDwjkDto;
import com.pd.server.main.mapper.EquipmentFilePVideoMapper;
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
public class EquipmentFilePVideoService {

    @Resource
    private EquipmentFilePVideoMapper equipmentFilePVideoMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFilePVideoExample equipmentFilePVideoExample = new EquipmentFilePVideoExample();
        List<EquipmentFilePVideo> equipmentFilePVideoList = equipmentFilePVideoMapper.selectByExample(equipmentFilePVideoExample);
        PageInfo<EquipmentFilePVideo> pageInfo = new PageInfo<>(equipmentFilePVideoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFilePVideoDto> equipmentFilePVideoDtoList = CopyUtil.copyList(equipmentFilePVideoList, EquipmentFilePVideoDto.class);
        pageDto.setList(equipmentFilePVideoDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFilePVideoDto equipmentFilePVideoDto) {
        EquipmentFilePVideo equipmentFilePVideo = CopyUtil.copy(equipmentFilePVideoDto, EquipmentFilePVideo.class);
        if (StringUtils.isEmpty(equipmentFilePVideoDto.getId())) {
            this.insert(equipmentFilePVideo);
        } else {
            this.update(equipmentFilePVideo);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFilePVideo equipmentFilePVideo) {
        equipmentFilePVideoMapper.insert(equipmentFilePVideo);
    }

    /**
    * 更新
    */
    private void update(EquipmentFilePVideo equipmentFilePVideo) {
        equipmentFilePVideoMapper.updateByPrimaryKey(equipmentFilePVideo);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFilePVideoMapper.deleteByPrimaryKey(id);
    }

    public List<EquipmentFileDwjkDto> listAllDw(EquipmentFilePVideoExample example){
        return equipmentFilePVideoMapper.listAllDw(example);
    }
}
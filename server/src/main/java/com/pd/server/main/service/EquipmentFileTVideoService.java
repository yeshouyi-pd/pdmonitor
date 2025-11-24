package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileTVideo;
import com.pd.server.main.domain.EquipmentFileTVideoExample;
import com.pd.server.main.dto.EquipmentFileTVideoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.EquipmentFileTVideoMapper;
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
public class EquipmentFileTVideoService {

    @Resource
    private EquipmentFileTVideoMapper equipmentFileTVideoMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileTVideoExample equipmentFileTVideoExample = new EquipmentFileTVideoExample();
        List<EquipmentFileTVideo> equipmentFileTVideoList = equipmentFileTVideoMapper.selectByExample(equipmentFileTVideoExample);
        PageInfo<EquipmentFileTVideo> pageInfo = new PageInfo<>(equipmentFileTVideoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileTVideoDto> equipmentFileTVideoDtoList = CopyUtil.copyList(equipmentFileTVideoList, EquipmentFileTVideoDto.class);
        pageDto.setList(equipmentFileTVideoDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileTVideoDto equipmentFileTVideoDto) {
        EquipmentFileTVideo equipmentFileTVideo = CopyUtil.copy(equipmentFileTVideoDto, EquipmentFileTVideo.class);
        if (StringUtils.isEmpty(equipmentFileTVideoDto.getId())) {
            this.insert(equipmentFileTVideo);
        } else {
            this.update(equipmentFileTVideo);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileTVideo equipmentFileTVideo) {
        equipmentFileTVideoMapper.insert(equipmentFileTVideo);
    }

    /**
    * 更新
    */
    private void update(EquipmentFileTVideo equipmentFileTVideo) {
        equipmentFileTVideoMapper.updateByPrimaryKey(equipmentFileTVideo);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFileTVideoMapper.deleteByPrimaryKey(id);
    }

    public List<EquipmentFileTVideo> selectByExample(EquipmentFileTVideoExample example){
        return equipmentFileTVideoMapper.selectByExample(example);
    }
}
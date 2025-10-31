package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileTCluster;
import com.pd.server.main.domain.EquipmentFileTClusterExample;
import com.pd.server.main.dto.EquipmentFileTClusterDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.EquipmentTyEventDwDto;
import com.pd.server.main.mapper.EquipmentFileTClusterMapper;
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
public class EquipmentFileTClusterService {

    @Resource
    private EquipmentFileTClusterMapper equipmentFileTClusterMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileTClusterExample equipmentFileTClusterExample = new EquipmentFileTClusterExample();
        List<EquipmentFileTCluster> equipmentFileTClusterList = equipmentFileTClusterMapper.selectByExample(equipmentFileTClusterExample);
        PageInfo<EquipmentFileTCluster> pageInfo = new PageInfo<>(equipmentFileTClusterList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileTClusterDto> equipmentFileTClusterDtoList = CopyUtil.copyList(equipmentFileTClusterList, EquipmentFileTClusterDto.class);
        pageDto.setList(equipmentFileTClusterDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileTClusterDto equipmentFileTClusterDto) {
        EquipmentFileTCluster equipmentFileTCluster = CopyUtil.copy(equipmentFileTClusterDto, EquipmentFileTCluster.class);
        if (StringUtils.isEmpty(equipmentFileTClusterDto.getId())) {
            this.insert(equipmentFileTCluster);
        } else {
            this.update(equipmentFileTCluster);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileTCluster equipmentFileTCluster) {
        equipmentFileTClusterMapper.insert(equipmentFileTCluster);
    }

    /**
    * 更新
    */
    private void update(EquipmentFileTCluster equipmentFileTCluster) {
        equipmentFileTClusterMapper.updateByPrimaryKey(equipmentFileTCluster);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFileTClusterMapper.deleteByPrimaryKey(id);
    }

    public List<EquipmentTyEventDwDto> listAllDw(EquipmentFileTClusterExample example){
        return equipmentFileTClusterMapper.listAllDw(example);
    }
}
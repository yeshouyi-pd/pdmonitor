package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileEvent;
import com.pd.server.main.domain.EquipmentFilePCluster;
import com.pd.server.main.domain.EquipmentFilePClusterExample;
import com.pd.server.main.domain.EquipmentTyEvent;
import com.pd.server.main.dto.EquipmentFilePClusterDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileDwjkDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileEventDwDto;
import com.pd.server.main.dto.basewx.my.WrjEventDto;
import com.pd.server.main.dto.basewx.my.WrjTcDto;
import com.pd.server.main.mapper.EquipmentFilePClusterMapper;
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
public class EquipmentFilePClusterService {

    @Resource
    private EquipmentFilePClusterMapper equipmentFilePClusterMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFilePClusterExample equipmentFilePClusterExample = new EquipmentFilePClusterExample();
        List<EquipmentFilePCluster> equipmentFilePClusterList = equipmentFilePClusterMapper.selectByExample(equipmentFilePClusterExample);
        PageInfo<EquipmentFilePCluster> pageInfo = new PageInfo<>(equipmentFilePClusterList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFilePClusterDto> equipmentFilePClusterDtoList = CopyUtil.copyList(equipmentFilePClusterList, EquipmentFilePClusterDto.class);
        pageDto.setList(equipmentFilePClusterDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFilePClusterDto equipmentFilePClusterDto) {
        EquipmentFilePCluster equipmentFilePCluster = CopyUtil.copy(equipmentFilePClusterDto, EquipmentFilePCluster.class);
        if (StringUtils.isEmpty(equipmentFilePClusterDto.getId())) {
            this.insert(equipmentFilePCluster);
        } else {
            this.update(equipmentFilePCluster);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFilePCluster equipmentFilePCluster) {
        equipmentFilePClusterMapper.insert(equipmentFilePCluster);
    }

    /**
    * 更新
    */
    private void update(EquipmentFilePCluster equipmentFilePCluster) {
        equipmentFilePClusterMapper.updateByPrimaryKey(equipmentFilePCluster);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFilePClusterMapper.deleteByPrimaryKey(id);
    }

    public List<EquipmentFilePCluster> selectLastDw(EquipmentFilePClusterExample example){
        return equipmentFilePClusterMapper.selectLastDw(example);
    }

    //含拖曳的聚类
    public List<EquipmentFileDwjkDto> listAllDw(EquipmentFilePClusterExample example){
        return equipmentFilePClusterMapper.listAllDw(example);
    }

    //只有A4的聚类
    public List<EquipmentFileEventDwDto> listDw(EquipmentFilePClusterExample example){
        return equipmentFilePClusterMapper.listDw(example);
    }

    //含拖曳的聚类
    public List<WrjEventDto> listAllWrj(EquipmentFilePClusterExample example){
        return equipmentFilePClusterMapper.listAllWrj(example);
    }

    //含拖曳的聚类
    public List<WrjTcDto> listSumTsWrj(EquipmentFilePClusterExample example){
        return equipmentFilePClusterMapper.listSumTsWrj(example);
    }
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileTCluster;
import com.pd.server.main.domain.EquipmentFileTClusterExample;
import java.util.List;

import com.pd.server.main.dto.basewx.my.EquipmentTyEventDwDto;
import com.pd.server.main.dto.basewx.my.GpsKVDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileTClusterMapper {
    long countByExample(EquipmentFileTClusterExample example);

    int deleteByExample(EquipmentFileTClusterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFileTCluster record);

    int insertSelective(EquipmentFileTCluster record);

    List<EquipmentFileTCluster> selectByExample(EquipmentFileTClusterExample example);

    EquipmentFileTCluster selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFileTCluster record, @Param("example") EquipmentFileTClusterExample example);

    int updateByExample(@Param("record") EquipmentFileTCluster record, @Param("example") EquipmentFileTClusterExample example);

    int updateByPrimaryKeySelective(EquipmentFileTCluster record);

    int updateByPrimaryKey(EquipmentFileTCluster record);

    List<EquipmentTyEventDwDto> listAllDw(EquipmentFileTClusterExample example);

    List<GpsKVDto> selectGpsByExample(EquipmentFileTClusterExample example);

    List<EquipmentFileTCluster> selectByExampleOnlyId(EquipmentFileTClusterExample example);
}
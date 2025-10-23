package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFilePCluster;
import com.pd.server.main.domain.EquipmentFilePClusterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFilePClusterMapper {
    long countByExample(EquipmentFilePClusterExample example);

    int deleteByExample(EquipmentFilePClusterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFilePCluster record);

    int insertSelective(EquipmentFilePCluster record);

    List<EquipmentFilePCluster> selectByExample(EquipmentFilePClusterExample example);

    EquipmentFilePCluster selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFilePCluster record, @Param("example") EquipmentFilePClusterExample example);

    int updateByExample(@Param("record") EquipmentFilePCluster record, @Param("example") EquipmentFilePClusterExample example);

    int updateByPrimaryKeySelective(EquipmentFilePCluster record);

    int updateByPrimaryKey(EquipmentFilePCluster record);
}
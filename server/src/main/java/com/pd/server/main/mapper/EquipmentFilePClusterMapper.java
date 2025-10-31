package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileEvent;
import com.pd.server.main.domain.EquipmentFilePCluster;
import com.pd.server.main.domain.EquipmentFilePClusterExample;
import java.util.List;

import com.pd.server.main.domain.EquipmentTyEvent;
import com.pd.server.main.dto.basewx.my.EquipmentFileDwjkDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileEventDwDto;
import com.pd.server.main.dto.basewx.my.WrjEventDto;
import com.pd.server.main.dto.basewx.my.WrjTcDto;
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

    List<EquipmentFilePCluster> selectLastDw(EquipmentFilePClusterExample example);

    List<EquipmentFileDwjkDto> listAllDw(EquipmentFilePClusterExample example);//含拖曳的聚类

    List<EquipmentFileEventDwDto> listDw(EquipmentFilePClusterExample example);//只有A4的聚类

    List<WrjEventDto> listAllWrj(EquipmentFilePClusterExample example);//含拖曳的聚类

    List<WrjTcDto> listSumTsWrj(EquipmentFilePClusterExample example);//含拖曳的聚类
}
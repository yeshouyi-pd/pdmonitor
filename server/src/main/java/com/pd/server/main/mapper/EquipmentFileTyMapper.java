package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileTy;
import com.pd.server.main.domain.EquipmentFileTyExample;
import java.util.List;

import com.pd.server.main.dto.EquipmentFileTyDto;
import com.pd.server.main.dto.basewx.my.GpsKVDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileTyMapper {
    long countByExample(EquipmentFileTyExample example);

    int deleteByExample(EquipmentFileTyExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFileTy record);

    int insertSelective(EquipmentFileTy record);

    List<EquipmentFileTy> selectByExample(EquipmentFileTyExample example);

    List<EquipmentFileTy> selectByExampleSpecial(@Param("record") EquipmentFileTyDto record);

    EquipmentFileTy selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFileTy record, @Param("example") EquipmentFileTyExample example);

    int updateByExample(@Param("record") EquipmentFileTy record, @Param("example") EquipmentFileTyExample example);

    int updateByPrimaryKeySelective(EquipmentFileTy record);

    int updateByPrimaryKey(EquipmentFileTy record);

    List<GpsKVDto> selectTodayGps(@Param("rq")String rq, @Param("sbbh")String sbbh);

    List<GpsKVDto> selectGpsByExample(EquipmentFileTyExample example);

    List<GpsKVDto> selectGpsByDto(@Param("record") EquipmentFileTyDto record);

    List<EquipmentFileTy> limitExample(EquipmentFileTyExample example);
}
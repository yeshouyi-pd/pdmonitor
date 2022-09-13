package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileTy;
import com.pd.server.main.domain.EquipmentFileTyToday;
import com.pd.server.main.domain.EquipmentFileTyTodayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileTyTodayMapper {

    long countByExample(EquipmentFileTyTodayExample example);

    int deleteByExample(EquipmentFileTyTodayExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFileTyToday record);

    int insertEquipTy(EquipmentFileTy record);

    int insertSelective(EquipmentFileTyToday record);

    List<EquipmentFileTyToday> selectByExample(EquipmentFileTyTodayExample example);

    EquipmentFileTyToday selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFileTyToday record, @Param("example") EquipmentFileTyTodayExample example);

    int updateByExample(@Param("record") EquipmentFileTyToday record, @Param("example") EquipmentFileTyTodayExample example);

    int updateByPrimaryKeySelective(EquipmentFileTyToday record);

    int updateByPrimaryKey(EquipmentFileTyToday record);

    List<String> selectTodayGps(@Param("rq")String rq,@Param("sbbh")String sbbh);
}
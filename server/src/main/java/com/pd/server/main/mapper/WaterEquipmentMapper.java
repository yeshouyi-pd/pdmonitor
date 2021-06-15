package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import java.util.List;

import com.pd.server.main.dto.PieChartDto;
import org.apache.ibatis.annotations.Param;

public interface WaterEquipmentMapper {
    long countByExample(WaterEquipmentExample example);

    int deleteByExample(WaterEquipmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(WaterEquipment record);

    int insertSelective(WaterEquipment record);

    List<WaterEquipment> selectByExample(WaterEquipmentExample example);

    WaterEquipment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WaterEquipment record, @Param("example") WaterEquipmentExample example);

    int updateByExample(@Param("record") WaterEquipment record, @Param("example") WaterEquipmentExample example);

    int updateByPrimaryKeySelective(WaterEquipment record);

    int updateByPrimaryKey(WaterEquipment record);

    List<PieChartDto> getPieChart(WaterEquipmentExample example);

    List<String> selectSbbhByExample(WaterEquipmentExample example);
}
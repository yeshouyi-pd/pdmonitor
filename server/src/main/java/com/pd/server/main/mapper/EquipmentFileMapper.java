package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import java.util.List;

import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.KvIntDto;
import com.pd.server.main.dto.WaterQualityResultDto;
import com.pd.server.main.dto.WelcomeKvDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;

import com.pd.server.main.dto.KvMapDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileMapper {
    long countByExample(EquipmentFileExample example);

    int deleteByExample(EquipmentFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFile record);

    int insertSelective(EquipmentFile record);

    List<EquipmentFile> selectByExample(EquipmentFileExample example);

    EquipmentFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFile record, @Param("example") EquipmentFileExample example);

    int updateByExample(@Param("record") EquipmentFile record, @Param("example") EquipmentFileExample example);

    int updateByPrimaryKeySelective(EquipmentFile record);

    int updateByPrimaryKey(EquipmentFile record);

    List<String> selectAudioByExample(EquipmentFileExample example);

    List<String> selectSbbhByExample(EquipmentFileExample example);

    List<AlarmNumbersDto> statisticsAlarmNums(EquipmentFileExample example);

    List<WelcomeKvDto> getWarningDate(EquipmentFileExample equipmentFileExample);

    List<KvIntDto> getAlljcsjByDept(EquipmentFileExample equipmentFileExample);

}
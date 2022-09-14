package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import java.util.List;

import com.pd.server.main.dto.EquipmentFileDto;
import com.pd.server.main.dto.KvIntDto;
import com.pd.server.main.dto.WelcomeKvDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileMapper {
    long countByExample(EquipmentFileExample example);

    int deleteByExample(EquipmentFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFile record);

    int insertSelective(EquipmentFile record);

    List<EquipmentFile> selectByExample(EquipmentFileExample example);

    List<EquipmentFile> selectByExampleSpecial(@Param("record") EquipmentFileDto record);

    EquipmentFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFile record, @Param("example") EquipmentFileExample example);

    int updateByExample(@Param("record") EquipmentFile record, @Param("example") EquipmentFileExample example);

    int updateByPrimaryKeySelective(EquipmentFile record);

    int updateByPrimaryKey(EquipmentFile record);

    List<String> selectAudioByExample(EquipmentFileExample example);

    List<String> selectSbbhByExample(EquipmentFileExample example);

    List<AlarmNumbersDto> statisticsAlarmNums(EquipmentFileExample example);

    List<AlarmNumbersDto> statisticsAlarmNumsByPage(EquipmentFileExample example);

    List<WelcomeKvDto> getWarningDate(EquipmentFileExample equipmentFileExample);

    List<KvIntDto> getAlljcsjByDept(EquipmentFileExample equipmentFileExample);

    List<EquipmentFile> getthisDeptjxsjJT(EquipmentFileExample equipmentFileExample);

    List<AlarmNumbersDto> statisticsAlarmNumsByHour(EquipmentFileExample example);

    List<AlarmNumbersDto> statisticsAlarmNumsByHourAndDP(EquipmentFileExample example);

    List<EquipmentFile> listBylimit(EquipmentFileExample example);

    List<AlarmNumbersDto> groupByRq(EquipmentFileExample example);

    List<EquipmentFile> lists(EquipmentFileExample example);

    EquipmentFile selectLastOneBySbbh(String sbbh);
}
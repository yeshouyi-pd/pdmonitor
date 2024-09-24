package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import java.util.List;

import com.pd.server.main.domain.EquipmentFileToday;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.dto.basewx.my.PredationStaticticsDto;
import com.pd.server.main.dto.basewx.my.SmsIntDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileMapper {

    List<KvIntDto> getAlljcsjByDept(EquipmentFileExample example);
    List<EquipmentFile>  getAlljcsjByDeptgetDay(EquipmentFileExample example);

    long countByExample(EquipmentFileExample example);

    long countByExampleSpecial(@Param("record") EquipmentFileDto record);

    int deleteByExample(EquipmentFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFile record);

    int insertSelective(EquipmentFile record);

    List<EquipmentFile> selectByExample(EquipmentFileExample example);

    List<EquipmentFile> selectByExampleDw(EquipmentFileExample example);

    List<EquipmentFile> selectByExampleSpecial(@Param("record") EquipmentFileDto record);

    List<EquipmentFile> selectByExampleSpecialNoForceindex(@Param("record") EquipmentFileDto record);

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

    List<EquipmentMainDto> getthisDeptjxsjJT(EquipmentFileExample equipmentFileExample);

    List<AlarmNumbersDto> statisticsAlarmNumsByHour(EquipmentFileExample example);

    List<AlarmNumbersDto> statisticsAlarmNumsByHourAndDP(EquipmentFileExample example);

    List<EquipmentFile> listBylimit(EquipmentFileExample example);

    List<AlarmNumbersDto> groupByRq(EquipmentFileExample example);

    List<EquipmentFile> lists(EquipmentFileExample example);

    EquipmentFile selectLastOneBySbbh(String sbbh);

    List<EquipmenInfo> getthisDeptEquipment(EquipmentFileExample equipmentFileExample);

    List<EquipmentMainListDto> getthisDeptEquipmentinfo(EquipmentFileExample equipmentFileExample);

    EquipmentFile selectVideoDp();

    List<SmsIntDto> sendSmsQuery(EquipmentFileExample example);

    List<SmsIntDto> sendSmsQueryXz(EquipmentFileExample example);

    Integer countTsByExample(EquipmentFileExample example);

    PredationStaticticsDto predationStatictics(EquipmentFileExample example);

    List<EquipmenInfo> getthisDeptEquipmentday(EquipmentFileExample equipmentFileExample);

    List<EquipmentMainListDto> getthisDeptEquipmentinfoday(EquipmentFileExample equipmentFileExample);
}
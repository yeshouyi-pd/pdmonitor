package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.EquipmentFileToday;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import java.util.List;

import com.pd.server.main.dto.AppearNumbersDto;
import com.pd.server.main.dto.KvIntDto;
import com.pd.server.main.dto.WelcomeKvDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.dto.basewx.my.NoTodaySbbhDto;
import com.pd.server.main.dto.basewx.my.SmsIntDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFileTodayMapper {

    List<KvIntDto> getAlljcsjByDept(EquipmentFileTodayExample example);

    Integer countTsByExample(EquipmentFileTodayExample example);

    List<WelcomeKvDto> getWarningDate(EquipmentFileTodayExample example);

    List<AlarmNumbersDto> groupByRq(EquipmentFileTodayExample example);

    List<AlarmNumbersDto> statisticsAlarmNums(EquipmentFileTodayExample example);

    long countByExample(EquipmentFileTodayExample example);

    int deleteByExample(EquipmentFileTodayExample example);

    int deleteByPrimaryKey(String id);

    int insert(EquipmentFileToday record);

    int insertEquipFile(EquipmentFile record);

    int insertSelective(EquipmentFileToday record);

    List<EquipmentFileToday> selectByExample(EquipmentFileTodayExample example);

    List<EquipmentFileToday> selectByExampleDw(EquipmentFileTodayExample example);

    EquipmentFileToday selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EquipmentFileToday record, @Param("example") EquipmentFileTodayExample example);

    int updateByExample(@Param("record") EquipmentFileToday record, @Param("example") EquipmentFileTodayExample example);

    int updateByPrimaryKeySelective(EquipmentFileToday record);

    int updateByPrimaryKey(EquipmentFileToday record);

    EquipmentFileToday selectLastOneBySbbh(String sbbh);

    List<SmsIntDto> sendSmsQuery(EquipmentFileTodayExample example);

    List<SmsIntDto> sendSmsQueryXz(EquipmentFileTodayExample example);

    List<NoTodaySbbhDto> noTodaySbbhDtoList(EquipmentFileTodayExample example);
}
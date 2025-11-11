package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFilePPic;
import com.pd.server.main.domain.EquipmentFilePPicExample;
import java.util.List;

import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileDwjkDto;
import com.pd.server.main.dto.basewx.my.PredationStaticticsDto;
import com.pd.server.main.dto.basewx.my.SmsIntDto;

import com.pd.server.main.dto.EquipmenInfo;
import com.pd.server.main.dto.EquipmentMainDto;
import com.pd.server.main.dto.EquipmentMainListDto;
import com.pd.server.main.dto.KvIntDto;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFilePPicMapper {
    long countByExample(EquipmentFilePPicExample example);

    int deleteByExample(EquipmentFilePPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFilePPic record);

    int insertSelective(EquipmentFilePPic record);

    List<EquipmentFilePPic> selectByExample(EquipmentFilePPicExample example);

    List<EquipmentFilePPic> selectByExampleOnlyId(EquipmentFilePPicExample example);

    EquipmentFilePPic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFilePPic record, @Param("example") EquipmentFilePPicExample example);

    int updateByExample(@Param("record") EquipmentFilePPic record, @Param("example") EquipmentFilePPicExample example);

    int updateByPrimaryKeySelective(EquipmentFilePPic record);

    int updateByPrimaryKey(EquipmentFilePPic record);

    List<EquipmentFileDwjkDto> listAllDw(EquipmentFilePPicExample example);

    List<AlarmNumbersDto> statisticsAlarmNumsByMinute(EquipmentFilePPicExample example);

    List<AlarmNumbersDto> statisticsAlarmNumsByHour(EquipmentFilePPicExample example);

    List<SmsIntDto> sendSmsQuery(EquipmentFilePPicExample example);

    PredationStaticticsDto predationStatictics(EquipmentFilePPicExample example);


    List<EquipmentFilePPic> getAlljcsjByDeptgetDay(EquipmentFilePPicExample example);

    List<KvIntDto> getAlljcsjByDept(EquipmentFilePPicExample example);

    List<EquipmenInfo> getthisDeptEquipmentday(EquipmentFilePPicExample equipmentFileExample);

    List<EquipmentMainListDto> getthisDeptEquipmentinfoday(EquipmentFilePPicExample equipmentFileExample);

    List<EquipmentMainDto> getthisDeptjxsjJT(EquipmentFilePPicExample equipmentFileExample);
}
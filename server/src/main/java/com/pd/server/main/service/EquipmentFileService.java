package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.dto.basewx.my.SmsIntDto;
import com.pd.server.main.mapper.EquipmentFileMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class EquipmentFileService {

    @Resource
    private EquipmentFileMapper equipmentFileMapper;

    public List<String> findSbbh(EquipmentFileExample example){
        return equipmentFileMapper.selectSbbhByExample(null);
    }

    public List<AlarmNumbersDto> statisticsAlarmNums(EquipmentFileExample example){
        return equipmentFileMapper.statisticsAlarmNums(example);
    }

    public List<AlarmNumbersDto> statisticsAlarmNumsByPage(EquipmentFileExample example){
        return equipmentFileMapper.statisticsAlarmNumsByPage(example);
    }

    public List<AlarmNumbersDto> statisticsAlarmNumsByHour(EquipmentFileExample example){
        return equipmentFileMapper.statisticsAlarmNumsByHour(example);
    }

    public List<EquipmentFile> selectByExampleSpecial(EquipmentFileDto record){
        return equipmentFileMapper.selectByExampleSpecial(record);
    }

    public List<EquipmentFile> selectByExampleSpecialNoForceindex(EquipmentFileDto record){
        return equipmentFileMapper.selectByExampleSpecialNoForceindex(record);
    }

    public EquipmentFile selectByPrimaryKey(String id){
        return equipmentFileMapper.selectByPrimaryKey(id);
    }

    public List<EquipmentFile> lists(EquipmentFileExample equipmentFileExample){
        List<EquipmentFile> equipmentFileList = equipmentFileMapper.lists(equipmentFileExample);
        return equipmentFileList;
    }

    public List<EquipmentFile> listAll(EquipmentFileExample equipmentFileExample){
        List<EquipmentFile> equipmentFileList = equipmentFileMapper.selectByExample(equipmentFileExample);
        return equipmentFileList;
    }

    public List<EquipmentFile> listAllDw(EquipmentFileExample equipmentFileExample){
        return equipmentFileMapper.selectByExampleDw(equipmentFileExample);
    }

    public long countByExample(EquipmentFileExample example){
        return equipmentFileMapper.countByExample(example);
    }

    public long countByExampleSpecial(EquipmentFileDto record){
        return equipmentFileMapper.countByExampleSpecial(record);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileDto equipmentFileDto) {
        EquipmentFile equipmentFile = CopyUtil.copy(equipmentFileDto, EquipmentFile.class);
        if (StringUtils.isEmpty(equipmentFileDto.getId())) {
            this.insert(equipmentFile);
        } else {
            this.update(equipmentFile);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFile equipmentFile) {
                Date now = new Date();
        equipmentFile.setId(UuidUtil.getShortUuid());
        equipmentFileMapper.insert(equipmentFile);
    }

    /**
    * 更新
    */
    public void update(EquipmentFile equipmentFile) {
        equipmentFileMapper.updateByPrimaryKeySelective(equipmentFile);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        equipmentFileMapper.deleteByPrimaryKey(id);
    }



    public  List<WelcomeKvDto> getWarningDate(EquipmentFileExample equipmentFileExample){
        return equipmentFileMapper.getWarningDate(equipmentFileExample);
    }

    public List<EquipmentMainDto> getthisDeptjxsjJT(EquipmentFileExample equipmentFileExample) {
        List<EquipmentMainDto> list  = equipmentFileMapper.getthisDeptjxsjJT(equipmentFileExample);
        return list;
    }

    public List<EquipmentFile> listBylimit(EquipmentFileExample equipmentFileExample) {
        return equipmentFileMapper.listBylimit(equipmentFileExample);
    }

    public EquipmentFile selectLastOneBySbbh(String sbbh) {
        return equipmentFileMapper.selectLastOneBySbbh(sbbh);
    }

    public List<EquipmenInfo> getthisDeptEquipment(EquipmentFileExample equipmentFileExample) {

        return equipmentFileMapper.getthisDeptEquipment(equipmentFileExample);
    }

    public List<EquipmentMainListDto> getthisDeptEquipmentinfo(EquipmentFileExample equipmentFileExample) {
        return equipmentFileMapper.getthisDeptEquipmentinfo(equipmentFileExample);

    }

    public EquipmentFile selectVideoDp(){ return equipmentFileMapper.selectVideoDp(); }

    public List<SmsIntDto> sendSmsQuery(EquipmentFileExample example){
        return equipmentFileMapper.sendSmsQuery(example);
    }

    public List<SmsIntDto> sendSmsQueryXz(EquipmentFileExample example){
        return equipmentFileMapper.sendSmsQueryXz(example);
    }
}
package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFilePPic;
import com.pd.server.main.domain.EquipmentFilePPicExample;
import com.pd.server.main.dto.EquipmentFilePPicDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileDwjkDto;
import com.pd.server.main.dto.basewx.my.PredationStaticticsDto;
import com.pd.server.main.dto.basewx.my.SmsIntDto;
import com.pd.server.main.dto.*;
import com.pd.server.main.mapper.EquipmentFilePPicMapper;
import com.pd.server.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EquipmentFilePPicService {

    @Resource
    private EquipmentFilePPicMapper equipmentFilePPicMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFilePPicExample equipmentFilePPicExample = new EquipmentFilePPicExample();
        List<EquipmentFilePPic> equipmentFilePPicList = equipmentFilePPicMapper.selectByExample(equipmentFilePPicExample);
        PageInfo<EquipmentFilePPic> pageInfo = new PageInfo<>(equipmentFilePPicList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFilePPicDto> equipmentFilePPicDtoList = CopyUtil.copyList(equipmentFilePPicList, EquipmentFilePPicDto.class);
        pageDto.setList(equipmentFilePPicDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFilePPicDto equipmentFilePPicDto) {
        EquipmentFilePPic equipmentFilePPic = CopyUtil.copy(equipmentFilePPicDto, EquipmentFilePPic.class);
        if (StringUtils.isEmpty(equipmentFilePPicDto.getId())) {
            this.insert(equipmentFilePPic);
        } else {
            this.update(equipmentFilePPic);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFilePPic equipmentFilePPic) {
        equipmentFilePPicMapper.insert(equipmentFilePPic);
    }

    /**
    * 更新
    */
    private void update(EquipmentFilePPic equipmentFilePPic) {
        equipmentFilePPicMapper.updateByPrimaryKey(equipmentFilePPic);
    }

    /**
    * 删除
    */
    public void delete(Long id) {
        equipmentFilePPicMapper.deleteByPrimaryKey(id);
    }

    public List<EquipmentFileDwjkDto> listAllDw(EquipmentFilePPicExample example){
        return equipmentFilePPicMapper.listAllDw(example);
    }
    public List<EquipmentFilePPic> selectByExampleOnlyId(EquipmentFilePPicExample equipmentFileExample) {
        return equipmentFilePPicMapper.selectByExampleOnlyId(equipmentFileExample);
    }

    public List<EquipmentFilePPic> selectByExample(EquipmentFilePPicExample equipmentFileExampleId) {
        return equipmentFilePPicMapper.selectByExample(equipmentFileExampleId);
    }

    public List<AlarmNumbersDto> statisticsAlarmNumsByMinute(EquipmentFilePPicExample example){
        return equipmentFilePPicMapper.statisticsAlarmNumsByMinute(example);
    }

    public List<AlarmNumbersDto> statisticsAlarmNumsByHour(EquipmentFilePPicExample example){
        return equipmentFilePPicMapper.statisticsAlarmNumsByHour(example);
    }

    public List<SmsIntDto> sendSmsQuery(EquipmentFilePPicExample example){
        return equipmentFilePPicMapper.sendSmsQuery(example);
    }

    public PredationStaticticsDto predationStatictics(EquipmentFilePPicExample example){
        return equipmentFilePPicMapper.predationStatictics(example);
    }

    public List<EquipmentFilePPic> getAlljcsjByDeptgetDay(EquipmentFilePPicExample example) {
        return equipmentFilePPicMapper.getAlljcsjByDeptgetDay(example);
    }

    public List<KvIntDto> getAlljcsjByDept(EquipmentFilePPicExample example) {
        return equipmentFilePPicMapper.getAlljcsjByDept(example);
    }

    public List<EquipmenInfo> getthisDeptEquipmentday(EquipmentFilePPicExample equipmentFileExample) {
        return equipmentFilePPicMapper.getthisDeptEquipmentday(equipmentFileExample);
    }

    public List<EquipmentMainListDto> getthisDeptEquipmentinfoday(EquipmentFilePPicExample equipmentFileExample) {
        return equipmentFilePPicMapper.getthisDeptEquipmentinfoday(equipmentFileExample);
    }

    /**
     * 获取部门设备信息（分页）
     */
    public void getthisDeptEquipmentinfoday(PageDto pageDto, EquipmentFilePPicExample equipmentFileExample) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<EquipmentMainListDto> list = equipmentFilePPicMapper.getthisDeptEquipmentinfoday(equipmentFileExample);
        PageInfo<EquipmentMainListDto> pageInfo = new PageInfo<>(list);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public List<EquipmentMainDto> getthisDeptjxsjJT(EquipmentFilePPicExample equipmentFileExample) {
        return equipmentFilePPicMapper.getthisDeptjxsjJT(equipmentFileExample);
    }
}
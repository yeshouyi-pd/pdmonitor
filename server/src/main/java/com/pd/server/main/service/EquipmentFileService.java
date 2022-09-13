package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
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

    public List<AlarmNumbersDto> statisticsAlarmNumsByHourAndDP(EquipmentFileExample example){
        return equipmentFileMapper.statisticsAlarmNumsByHourAndDP(example);
    }

    /**
    * 列表查询
    */
    public void list(EquipmentFileDto pageDto, List<String> list, LoginUserDto user) {
        EquipmentFileExample audioExample = new EquipmentFileExample();
        EquipmentFileExample.Criteria audioCa = audioExample.createCriteria();
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria txtCa = example.createCriteria();
        EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = equipmentFileExample.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            audioCa.andDeptcodeIn(list);
            txtCa.andDeptcodeIn(list);
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            audioCa.andRqGreaterThanOrEqualTo(pageDto.getStime());
            txtCa.andRqGreaterThanOrEqualTo(pageDto.getStime());
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }else{
            audioCa.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-6),"yyyy-MM-dd"));
            txtCa.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-6),"yyyy-MM-dd"));
            ca.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-6),"yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            audioCa.andRqLessThanOrEqualTo(pageDto.getEtime());
            txtCa.andRqLessThanOrEqualTo(pageDto.getEtime());
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }else{
            audioCa.andRqLessThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
            txtCa.andRqLessThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
            ca.andRqLessThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            audioCa.andSbbhEqualTo(pageDto.getSbbh());
            txtCa.andSbbhEqualTo(pageDto.getSbbh());
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getTplj())&&"predation".equals(pageDto.getTplj())){
            audioCa.andJczlEqualTo("1");
            txtCa.andJczlEqualTo("1");
            ca.andJczlEqualTo("1");
        }
        audioCa.andWjlxEqualTo("2");
        List<String> audioFileList = equipmentFileMapper.selectAudioByExample(audioExample);
        txtCa.andWjlxEqualTo("1");
        List<String> txtFileList = equipmentFileMapper.selectAudioByExample(example);
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ca.andTxtlxEqualTo("1");
        equipmentFileExample.setOrderByClause(" cjsj desc ");
        List<EquipmentFile> equipmentFileList = equipmentFileMapper.selectByExample(equipmentFileExample);
        PageInfo<EquipmentFile> pageInfo = new PageInfo<>(equipmentFileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileDto> equipmentFileDtoList = CopyUtil.copyList(equipmentFileList, EquipmentFileDto.class);
        for(EquipmentFileDto item : equipmentFileDtoList){
            if(audioFileList.contains(item.getWjmc())){
                item.setHasAudio(true);
            }else{
                item.setHasAudio(false);
            }
            item.setHasTxt(false);
            for(String str : txtFileList){
                if(str.contains(item.getWjmc())){
                    item.setHasTxt(true);
                    item.setTxtSrc(item.getTplj());
                    break;
                }
            }
        }
        pageDto.setList(equipmentFileDtoList);
    }

    public List<EquipmentFile> lists(EquipmentFileExample equipmentFileExample){
        List<EquipmentFile> equipmentFileList = equipmentFileMapper.lists(equipmentFileExample);
        return equipmentFileList;
    }

    public List<EquipmentFile> listAll(EquipmentFileExample equipmentFileExample){
        List<EquipmentFile> equipmentFileList = equipmentFileMapper.selectByExample(equipmentFileExample);
        return equipmentFileList;
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
    private void update(EquipmentFile equipmentFile) {
        equipmentFileMapper.updateByPrimaryKey(equipmentFile);
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

    public List<KvIntDto> getAlljcsjByDept(EquipmentFileExample equipmentFileExample) {
        return equipmentFileMapper.getAlljcsjByDept(equipmentFileExample);
    }

    public List<EquipmentFileDto> getthisDeptjxsjJT(EquipmentFileExample equipmentFileExample) {
        List<EquipmentFile> list  = equipmentFileMapper.getthisDeptjxsjJT(equipmentFileExample);
        List<EquipmentFileDto> equipmentFileDtoList = CopyUtil.copyList(list , EquipmentFileDto.class);
        return equipmentFileDtoList;
    }

    public List<EquipmentFile> listBylimit(EquipmentFileExample equipmentFileExample) {
        return equipmentFileMapper.listBylimit(equipmentFileExample);
    }

    public List<AlarmNumbersDto> groupByRq(EquipmentFileExample example){
        return equipmentFileMapper.groupByRq(example);
    }

    public EquipmentFile selectLastOneBySbbh(String sbbh) {
        return equipmentFileMapper.selectLastOneBySbbh(sbbh);
    }
}
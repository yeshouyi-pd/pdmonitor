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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class EquipmentFileService {

    @Resource
    private EquipmentFileMapper equipmentFileMapper;

    public List<String> findSbbh(EquipmentFileDto equipmentFileDto){
        return equipmentFileMapper.selectSbbhByExample(null);
    }

    public List<AlarmNumbersDto> statisticsAlarmNums(EquipmentFileExample example){
        return equipmentFileMapper.statisticsAlarmNums(example);
    }

    /**
    * 列表查询
    */
    public void list(EquipmentFileDto pageDto) {
        EquipmentFileExample audioExample = new EquipmentFileExample();
        EquipmentFileExample.Criteria audioCa = audioExample.createCriteria();
        audioCa.andTpljLike("%wav");
        List<String> audioFileList = equipmentFileMapper.selectAudioByExample(audioExample);
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = equipmentFileExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());//
        }
        ca.andTpljLike("%png");
        equipmentFileExample.setOrderByClause(" cjsj desc ");
        List<EquipmentFile> equipmentFileList = equipmentFileMapper.selectByExample(equipmentFileExample);
        PageInfo<EquipmentFile> pageInfo = new PageInfo<>(equipmentFileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileDto> equipmentFileDtoList = CopyUtil.copyList(equipmentFileList, EquipmentFileDto.class);
        for(EquipmentFileDto item : equipmentFileDtoList){
            if(audioFileList.contains(item.getTplj().substring(0,item.getTplj().length()-3)+"wav")){
                item.setHasAudio(true);
            }else{
                item.setHasAudio(false);
            }
        }
        pageDto.setList(equipmentFileDtoList);
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
}
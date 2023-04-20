package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentFileTy;
import com.pd.server.main.domain.EquipmentFileTyExample;
import com.pd.server.main.dto.EquipmentFileTyDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.GpsKVDto;
import com.pd.server.main.mapper.EquipmentFileTyMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class EquipmentFileTyService {

    @Resource
    private EquipmentFileTyMapper equipmentFileTyMapper;

    public List<GpsKVDto> selectGpsByDto(EquipmentFileTyDto equipmentFileTyDto){
        return equipmentFileTyMapper.selectGpsByDto(equipmentFileTyDto);
    }

    public List<GpsKVDto> selectGpsByExample(EquipmentFileTyExample example){
        return equipmentFileTyMapper.selectGpsByExample(example);
    }

    public EquipmentFileTy selectByPrimaryKey(String id){
        return equipmentFileTyMapper.selectByPrimaryKey(id);
    }

    public List<GpsKVDto> selectTodayGps(String rq, String sbbh){
        return equipmentFileTyMapper.selectTodayGps(rq,sbbh);
    }

    public List<EquipmentFileTy> selectByExample(EquipmentFileTyExample example){
        return equipmentFileTyMapper.selectByExample(example);
    }

    public List<EquipmentFileTy> selectByExampleSpecial(EquipmentFileTyDto record){
        return equipmentFileTyMapper.selectByExampleSpecial(record);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(EquipmentFileTyDto equipmentFileTyDto) {
        EquipmentFileTy equipmentFileTy = CopyUtil.copy(equipmentFileTyDto, EquipmentFileTy.class);
        if (StringUtils.isEmpty(equipmentFileTyDto.getId())) {
            this.insert(equipmentFileTy);
        } else {
            this.update(equipmentFileTy);
        }
    }

    /**
    * 新增
    */
    private void insert(EquipmentFileTy equipmentFileTy) {
                Date now = new Date();
        equipmentFileTy.setId(UuidUtil.getShortUuid());
        equipmentFileTyMapper.insert(equipmentFileTy);
    }

    /**
    * 更新
    */
    public void update(EquipmentFileTy equipmentFileTy) {
        equipmentFileTyMapper.updateByPrimaryKeySelective(equipmentFileTy);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        equipmentFileTyMapper.deleteByPrimaryKey(id);
    }

}
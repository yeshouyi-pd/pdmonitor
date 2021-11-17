package com.pd.server.main.service;

import com.pd.server.main.domain.WaterProEquip;
import com.pd.server.main.domain.WaterProEquipExample;
import com.pd.server.main.dto.WaterProEquipDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WaterProEquipMapper;
import com.pd.server.util.CopyUtil;
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
public class WaterProEquipService {

    @Resource
    private WaterProEquipMapper waterProEquipMapper;

    public List<String> findSbsnByXmbh(String xmbh){
        return waterProEquipMapper.findSbsnByXmbh(xmbh);
    }

    public List<WaterProEquip> findByXmbh(String xmbh){
        WaterProEquipExample waterProEquipExample = new WaterProEquipExample();
        waterProEquipExample.createCriteria().andXmbhEqualTo(xmbh);
        return waterProEquipMapper.selectByExample(waterProEquipExample);
    }

    public List<WaterProEquip> selectByExample(WaterProEquipExample example){
        return waterProEquipMapper.selectByExample(example);
    }

    /**
    * 列表查询
    */
    public void list(WaterProEquipDto pageDto, List<String> xmbhs) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaterProEquipExample waterProEquipExample = new WaterProEquipExample();
        WaterProEquipExample.Criteria ca = waterProEquipExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbsn())){
            ca.andSbsnEqualTo(pageDto.getSbsn());
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            ca.andXmbhEqualTo(pageDto.getXmbh());
        }
        if(!CollectionUtils.isEmpty(xmbhs)){
            ca.andXmbhIn(xmbhs);
        }
        waterProEquipExample.setOrderByClause(" cjsj desc ");
        List<WaterProEquip> waterProEquipList = waterProEquipMapper.selectByExample(waterProEquipExample);
        PageInfo<WaterProEquip> pageInfo = new PageInfo<>(waterProEquipList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterProEquipDto> waterProEquipDtoList = CopyUtil.copyList(waterProEquipList, WaterProEquipDto.class);
        pageDto.setList(waterProEquipDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterProEquipDto waterProEquipDto) {
        WaterProEquip waterProEquip = CopyUtil.copy(waterProEquipDto, WaterProEquip.class);
        if (StringUtils.isEmpty(waterProEquipDto.getId())) {
            this.insert(waterProEquip);
        } else {
            this.update(waterProEquip);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterProEquip waterProEquip) {
        Date now = new Date();
        waterProEquip.setId(UuidUtil.getShortUuid());
        waterProEquipMapper.insert(waterProEquip);
    }

    /**
    * 更新
    */
    private void update(WaterProEquip waterProEquip) {
        waterProEquipMapper.updateByPrimaryKey(waterProEquip);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterProEquipMapper.deleteByPrimaryKey(id);
    }

}
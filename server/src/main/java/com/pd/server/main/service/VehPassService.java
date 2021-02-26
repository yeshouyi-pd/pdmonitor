package com.pd.server.main.service;

import com.pd.server.main.domain.VehPass;
import com.pd.server.main.domain.VehPassExample;
import com.pd.server.main.dto.VehPassDto;
import com.pd.server.main.mapper.VehPassMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class VehPassService {

@Resource
private VehPassMapper vehPassMapper;

/**
* 列表查询
*/
public List list(VehPassExample vehPassExample) {
    List<VehPass> vehPassList = vehPassMapper.selectByExample(vehPassExample);
    return vehPassList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehPassDto vehPassDto) {
    VehPass vehPass = CopyUtil.copy(vehPassDto, VehPass.class);
    if (StringUtils.isEmpty(vehPassDto.getId())) {
    this.insert(vehPass);
    } else {
    this.update(vehPass);
    }
    }

    public void add(VehPassDto vehPassDto){
        VehPass vehPass = CopyUtil.copy(vehPassDto, VehPass.class);
        VehPassExample vehPassExample = new VehPassExample();
        VehPassExample.Criteria ca = vehPassExample.createCriteria();
        ca.andIdEqualTo(vehPassDto.getId());
        long count = vehPassMapper.countByExample(vehPassExample);
        if(count > 0){
            vehPassMapper.insert(vehPass);
        }else{
            vehPassMapper.updateByPrimaryKey(vehPass);
        }

    }

    /**
    * 新增
    */
    private void insert(VehPass vehPass) {
            Date now = new Date();
    vehPass.setId(UuidUtil.getShortUuid());
    vehPassMapper.insert(vehPass);
    }

    /**
    * 更新
    */
    private void update(VehPass vehPass) {
    vehPassMapper.updateByPrimaryKey(vehPass);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehPassMapper.deleteByPrimaryKey(id);
    }
    }

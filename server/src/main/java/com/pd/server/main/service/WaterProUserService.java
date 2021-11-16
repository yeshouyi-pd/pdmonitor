package com.pd.server.main.service;

import com.pd.server.main.domain.WaterProUser;
import com.pd.server.main.domain.WaterProUserExample;
import com.pd.server.main.dto.WaterProUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WaterProUserMapper;
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
public class WaterProUserService {

    @Resource
    private WaterProUserMapper waterProUserMapper;

    public List<WaterProUser> findByXmbh(String xmbh){
        WaterProUserExample waterProUserExample = new WaterProUserExample();
        waterProUserExample.createCriteria().andXmbhEqualTo(xmbh);
        return waterProUserMapper.selectByExample(waterProUserExample);
    }

    public List<WaterProUser> selectByExample(WaterProUserExample example){
        return waterProUserMapper.selectByExample(example);
    }

    /**
    * 列表查询
    */
    public void list(WaterProUserDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaterProUserExample waterProUserExample = new WaterProUserExample();
        WaterProUserExample.Criteria ca = waterProUserExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            ca.andXmbhEqualTo(pageDto.getXmbh());
        }
        if(!StringUtils.isEmpty(pageDto.getUsercode())){
            ca.andUsercodeEqualTo(pageDto.getUsercode());
        }
        waterProUserExample.setOrderByClause(" cjsj desc ");
        List<WaterProUser> waterProUserList = waterProUserMapper.selectByExample(waterProUserExample);
        PageInfo<WaterProUser> pageInfo = new PageInfo<>(waterProUserList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterProUserDto> waterProUserDtoList = CopyUtil.copyList(waterProUserList, WaterProUserDto.class);
        pageDto.setList(waterProUserDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterProUserDto waterProUserDto) {
        WaterProUser waterProUser = CopyUtil.copy(waterProUserDto, WaterProUser.class);
        if (StringUtils.isEmpty(waterProUserDto.getId())) {
            this.insert(waterProUser);
        } else {
            this.update(waterProUser);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterProUser waterProUser) {
        Date now = new Date();
        waterProUser.setId(UuidUtil.getShortUuid());
        waterProUserMapper.insert(waterProUser);
    }

    /**
    * 更新
    */
    private void update(WaterProUser waterProUser) {
        waterProUserMapper.updateByPrimaryKey(waterProUser);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterProUserMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.AddrInfo;
import com.pd.server.main.domain.AddrInfoExample;
import com.pd.server.main.dto.AddrInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AddrInfoMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddrInfoService {

@Resource
private AddrInfoMapper addrInfoMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AddrInfoExample addrInfoExample = new AddrInfoExample();
        List<AddrInfo> addrInfoList = addrInfoMapper.selectByExample(addrInfoExample);
        PageInfo<AddrInfo> pageInfo = new PageInfo<>(addrInfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AddrInfoDto> addrInfoDtoList = CopyUtil.copyList(addrInfoList, AddrInfoDto.class);
        pageDto.setList(addrInfoDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AddrInfoDto addrInfoDto) {
    AddrInfo addrInfo = CopyUtil.copy(addrInfoDto, AddrInfo.class);
    if (StringUtils.isEmpty(addrInfoDto.getId())) {
    this.insert(addrInfo);
    } else {
    this.update(addrInfo);
    }
    }

    /**
    * 新增
    */
    private void insert(AddrInfo addrInfo) {
    addrInfo.setId(UuidUtil.getShortUuid());
    addrInfoMapper.insert(addrInfo);
    }

    /**
    * 更新
    */
    private void update(AddrInfo addrInfo) {
    addrInfoMapper.updateByPrimaryKey(addrInfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    addrInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询所有
     */
    public List<AddrInfo> findListAll(){
        AddrInfoExample addrInfoExample = new AddrInfoExample();
        List<AddrInfo> addrInfoList = addrInfoMapper.selectByExample(addrInfoExample);
        return addrInfoList;
    }

    }


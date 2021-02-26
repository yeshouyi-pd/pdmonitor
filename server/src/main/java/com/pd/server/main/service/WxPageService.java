package com.pd.server.main.service;

import com.pd.server.main.domain.WxPage;
import com.pd.server.main.domain.WxPageExample;
import com.pd.server.main.dto.WxPageDto;
import com.pd.server.main.mapper.WxPageMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WxPageService {

    @Resource
    private WxPageMapper wxPageMapper;

    /**
    * 列表查询
    */
    public void list(WxPageDto wxPageDto) {
        PageHelper.startPage(wxPageDto.getPage(), wxPageDto.getSize());
        WxPageExample wxPageExample = new WxPageExample();
        WxPageExample.Criteria ca = wxPageExample.createCriteria();
        if(!StringUtils.isEmpty(wxPageDto.getLevel())){
            ca.andLevelEqualTo(wxPageDto.getLevel());
        }
        if(!StringUtils.isEmpty(wxPageDto.getIsEnable())){
            ca.andIsEnableEqualTo(wxPageDto.getIsEnable());
        }
        if(!StringUtils.isEmpty(wxPageDto.getParentName())){
            ca.andParentNameEqualTo(wxPageDto.getParentName());
        }
        wxPageExample.setOrderByClause(" sx");
        List<WxPage> wxPageList = wxPageMapper.selectByExample(wxPageExample);
        PageInfo<WxPage> pageInfo = new PageInfo<>(wxPageList);
        wxPageDto.setTotal(pageInfo.getTotal());
        List<WxPageDto> wxPageDtoList = CopyUtil.copyList(wxPageList, WxPageDto.class);
        wxPageDto.setList(wxPageDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WxPageDto wxPageDto) {
        WxPage wxPage = CopyUtil.copy(wxPageDto, WxPage.class);
        if (StringUtils.isEmpty(wxPageDto.getId())) {
            this.insert(wxPage);
        } else {
            this.update(wxPage);
        }
    }

    /**
    * 新增
    */
    private void insert(WxPage wxPage) {
        wxPage.setId(UuidUtil.getShortUuid());
        wxPageMapper.insert(wxPage);
    }

    /**
    * 更新
    */
    private void update(WxPage wxPage) {
        wxPageMapper.updateByPrimaryKeySelective(wxPage);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        wxPageMapper.deleteByPrimaryKey(id);
    }

    public List<WxPage> findAllByParam(WxPageDto wxPageDto){
        WxPageExample wxPageExample = new WxPageExample();
        WxPageExample.Criteria ca = wxPageExample.createCriteria();
        if(!StringUtils.isEmpty(wxPageDto.getLevel())){
            ca.andLevelEqualTo(wxPageDto.getLevel());
        }
        if(!StringUtils.isEmpty(wxPageDto.getIsEnable())){
            ca.andIsEnableEqualTo(wxPageDto.getIsEnable());
        }
        if(!StringUtils.isEmpty(wxPageDto.getParentName())){
            ca.andParentNameEqualTo(wxPageDto.getParentName());
        }
        wxPageExample.setOrderByClause(" sx");
        List<WxPage> wxPageList = wxPageMapper.selectByExample(wxPageExample);
        return wxPageList;
    }
}

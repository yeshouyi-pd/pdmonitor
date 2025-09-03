package com.pd.server.main.service;

import com.pd.server.main.domain.AppMonitorDiscovery;
import com.pd.server.main.domain.AppMonitorDiscoveryExample;
import com.pd.server.main.dto.AppMonitorDiscoveryDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AppMonitorDiscoveryMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class AppMonitorDiscoveryService {

    @Resource
    private AppMonitorDiscoveryMapper appMonitorDiscoveryMapper;

    /**
    * 列表查询
    */
    public PageDto list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppMonitorDiscoveryExample appMonitorDiscoveryExample = new AppMonitorDiscoveryExample();
        AppMonitorDiscoveryExample.Criteria criteria = appMonitorDiscoveryExample.createCriteria();
        AppMonitorDiscoveryDto appMonitorDiscoveryDto = CopyUtil.copy(pageDto, AppMonitorDiscoveryDto.class);
        if(StringUtils.isNotBlank(appMonitorDiscoveryDto.getDeptcode())){
            criteria.andDeptcodeEqualTo(appMonitorDiscoveryDto.getDeptcode());
        }
        if (StringUtils.isNotBlank(appMonitorDiscoveryDto.getGczxm())){
            criteria.andGczxmEqualTo(appMonitorDiscoveryDto.getGczxm());
        }
        appMonitorDiscoveryExample.setOrderByClause(" scjs desc");

        List<AppMonitorDiscovery> appMonitorDiscoveryList = appMonitorDiscoveryMapper.selectByExample(appMonitorDiscoveryExample);
        PageInfo<AppMonitorDiscovery> pageInfo = new PageInfo<>(appMonitorDiscoveryList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppMonitorDiscoveryDto> appMonitorDiscoveryDtoList = CopyUtil.copyList(appMonitorDiscoveryList, AppMonitorDiscoveryDto.class);
        pageDto.setList(appMonitorDiscoveryDtoList);
        return pageDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        AppMonitorDiscovery appMonitorDiscovery = CopyUtil.copy(appMonitorDiscoveryDto, AppMonitorDiscovery.class);
        try {
            appMonitorDiscovery.setScjs(new Date());
            this.insert(appMonitorDiscovery);
        } catch (DuplicateKeyException e) {
            this.update(appMonitorDiscovery);
        }


    }

    /**
    * 新增
    */
    private void insert(AppMonitorDiscovery appMonitorDiscovery) {
                Date now = new Date();
        appMonitorDiscoveryMapper.insert(appMonitorDiscovery);
    }

    /**
    * 更新
    */
    private void update(AppMonitorDiscovery appMonitorDiscovery) {
        appMonitorDiscoveryMapper.updateByPrimaryKey(appMonitorDiscovery);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appMonitorDiscoveryMapper.deleteByPrimaryKey(id);
    }

    public List<AppMonitorDiscovery> selectByExample(AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        AppMonitorDiscoveryExample appMonitorDiscoveryExample = new AppMonitorDiscoveryExample();
        AppMonitorDiscoveryExample.Criteria criteria = appMonitorDiscoveryExample.createCriteria();
        criteria.andDeptcodeEqualTo(appMonitorDiscoveryDto.getDeptcode());
        return appMonitorDiscoveryMapper.selectByExample(appMonitorDiscoveryExample);
    }

    public List<AppMonitorDiscovery> selectByMid(String mid) {
        AppMonitorDiscoveryExample appMonitorDiscoveryExample = new AppMonitorDiscoveryExample();
        AppMonitorDiscoveryExample.Criteria criteria = appMonitorDiscoveryExample.createCriteria();
        criteria.andMidEqualTo(mid);
        return appMonitorDiscoveryMapper.selectByExample(appMonitorDiscoveryExample);
    }

    /**
     * 根据mid查询发现江豚信息，按fxsj倒序排列
     */
    public List<AppMonitorDiscoveryDto> findByMidOrderByFxsjDesc(String mid) {
        AppMonitorDiscoveryExample example = new AppMonitorDiscoveryExample();
        AppMonitorDiscoveryExample.Criteria criteria = example.createCriteria();
        criteria.andMidEqualTo(mid);
        example.setOrderByClause("fxsj desc");
        
        List<AppMonitorDiscovery> list = appMonitorDiscoveryMapper.selectByExample(example);
        return CopyUtil.copyList(list, AppMonitorDiscoveryDto.class);
    }
}
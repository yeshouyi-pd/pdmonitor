package com.pd.server.main.service;

import com.pd.server.main.domain.AppMonitorInfo;
import com.pd.server.main.domain.AppMonitorInfoExample;
import com.pd.server.main.dto.AppMonitorInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AppMonitorInfoMapper;
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
public class AppMonitorInfoService {

    @Resource
    private AppMonitorInfoMapper appMonitorInfoMapper;

    /**
    * 列表查询
    */
    public PageDto list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppMonitorInfoExample appMonitorInfoExample = new AppMonitorInfoExample();
        AppMonitorInfoExample.Criteria ca = appMonitorInfoExample.createCriteria();
        AppMonitorInfoDto appMonitorInfoDto = CopyUtil.copy(pageDto, AppMonitorInfoDto.class);
        
        // 部门代码查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getDeptcode())){
            ca.andDeptcodeEqualTo(appMonitorInfoDto.getDeptcode());
        }
        
        // 考察船类型查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getKcclx())){
            ca.andKcclxEqualTo(appMonitorInfoDto.getKcclx());
        }
        
        // 观察者中文名查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getGczzwm())){
            ca.andGczzwmLike("%" + appMonitorInfoDto.getGczzwm().trim() + "%");
        }
        
        // 观察者姓名查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getGczxm())){
            ca.andGczxmLike("%" + appMonitorInfoDto.getGczxm().trim() + "%");
        }
        
        // 考察区域查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getGcqy())){
            ca.andGcqyLike("%" + appMonitorInfoDto.getGcqy().trim() + "%");
        }
        
        // 考察单位查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getGcdw())){
            ca.andGcdwLike("%" + appMonitorInfoDto.getGcdw().trim() + "%");
        }
        
        // 独立观察者所用方法查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getPdlgczsyff())){
            ca.andPDlgczsyffEqualTo(appMonitorInfoDto.getPdlgczsyff());
        }
        
        // 总体观察状况查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getWztgczk())){
            ca.andWZtgczkEqualTo(appMonitorInfoDto.getWztgczk());
        }
        
        // 眩光所在范围查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getWxgszfw())){
            ca.andWXgszfwEqualTo(appMonitorInfoDto.getWxgszfw());
        }
        
        // 眩光状况查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getWxgzk())){
            ca.andWXgzkEqualTo(appMonitorInfoDto.getWxgzk());
        }
        
        // 近岸方向查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getNjafx())){
            ca.andNJafxEqualTo(appMonitorInfoDto.getNjafx());
        }
        
        // 方向查询
        if(StringUtils.isNotBlank(appMonitorInfoDto.getNfx())){
            ca.andNFxEqualTo(appMonitorInfoDto.getNfx());
        }
        
        appMonitorInfoExample.setOrderByClause(" ksgcsj desc");
        List<AppMonitorInfo> appMonitorInfoList = appMonitorInfoMapper.selectByExample(appMonitorInfoExample);
        PageInfo<AppMonitorInfo> pageInfo = new PageInfo<>(appMonitorInfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppMonitorInfoDto> appMonitorInfoDtoList = CopyUtil.copyList(appMonitorInfoList, AppMonitorInfoDto.class);
        pageDto.setList(appMonitorInfoDtoList);
        return pageDto;
    }

    public List<AppMonitorInfo> selectByExample( AppMonitorInfoDto appMonitorInfoDto) {
        AppMonitorInfoExample appMonitorInfoExample = new AppMonitorInfoExample();
        AppMonitorInfoExample.Criteria ca = appMonitorInfoExample.createCriteria();
        ca.andDeptcodeEqualTo(appMonitorInfoDto.getDeptcode());
        appMonitorInfoExample.setOrderByClause(" ksgcsj desc");
        return appMonitorInfoMapper.selectByExample(appMonitorInfoExample);

    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppMonitorInfoDto appMonitorInfoDto) {
        AppMonitorInfo appMonitorInfo = CopyUtil.copy(appMonitorInfoDto, AppMonitorInfo.class);
        try {
            appMonitorInfo.setScsj(new Date());
            this.insert(appMonitorInfo);
        } catch (DuplicateKeyException e) {
            appMonitorInfo.setGxsj(new Date());
            this.update(appMonitorInfo);
        }
    }

    /**
    * 新增
    */
    private void insert(AppMonitorInfo appMonitorInfo) {
                Date now = new Date();
        appMonitorInfoMapper.insert(appMonitorInfo);
    }

    /**
    * 更新
    */
    private void update(AppMonitorInfo appMonitorInfo) {
        appMonitorInfoMapper.updateByPrimaryKey(appMonitorInfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appMonitorInfoMapper.deleteByPrimaryKey(id);
    }

    public AppMonitorInfo findById(String id) {
        return appMonitorInfoMapper.selectByPrimaryKey(id);
    }
}
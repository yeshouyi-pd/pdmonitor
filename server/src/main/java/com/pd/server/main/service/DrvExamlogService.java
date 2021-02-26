package com.pd.server.main.service;

import com.pd.server.main.domain.DrvExamlog;
import com.pd.server.main.domain.DrvExamlogExample;
import com.pd.server.main.dto.DrvExamlogDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.DrvExamlogMapper;
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
public class DrvExamlogService {

    @Resource
    private DrvExamlogMapper drvExamlogMapper;

    /**
    * 列表查询
    */
    public void list(DrvExamlogDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DrvExamlogExample drvExamlogExample = new DrvExamlogExample();
        DrvExamlogExample.Criteria ca = drvExamlogExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXm())){
            ca.andXmEqualTo(pageDto.getXm());
        }
        if(!StringUtils.isEmpty(pageDto.getCfbh())){
            ca.andCfbhEqualTo(pageDto.getCfbh());
        }
        List<DrvExamlog> drvExamlogList = drvExamlogMapper.selectByExample(drvExamlogExample);
        PageInfo<DrvExamlog> pageInfo = new PageInfo<>(drvExamlogList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DrvExamlogDto> drvExamlogDtoList = CopyUtil.copyList(drvExamlogList, DrvExamlogDto.class);
        pageDto.setList(drvExamlogDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DrvExamlogDto drvExamlogDto) {
        DrvExamlog drvExamlog = CopyUtil.copy(drvExamlogDto, DrvExamlog.class);
        if (StringUtils.isEmpty(drvExamlogDto.getId())) {
            this.insert(drvExamlog);
        } else {
            this.update(drvExamlog);
        }
    }

    /**
    * 新增
    */
    public void insert(DrvExamlog drvExamlog) {
        Date now = new Date();
        drvExamlog.setId(UuidUtil.getShortUuid());
        drvExamlogMapper.insert(drvExamlog);
    }

    /**
    * 更新
    */
    private void update(DrvExamlog drvExamlog) {
        drvExamlogMapper.updateByPrimaryKey(drvExamlog);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        drvExamlogMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据条件查询结果（不分页）
     */
    public List<DrvExamlogDto> findLog(DrvExamlogDto pageDto) {
        DrvExamlogExample drvExamlogExample = new DrvExamlogExample();
        DrvExamlogExample.Criteria ca = drvExamlogExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXm())){
            ca.andXmEqualTo(pageDto.getXm());
        }
        if(!StringUtils.isEmpty(pageDto.getSfzmhm())){
            ca.andSfzmhmEqualTo(pageDto.getSfzmhm());
        }
        if(!StringUtils.isEmpty(pageDto.getCfbh())){
            ca.andCfbhEqualTo(pageDto.getCfbh());
        }
        drvExamlogExample.setOrderByClause(" kssj desc");
        List<DrvExamlog> drvExamlogList = drvExamlogMapper.selectByExample(drvExamlogExample);
        List<DrvExamlogDto> drvExamlogDtoList = CopyUtil.copyList(drvExamlogList, DrvExamlogDto.class);
        return drvExamlogDtoList;
    }
}

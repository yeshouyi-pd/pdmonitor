package com.pd.server.main.service;

import com.pd.server.main.domain.DrvExamrecord;
import com.pd.server.main.domain.DrvExamrecordExample;
import com.pd.server.main.dto.DrvExamrecordDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.DrvExamrecordMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DrvExamrecordService {

    @Resource
    private DrvExamrecordMapper drvExamrecordMapper;

    /**
    * 列表查询
    */
    public void list(DrvExamrecordDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DrvExamrecordExample drvExamrecordExample = new DrvExamrecordExample();
        DrvExamrecordExample.Criteria ca = drvExamrecordExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getExamlogid())){
            ca.andExamlogidEqualTo(pageDto.getExamlogid());
        }
        if(!StringUtils.isEmpty(pageDto.getDtlb())){
            ca.andDtlbEqualTo(pageDto.getDtlb());
        }
        if(!StringUtils.isEmpty(pageDto.getTmbt())){
            ca.andTmbtLike("%"+pageDto.getTmbt()+"%");
        }
        drvExamrecordExample.setOrderByClause(" ktsx ASC");
        List<DrvExamrecord> drvExamrecordList = drvExamrecordMapper.selectByExample(drvExamrecordExample);
        PageInfo<DrvExamrecord> pageInfo = new PageInfo<>(drvExamrecordList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DrvExamrecordDto> drvExamrecordDtoList = CopyUtil.copyList(drvExamrecordList, DrvExamrecordDto.class);
        pageDto.setList(drvExamrecordDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DrvExamrecordDto drvExamrecordDto) {
        DrvExamrecord drvExamrecord = CopyUtil.copy(drvExamrecordDto, DrvExamrecord.class);
        if (StringUtils.isEmpty(drvExamrecordDto.getId())) {
            this.insert(drvExamrecord);
        } else {
            this.update(drvExamrecord);
        }
    }

    /**
    * 新增
    */
    private void insert(DrvExamrecord drvExamrecord) {
        drvExamrecord.setId(UuidUtil.getShortUuid());
        drvExamrecordMapper.insert(drvExamrecord);
    }

    /**
    * 更新
    */
    private void update(DrvExamrecord drvExamrecord) {
        drvExamrecordMapper.updateByPrimaryKey(drvExamrecord);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        drvExamrecordMapper.deleteByPrimaryKey(id);
    }

    /**
     * 通过日志id删除所有记录
     */
    public void deleteByLogid(String logid){
        drvExamrecordMapper.deleteByLogid(logid);
    }

    /**
     * 根据日志id查询所有题目
     */
    public List<DrvExamrecordDto> findByLogId(String logid){
        DrvExamrecordExample drvExamrecordExample = new DrvExamrecordExample();
        DrvExamrecordExample.Criteria ca = drvExamrecordExample.createCriteria();
        ca.andExamlogidEqualTo(logid);
        List<DrvExamrecord> drvExamrecordList = drvExamrecordMapper.selectByExample(drvExamrecordExample);
        List<DrvExamrecordDto> drvExamrecordDtoList = CopyUtil.copyList(drvExamrecordList, DrvExamrecordDto.class);
        return drvExamrecordDtoList;
    }
}

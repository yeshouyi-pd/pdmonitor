package com.pd.server.main.service;

import com.pd.server.main.domain.ReportFacilityfail;
import com.pd.server.main.domain.ReportFacilityfailExample;
import com.pd.server.main.dto.ReportFacilityfailDto;
import com.pd.server.main.mapper.ReportFacilityfailMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class ReportFacilityfailService {

@Resource
private ReportFacilityfailMapper reportFacilityfailMapper;

/**
* 列表查询
*/
public List<ReportFacilityfail> list(ReportFacilityfailExample reportFacilityfailExample) {
    List<ReportFacilityfail> reportFacilityfailList = reportFacilityfailMapper.selectByExample(reportFacilityfailExample);
    return reportFacilityfailList;
    }


    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(ReportFacilityfailDto reportFacilityfailDto) {
    ReportFacilityfail reportFacilityfail = CopyUtil.copy(reportFacilityfailDto, ReportFacilityfail.class);
    if (StringUtils.isEmpty(reportFacilityfailDto.getId())) {
    this.insert(reportFacilityfail);
    } else {
    this.update(reportFacilityfail);
    }
    }

    /**
    * 新增
    */
    private void insert(ReportFacilityfail reportFacilityfail) {
            Date now = new Date();
    reportFacilityfail.setId(UuidUtil.getShortUuid());
    reportFacilityfailMapper.insert(reportFacilityfail);
    }

    /**
    * 更新
    */
    private void update(ReportFacilityfail reportFacilityfail) {
    reportFacilityfailMapper.updateByPrimaryKey(reportFacilityfail);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    reportFacilityfailMapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据openid查询
     * @param openid
     * @return
     */
    public List<ReportFacilityfail> findByOpenid(String openid){
        ReportFacilityfailExample example = new ReportFacilityfailExample();
        example.createCriteria().andOpenidEqualTo(openid);
        List<ReportFacilityfail> list = reportFacilityfailMapper.selectByExample(example);
        return list;
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public ReportFacilityfail findByID(String id){
        ReportFacilityfail reportFacilityfail = reportFacilityfailMapper.selectByPrimaryKey(id);
        return reportFacilityfail;
    }

}

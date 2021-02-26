package com.pd.server.main.service;

import com.pd.server.main.domain.ReportWater;
import com.pd.server.main.domain.ReportWaterExample;
import com.pd.server.main.dto.ReportWaterDto;
import com.pd.server.main.mapper.ReportWaterMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class ReportWaterService {

@Resource
private ReportWaterMapper reportWaterMapper;

/**
* 列表查询
*/
public List<ReportWater> list(ReportWaterExample reportWaterExample) {
    List<ReportWater> reportWaterList = reportWaterMapper.selectByExample(reportWaterExample);
    return reportWaterList;

    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(ReportWaterDto reportWaterDto) {
    ReportWater reportWater = CopyUtil.copy(reportWaterDto, ReportWater.class);
    if (StringUtils.isEmpty(reportWaterDto.getId())) {
    this.insert(reportWater);
    } else {
    this.update(reportWater);
    }
    }

    /**
    * 新增
    */
    private void insert(ReportWater reportWater) {
            Date now = new Date();
    reportWater.setId(UuidUtil.getShortUuid());
    reportWaterMapper.insert(reportWater);
    }

    /**
    * 更新
    */
    private void update(ReportWater reportWater) {
    reportWaterMapper.updateByPrimaryKey(reportWater);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    reportWaterMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据openid查询
     * @param openid
     * @return
     */
    public List<ReportWater> findByOpenid(String openid){
        ReportWaterExample example = new ReportWaterExample();
        example.createCriteria().andOpenidEqualTo(openid);
        List<ReportWater> list = reportWaterMapper.selectByExample(example);
        return list;
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public ReportWater findByID(String id){
        return reportWaterMapper.selectByPrimaryKey(id);
    }

}

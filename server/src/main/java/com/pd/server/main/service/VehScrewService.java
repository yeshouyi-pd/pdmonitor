package com.pd.server.main.service;

import com.pd.server.main.domain.VehScrew;
import com.pd.server.main.domain.VehScrewExample;
import com.pd.server.main.dto.VehScrewDto;
import com.pd.server.main.mapper.VehScrewMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class VehScrewService {

@Resource
private VehScrewMapper vehScrewMapper;

/**
* 列表查询
*/
public List list(VehScrewExample vehScrewExample) {
    List<VehScrew> vehScrewList = vehScrewMapper.selectByExample(vehScrewExample);
    return vehScrewList;
    }

    /***
     * 线上固封螺丝申请处理列表
     * @param vehScrewExample
     */
    public List vehScrewCheck(VehScrewExample vehScrewExample) {
        List<VehScrew> vehScrewList = vehScrewMapper.selectByExample(vehScrewExample);
        return vehScrewList;
    }

    /**
     * 现场申领车牌固封螺丝申领处理
     * @param vehScrewExample
     */
    public List<VehScrew> vehScrewScene(VehScrewExample vehScrewExample) {
        List<VehScrew> vehScrewList = vehScrewMapper.selectByExample(vehScrewExample);
        return vehScrewList;
    }
    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehScrewDto vehScrewDto) {
    VehScrew vehScrew = CopyUtil.copy(vehScrewDto, VehScrew.class);
    if (StringUtils.isEmpty(vehScrewDto.getId())) {
    this.insert(vehScrew);
    } else {
    this.update(vehScrew);
    }
    }

    /**
    * 新增
    */
    private void insert(VehScrew vehScrew) {
            Date now = new Date();
    vehScrew.setId(UuidUtil.getShortUuid());
    vehScrewMapper.insert(vehScrew);
    }

    /**
    * 更新
    */
    private void update(VehScrew vehScrew) {
    vehScrewMapper.updateByPrimaryKey(vehScrew);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehScrewMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据sfzmhm查询
     * @param sfzmhm
     */
    public VehScrewDto findBySfzmhm(String sfzmhm){
        VehScrewExample example = new VehScrewExample();
        VehScrewExample.Criteria ca = example.createCriteria();
        ca.andSfzmhmEqualTo(sfzmhm);
        ca.andZtEqualTo("1");
        example.setOrderByClause(" create_time desc");
        List<VehScrew> list = vehScrewMapper.selectByExample(example);
        if(null != list && list.size() > 0){
            VehScrew vehScrew = list.get(0);
            VehScrewDto vehScrewDto = CopyUtil.copy(vehScrew, VehScrewDto.class);
            return vehScrewDto;
        }
        return null;
    }

    /**
     * 根据sfzmhm和sllb查询
     * @param sfzmhm
     */
    public VehScrewDto findBySfzmhmAndSllb(String sfzmhm,String sllb){
        VehScrewExample example = new VehScrewExample();
        VehScrewExample.Criteria ca = example.createCriteria();
        ca.andSfzmhmEqualTo(sfzmhm);
        ca.andSllbEqualTo(sllb);
        ca.andZtEqualTo("1");
        example.setOrderByClause(" create_time desc");
        List<VehScrew> list = vehScrewMapper.selectByExample(example);
        if(null != list && list.size() > 0){
            VehScrew vehScrew = list.get(0);
            VehScrewDto vehScrewDto = CopyUtil.copy(vehScrew, VehScrewDto.class);
            return vehScrewDto;
        }
        return null;
    }

    /**
     * 查询一年内申请的次数(一年不能超过2次)
     * @param sfzmhm
     * @return
     */
    public int sumCount(String sfzmhm){
        VehScrewExample example = new VehScrewExample();
        VehScrewExample.Criteria ca = example.createCriteria();
        ca.andSfzmhmEqualTo(sfzmhm);
        ca.andSllbEqualTo("1");
        ca.andCreateTimeBetween(DateUtil.getYearBefore(new Date()), new Date());
        List<VehScrew> list = vehScrewMapper.selectByExample(example);
        return list.size();
    }

    /**
     * 查询所以申请的次数(不能超过3次)
     * @param sfzmhm
     * @return
     */
    public int sumCountday(String sfzmhm){
        VehScrewExample example = new VehScrewExample();
        VehScrewExample.Criteria ca = example.createCriteria();
        ca.andSfzmhmEqualTo(sfzmhm);
        List<VehScrew> list = vehScrewMapper.selectByExample(example);
        return list.size();
    }

    /**
     * 根据车牌查询车辆领取固封螺丝次数
     * @param hphm
     * @return
     */
    public int getScrewByHphm(String hphm) {
        VehScrewExample example = new VehScrewExample();
        VehScrewExample.Criteria ca = example.createCriteria();
        ca.andHphmEqualTo(hphm);
        List<VehScrew> list = vehScrewMapper.selectByExample(example);
        return list.size();
    }

    /**
     * 查询异常申领统计报表
     * @param vehScrewExample
     * @return
     */
    public List<VehScrewDto> vehScrewReport(VehScrewExample vehScrewExample) {
        List<VehScrewDto> list = vehScrewMapper.vehScrewReport(vehScrewExample);
        return list;
    }

    /**
     * 查询申领类别统计报表
     * @param vehScrewExample
     * @return
     */
    public List<VehScrewDto> getSllbReport(VehScrewExample vehScrewExample) {
        List<VehScrewDto> list = vehScrewMapper.getSllbReport(vehScrewExample);
        return list;
    }
}

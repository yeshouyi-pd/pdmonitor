package com.pd.server.main.service;

import com.pd.server.main.domain.VehAccident;
import com.pd.server.main.domain.VehAccidentExample;
import com.pd.server.main.dto.VehAccidentDto;
import com.pd.server.main.mapper.VehAccidentMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehAccidentService {

    @Resource
    private VehAccidentMapper vehAccidentMapper;

    /**
     * 列表查询
     */
    public List<VehAccident> list(VehAccidentExample vehAccidentExample) {
        List<VehAccident> vehAccidentList = vehAccidentMapper.selectByExample(vehAccidentExample);
        return vehAccidentList;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(VehAccidentDto vehAccidentDto) {
        VehAccident vehAccident = CopyUtil.copy(vehAccidentDto, VehAccident.class);
        if (StringUtils.isEmpty(vehAccidentDto.getId())) {
            this.insert(vehAccident);
        } else {
            this.update(vehAccident);
        }
    }

    /**
     * 新增
     */
    public void insert(VehAccident vehAccident) {
        vehAccident.setId(UuidUtil.getShortUuid());
        vehAccidentMapper.insert(vehAccident);
    }

    /**
     * 更新
     */
    public void update(VehAccident vehAccident) {
        vehAccidentMapper.updateByPrimaryKeySelective(vehAccident);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        vehAccidentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据事故编号查询
     * @param sgbh
     * @return
     */
    public VehAccident findBySgbh(String sgbh){
        VehAccidentExample example = new VehAccidentExample();
        example.createCriteria().andSgbhEqualTo(sgbh);
        List<VehAccident> list = vehAccidentMapper.selectByExample(example);
        if(null != list && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据openid查询
     * @param openid
     * @return
     */
    public List<VehAccident> findByOpenid(String openid){
        VehAccidentExample example = new VehAccidentExample();
        example.createCriteria().andOpenidEqualTo(openid);
        example.setOrderByClause(" sgfssj ");
        List<VehAccident> list = vehAccidentMapper.selectByExample(example);
        return list;
    }

}

package com.pd.server.main.service;

import com.pd.server.main.domain.VehLicelost;
import com.pd.server.main.domain.VehLicelostExample;
import com.pd.server.main.dto.VehLicelostDto;
import com.pd.server.main.mapper.VehLicelostMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class VehLicelostService {

@Resource
private VehLicelostMapper vehLicelostMapper;

/**
* 列表查询
*/
public List<VehLicelost> list(VehLicelostExample vehLicelostExample) {
    List<VehLicelost> vehLicelostList = vehLicelostMapper.selectByExample(vehLicelostExample);
    return vehLicelostList;
}

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehLicelostDto vehLicelostDto) {
    VehLicelost vehLicelost = CopyUtil.copy(vehLicelostDto, VehLicelost.class);
    if (StringUtils.isEmpty(vehLicelostDto.getId())) {
    this.insert(vehLicelost);
    } else {
    this.update(vehLicelost);
    }
    }

    /**
    * 新增
    */
    private void insert(VehLicelost vehLicelost) {
            Date now = new Date();
    vehLicelost.setId(UuidUtil.getShortUuid());
    vehLicelostMapper.insert(vehLicelost);
    }

    /**
    * 更新
    */
    private void update(VehLicelost vehLicelost) {
    vehLicelostMapper.updateByPrimaryKey(vehLicelost);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehLicelostMapper.deleteByPrimaryKey(id);
    }

    /**
     * 条件查询
     * @param hpzl
     * @param hphm
     * @return
     */
    public VehLicelostDto findByHpzlAndHphm(String hpzl, String hphm){
        VehLicelostExample example = new VehLicelostExample();
        VehLicelostExample.Criteria ca = example.createCriteria();
        ca.andHpzlEqualTo(hpzl);
        ca.andHphmEqualTo(hphm);
        List<VehLicelost> list = vehLicelostMapper.selectByExample(example);
        if(null != list && list.size() > 0){
            VehLicelost vehLicelost = list.get(0);
            VehLicelostDto vehLicelostDto = CopyUtil.copy(vehLicelost, VehLicelostDto.class);
            return vehLicelostDto;
        }
        return null;
    }

}

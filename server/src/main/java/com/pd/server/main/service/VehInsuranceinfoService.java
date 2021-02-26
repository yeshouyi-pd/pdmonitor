package com.pd.server.main.service;

import com.pd.server.main.domain.VehInsuranceinfo;
import com.pd.server.main.domain.VehInsuranceinfoExample;
import com.pd.server.main.dto.VehInsuranceinfoDto;
import com.pd.server.main.mapper.VehInsuranceinfoMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class VehInsuranceinfoService {

@Resource
private VehInsuranceinfoMapper vehInsuranceinfoMapper;

/**
* 列表查询
*/
public List list(VehInsuranceinfoExample vehInsuranceinfoExample) {
    List<VehInsuranceinfo> vehInsuranceinfoList = vehInsuranceinfoMapper.selectByExample(vehInsuranceinfoExample);
    return vehInsuranceinfoList;

    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehInsuranceinfoDto vehInsuranceinfoDto) {
    VehInsuranceinfo vehInsuranceinfo = CopyUtil.copy(vehInsuranceinfoDto, VehInsuranceinfo.class);
    if (StringUtils.isEmpty(vehInsuranceinfoDto.getId())) {
    this.insert(vehInsuranceinfo);
    } else {
    this.update(vehInsuranceinfo);
    }
    }

    /**
    * 新增
    */
    private void insert(VehInsuranceinfo vehInsuranceinfo) {
            Date now = new Date();
    vehInsuranceinfo.setId(UuidUtil.getShortUuid());
    vehInsuranceinfoMapper.insert(vehInsuranceinfo);
    }

    /**
    * 更新
    */
    private void update(VehInsuranceinfo vehInsuranceinfo) {
    vehInsuranceinfoMapper.updateByPrimaryKey(vehInsuranceinfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehInsuranceinfoMapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据号牌号码查询
     * @param hphm
     * @return
     */
    public VehInsuranceinfoDto findByHphm(String hphm){
        VehInsuranceinfoExample example = new VehInsuranceinfoExample();
        VehInsuranceinfoExample.Criteria criteria = example.createCriteria();
        criteria.andHphmEqualTo(hphm);
        List<VehInsuranceinfo> list = vehInsuranceinfoMapper.selectByExample(example);
        if(null != list && list.size() > 0){
            VehInsuranceinfo vehInsuranceinfo = list.get(0);
            VehInsuranceinfoDto vehInsuranceinfoDto = CopyUtil.copy(vehInsuranceinfo, VehInsuranceinfoDto.class);
            return vehInsuranceinfoDto;
        }
        return null;
    }


}

package com.pd.server.main.service;

import com.pd.server.main.domain.VehRobbery;
import com.pd.server.main.domain.VehRobberyExample;
import com.pd.server.main.dto.VehRobberyDto;
import com.pd.server.main.mapper.VehRobberyMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class VehRobberyService {

@Resource
private VehRobberyMapper vehRobberyMapper;

/**
* 列表查询
*/
public List<VehRobbery> list(VehRobberyExample vehRobberyExample) {
    List<VehRobbery> vehRobberyList = vehRobberyMapper.selectByExample(vehRobberyExample);
    return vehRobberyList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public String save(VehRobberyDto vehRobberyDto) {
    VehRobbery vehRobbery = CopyUtil.copy(vehRobberyDto, VehRobbery.class);
    if (StringUtils.isEmpty(vehRobberyDto.getId())) {
        return this.insert(vehRobbery);
    } else {
        return this.update(vehRobbery);
    }
    }

    /**
    * 新增
    */
    private String insert(VehRobbery vehRobbery) {
            Date now = new Date();
    vehRobbery.setId(UuidUtil.getShortUuid());
    vehRobberyMapper.insert(vehRobbery);
        return vehRobbery.getId();
    }

    /**
    * 更新
    */
    private String update(VehRobbery vehRobbery) {
        vehRobberyMapper.updateByPrimaryKey(vehRobbery);
        return vehRobbery.getId();
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehRobberyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据ID查询
     * @return
     */
    public VehRobberyDto findByID(String id){
        VehRobbery vehRobbery = vehRobberyMapper.selectByPrimaryKey(id);
        VehRobberyDto vehRobberyDto = CopyUtil.copy(vehRobbery, VehRobberyDto.class);
        return vehRobberyDto;
    }

    /**
     * 根据身份证明号码查询信息
     */
    public List<VehRobbery> findBySfzmhm(String sfzmhm){
        VehRobberyExample example = new VehRobberyExample();
        VehRobberyExample.Criteria ca = example.createCriteria();
        ca.andSfzmhmEqualTo(sfzmhm);
        List<VehRobbery> list = vehRobberyMapper.selectByExample(example);
        return list;
    }


}

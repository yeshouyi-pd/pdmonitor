package com.pd.server.main.service;

import com.pd.server.main.domain.VehPassline;
import com.pd.server.main.domain.VehPasslineExample;
import com.pd.server.main.dto.VehPasslineDto;
import com.pd.server.main.mapper.VehPasslineMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class VehPasslineService {

@Resource
private VehPasslineMapper vehPasslineMapper;

/**
 * 列表查询
 */
public List list(VehPasslineExample vehPasslineExample) {
    List<VehPassline> vehPasslineList = vehPasslineMapper.selectByExample(vehPasslineExample);
    return vehPasslineList;
}

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehPasslineDto vehPasslineDto) {
    VehPassline vehPassline = CopyUtil.copy(vehPasslineDto, VehPassline.class);
    if (StringUtils.isEmpty(vehPasslineDto.getId())) {
    this.insert(vehPassline);
    } else {
    this.update(vehPassline);
    }
    }

    /**
    * 新增
    */
    private void insert(VehPassline vehPassline) {
            Date now = new Date();
    vehPassline.setId(UuidUtil.getShortUuid());
    vehPasslineMapper.insert(vehPassline);
    }

    /**
    * 更新
    */
    private void update(VehPassline vehPassline) {
    vehPasslineMapper.updateByPrimaryKey(vehPassline);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehPasslineMapper.deleteByPrimaryKey(id);
    }

    public List<VehPassline> getVehPassline() {
        VehPasslineExample vehPasslineExample = new VehPasslineExample();
        return vehPasslineMapper.selectByExample(vehPasslineExample);
    }
}

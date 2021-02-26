package com.pd.server.main.service;

import com.pd.server.main.domain.VehLogout;
import com.pd.server.main.domain.VehLogoutExample;
import com.pd.server.main.dto.VehLogoutDto;
import com.pd.server.main.mapper.VehLogoutMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class VehLogoutService {

@Resource
private VehLogoutMapper vehLogoutMapper;

/**
* 列表查询
*/
public List<VehLogout> list(VehLogoutExample vehLogoutExample) {
    List<VehLogout> vehLogoutList = vehLogoutMapper.selectByExample(vehLogoutExample);
    return vehLogoutList;

    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehLogoutDto vehLogoutDto) {
    VehLogout vehLogout = CopyUtil.copy(vehLogoutDto, VehLogout.class);
    if (StringUtils.isEmpty(vehLogoutDto.getId())) {
    this.insert(vehLogout);
    } else {
    this.update(vehLogout);
    }
    }

    /**
    * 新增
    */
    private void insert(VehLogout vehLogout) {
            Date now = new Date();
    vehLogout.setId(UuidUtil.getShortUuid());
    vehLogoutMapper.insert(vehLogout);
    }

    /**
    * 更新
    */
    private void update(VehLogout vehLogout) {
    vehLogoutMapper.updateByPrimaryKey(vehLogout);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehLogoutMapper.deleteByPrimaryKey(id);
    }
    }

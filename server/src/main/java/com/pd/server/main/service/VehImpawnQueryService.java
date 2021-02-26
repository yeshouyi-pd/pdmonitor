package com.pd.server.main.service;

import com.pd.server.main.domain.VehImpawnQuery;
import com.pd.server.main.domain.VehImpawnQueryExample;
import com.pd.server.main.dto.VehImpawnQueryDto;
import com.pd.server.main.mapper.VehImpawnQueryMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class VehImpawnQueryService {

@Resource
private VehImpawnQueryMapper vehImpawnQueryMapper;

/**
* 列表查询
*/
public List<VehImpawnQuery> list(VehImpawnQueryExample vehImpawnQueryExample) {
    List<VehImpawnQuery> vehLogoutList = vehImpawnQueryMapper.selectByExample(vehImpawnQueryExample);

    return vehLogoutList;
}

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehImpawnQueryDto vehImpawnQueryDto) {
    VehImpawnQuery vehImpawnQuery = CopyUtil.copy(vehImpawnQueryDto, VehImpawnQuery.class);
    if (StringUtils.isEmpty(vehImpawnQueryDto.getId())) {
    this.insert(vehImpawnQuery);
    } else {
    this.update(vehImpawnQuery);
    }
    }

    /**
    * 新增
    */
    private void insert(VehImpawnQuery vehImpawnQuery) {
            Date now = new Date();
    vehImpawnQuery.setId(UuidUtil.getShortUuid());
    vehImpawnQueryMapper.insert(vehImpawnQuery);
    }

    /**
    * 更新
    */
    private void update(VehImpawnQuery vehImpawnQuery) {
    vehImpawnQueryMapper.updateByPrimaryKey(vehImpawnQuery);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehImpawnQueryMapper.deleteByPrimaryKey(id);
    }
    }

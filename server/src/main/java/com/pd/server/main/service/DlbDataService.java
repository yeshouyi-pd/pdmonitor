package com.pd.server.main.service;

import com.pd.server.main.domain.DlbData;
import com.pd.server.main.domain.DlbDataExample;
import com.pd.server.main.dto.DlbDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.DlbDataMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class DlbDataService {

    @Resource
    private DlbDataMapper dlbDataMapper;

    /**
    * 列表查询
    */
    public List<DlbData> list(DlbDataExample dlbDataExample) {
        return dlbDataMapper.selectByExample(dlbDataExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DlbDataDto dlbDataDto) {
        DlbData dlbData = CopyUtil.copy(dlbDataDto, DlbData.class);
        if (StringUtils.isEmpty(dlbDataDto.getId())) {
            this.insert(dlbData);
        } else {
            this.update(dlbData);
        }
    }

    /**
    * 新增
    */
    private void insert(DlbData dlbData) {
                Date now = new Date();
//        dlbData.setId(UuidUtil.getShortUuid());
        dlbDataMapper.insertSelective(dlbData);
    }

    /**
    * 更新
    */
    private void update(DlbData dlbData) {
        dlbDataMapper.updateByPrimaryKey(dlbData);
    }

    /**
    * 删除
    */
    public void delete(Integer id) {
        dlbDataMapper.deleteByPrimaryKey(id);
    }

}
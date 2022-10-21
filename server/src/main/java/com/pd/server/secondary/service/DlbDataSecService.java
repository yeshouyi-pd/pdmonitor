package com.pd.server.secondary.service;

import com.pd.server.secondary.domain.DlbDataSec;
import com.pd.server.secondary.domain.DlbDataSecExample;
import com.pd.server.secondary.dto.DlbDataSecDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.secondary.mapper.DlbDataSecMapper;
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
public class DlbDataSecService {

    @Resource
    private DlbDataSecMapper dlbDataSecMapper;

    /**
    * 列表查询
    */
    public List<DlbDataSec> list(DlbDataSecExample example) {
        return dlbDataSecMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DlbDataSecDto dlbDataSecDto) {
        DlbDataSec dlbDataSec = CopyUtil.copy(dlbDataSecDto, DlbDataSec.class);
        if (StringUtils.isEmpty(dlbDataSecDto.getId())) {
            this.insert(dlbDataSec);
        } else {
            this.update(dlbDataSec);
        }
    }

    /**
    * 新增
    */
    private void insert(DlbDataSec dlbDataSec) {
                Date now = new Date();
        //dlbDataSec.setId(UuidUtil.getShortUuid());
        dlbDataSecMapper.insertSelective(dlbDataSec);
    }

    /**
    * 更新
    */
    private void update(DlbDataSec dlbDataSec) {
        dlbDataSecMapper.updateByPrimaryKey(dlbDataSec);
    }

    /**
    * 删除
    */
    public void delete(Integer id) {
        dlbDataSecMapper.deleteByPrimaryKey(id);
    }

}
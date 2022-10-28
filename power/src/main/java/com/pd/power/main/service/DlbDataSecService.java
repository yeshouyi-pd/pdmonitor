package com.pd.power.main.service;

import com.pd.power.main.domain.DlbDataSec;
import com.pd.power.main.domain.DlbDataSecExample;
import com.pd.power.main.dto.DlbDataSecDto;
import com.pd.power.main.mapper.DlbDataSecMapper;
import com.pd.power.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
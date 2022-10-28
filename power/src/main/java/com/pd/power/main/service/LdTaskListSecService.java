package com.pd.power.main.service;

import com.pd.power.main.domain.LdTaskListSec;
import com.pd.power.main.domain.LdTaskListSecExample;
import com.pd.power.main.dto.LdTaskListSecDto;
import com.pd.power.main.mapper.LdTaskListSecMapper;
import com.pd.power.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class LdTaskListSecService {

    @Resource
    private LdTaskListSecMapper ldTaskListSecMapper;

    /**
    * 列表查询
    */
    public List<LdTaskListSec> list(LdTaskListSecExample example) {
        return ldTaskListSecMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(LdTaskListSecDto ldTaskListSecDto) {
        LdTaskListSec ldTaskListSec = CopyUtil.copy(ldTaskListSecDto, LdTaskListSec.class);
        if (StringUtils.isEmpty(ldTaskListSecDto.getId())) {
            this.insert(ldTaskListSec);
        } else {
            this.update(ldTaskListSec);
        }
    }

    /**
    * 新增
    */
    private void insert(LdTaskListSec ldTaskListSec) {
                Date now = new Date();
        //ldTaskListSec.setId(UuidUtil.getShortUuid());
        ldTaskListSecMapper.insertSelective(ldTaskListSec);
    }

    /**
    * 更新
    */
    private void update(LdTaskListSec ldTaskListSec) {
        ldTaskListSecMapper.updateByPrimaryKey(ldTaskListSec);
    }

    /**
    * 删除
    */
    public void delete(Integer id) {
        ldTaskListSecMapper.deleteByPrimaryKey(id);
    }

}
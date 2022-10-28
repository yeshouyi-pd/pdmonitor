package com.pd.power.main.service;

import com.pd.power.main.domain.TdDataSec;
import com.pd.power.main.domain.TdDataSecExample;
import com.pd.power.main.dto.TdDataSecDto;
import com.pd.power.main.mapper.TdDataSecMapper;
import com.pd.power.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TdDataSecService {

    @Resource
    private TdDataSecMapper tdDataSecMapper;

    /**
    * 列表查询
    */
    public List<TdDataSec> list(TdDataSecExample example) {
        return tdDataSecMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(TdDataSecDto tdDataSecDto) {
        TdDataSec tdDataSec = CopyUtil.copy(tdDataSecDto, TdDataSec.class);
        if (StringUtils.isEmpty(tdDataSecDto.getId())) {
            this.insert(tdDataSec);
        } else {
            this.update(tdDataSec);
        }
    }

    /**
    * 新增
    */
    private void insert(TdDataSec tdDataSec) {
                Date now = new Date();
        //tdDataSec.setId(UuidUtil.getShortUuid());
        tdDataSecMapper.insertSelective(tdDataSec);
    }

    /**
    * 更新
    */
    private void update(TdDataSec tdDataSec) {
        tdDataSecMapper.updateByPrimaryKey(tdDataSec);
    }

    /**
    * 删除
    */
    public void delete(Integer id) {
        tdDataSecMapper.deleteByPrimaryKey(id);
    }

}
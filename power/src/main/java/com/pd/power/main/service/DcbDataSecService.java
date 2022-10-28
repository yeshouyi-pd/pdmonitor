package com.pd.power.main.service;

import com.pd.power.main.domain.DcbDataSec;
import com.pd.power.main.domain.DcbDataSecExample;
import com.pd.power.main.dto.DcbDataSecDto;
import com.pd.power.main.mapper.DcbDataSecMapper;
import com.pd.power.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DcbDataSecService {

    @Resource
    private DcbDataSecMapper dcbDataSecMapper;

    /**
    * 列表查询
    */
    public List<DcbDataSec> list(DcbDataSecExample example) {
        return dcbDataSecMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DcbDataSecDto dcbDataSecDto) {
        DcbDataSec dcbDataSec = CopyUtil.copy(dcbDataSecDto, DcbDataSec.class);
        if (StringUtils.isEmpty(dcbDataSecDto.getId())) {
            this.insert(dcbDataSec);
        } else {
            this.update(dcbDataSec);
        }
    }

    /**
    * 新增
    */
    private void insert(DcbDataSec dcbDataSec) {
                Date now = new Date();
        //dcbDataSec.setId(UuidUtil.getShortUuid());
        dcbDataSecMapper.insertSelective(dcbDataSec);
    }

    /**
    * 更新
    */
    private void update(DcbDataSec dcbDataSec) {
        dcbDataSecMapper.updateByPrimaryKey(dcbDataSec);
    }

    /**
    * 删除
    */
    public void delete(Integer id) {
        dcbDataSecMapper.deleteByPrimaryKey(id);
    }

}
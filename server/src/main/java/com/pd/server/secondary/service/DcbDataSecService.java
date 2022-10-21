package com.pd.server.secondary.service;

import com.pd.server.secondary.domain.DcbDataSec;
import com.pd.server.secondary.domain.DcbDataSecExample;
import com.pd.server.secondary.dto.DcbDataSecDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.secondary.mapper.DcbDataSecMapper;
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
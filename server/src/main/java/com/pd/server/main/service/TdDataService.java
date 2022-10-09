package com.pd.server.main.service;

import com.pd.server.main.domain.TdData;
import com.pd.server.main.domain.TdDataExample;
import com.pd.server.main.dto.TdDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.TdDataMapper;
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
public class TdDataService {

    @Resource
    private TdDataMapper tdDataMapper;

    /**
    * 列表查询
    */
    public List<TdData> list(TdDataExample tdDataExample) {
        return tdDataMapper.selectByExample(tdDataExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(TdDataDto tdDataDto) {
        TdData tdData = CopyUtil.copy(tdDataDto, TdData.class);
        if (StringUtils.isEmpty(tdDataDto.getId())) {
            this.insert(tdData);
        } else {
            this.update(tdData);
        }
    }

    /**
    * 新增
    */
    private void insert(TdData tdData) {
                Date now = new Date();
//        tdData.setId(UuidUtil.getShortUuid());
        tdDataMapper.insertSelective(tdData);
    }

    /**
    * 更新
    */
    private void update(TdData tdData) {
        tdDataMapper.updateByPrimaryKey(tdData);
    }

    /**
    * 删除
    */
    public void delete(Integer id) {
        tdDataMapper.deleteByPrimaryKey(id);
    }

}
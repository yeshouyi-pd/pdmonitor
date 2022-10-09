package com.pd.server.main.service;

import com.pd.server.main.domain.DcbData;
import com.pd.server.main.domain.DcbDataExample;
import com.pd.server.main.dto.DcbDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.DcbDataMapper;
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
public class DcbDataService {

    @Resource
    private DcbDataMapper dcbDataMapper;

    /**
    * 列表查询
    */
    public List<DcbData> list(DcbDataExample dcbDataExample) {
        return dcbDataMapper.selectByExample(dcbDataExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DcbDataDto dcbDataDto) {
        DcbData dcbData = CopyUtil.copy(dcbDataDto, DcbData.class);
        if (StringUtils.isEmpty(dcbDataDto.getId())) {
            this.insert(dcbData);
        } else {
            this.update(dcbData);
        }
    }

    /**
    * 新增
    */
    private void insert(DcbData dcbData) {
                Date now = new Date();
//        dcbData.setId(UuidUtil.getShortUuid());
        dcbDataMapper.insertSelective(dcbData);
    }

    /**
    * 更新
    */
    private void update(DcbData dcbData) {
        dcbDataMapper.updateByPrimaryKey(dcbData);
    }

    /**
    * 删除
    */
    public void delete(Integer id) {
        dcbDataMapper.deleteByPrimaryKey(id);
    }

}
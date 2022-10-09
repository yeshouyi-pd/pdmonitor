package com.pd.server.main.service;

import com.pd.server.main.domain.LdData;
import com.pd.server.main.domain.LdDataExample;
import com.pd.server.main.dto.LdDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.LdDataMapper;
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
public class LdDataService {

    @Resource
    private LdDataMapper ldDataMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        LdDataExample ldDataExample = new LdDataExample();
        List<LdData> ldDataList = ldDataMapper.selectByExample(ldDataExample);
        PageInfo<LdData> pageInfo = new PageInfo<>(ldDataList);
        pageDto.setTotal(pageInfo.getTotal());
        List<LdDataDto> ldDataDtoList = CopyUtil.copyList(ldDataList, LdDataDto.class);
        pageDto.setList(ldDataDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(LdDataDto ldDataDto) {
        LdData ldData = CopyUtil.copy(ldDataDto, LdData.class);
        if (StringUtils.isEmpty(ldDataDto.getId())) {
            this.insert(ldData);
        } else {
            this.update(ldData);
        }
    }

    /**
    * 新增
    */
    private void insert(LdData ldData) {
                Date now = new Date();
//        ldData.setId(UuidUtil.getShortUuid());
        ldDataMapper.insertSelective(ldData);
    }

    /**
    * 更新
    */
    private void update(LdData ldData) {
        ldDataMapper.updateByPrimaryKeySelective(ldData);
    }

    /**
    * 删除
    */
    public void delete(Integer id) {
        ldDataMapper.deleteByPrimaryKey(id);
    }

}
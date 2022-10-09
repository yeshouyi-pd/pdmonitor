package com.pd.server.main.service;

import com.pd.server.main.domain.LdList;
import com.pd.server.main.domain.LdListExample;
import com.pd.server.main.dto.LdListDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.LdListMapper;
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
public class LdListService {

    @Resource
    private LdListMapper ldListMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        LdListExample ldListExample = new LdListExample();
        List<LdList> ldListList = ldListMapper.selectByExample(ldListExample);
        PageInfo<LdList> pageInfo = new PageInfo<>(ldListList);
        pageDto.setTotal(pageInfo.getTotal());
        List<LdListDto> ldListDtoList = CopyUtil.copyList(ldListList, LdListDto.class);
        pageDto.setList(ldListDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(LdListDto ldListDto) {
        LdList ldList = CopyUtil.copy(ldListDto, LdList.class);
        if (StringUtils.isEmpty(ldListDto.getIccid())) {
            this.insert(ldList);
        } else {
            this.update(ldList);
        }
    }

    /**
    * 新增
    */
    private void insert(LdList ldList) {
                Date now = new Date();
//        ldList.setId(UuidUtil.getShortUuid());
        ldListMapper.insertSelective(ldList);
    }

    /**
    * 更新
    */
    private void update(LdList ldList) {
        ldListMapper.updateByPrimaryKey(ldList);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        ldListMapper.deleteByPrimaryKey(id);
    }

}
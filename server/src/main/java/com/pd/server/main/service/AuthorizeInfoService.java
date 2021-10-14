package com.pd.server.main.service;

import com.pd.server.main.domain.AuthorizeInfo;
import com.pd.server.main.domain.AuthorizeInfoExample;
import com.pd.server.main.dto.AuthorizeInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AuthorizeInfoMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthorizeInfoService {

    @Resource
    private AuthorizeInfoMapper authorizeInfoMapper;

    /**
    * 列表查询分页
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AuthorizeInfoExample authorizeInfoExample = new AuthorizeInfoExample();
        List<AuthorizeInfo> authorizeInfoList = authorizeInfoMapper.selectByExample(authorizeInfoExample);
        PageInfo<AuthorizeInfo> pageInfo = new PageInfo<>(authorizeInfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AuthorizeInfoDto> authorizeInfoDtoList = CopyUtil.copyList(authorizeInfoList, AuthorizeInfoDto.class);
        pageDto.setList(authorizeInfoDtoList);
    }

    /**
     * 列表查询不分页
     */
    public List<AuthorizeInfo> listAll(AuthorizeInfoExample authorizeInfoExample) {
        List<AuthorizeInfo> authorizeInfoList = authorizeInfoMapper.selectByExample(authorizeInfoExample);
        return authorizeInfoList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AuthorizeInfoDto authorizeInfoDto) {
        AuthorizeInfo authorizeInfo = CopyUtil.copy(authorizeInfoDto, AuthorizeInfo.class);
        if (StringUtils.isEmpty(authorizeInfoDto.getId())) {
            this.insert(authorizeInfo);
        } else {
            this.update(authorizeInfo);
        }
    }

    /**
    * 新增
    */
    private void insert(AuthorizeInfo authorizeInfo) {
        authorizeInfo.setId(UuidUtil.getShortUuid());
        authorizeInfoMapper.insert(authorizeInfo);
    }

    /**
    * 更新
    */
    private void update(AuthorizeInfo authorizeInfo) {
        authorizeInfoMapper.updateByPrimaryKey(authorizeInfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        authorizeInfoMapper.deleteByPrimaryKey(id);
    }

}
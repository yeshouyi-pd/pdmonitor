package com.pd.server.main.service;

import com.pd.server.main.domain.SpaceFile;
import com.pd.server.main.domain.SpaceFileExample;
import com.pd.server.main.dto.SpaceFileDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SpaceFileMapper;
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
public class SpaceFileService {

    @Resource
    private SpaceFileMapper spaceFileMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SpaceFileExample spaceFileExample = new SpaceFileExample();
        List<SpaceFile> spaceFileList = spaceFileMapper.selectByExample(spaceFileExample);
        PageInfo<SpaceFile> pageInfo = new PageInfo<>(spaceFileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SpaceFileDto> spaceFileDtoList = CopyUtil.copyList(spaceFileList, SpaceFileDto.class);
        pageDto.setList(spaceFileDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SpaceFileDto spaceFileDto) {
        SpaceFile spaceFile = CopyUtil.copy(spaceFileDto, SpaceFile.class);
        if (StringUtils.isEmpty(spaceFileDto.getId())) {
            this.insert(spaceFile);
        } else {
            this.update(spaceFile);
        }
    }

    /**
    * 新增
    */
    private void insert(SpaceFile spaceFile) {
                Date now = new Date();
        spaceFile.setId(UuidUtil.getShortUuid());
        spaceFileMapper.insert(spaceFile);
    }

    /**
    * 更新
    */
    private void update(SpaceFile spaceFile) {
        spaceFileMapper.updateByPrimaryKey(spaceFile);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        spaceFileMapper.deleteByPrimaryKey(id);
    }

}
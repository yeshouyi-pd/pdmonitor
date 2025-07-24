package com.pd.server.main.service;

import com.pd.server.main.domain.SpaceFile;
import com.pd.server.main.domain.SpaceFileExample;
import com.pd.server.main.dto.SpaceFileDto;
import com.pd.server.main.dto.basewx.my.SpaceFileExcel;
import com.pd.server.main.mapper.SpaceFileMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
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
    public List<SpaceFile> selectByExample(SpaceFileExample spaceFileExample) {
        return spaceFileMapper.selectByExample(spaceFileExample);
    }

    public List<SpaceFile> selectByExampleSpecial(SpaceFileDto record){
        return spaceFileMapper.selectByExampleSpecial(record);
    }

    public List<SpaceFileExcel> selectByExampleExcel(SpaceFileExample spaceFileExample){
        return spaceFileMapper.selectByExampleExcel(spaceFileExample);
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
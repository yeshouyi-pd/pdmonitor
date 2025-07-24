package com.pd.server.main.service;

import com.pd.server.main.domain.AngleFile;
import com.pd.server.main.domain.AngleFileExample;
import com.pd.server.main.dto.AngleFileDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.AngleFileMapper;
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
public class AngleFileService {

    @Resource
    private AngleFileMapper angleFileMapper;

    /**
    * 列表查询
    */
    public List<AngleFile> selectByExample(AngleFileExample angleFileExample) {
        return angleFileMapper.selectByExample(angleFileExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AngleFileDto angleFileDto) {
        AngleFile angleFile = CopyUtil.copy(angleFileDto, AngleFile.class);
        if (StringUtils.isEmpty(angleFileDto.getId())) {
            this.insert(angleFile);
        } else {
            this.update(angleFile);
        }
    }

    /**
    * 新增
    */
    private void insert(AngleFile angleFile) {
                Date now = new Date();
        angleFile.setId(UuidUtil.getShortUuid());
        angleFileMapper.insert(angleFile);
    }

    /**
    * 更新
    */
    private void update(AngleFile angleFile) {
        angleFileMapper.updateByPrimaryKey(angleFile);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        angleFileMapper.deleteByPrimaryKey(id);
    }

}
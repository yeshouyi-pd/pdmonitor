package com.pd.server.main.service;

import com.pd.server.main.domain.BeconFile;
import com.pd.server.main.domain.BeconFileExample;
import com.pd.server.main.domain.BeconFileStatistics;
import com.pd.server.main.dto.BeconFileDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.BeconFileMapper;
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
public class BeconFileService {

    @Resource
    private BeconFileMapper beconFileMapper;

    /**
    * 列表查询
    */
    public List<BeconFile> selectByExample(BeconFileExample beconFileExample) {
        return beconFileMapper.selectByExample(beconFileExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(BeconFileDto beconFileDto) {
        BeconFile beconFile = CopyUtil.copy(beconFileDto, BeconFile.class);
        if (StringUtils.isEmpty(beconFileDto.getId())) {
            this.insert(beconFile);
        } else {
            this.update(beconFile);
        }
    }

    /**
    * 新增
    */
    private void insert(BeconFile beconFile) {
                Date now = new Date();
        beconFile.setId(UuidUtil.getShortUuid());
        beconFileMapper.insert(beconFile);
    }

    /**
    * 更新
    */
    private void update(BeconFile beconFile) {
        beconFileMapper.updateByPrimaryKey(beconFile);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        beconFileMapper.deleteByPrimaryKey(id);
    }

    public List<BeconFileStatistics> statisticsByExample(BeconFileExample example){
        return beconFileMapper.statisticsByExample(example);
    };
}
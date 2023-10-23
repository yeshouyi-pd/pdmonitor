package com.pd.server.main.service;

import com.pd.server.main.domain.WaveData;
import com.pd.server.main.domain.WaveDataExample;
import com.pd.server.main.dto.WaveDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WaveDataMapper;
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
public class WaveDataService {

    @Resource
    private WaveDataMapper waveDataMapper;

    public List<WaveData> selectByExample(WaveDataExample example){
        return waveDataMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaveDataDto waveDataDto) {
        WaveData waveData = CopyUtil.copy(waveDataDto, WaveData.class);
        if (StringUtils.isEmpty(waveDataDto.getId())) {
            this.insert(waveData);
        } else {
            this.update(waveData);
        }
    }

    /**
    * 新增
    */
    private void insert(WaveData waveData) {
                Date now = new Date();
        waveData.setId(UuidUtil.getShortUuid());
        waveDataMapper.insert(waveData);
    }

    /**
    * 更新
    */
    private void update(WaveData waveData) {
        waveDataMapper.updateByPrimaryKey(waveData);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waveDataMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.CurrentMeter;
import com.pd.server.main.domain.CurrentMeterExample;
import com.pd.server.main.dto.CurrentMeterDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.CurrentMeterMapper;
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
public class CurrentMeterService {

    @Resource
    private CurrentMeterMapper currentMeterMapper;


    public List<CurrentMeter> selectByExample(CurrentMeterExample example){
        return currentMeterMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(CurrentMeterDto currentMeterDto) {
        CurrentMeter currentMeter = CopyUtil.copy(currentMeterDto, CurrentMeter.class);
        if (StringUtils.isEmpty(currentMeterDto.getId())) {
            this.insert(currentMeter);
        } else {
            this.update(currentMeter);
        }
    }

    /**
    * 新增
    */
    private void insert(CurrentMeter currentMeter) {
                Date now = new Date();
        currentMeter.setId(UuidUtil.getShortUuid());
        currentMeterMapper.insert(currentMeter);
    }

    /**
    * 更新
    */
    private void update(CurrentMeter currentMeter) {
        currentMeterMapper.updateByPrimaryKey(currentMeter);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        currentMeterMapper.deleteByPrimaryKey(id);
    }

}
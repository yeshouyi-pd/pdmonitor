package com.pd.server.main.service;

import com.pd.server.main.domain.MeteorologicalData;
import com.pd.server.main.domain.MeteorologicalDataExample;
import com.pd.server.main.dto.MeteorologicalDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.MeteorologicalDataSum;
import com.pd.server.main.mapper.MeteorologicalDataMapper;
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
public class MeteorologicalDataService {

    @Resource
    private MeteorologicalDataMapper meteorologicalDataMapper;

    public List<MeteorologicalDataSum> sumByExample(MeteorologicalDataExample example){
        return meteorologicalDataMapper.sumByExample(example);
    }

    public List<MeteorologicalData> selectByExample(MeteorologicalDataExample example){
        return meteorologicalDataMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(MeteorologicalDataDto meteorologicalDataDto) {
        MeteorologicalData meteorologicalData = CopyUtil.copy(meteorologicalDataDto, MeteorologicalData.class);
        if (StringUtils.isEmpty(meteorologicalDataDto.getId())) {
            this.insert(meteorologicalData);
        } else {
            this.update(meteorologicalData);
        }
    }

    /**
    * 新增
    */
    private void insert(MeteorologicalData meteorologicalData) {
                Date now = new Date();
        meteorologicalData.setId(UuidUtil.getShortUuid());
        meteorologicalDataMapper.insert(meteorologicalData);
    }

    /**
    * 更新
    */
    private void update(MeteorologicalData meteorologicalData) {
        meteorologicalDataMapper.updateByPrimaryKey(meteorologicalData);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        meteorologicalDataMapper.deleteByPrimaryKey(id);
    }

}
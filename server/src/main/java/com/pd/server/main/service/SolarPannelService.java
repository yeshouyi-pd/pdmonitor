package com.pd.server.main.service;

import com.pd.server.main.domain.SolarPannel;
import com.pd.server.main.domain.SolarPannelExample;
import com.pd.server.main.dto.SolarPannelDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SolarPannelMapper;
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
public class SolarPannelService {

    @Resource
    private SolarPannelMapper solarPannelMapper;

    /**
    * 列表查询
    */
    public List<SolarPannel> list(SolarPannelExample solarPannelExample) {
        return solarPannelMapper.selectByExample(solarPannelExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SolarPannelDto solarPannelDto) {
        SolarPannel solarPannel = CopyUtil.copy(solarPannelDto, SolarPannel.class);
        if (StringUtils.isEmpty(solarPannelDto.getId())) {
            this.insert(solarPannel);
        } else {
            this.update(solarPannel);
        }
    }

    public void saveEntity(SolarPannel solarPannel){
        solarPannelMapper.insert(solarPannel);
    }

    /**
    * 新增
    */
    private void insert(SolarPannel solarPannel) {
                Date now = new Date();
        solarPannel.setId(UuidUtil.getShortUuid());
        solarPannelMapper.insert(solarPannel);
    }

    /**
    * 更新
    */
    private void update(SolarPannel solarPannel) {
        solarPannelMapper.updateByPrimaryKey(solarPannel);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        solarPannelMapper.deleteByPrimaryKey(id);
    }

}
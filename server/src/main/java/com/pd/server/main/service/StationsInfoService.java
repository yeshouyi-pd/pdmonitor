package com.pd.server.main.service;

import com.pd.server.main.domain.StationsInfo;
import com.pd.server.main.domain.StationsInfoExample;
import com.pd.server.main.dto.StationsInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.StationsInfoMapper;
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
public class StationsInfoService {

    @Resource
    private StationsInfoMapper stationsInfoMapper;

    public StationsInfo selectByPrimaryKey(String id){
        return stationsInfoMapper.selectByPrimaryKey(id);
    }

    /**
    * 列表查询
    */
    public List<StationsInfo> selectByExample(StationsInfoExample stationsInfoExample) {
        return stationsInfoMapper.selectByExample(stationsInfoExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(StationsInfoDto stationsInfoDto) {
        StationsInfo stationsInfo = CopyUtil.copy(stationsInfoDto, StationsInfo.class);
        if (StringUtils.isEmpty(stationsInfoDto.getId())) {
            this.insert(stationsInfo);
        } else {
            this.update(stationsInfo);
        }
    }

    /**
    * 新增
    */
    private void insert(StationsInfo stationsInfo) {
                Date now = new Date();
        stationsInfo.setId(UuidUtil.getShortUuid());
        stationsInfoMapper.insert(stationsInfo);
    }

    public void updateByPrimaryKeySelective(StationsInfo stationsInfo){
        stationsInfoMapper.updateByPrimaryKeySelective(stationsInfo);
    }

    /**
    * 更新
    */
    private void update(StationsInfo stationsInfo) {
        stationsInfoMapper.updateByPrimaryKey(stationsInfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        stationsInfoMapper.deleteByPrimaryKey(id);
    }

}
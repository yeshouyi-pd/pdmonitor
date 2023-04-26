package com.pd.server.main.service;

import com.pd.server.main.domain.StationsHeartbeat;
import com.pd.server.main.domain.StationsHeartbeatExample;
import com.pd.server.main.dto.StationsHeartbeatDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.StationsHeartbeatMapper;
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
public class StationsHeartbeatService {

    @Resource
    private StationsHeartbeatMapper stationsHeartbeatMapper;

    /**
    * 列表查询
    */
    public List<StationsHeartbeat> selectByExample(StationsHeartbeatExample stationsHeartbeatExample) {
        return stationsHeartbeatMapper.selectByExample(stationsHeartbeatExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(StationsHeartbeatDto stationsHeartbeatDto) {
        StationsHeartbeat stationsHeartbeat = CopyUtil.copy(stationsHeartbeatDto, StationsHeartbeat.class);
        if (StringUtils.isEmpty(stationsHeartbeatDto.getId())) {
            this.insert(stationsHeartbeat);
        } else {
            this.update(stationsHeartbeat);
        }
    }

    /**
    * 新增
    */
    private void insert(StationsHeartbeat stationsHeartbeat) {
                Date now = new Date();
        stationsHeartbeat.setId(UuidUtil.getShortUuid());
        stationsHeartbeatMapper.insert(stationsHeartbeat);
    }

    public void insertSelective(StationsHeartbeat stationsHeartbeat){
        stationsHeartbeatMapper.insertSelective(stationsHeartbeat);
    }

    /**
    * 更新
    */
    private void update(StationsHeartbeat stationsHeartbeat) {
        stationsHeartbeatMapper.updateByPrimaryKey(stationsHeartbeat);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        stationsHeartbeatMapper.deleteByPrimaryKey(id);
    }

}
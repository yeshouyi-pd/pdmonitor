package com.pd.server.main.service;

import com.pd.server.main.domain.StationsHeart;
import com.pd.server.main.domain.StationsHeartExample;
import com.pd.server.main.dto.StationsHeartDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.StationsHeartMapper;
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
public class StationsHeartService {

    @Resource
    private StationsHeartMapper stationsHeartMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        StationsHeartExample stationsHeartExample = new StationsHeartExample();
        List<StationsHeart> stationsHeartList = stationsHeartMapper.selectByExample(stationsHeartExample);
        PageInfo<StationsHeart> pageInfo = new PageInfo<>(stationsHeartList);
        pageDto.setTotal(pageInfo.getTotal());
        List<StationsHeartDto> stationsHeartDtoList = CopyUtil.copyList(stationsHeartList, StationsHeartDto.class);
        pageDto.setList(stationsHeartDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(StationsHeartDto stationsHeartDto) {
        StationsHeart stationsHeart = CopyUtil.copy(stationsHeartDto, StationsHeart.class);
        if (StringUtils.isEmpty(stationsHeartDto.getId())) {
            this.insert(stationsHeart);
        } else {
            this.update(stationsHeart);
        }
    }

    /**
     * 新增
     */
    private void insert(StationsHeart stationsHeart) {
        Date now = new Date();
        stationsHeart.setId(UuidUtil.getShortUuid());
        stationsHeartMapper.insert(stationsHeart);
    }

    public void insertSelective(StationsHeart stationsHeart) {
        stationsHeartMapper.insertSelective(stationsHeart);
    }

    /**
     * 更新
     */
    private void update(StationsHeart stationsHeart) {
        stationsHeartMapper.updateByPrimaryKey(stationsHeart);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        stationsHeartMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.BeconFileTyToday;
import com.pd.server.main.domain.BeconFileTyTodayExample;
import com.pd.server.main.dto.BeconFileTyTodayDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.BeconFileTyTodayMapper;
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
public class BeconFileTyTodayService {

    @Resource
    private BeconFileTyTodayMapper beconFileTyTodayMapper;

    /**
    * 列表查询
    */
    public List<BeconFileTyToday> selectByExample(BeconFileTyTodayExample example) {
        return beconFileTyTodayMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(BeconFileTyTodayDto beconFileTyTodayDto) {
        BeconFileTyToday beconFileTyToday = CopyUtil.copy(beconFileTyTodayDto, BeconFileTyToday.class);
        if (StringUtils.isEmpty(beconFileTyTodayDto.getId())) {
            this.insert(beconFileTyToday);
        } else {
            this.update(beconFileTyToday);
        }
    }

    /**
    * 新增
    */
    public void insert(BeconFileTyToday beconFileTyToday) {
                Date now = new Date();
        beconFileTyToday.setId(UuidUtil.getShortUuid());
        beconFileTyTodayMapper.insert(beconFileTyToday);
    }

    /**
    * 更新
    */
    private void update(BeconFileTyToday beconFileTyToday) {
        beconFileTyTodayMapper.updateByPrimaryKey(beconFileTyToday);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        beconFileTyTodayMapper.deleteByPrimaryKey(id);
    }

    public void deleteByExample(BeconFileTyTodayExample example) {
        beconFileTyTodayMapper.deleteByExample(example);
    }

}
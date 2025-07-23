package com.pd.server.main.service;

import com.pd.server.main.domain.BeconFileToday;
import com.pd.server.main.domain.BeconFileTodayExample;
import com.pd.server.main.dto.BeconFileTodayDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.NoTodaySbbhDto;
import com.pd.server.main.mapper.BeconFileTodayMapper;
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
public class BeconFileTodayService {

    @Resource
    private BeconFileTodayMapper beconFileTodayMapper;

    /**
    * 列表查询
    */
    public List<BeconFileToday> selectByExample(BeconFileTodayExample beconFileTodayExample) {
        return beconFileTodayMapper.selectByExample(beconFileTodayExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(BeconFileTodayDto beconFileTodayDto) {
        BeconFileToday beconFileToday = CopyUtil.copy(beconFileTodayDto, BeconFileToday.class);
        if (StringUtils.isEmpty(beconFileTodayDto.getId())) {
            this.insert(beconFileToday);
        } else {
            this.update(beconFileToday);
        }
    }

    /**
    * 新增
    */
    public void insert(BeconFileToday beconFileToday) {
                Date now = new Date();
        beconFileToday.setId(UuidUtil.getShortUuid());
        beconFileTodayMapper.insert(beconFileToday);
    }

    /**
    * 更新
    */
    private void update(BeconFileToday beconFileToday) {
        beconFileTodayMapper.updateByPrimaryKey(beconFileToday);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        beconFileTodayMapper.deleteByPrimaryKey(id);
    }

    public void deleteByExample(BeconFileTodayExample example) {
        beconFileTodayMapper.deleteByExample(example);
    }

    public List<NoTodaySbbhDto> noTodaySbbhDtoList(BeconFileTodayExample example){
        return beconFileTodayMapper.noTodaySbbhDtoList(example);
    }
}
package com.pd.server.main.service;

import com.pd.server.main.domain.PointerDay;
import com.pd.server.main.domain.PointerDayExample;
import com.pd.server.main.dto.PointerDayDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.PointerDayMapper;
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
public class PointerDayService {

    @Resource
    private PointerDayMapper pointerDayMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        PointerDayExample pointerDayExample = new PointerDayExample();
        List<PointerDay> pointerDayList = pointerDayMapper.selectByExample(pointerDayExample);
        PageInfo<PointerDay> pageInfo = new PageInfo<>(pointerDayList);
        pageDto.setTotal(pageInfo.getTotal());
        List<PointerDayDto> pointerDayDtoList = CopyUtil.copyList(pointerDayList, PointerDayDto.class);
        pageDto.setList(pointerDayDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(PointerDayDto pointerDayDto) {
        PointerDay pointerDay = CopyUtil.copy(pointerDayDto, PointerDay.class);
        if (StringUtils.isEmpty(pointerDayDto.getId())) {
            this.insert(pointerDay);
        } else {
            this.update(pointerDay);
        }
    }

    /**
    * 新增
    */
    private void insert(PointerDay pointerDay) {
                Date now = new Date();
        pointerDay.setId(UuidUtil.getShortUuid());
        pointerDayMapper.insert(pointerDay);
    }

    /**
    * 更新
    */
    private void update(PointerDay pointerDay) {
        pointerDayMapper.updateByPrimaryKey(pointerDay);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        pointerDayMapper.deleteByPrimaryKey(id);
    }

}
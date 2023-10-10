package com.pd.server.main.service;

import com.pd.server.main.domain.CameraMiddle;
import com.pd.server.main.domain.CameraMiddleExample;
import com.pd.server.main.dto.CameraMiddleDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.CameraMiddleMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CameraMiddleService {

    @Resource
    private CameraMiddleMapper cameraMiddleMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CameraMiddleExample cameraMiddleExample = new CameraMiddleExample();
        List<CameraMiddle> cameraMiddleList = cameraMiddleMapper.selectByExample(cameraMiddleExample);
        PageInfo<CameraMiddle> pageInfo = new PageInfo<>(cameraMiddleList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CameraMiddleDto> cameraMiddleDtoList = CopyUtil.copyList(cameraMiddleList, CameraMiddleDto.class);
        pageDto.setList(cameraMiddleDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(CameraMiddleDto cameraMiddleDto) {
        CameraMiddle cameraMiddle = CopyUtil.copy(cameraMiddleDto, CameraMiddle.class);
        if (StringUtils.isEmpty(cameraMiddleDto.getId())) {
            this.insert(cameraMiddle);
        } else {
            this.update(cameraMiddle);
        }
    }

    /**
    * 新增
    */
    private void insert(CameraMiddle cameraMiddle) {
        cameraMiddle.setId(UuidUtil.getShortUuid());
        cameraMiddleMapper.insert(cameraMiddle);
    }

    /**
    * 更新
    */
    private void update(CameraMiddle cameraMiddle) {
        cameraMiddleMapper.updateByPrimaryKey(cameraMiddle);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        cameraMiddleMapper.deleteByPrimaryKey(id);
    }

}
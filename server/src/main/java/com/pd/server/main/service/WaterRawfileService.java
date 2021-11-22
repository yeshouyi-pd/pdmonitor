package com.pd.server.main.service;

import com.pd.server.main.domain.WaterRawfile;
import com.pd.server.main.domain.WaterRawfileExample;
import com.pd.server.main.dto.WaterRawfileDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WaterRawfileMapper;
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
public class WaterRawfileService {

    @Resource
    private WaterRawfileMapper waterRawfileMapper;

    /**
    * 列表查询
    */
    public void list(WaterRawfileDto pageDto,WaterRawfileExample waterRawfileExample) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<WaterRawfile> waterRawfileList = waterRawfileMapper.selectByExample(waterRawfileExample);
        PageInfo<WaterRawfile> pageInfo = new PageInfo<>(waterRawfileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterRawfileDto> waterRawfileDtoList = CopyUtil.copyList(waterRawfileList, WaterRawfileDto.class);
        pageDto.setList(waterRawfileDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterRawfileDto waterRawfileDto) {
        WaterRawfile waterRawfile = CopyUtil.copy(waterRawfileDto, WaterRawfile.class);
        if (StringUtils.isEmpty(waterRawfileDto.getId())) {
            this.insert(waterRawfile);
        } else {
            this.update(waterRawfile);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterRawfile waterRawfile) {
                Date now = new Date();
        waterRawfile.setId(UuidUtil.getShortUuid());
        waterRawfileMapper.insert(waterRawfile);
    }

    /**
    * 更新
    */
    private void update(WaterRawfile waterRawfile) {
        waterRawfileMapper.updateByPrimaryKey(waterRawfile);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterRawfileMapper.deleteByPrimaryKey(id);
    }

}
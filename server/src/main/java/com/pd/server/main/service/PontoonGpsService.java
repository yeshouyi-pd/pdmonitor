package com.pd.server.main.service;

import com.pd.server.main.domain.PontoonGps;
import com.pd.server.main.domain.PontoonGpsExample;
import com.pd.server.main.dto.PontoonGpsDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.PontoonGpsMapper;
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
public class PontoonGpsService {

    @Resource
    private PontoonGpsMapper pontoonGpsMapper;

    public List<PontoonGps> selectByExample(PontoonGpsExample example){
        return pontoonGpsMapper.selectByExample(example);
    }

    /**
     * 获取趸船gps
     */
    public List<String> selectGpsByExample(PontoonGpsExample example){
        return pontoonGpsMapper.selectGpsByExample(example);
    }

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        PontoonGpsExample pontoonGpsExample = new PontoonGpsExample();
        List<PontoonGps> pontoonGpsList = pontoonGpsMapper.selectByExample(pontoonGpsExample);
        PageInfo<PontoonGps> pageInfo = new PageInfo<>(pontoonGpsList);
        pageDto.setTotal(pageInfo.getTotal());
        List<PontoonGpsDto> pontoonGpsDtoList = CopyUtil.copyList(pontoonGpsList, PontoonGpsDto.class);
        pageDto.setList(pontoonGpsDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(PontoonGpsDto pontoonGpsDto) {
        PontoonGps pontoonGps = CopyUtil.copy(pontoonGpsDto, PontoonGps.class);
        if (StringUtils.isEmpty(pontoonGpsDto.getId())) {
            this.insert(pontoonGps);
        } else {
            this.update(pontoonGps);
        }
    }

    /**
    * 新增
    */
    private void insert(PontoonGps pontoonGps) {
                Date now = new Date();
        pontoonGps.setId(UuidUtil.getShortUuid());
        pontoonGpsMapper.insert(pontoonGps);
    }

    /**
    * 更新
    */
    private void update(PontoonGps pontoonGps) {
        pontoonGpsMapper.updateByPrimaryKey(pontoonGps);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        pontoonGpsMapper.deleteByPrimaryKey(id);
    }

}
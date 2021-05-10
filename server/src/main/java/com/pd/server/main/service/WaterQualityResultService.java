package com.pd.server.main.service;

import com.pd.server.main.domain.WaterQualityResult;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.KvIntDto;
import com.pd.server.main.dto.KvMapDto;
import com.pd.server.main.dto.WaterQualityResultDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WaterQualityResultMapper;
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
public class WaterQualityResultService {

    @Resource
    private WaterQualityResultMapper waterQualityResultMapper;

    public List<WaterQualityResult> findByExample(WaterQualityResultExample example){
        List<WaterQualityResult> waterQualityResultList = waterQualityResultMapper.selectByExample(example);
        return waterQualityResultList;
    }

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaterQualityResultExample waterQualityResultExample = new WaterQualityResultExample();
        waterQualityResultExample.setOrderByClause(" create_time desc");
        List<WaterQualityResult> waterQualityResultList = waterQualityResultMapper.selectByExample(waterQualityResultExample);
        PageInfo<WaterQualityResult> pageInfo = new PageInfo<>(waterQualityResultList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterQualityResultDto> waterQualityResultDtoList = CopyUtil.copyList(waterQualityResultList, WaterQualityResultDto.class);
        pageDto.setList(waterQualityResultDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterQualityResultDto waterQualityResultDto) {
        WaterQualityResult waterQualityResult = CopyUtil.copy(waterQualityResultDto, WaterQualityResult.class);
        if (StringUtils.isEmpty(waterQualityResultDto.getId())) {
            this.insert(waterQualityResult);
        } else {
            this.update(waterQualityResult);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterQualityResult waterQualityResult) {
                Date now = new Date();
        waterQualityResult.setId(UuidUtil.getShortUuid());
        waterQualityResultMapper.insert(waterQualityResult);
    }

    /**
    * 更新
    */
    private void update(WaterQualityResult waterQualityResult) {
        waterQualityResultMapper.updateByPrimaryKey(waterQualityResult);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterQualityResultMapper.deleteByPrimaryKey(id);
    }


    public List<WaterQualityResultDto> getLatestDate(WaterQualityResultExample waterQualityResultExample){
        List<WaterQualityResult>  listdata = waterQualityResultMapper.getLatestDate(waterQualityResultExample);
        List<WaterQualityResultDto> list = CopyUtil.copyList(listdata, WaterQualityResultDto.class);
        return   list;

    }

    public List<KvIntDto> getAlljcsjByDept(WaterQualityResultExample example){
        return   waterQualityResultMapper.getAlljcsjByDept(example);

    }


    public List<WaterQualityResultDto> getthisDeptjxsj(WaterQualityResultExample waterQualityResultExample) {
        return   waterQualityResultMapper.getthisDeptjxsj(waterQualityResultExample);
    }
}
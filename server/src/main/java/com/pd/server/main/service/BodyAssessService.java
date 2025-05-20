package com.pd.server.main.service;

import com.pd.server.main.domain.BodyAssess;
import com.pd.server.main.domain.BodyAssessExample;
import com.pd.server.main.dto.BodyAssessDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.BodyAssessMapper;
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
public class BodyAssessService {

    @Resource
    private BodyAssessMapper bodyAssessMapper;

    public BodyAssess getInfoById(String id){
        return bodyAssessMapper.selectByPrimaryKey(id);
    }

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        BodyAssessExample bodyAssessExample = new BodyAssessExample();
        List<BodyAssess> bodyAssessList = bodyAssessMapper.selectByExample(bodyAssessExample);
        PageInfo<BodyAssess> pageInfo = new PageInfo<>(bodyAssessList);
        pageDto.setTotal(pageInfo.getTotal());
        List<BodyAssessDto> bodyAssessDtoList = CopyUtil.copyList(bodyAssessList, BodyAssessDto.class);
        pageDto.setList(bodyAssessDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(BodyAssessDto bodyAssessDto) {
        BodyAssess bodyAssess = CopyUtil.copy(bodyAssessDto, BodyAssess.class);
        if (StringUtils.isEmpty(bodyAssessDto.getId())) {
            this.insert(bodyAssess);
        } else {
            this.update(bodyAssess);
        }
    }

    public void saveInfo(BodyAssessDto bodyAssessDto){
        BodyAssess bodyAssess = CopyUtil.copy(bodyAssessDto, BodyAssess.class);
        bodyAssessMapper.insert(bodyAssess);
    }

    /**
    * 新增
    */
    private void insert(BodyAssess bodyAssess) {
                Date now = new Date();
        bodyAssess.setId(UuidUtil.getShortUuid());
        bodyAssessMapper.insert(bodyAssess);
    }

    /**
    * 更新
    */
    private void update(BodyAssess bodyAssess) {
        bodyAssessMapper.updateByPrimaryKey(bodyAssess);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        bodyAssessMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.Turbidity;
import com.pd.server.main.domain.TurbidityExample;
import com.pd.server.main.dto.TurbidityDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.TurbidityMapper;
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
public class TurbidityService {

    @Resource
    private TurbidityMapper turbidityMapper;

    public List<Turbidity> selectByExample(TurbidityExample example){
        return turbidityMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(TurbidityDto turbidityDto) {
        Turbidity turbidity = CopyUtil.copy(turbidityDto, Turbidity.class);
        if (StringUtils.isEmpty(turbidityDto.getId())) {
            this.insert(turbidity);
        } else {
            this.update(turbidity);
        }
    }

    /**
    * 新增
    */
    private void insert(Turbidity turbidity) {
                Date now = new Date();
        turbidity.setId(UuidUtil.getShortUuid());
        turbidityMapper.insert(turbidity);
    }

    /**
    * 更新
    */
    private void update(Turbidity turbidity) {
        turbidityMapper.updateByPrimaryKey(turbidity);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        turbidityMapper.deleteByPrimaryKey(id);
    }

}
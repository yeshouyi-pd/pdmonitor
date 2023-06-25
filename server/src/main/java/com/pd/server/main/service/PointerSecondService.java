package com.pd.server.main.service;

import com.pd.server.main.domain.PointerSecond;
import com.pd.server.main.domain.PointerSecondExample;
import com.pd.server.main.dto.PointerSecondDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.PointerCommenDto;
import com.pd.server.main.mapper.PointerSecondMapper;
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
public class PointerSecondService {

    @Resource
    private PointerSecondMapper pointerSecondMapper;

    public List<PointerCommenDto> selectAll(PointerSecondExample example){
        return pointerSecondMapper.selectAll(example);
    }

    public List<PointerCommenDto> selectAllSpecial(PointerSecondDto record){
        return pointerSecondMapper.selectAllSpecial(record);
    }

    public PointerSecond selectByDp(PointerSecondExample example){
        return pointerSecondMapper.selectByDp(example);
    }

    public String selectByWrj(PointerSecondExample example){
        return pointerSecondMapper.selectByDp(example).getDecibelValue();
    }

    public List<PointerSecond> selectByExample(PointerSecondExample pointerSecondExample){
        return pointerSecondMapper.selectByExample(pointerSecondExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(PointerSecondDto pointerSecondDto) {
        PointerSecond pointerSecond = CopyUtil.copy(pointerSecondDto, PointerSecond.class);
        if (StringUtils.isEmpty(pointerSecondDto.getId())) {
            this.insert(pointerSecond);
        } else {
            this.update(pointerSecond);
        }
    }

    /**
    * 新增
    */
    private void insert(PointerSecond pointerSecond) {
                Date now = new Date();
        pointerSecond.setId(UuidUtil.getShortUuid());
        pointerSecondMapper.insert(pointerSecond);
    }

    /**
    * 更新
    */
    private void update(PointerSecond pointerSecond) {
        pointerSecondMapper.updateByPrimaryKey(pointerSecond);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        pointerSecondMapper.deleteByPrimaryKey(id);
    }

}
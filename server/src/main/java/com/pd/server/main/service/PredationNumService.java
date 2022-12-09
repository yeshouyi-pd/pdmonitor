package com.pd.server.main.service;

import com.pd.server.main.domain.EquipmentTyEvent;
import com.pd.server.main.domain.PredationNum;
import com.pd.server.main.domain.PredationNumExample;
import com.pd.server.main.dto.PredationNumDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.PredationNumDwDto;
import com.pd.server.main.mapper.PredationNumMapper;
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
public class PredationNumService {

    @Resource
    private PredationNumMapper predationNumMapper;

    public List<PredationNumDwDto> statisticsExample(PredationNumExample example){
        return predationNumMapper.statisticsExample(example);
    }

    public List<EquipmentTyEvent> listEventCount(PredationNumExample example){
        return predationNumMapper.listEventCount(example);
    }

    /**
    * 列表查询
    */
    public List<PredationNum> list(PredationNumExample example) {
        return predationNumMapper.selectByExample(example);
    }

    public List<PredationNum> selectByExampleSpecial(PredationNumDto record) {
        return predationNumMapper.selectByExampleSpecial(record);
    }

    /**
     * 列表查询
     */
    public List<PredationNumDwDto> listDw(PredationNumExample example) {
        return predationNumMapper.selectDwByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(PredationNumDto predationNumDto) {
        PredationNum predationNum = CopyUtil.copy(predationNumDto, PredationNum.class);
        if (StringUtils.isEmpty(predationNumDto.getId())) {
            this.insert(predationNum);
        } else {
            this.update(predationNum);
        }
    }

    /**
    * 新增
    */
    private void insert(PredationNum predationNum) {
                Date now = new Date();
        predationNum.setId(UuidUtil.getShortUuid());
        predationNumMapper.insert(predationNum);
    }

    /**
    * 更新
    */
    private void update(PredationNum predationNum) {
        predationNumMapper.updateByPrimaryKey(predationNum);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        predationNumMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.BeconFileTy;
import com.pd.server.main.domain.BeconFileTyExample;
import com.pd.server.main.dto.BeconFileTyDto;
import com.pd.server.main.mapper.BeconFileTyMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class BeconFileTyService {

    @Resource
    private BeconFileTyMapper beconFileTyMapper;

    /**
    * 列表查询
    */
    public List<BeconFileTy> selectByExample(BeconFileTyExample beconFileTyExample) {
        return beconFileTyMapper.selectByExample(beconFileTyExample);
    }


    public List<BeconFileTy> selectByExampleSpecial(BeconFileTyDto record){
        return beconFileTyMapper.selectByExampleSpecial(record);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(BeconFileTyDto beconFileTyDto) {
        BeconFileTy beconFileTy = CopyUtil.copy(beconFileTyDto, BeconFileTy.class);
        if (StringUtils.isEmpty(beconFileTyDto.getId())) {
            this.insert(beconFileTy);
        } else {
            this.update(beconFileTy);
        }
    }

    /**
    * 新增
    */
    private void insert(BeconFileTy beconFileTy) {
                Date now = new Date();
        beconFileTy.setId(UuidUtil.getShortUuid());
        beconFileTyMapper.insert(beconFileTy);
    }

    /**
    * 更新
    */
    private void update(BeconFileTy beconFileTy) {
        beconFileTyMapper.updateByPrimaryKey(beconFileTy);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        beconFileTyMapper.deleteByPrimaryKey(id);
    }

}
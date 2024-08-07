package com.pd.server.main.service;

import com.pd.server.main.domain.AppearNumbers;
import com.pd.server.main.domain.AppearNumbersExample;
import com.pd.server.main.dto.AppearNumbersDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.basewx.my.AppearNumDpDto;
import com.pd.server.main.mapper.AppearNumbersMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppearNumbersService {

    @Resource
    private AppearNumbersMapper appearNumbersMapper;

    /**
    * 列表查询
    */
    public List<AppearNumbers> list(AppearNumbersExample example) {
        return appearNumbersMapper.selectByExample(example);
    }

    public List<AppearNumbers> selectByExampleSpecial(AppearNumbersDto record) {
        return appearNumbersMapper.selectByExampleSpecial(record);
    }

    public List<AppearNumDpDto> listSumAlarmNumByXs(AppearNumbersExample example) {
        return appearNumbersMapper.listSumAlarmNumByXs(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppearNumbersDto appearNumbersDto) {
        AppearNumbers appearNumbers = CopyUtil.copy(appearNumbersDto, AppearNumbers.class);
        if (StringUtils.isEmpty(appearNumbersDto.getId())) {
            this.insert(appearNumbers);
        } else {
            this.update(appearNumbers);
        }
    }

    /**
    * 新增
    */
    private void insert(AppearNumbers appearNumbers) {
        appearNumbers.setId(UuidUtil.getShortUuid());
        appearNumbersMapper.insert(appearNumbers);
    }

    /**
    * 更新
    */
    private void update(AppearNumbers appearNumbers) {
        appearNumbersMapper.updateByPrimaryKey(appearNumbers);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appearNumbersMapper.deleteByPrimaryKey(id);
    }

    public void deleteByExample(AppearNumbersExample example){
        appearNumbersMapper.deleteByExample(example);
    }

}
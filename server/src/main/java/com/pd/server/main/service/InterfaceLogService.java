package com.pd.server.main.service;

import com.pd.server.main.domain.InterfaceLog;
import com.pd.server.main.domain.InterfaceLogExample;
import com.pd.server.main.dto.InterfaceLogDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.InterfaceLogMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InterfaceLogService {

    @Resource
    private InterfaceLogMapper interfaceLogMapper;

    /**
    * 列表查询分页
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        InterfaceLogExample interfaceLogExample = new InterfaceLogExample();
        List<InterfaceLog> interfaceLogList = interfaceLogMapper.selectByExample(interfaceLogExample);
        PageInfo<InterfaceLog> pageInfo = new PageInfo<>(interfaceLogList);
        pageDto.setTotal(pageInfo.getTotal());
        List<InterfaceLogDto> interfaceLogDtoList = CopyUtil.copyList(interfaceLogList, InterfaceLogDto.class);
        pageDto.setList(interfaceLogDtoList);
    }

    public List<InterfaceLog> listAll(InterfaceLogExample interfaceLogExample){
        List<InterfaceLog> interfaceLogList = interfaceLogMapper.selectByExample(interfaceLogExample);
        return interfaceLogList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(InterfaceLogDto interfaceLogDto) {
        InterfaceLog interfaceLog = CopyUtil.copy(interfaceLogDto, InterfaceLog.class);
        if (StringUtils.isEmpty(interfaceLogDto.getId())) {
            this.insert(interfaceLog);
        } else {
            this.update(interfaceLog);
        }
    }

    /**
    * 新增
    */
    private void insert(InterfaceLog interfaceLog) {
        interfaceLog.setId(UuidUtil.getShortUuid());
        interfaceLogMapper.insert(interfaceLog);
    }

    /**
    * 更新
    */
    private void update(InterfaceLog interfaceLog) {
        interfaceLogMapper.updateByPrimaryKey(interfaceLog);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        interfaceLogMapper.deleteByPrimaryKey(id);
    }

}
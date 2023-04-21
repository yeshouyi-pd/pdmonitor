package com.pd.server.main.service;

import com.pd.server.main.domain.SendCommand;
import com.pd.server.main.domain.SendCommandExample;
import com.pd.server.main.dto.SendCommandDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.SendCommandMapper;
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
public class SendCommandService {

    @Resource
    private SendCommandMapper sendCommandMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SendCommandExample sendCommandExample = new SendCommandExample();
        List<SendCommand> sendCommandList = sendCommandMapper.selectByExample(sendCommandExample);
        PageInfo<SendCommand> pageInfo = new PageInfo<>(sendCommandList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SendCommandDto> sendCommandDtoList = CopyUtil.copyList(sendCommandList, SendCommandDto.class);
        pageDto.setList(sendCommandDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SendCommandDto sendCommandDto) {
        SendCommand sendCommand = CopyUtil.copy(sendCommandDto, SendCommand.class);
        if (StringUtils.isEmpty(sendCommandDto.getId())) {
            this.insert(sendCommand);
        } else {
            this.update(sendCommand);
        }
    }

    /**
    * 新增
    */
    private void insert(SendCommand sendCommand) {
                Date now = new Date();
        sendCommand.setId(UuidUtil.getShortUuid());
        sendCommandMapper.insert(sendCommand);
    }

    public void insertSelective(SendCommand sendCommand){
        sendCommandMapper.insertSelective(sendCommand);
    }

    /**
    * 更新
    */
    private void update(SendCommand sendCommand) {
        sendCommandMapper.updateByPrimaryKey(sendCommand);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        sendCommandMapper.deleteByPrimaryKey(id);
    }

}
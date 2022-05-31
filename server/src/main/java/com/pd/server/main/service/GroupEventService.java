package com.pd.server.main.service;

import com.pd.server.main.domain.GroupEvent;
import com.pd.server.main.domain.GroupEventExample;
import com.pd.server.main.dto.GroupEventDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.GroupEventMapper;
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
public class GroupEventService {

    @Resource
    private GroupEventMapper groupEventMapper;

    /**
    * 列表查询
    */
    public List<GroupEvent> list(GroupEventExample example) {
        return groupEventMapper.selectByExampleWithBLOBs(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(GroupEventDto groupEventDto) {
        GroupEvent groupEvent = CopyUtil.copy(groupEventDto, GroupEvent.class);
        if (StringUtils.isEmpty(groupEventDto.getId())) {
            this.insert(groupEvent);
        } else {
            this.update(groupEvent);
        }
    }

    /**
    * 新增
    */
    private void insert(GroupEvent groupEvent) {
                Date now = new Date();
        groupEvent.setId(UuidUtil.getShortUuid());
        groupEventMapper.insert(groupEvent);
    }

    /**
    * 更新
    */
    private void update(GroupEvent groupEvent) {
        groupEventMapper.updateByPrimaryKey(groupEvent);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        groupEventMapper.deleteByPrimaryKey(id);
    }

}
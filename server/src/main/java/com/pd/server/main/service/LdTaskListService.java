package com.pd.server.main.service;

import com.pd.server.main.domain.LdTaskList;
import com.pd.server.main.domain.LdTaskListExample;
import com.pd.server.main.dto.LdTaskListDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.LdTaskListMapper;
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
public class LdTaskListService {

    @Resource
    private LdTaskListMapper ldTaskListMapper;

    /**
    * 列表查询
    */
    public List<LdTaskList> list(LdTaskListExample example) {
        return ldTaskListMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(LdTaskListDto ldTaskListDto) {
        LdTaskList ldTaskList = CopyUtil.copy(ldTaskListDto, LdTaskList.class);
        if (StringUtils.isEmpty(ldTaskListDto.getId())) {
            this.insert(ldTaskList);
        } else {
            this.update(ldTaskList);
        }
    }

    /**
    * 新增
    */
    private void insert(LdTaskList ldTaskList) {
                Date now = new Date();
//        ldTaskList.setId(UuidUtil.getShortUuid());
        ldTaskListMapper.insertSelective(ldTaskList);
    }

    /**
    * 更新
    */
    private void update(LdTaskList ldTaskList) {
        ldTaskListMapper.updateByPrimaryKey(ldTaskList);
    }

    /**
    * 删除
    */
    public void delete(Integer id) {
        ldTaskListMapper.deleteByPrimaryKey(id);
    }

}
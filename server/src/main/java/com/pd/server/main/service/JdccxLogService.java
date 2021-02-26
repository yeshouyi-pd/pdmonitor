package com.pd.server.main.service;

import com.pd.server.main.domain.JdccxLog;
import com.pd.server.main.domain.JdccxLogExample;
import com.pd.server.main.dto.JdccxLogDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.JdccxLogMapper;
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
public class JdccxLogService {

    @Resource
    private JdccxLogMapper jdccxLogMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        JdccxLogExample jdccxLogExample = new JdccxLogExample();
        List<JdccxLog> jdccxLogList = jdccxLogMapper.selectByExample(jdccxLogExample);
        PageInfo<JdccxLog> pageInfo = new PageInfo<>(jdccxLogList);
        pageDto.setTotal(pageInfo.getTotal());
        List<JdccxLogDto> jdccxLogDtoList = CopyUtil.copyList(jdccxLogList, JdccxLogDto.class);
        pageDto.setList(jdccxLogDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(JdccxLogDto jdccxLogDto) {
        JdccxLog jdccxLog = CopyUtil.copy(jdccxLogDto, JdccxLog.class);
        if (StringUtils.isEmpty(jdccxLogDto.getId())) {
            this.insert(jdccxLog);
        } else {
            this.update(jdccxLog);
        }
    }

    /**
    * 新增
    */
    private void insert(JdccxLog jdccxLog) {
        Date now = new Date();
        jdccxLog.setId(UuidUtil.getShortUuid());
        jdccxLogMapper.insert(jdccxLog);
    }

    /**
    * 更新
    */
    private void update(JdccxLog jdccxLog) {
    jdccxLogMapper.updateByPrimaryKey(jdccxLog);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    jdccxLogMapper.deleteByPrimaryKey(id);
    }


}

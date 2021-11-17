package com.pd.server.main.service;

import com.pd.server.main.domain.Fileinfo;
import com.pd.server.main.domain.FileinfoExample;
import com.pd.server.main.dto.FileinfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.FileinfoMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileinfoService {

    @Resource
    private FileinfoMapper fileinfoMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        FileinfoExample fileinfoExample = new FileinfoExample();
        List<Fileinfo> fileinfoList = fileinfoMapper.selectByExample(fileinfoExample);
        PageInfo<Fileinfo> pageInfo = new PageInfo<>(fileinfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<FileinfoDto> fileinfoDtoList = CopyUtil.copyList(fileinfoList, FileinfoDto.class);
        pageDto.setList(fileinfoDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(FileinfoDto fileinfoDto) {
        Fileinfo fileinfo = CopyUtil.copy(fileinfoDto, Fileinfo.class);
        if (StringUtils.isEmpty(fileinfoDto.getId())) {
            this.insert(fileinfo);
        } else {
            this.update(fileinfo);
        }
    }

    /**
    * 新增
    */
    public void insert(Fileinfo fileinfo) {
        fileinfo.setId(UuidUtil.getShortUuid());
        fileinfoMapper.insertSelective(fileinfo);
    }

    public void updates(Fileinfo fileinfo,FileinfoExample example ) {
        fileinfoMapper.updateByExampleSelective(fileinfo ,example);
    }

    public int querycount(FileinfoExample example){
        Long count = fileinfoMapper.countByExample(example);
        return  count.intValue();

    }

    /**
    * 更新
    */
    private void update(Fileinfo fileinfo) {
        fileinfoMapper.updateByPrimaryKey(fileinfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        fileinfoMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.WebchatPostinfo;
import com.pd.server.main.domain.WebchatPostinfoExample;
import com.pd.server.main.dto.WebchatPostinfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WebchatPostinfoMapper;
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
public class WebchatPostinfoService {

    @Resource
    private WebchatPostinfoMapper webchatPostinfoMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WebchatPostinfoExample webchatPostinfoExample = new WebchatPostinfoExample();
        List<WebchatPostinfo> webchatPostinfoList = webchatPostinfoMapper.selectByExample(webchatPostinfoExample);
        PageInfo<WebchatPostinfo> pageInfo = new PageInfo<>(webchatPostinfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WebchatPostinfoDto> webchatPostinfoDtoList = CopyUtil.copyList(webchatPostinfoList, WebchatPostinfoDto.class);
        pageDto.setList(webchatPostinfoDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(WebchatPostinfoDto webchatPostinfoDto) {
        WebchatPostinfo webchatPostinfo = CopyUtil.copy(webchatPostinfoDto, WebchatPostinfo.class);
        if (StringUtils.isEmpty(webchatPostinfoDto.getId())) {
            this.insert(webchatPostinfo);
        } else {
            this.update(webchatPostinfo);
        }
    }

    /**
     * 新增
     */
    private void insert(WebchatPostinfo webchatPostinfo) {
        Date now = new Date();
        webchatPostinfo.setId(UuidUtil.getShortUuid());
        webchatPostinfoMapper.insert(webchatPostinfo);
    }

    /**
     * 更新
     */
    private void update(WebchatPostinfo webchatPostinfo) {
        webchatPostinfoMapper.updateByPrimaryKey(webchatPostinfo);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        webchatPostinfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询列表不分页
     */
    public WebchatPostinfoDto getOne(){
        WebchatPostinfoExample webchatPostinfoExample = new WebchatPostinfoExample();
        List<WebchatPostinfo> webchatPostinfoList = webchatPostinfoMapper.selectByExample(webchatPostinfoExample);
        List<WebchatPostinfoDto> webchatPostinfoDtoList = CopyUtil.copyList(webchatPostinfoList, WebchatPostinfoDto.class);
        WebchatPostinfoDto webchatPostinfoDto = null;
        if(!StringUtils.isEmpty(webchatPostinfoDtoList)&&webchatPostinfoDtoList.size()>0){
            webchatPostinfoDto = webchatPostinfoDtoList.get(0);
        }else{
            webchatPostinfoDto = new WebchatPostinfoDto();
        }
        return webchatPostinfoDto;
    }

}

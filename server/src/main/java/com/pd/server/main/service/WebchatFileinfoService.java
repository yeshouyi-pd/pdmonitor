package com.pd.server.main.service;

import com.pd.server.main.domain.WebchatFileinfo;
import com.pd.server.main.domain.WebchatFileinfoExample;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WebchatFileinfoMapper;
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
public class WebchatFileinfoService {

@Resource
private WebchatFileinfoMapper webchatFileinfoMapper;

/**
* 列表查询
*/
public void list(PageDto pageDto) {
PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
WebchatFileinfoExample webchatFileinfoExample = new WebchatFileinfoExample();
List<WebchatFileinfo> webchatFileinfoList = webchatFileinfoMapper.selectByExample(webchatFileinfoExample);
PageInfo<WebchatFileinfo> pageInfo = new PageInfo<>(webchatFileinfoList);
pageDto.setTotal(pageInfo.getTotal());
List<WebchatFileinfoDto> webchatFileinfoDtoList = CopyUtil.copyList(webchatFileinfoList, WebchatFileinfoDto.class);
    pageDto.setList(webchatFileinfoDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WebchatFileinfoDto webchatFileinfoDto) {
        WebchatFileinfo webchatFileinfo = CopyUtil.copy(webchatFileinfoDto, WebchatFileinfo.class);
        WebchatFileinfoExample webchatFileinfoExample = new WebchatFileinfoExample();
        WebchatFileinfoExample.Criteria ca = webchatFileinfoExample.createCriteria();
        ca.andLshEqualTo(webchatFileinfo.getLsh());
        if(StringUtils.isEmpty((webchatFileinfoDto.getZpzl()))||
                StringUtils.isEmpty((webchatFileinfoDto.getZplx()))){
            //不需要照片名字的
            if (StringUtils.isEmpty(webchatFileinfoDto.getId())) {
                this.insert(webchatFileinfo);
            } else {
                this.update(webchatFileinfo);
            }
        }else{
            //针对需要照片名字的
            ca.andZplxEqualTo(webchatFileinfoDto.getZplx());
            ca.andZpzlEqualTo(webchatFileinfoDto.getZpzl());
            Long count = webchatFileinfoMapper.countByExample(webchatFileinfoExample);
            if(count ==0){
                this.insert(webchatFileinfo);
            }
        }


    }

    /**
    * 新增
    */
    private void insert(WebchatFileinfo webchatFileinfo) {
            Date now = new Date();
    webchatFileinfo.setId(UuidUtil.getShortUuid());
    webchatFileinfoMapper.insert(webchatFileinfo);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void wxsave(WebchatFileinfoDto webchatFileinfoDto) {
        WebchatFileinfo webchatFileinfo= CopyUtil.copy(webchatFileinfoDto, WebchatFileinfo.class);
        if (StringUtils.isEmpty(webchatFileinfoDto.getId())) {
            this.insert(webchatFileinfo);
        } else {
            this.update(webchatFileinfo);
        }
    }

    /**
     * 根据流水号查询图片信息
     */
    public List<WebchatFileinfo> getFileinfoByLsh(String lsh) {
        WebchatFileinfoExample webchatFileinfoExample = new WebchatFileinfoExample();
        WebchatFileinfoExample.Criteria ca = webchatFileinfoExample.createCriteria();
        ca.andLshEqualTo(lsh);
        List<WebchatFileinfo> webchatFileinfoList = webchatFileinfoMapper.selectByExample(webchatFileinfoExample);
        return webchatFileinfoList;
    }

    /**
     * 根据流水号查询图片信息
     */
    public List<WebchatFileinfo> getFileinfoByLshAndZplx(String lsh,String zplx) {
        WebchatFileinfoExample webchatFileinfoExample = new WebchatFileinfoExample();
        WebchatFileinfoExample.Criteria ca = webchatFileinfoExample.createCriteria();
        ca.andLshEqualTo(lsh);
        ca.andZplxEqualTo(zplx);
        List<WebchatFileinfo> webchatFileinfoList = webchatFileinfoMapper.selectByExample(webchatFileinfoExample);
        return webchatFileinfoList;
    }


    /**
    * 更新
    */
    private void update(WebchatFileinfo webchatFileinfo) {
    webchatFileinfoMapper.updateByPrimaryKey(webchatFileinfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    webchatFileinfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据流水号查询图片信息
     */
    public List<WebchatFileinfo> getFileinfoByLsh(String lsh,String filetype) {

        WebchatFileinfoExample webchatFileinfoExample = new WebchatFileinfoExample();
        WebchatFileinfoExample.Criteria ca = webchatFileinfoExample.createCriteria();
        ca.andLshEqualTo(lsh);
        if(!StringUtils.isEmpty(filetype)){

            ca.andZpzlEqualTo(filetype);
        }
        List<WebchatFileinfo> webchatFileinfoList = webchatFileinfoMapper.selectByExample(webchatFileinfoExample);
        return webchatFileinfoList;
    }
    /**
     * 根据流水号删除图片
     */
    public void deleteByLsh(String lsh){
        WebchatFileinfoExample webchatFileinfoExample = new WebchatFileinfoExample();
        WebchatFileinfoExample.Criteria ca = webchatFileinfoExample.createCriteria();
        ca.andLshEqualTo(lsh);
        webchatFileinfoMapper.deleteByExample(webchatFileinfoExample);
    }

}

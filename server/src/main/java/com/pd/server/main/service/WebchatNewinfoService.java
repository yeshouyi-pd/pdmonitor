package com.pd.server.main.service;

import com.pd.server.main.domain.WebchatIllegalSave;
import com.pd.server.main.domain.WebchatNewinfo;
import com.pd.server.main.domain.WebchatNewinfoExample;
import com.pd.server.main.dto.WebchatNewinfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WebchatNewinfoMapper;
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
public class WebchatNewinfoService {

@Resource
private WebchatNewinfoMapper webchatNewinfoMapper;

    /**
    * 列表查询
    */
    public List<WebchatNewinfo> list(WebchatNewinfoExample example) {
        List<WebchatNewinfo> list = webchatNewinfoMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    /**
     * 微信列表查询
     */
    public List<WebchatNewinfo> wxlist(String xxlb) {
        WebchatNewinfoExample WebchatNewinfoExample = new WebchatNewinfoExample();
        WebchatNewinfoExample.Criteria criteria = WebchatNewinfoExample.createCriteria();
        criteria.andXxlbEqualTo(xxlb);
        List<WebchatNewinfo> WebchatNewinfoList = webchatNewinfoMapper.selectByExample(WebchatNewinfoExample);
        return WebchatNewinfoList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WebchatNewinfoDto WebchatNewinfoDto) {
        WebchatNewinfo WebchatNewinfo = CopyUtil.copy(WebchatNewinfoDto, WebchatNewinfo.class);
        if (StringUtils.isEmpty(WebchatNewinfoDto.getId())) {
            this.insert(WebchatNewinfo);
        } else {
            this.update(WebchatNewinfo);
        }
    }

    /**
    * 新增
    */
    private void insert(WebchatNewinfo WebchatNewinfo) {
            Date now = new Date();
        WebchatNewinfo.setId(UuidUtil.getShortUuid());
        webchatNewinfoMapper.insert(WebchatNewinfo);
    }

    /**
    * 更新
    */
    private void update(WebchatNewinfo WebchatNewinfo) {
        webchatNewinfoMapper.updateByPrimaryKeyWithBLOBs(WebchatNewinfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        webchatNewinfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public WebchatNewinfoDto findByID(String id){
        WebchatNewinfo WebchatNewinfo = webchatNewinfoMapper.selectByPrimaryKey(id);
        WebchatNewinfoDto WebchatNewinfoDto = CopyUtil.copy(WebchatNewinfo, WebchatNewinfoDto.class);
        return WebchatNewinfoDto;
    }

}

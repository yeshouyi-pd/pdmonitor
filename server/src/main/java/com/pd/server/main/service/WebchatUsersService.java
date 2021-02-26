package com.pd.server.main.service;

import com.pd.server.main.domain.WebchatNewinfo;
import com.pd.server.main.domain.WebchatUsers;
import com.pd.server.main.domain.WebchatUsersExample;
import com.pd.server.main.dto.WebchatUsersDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.WebchatUsersMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class WebchatUsersService {

@Resource
private WebchatUsersMapper wxUsersMapper;

    /**
    * 列表查询
    */
    public List<WebchatUsers> list(WebchatUsersExample example) throws Exception {
        List<WebchatUsers> list = wxUsersMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WebchatUsersDto wxUsersDto) {
    WebchatUsers wxUsers = CopyUtil.copy(wxUsersDto, WebchatUsers.class);
    if (StringUtils.isEmpty(wxUsersDto.getId())) {
    this.insert(wxUsers);
    } else {
    this.update(wxUsers);
    }
    }

    /**
    * 新增
    */
    private void insert(WebchatUsers wxUsers) {
            Date now = new Date();
    wxUsers.setId(UuidUtil.getShortUuid());
    wxUsersMapper.insert(wxUsers);
    }

    /**
    * 更新
    */
    private void update(WebchatUsers wxUsers) {
    wxUsersMapper.updateByPrimaryKeyWithBLOBs(wxUsers);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    wxUsersMapper.deleteByPrimaryKey(id);
    }

    /**
     * 身份证号码查询
     */
    public WebchatUsers findBySfzmhm(String sfzmhm) {
        WebchatUsersExample example = new WebchatUsersExample();
        WebchatUsersExample.Criteria ca = example.createCriteria();
        ca.andZjhmEqualTo(sfzmhm);
        List<WebchatUsers> list = wxUsersMapper.selectByExampleWithBLOBs(example);
        if(null != list && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据用户openid查询
     * @param openid
     * @return
     */
    public WebchatUsers findByOpenid(String openid) {
        WebchatUsersExample example = new WebchatUsersExample();
        WebchatUsersExample.Criteria ca = example.createCriteria();
        ca.andOpenidEqualTo(openid);
        ca.andSfrzEqualTo("1");//是否认证,1认证
        List<WebchatUsers> list = wxUsersMapper.selectByExampleWithBLOBs(example);
        if(null != list && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

}


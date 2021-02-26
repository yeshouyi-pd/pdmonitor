package com.pd.server.main.service;

import com.pd.server.main.dto.WebchatIllegalSaveDto;
import com.pd.server.main.domain.WebchatIllegalSave;
import com.pd.server.main.domain.WebchatIllegalSaveExample;
import com.pd.server.main.mapper.WebchatIllegalSaveMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class WebchatIllegalSaveService {

@Resource
private WebchatIllegalSaveMapper webchatIllegalSaveMapper;

/**
* 列表查询
*/
public List<WebchatIllegalSave> list(WebchatIllegalSaveExample webchatIllegalExample) {
    List<WebchatIllegalSave> webchatIllegalList = webchatIllegalSaveMapper.selectByExample(webchatIllegalExample);
    return webchatIllegalList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WebchatIllegalSaveDto webchatIllegalSaveDto) {
    WebchatIllegalSave webchatIllegalSave = CopyUtil.copy(webchatIllegalSaveDto, WebchatIllegalSave.class);
    if (StringUtils.isEmpty(webchatIllegalSaveDto.getId())) {
    this.insert(webchatIllegalSave);
    } else {
    this.update(webchatIllegalSave);
    }
    }

    /**
    * 新增
    */
    private void insert(WebchatIllegalSave webchatIllegalSave) {
            Date now = new Date();
    webchatIllegalSave.setId(UuidUtil.getShortUuid());
    webchatIllegalSaveMapper.insert(webchatIllegalSave);
    }

    /**
    * 更新
    */
    private void update(WebchatIllegalSave webchatIllegalSave) {
    webchatIllegalSaveMapper.updateByPrimaryKey(webchatIllegalSave);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    webchatIllegalSaveMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据openid查询
     * @param openid
     * @return
     */
    public List<WebchatIllegalSave> findByOpenid(String openid){
        WebchatIllegalSaveExample example = new WebchatIllegalSaveExample();
        example.createCriteria().andOpenidEqualTo(openid);
        List<WebchatIllegalSave> list = webchatIllegalSaveMapper.selectByExample(example);
        return list;
    }
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public WebchatIllegalSave findByID(String id){
        WebchatIllegalSave webchatIllegalSave = webchatIllegalSaveMapper.selectByPrimaryKey(id);
        return webchatIllegalSave;
    }
    }

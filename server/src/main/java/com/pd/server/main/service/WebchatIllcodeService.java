package com.pd.server.main.service;

import com.pd.server.main.domain.WebchatIllcode;
import com.pd.server.main.domain.WebchatIllcodeExample;
import com.pd.server.main.dto.WebchatIllcodeDto;
import com.pd.server.main.mapper.WebchatIllcodeMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class WebchatIllcodeService {

@Resource
private WebchatIllcodeMapper webchatIllcodeMapper;

/**
* 列表查询
*/
public List list(WebchatIllcodeExample webchatIllcodeExample ) {
    List<WebchatIllcode> webchatIllcodeList = webchatIllcodeMapper.selectByExample(webchatIllcodeExample);
    return webchatIllcodeList;


    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WebchatIllcodeDto webchatIllcodeDto) {
    WebchatIllcode webchatIllcode = CopyUtil.copy(webchatIllcodeDto, WebchatIllcode.class);
    if (StringUtils.isEmpty(webchatIllcodeDto.getId())) {
    this.insert(webchatIllcode);
    } else {
    this.update(webchatIllcode);
    }
    }

    /**
    * 新增
    */
    private void insert(WebchatIllcode webchatIllcode) {
            Date now = new Date();
    webchatIllcode.setId(UuidUtil.getShortUuid());
    webchatIllcode.setFbsj(new Date());
    webchatIllcodeMapper.insert(webchatIllcode);
    }

    /**
    * 更新
    */
    private void update(WebchatIllcode webchatIllcode) {
    webchatIllcodeMapper.updateByPrimaryKey(webchatIllcode);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    webchatIllcodeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据wfdm和lb查询
     * @param wfdm
     * @param lb
     * @return
     */
    public WebchatIllcodeDto findByWfdmandLb(String wfdm, String lb){
        WebchatIllcodeExample example = new WebchatIllcodeExample();
        WebchatIllcodeExample.Criteria criteria = example.createCriteria();
        criteria.andWfdmEqualTo(wfdm);
        criteria.andLbEqualTo(lb);
        criteria.andZtEqualTo("1");
        List<WebchatIllcode> list = webchatIllcodeMapper.selectByExample(example);
        if(null != list && list.size() > 0){
            WebchatIllcode webchatIllcode = list.get(0);
            WebchatIllcodeDto webchatIllcodeDto = CopyUtil.copy(webchatIllcode, WebchatIllcodeDto.class);
            return webchatIllcodeDto;
        }
        return null;
    }

    public List<String> findAllWfdm(){
        List<String> list = webchatIllcodeMapper.findAllWfdm();
        return list;
    }
}


package com.pd.server.main.service.dsjk;

import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.Attr;
import com.pd.server.main.domain.AttrExample;
import com.pd.server.main.mapper.AttrMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WfzxjyspxxService extends AbstractScanRequest{

    private AttrMapper attrMapper;

    public WfzxjyspxxService(){
        attrMapper = SpringUtil.getBean(AttrMapper.class);
    }


    /**
     * 预约查询
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(String jsonParam) throws Exception {
        AttrExample example = new AttrExample();
        AttrExample.Criteria ca = example.createCriteria();
        ca.andAttrcodeEqualTo("ngurl");
        List<Attr> attrs = attrMapper.selectByExample(example);
        if(attrs.size()==1){
            data = attrs.get(0).getAttrkey()+"/static/wx/wfzxjy/example.mp4";
        }else {
            data = "系统异常";
        }
        return data;
    }
}

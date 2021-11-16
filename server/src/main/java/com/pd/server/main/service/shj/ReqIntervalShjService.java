package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.AttrMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ReqIntervalShjService extends AbstractScanRequest{

    /**
     * 设备请求时间间隔
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {
        String sbbh = jsonParam.getString("sbbh");
        if(StringUtils.isEmpty(sbbh)){
            data = "参数错误";
            return data;
        }
        AttrMapper attrMapper = SpringUtil.getBean(AttrMapper.class);
        AttrExample example = new AttrExample();
        example.createCriteria().andAttrcodeEqualTo("reqinterval");
        List<Attr> list = attrMapper.selectByExample(example);
        if(list.size() == 0){
            data = "60";
        }else{
            data = list.get(0).getAttrkey();
        }
        return data;
    }

}

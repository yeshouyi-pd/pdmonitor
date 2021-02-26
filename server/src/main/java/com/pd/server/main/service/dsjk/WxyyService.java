package com.pd.server.main.service.dsjk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.WxYy;
import com.pd.server.main.domain.WxYyExample;
import com.pd.server.main.dto.WxYyDsjkDto;
import com.pd.server.main.mapper.WxYyMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxyyService extends AbstractScanRequest{

    private WxYyMapper wxYyMapper;

    public WxyyService(){
        wxYyMapper = SpringUtil.getBean(WxYyMapper.class);
    }

    /**
     * 预约查询
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(String jsonParam) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonParam);
        if(StringUtils.isEmpty(jsonObject.getString("zjhm"))){
            data = "参数错误";
            return data;
        }
        WxYyExample wxYyExample = new WxYyExample();
        WxYyExample.Criteria ca = wxYyExample.createCriteria();
        ca.andZjhmEqualTo(jsonObject.getString("zjhm"));
        ca.andCjsjGreaterThanOrEqualTo(DateUtil.nowTime());
        ca.andCjsjLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getMonthBeforeOrLater(3),"yyyy-MM-dd HH:mm:ss"));
        List<WxYy> wxYyList = wxYyMapper.selectByExample(wxYyExample);
        if(wxYyList.size() > 0){
            List<WxYyDsjkDto> jdccxLogDtoList = CopyUtil.copyList(wxYyList, WxYyDsjkDto.class);
            data = JSON.toJSONString(jdccxLogDtoList);
        }
        return data;
    }

}

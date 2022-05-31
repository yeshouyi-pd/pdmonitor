package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.dto.PointerSecondDto;
import com.pd.server.main.service.PointerSecondService;
import com.pd.server.util.DateUtil;
import org.springframework.util.StringUtils;

import java.util.Date;

public class PointerSecondShjService extends AbstractScanRequest {

    /**
     * 指针数据回传（每秒）
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {

        if(StringUtils.isEmpty(jsonParam.getString("decibelValue")) || StringUtils.isEmpty(jsonParam.getString("cjsj"))
                || StringUtils.isEmpty(jsonParam.getString("sm"))){
            data = "参数错误";
            return data;
        }
        PointerSecondService service = SpringUtil.getBean(PointerSecondService.class);
        PointerSecondDto dto = new PointerSecondDto();
        dto.setDecibelValue(jsonParam.getString("decibelValue"));
        dto.setCjsj(DateUtil.toDate(jsonParam.getString("cjsj"),"yyyy-MM-dd HH:mm:ss"));
        dto.setSm(jsonParam.getString("sm"));
        dto.setCreateTime(new Date());
        service.save(dto);
        data = "保存成功";
        return data;
    }

}

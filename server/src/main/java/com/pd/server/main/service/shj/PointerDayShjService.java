package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.dto.PointerDayDto;
import com.pd.server.main.service.PointerDayService;
import com.pd.server.util.DateUtil;
import org.springframework.util.StringUtils;

import java.util.Date;

public class PointerDayShjService extends AbstractScanRequest {

    /**
     * 指针数据回传（每天）
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
        PointerDayService service = SpringUtil.getBean(PointerDayService.class);
        PointerDayDto dto = new PointerDayDto();
        dto.setDecibelValue(jsonParam.getString("decibelValue"));
        dto.setCjsj(DateUtil.toDate(jsonParam.getString("cjsj"),"yyyy-MM-dd HH:mm:ss"));
        dto.setSm(jsonParam.getString("sm"));
        dto.setCreateTime(new Date());
        service.save(dto);
        data = "保存成功";
        return data;
    }

}

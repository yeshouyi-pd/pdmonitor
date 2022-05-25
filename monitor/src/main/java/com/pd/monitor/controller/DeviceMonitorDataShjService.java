package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.dto.DeviceMonitorDataDto;
import com.pd.server.main.dto.PointerSecondDto;
import com.pd.server.main.service.DeviceMonitorDataService;
import com.pd.server.main.service.PointerSecondService;
import com.pd.server.main.service.shj.AbstractScanRequest;
import com.pd.server.util.DateUtil;
import org.springframework.util.StringUtils;

import java.util.Date;

public class DeviceMonitorDataShjService extends AbstractScanRequest {

    /**
     * 设备监测数据
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {

        if(StringUtils.isEmpty(jsonParam.getString("sbbh")) || StringUtils.isEmpty(jsonParam.getString("type"))
                || StringUtils.isEmpty(jsonParam.getString("value")) || StringUtils.isEmpty(jsonParam.getString("cjsj"))){
            data = "参数错误";
            return data;
        }
        DeviceMonitorDataService service = SpringUtil.getBean(DeviceMonitorDataService.class);
        DeviceMonitorDataDto dto = new DeviceMonitorDataDto();
        dto.setSbbh(jsonParam.getString("sbbh"));
        dto.setType(jsonParam.getString("type"));
        dto.setValue(jsonParam.getString("value"));
        dto.setCjsj(DateUtil.toDate(jsonParam.getString("cjsj"),"yyyy-MM-dd HH:mm:ss"));
        dto.setCreateTime(new Date());
        service.save(dto);
        data = "保存成功";
        return data;
    }
}

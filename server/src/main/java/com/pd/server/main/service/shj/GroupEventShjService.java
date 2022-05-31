package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.GroupEventDto;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.main.service.GroupEventService;
import com.pd.server.util.DateUtil;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

public class GroupEventShjService extends AbstractScanRequest{

    /**
     * 群次数据回传
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {

        if(StringUtils.isEmpty(jsonParam.getString("sbbh")) || StringUtils.isEmpty(jsonParam.getString("kssj"))
                || StringUtils.isEmpty(jsonParam.getString("jssj")) || StringUtils.isEmpty(jsonParam.getString("zcfwj")) ){
            data = "参数错误";
            return data;
        }
        GroupEventService service = SpringUtil.getBean(GroupEventService.class);
        GroupEventDto dto = new GroupEventDto();
        dto.setSbbh(jsonParam.getString("sbbh"));
        dto.setKssj(DateUtil.toDate(jsonParam.getString("kssj"),"yyyy-MM-dd HH:mm:ss"));
        dto.setJssj(DateUtil.toDate(jsonParam.getString("jssj"),"yyyy-MM-dd HH:mm:ss"));
        dto.setZcfwj(jsonParam.getString("zcfwj"));
        WaterEquipmentMapper waterEquipmentMapper = SpringUtil.getBean(WaterEquipmentMapper.class);
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        ca.andSbsnEqualTo(jsonParam.getString("sbbh"));
        List<WaterEquipment> lists = waterEquipmentMapper.selectByExample(example);
        if(!StringUtils.isEmpty(lists)&&lists.size()>0&&!StringUtils.isEmpty(lists.get(0).getDeptcode())){
            dto.setDeptcode(lists.get(0).getDeptcode());
        }
        dto.setCjsj(new Date());
        service.save(dto);
        data = "保存成功";
        return data;
    }
}

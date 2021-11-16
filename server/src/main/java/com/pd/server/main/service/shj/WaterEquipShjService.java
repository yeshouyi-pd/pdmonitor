package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.AttrMapper;
import com.pd.server.main.mapper.WaterEquiplogMapper;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class WaterEquipShjService extends AbstractScanRequest{

    /**
     * 设备心跳包
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {
        String sbbh = jsonParam.getString("sbbh");
        String code = jsonParam.getString("code");
        String msg = jsonParam.getString("msg");
        if(StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(code)){
            data = "参数错误";
            return data;
        }
        WaterEquiplogMapper waterEquiplogMapper = SpringUtil.getBean(WaterEquiplogMapper.class);
        WaterEquipmentMapper waterEquipmentMapper = SpringUtil.getBean(WaterEquipmentMapper.class);
        AttrMapper attrMapper = SpringUtil.getBean(AttrMapper.class);
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSbsnEqualTo(sbbh);
        List<WaterEquipment> listWater = waterEquipmentMapper.selectByExample(example);
        if(listWater.size() == 0){
            data = "设备未备案";
            return data;
        }
        try {
            WaterEquiplog record = new WaterEquiplog();
            record.setId(UuidUtil.getShortUuid());
            record.setSbbh(sbbh);
            record.setCode(code);
            record.setMsg(msg);
            record.setReqmsg(JSONObject.toJSONString(jsonParam));
            record.setCjsj(new Date());
            record.setRespmsg("保存成功");
            waterEquiplogMapper.insert(record);
            AttrExample attrExample = new AttrExample();
            attrExample.createCriteria().andAttrcodeEqualTo("reqinterval");
            List<Attr> list = attrMapper.selectByExample(attrExample);
            if(list.size() == 0){
                data = "60";
            }else{
                data = list.get(0).getAttrkey();
            }
        } catch (Exception e){
            data = "保存失败";
            e.printStackTrace();
        }
        return data;
    }

}

package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.domain.WaterRawfile;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.main.mapper.WaterRawfileMapper;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class WaterRawfileShjService extends AbstractScanRequest{

    /**
     * 非实时数据上传
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {
        String xmbh = jsonParam.getString("xmbh");
        String sbbh = jsonParam.getString("sbbh");
        String wjlj = jsonParam.getString("wjlj");
        if(StringUtils.isEmpty(xmbh)||StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(wjlj)){
            data = "参数错误";
            return data;
        }
        WaterEquipmentMapper waterEquipmentMapper = SpringUtil.getBean(WaterEquipmentMapper.class);
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSbsnEqualTo(sbbh);
        List<WaterEquipment> listWater = waterEquipmentMapper.selectByExample(example);
        if(listWater.size() == 0){
            data = "设备未备案";
            return data;
        }
        try {
            WaterRawfileMapper waterRawfileMapper = SpringUtil.getBean(WaterRawfileMapper.class);
            WaterRawfile record = new WaterRawfile();
            record.setId(UuidUtil.getShortUuid());
            record.setXmbh(xmbh);
            record.setSbbh(sbbh);
            record.setWjlj(wjlj);
            String wjlx = wjlj.substring((wjlj.lastIndexOf(".") + 1));
            record.setWjlx(wjlx);
            record.setCjsj(new Date());
            record.setFxcs(0);
            record.setZt("0");
            waterRawfileMapper.insert(record);
            data = "保存成功";
        } catch (Exception e){
            data = "保存失败";
            e.printStackTrace();
        }
        return data;
    }

}

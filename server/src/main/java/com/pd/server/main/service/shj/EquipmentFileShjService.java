package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.mapper.EquipmentFileMapper;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class EquipmentFileShjService extends AbstractScanRequest{
    /**
     * 设备文件
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {
        String sbbh = jsonParam.getString("sbbh");
        String tplj = jsonParam.getString("tplj");
        String cjsj = jsonParam.getString("cjsj");
        if(StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(tplj)||StringUtils.isEmpty(cjsj)){
            data = "参数错误";
            return data;
        }
        EquipmentFileMapper equipmentFileMapper = SpringUtil.getBean(EquipmentFileMapper.class);
        EquipmentFile entity = new EquipmentFile();
        entity.setId(UuidUtil.getShortUuid());
        entity.setSbbh(sbbh);
        entity.setTplj(tplj);
        entity.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
        entity.setNf(DateUtil.getFormatDate(entity.getCjsj(),"yyyy"));
        entity.setYf(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM"));
        entity.setRq(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd"));
        entity.setCreateTime(new Date());
        equipmentFileMapper.insert(entity);
        data="保存成功";
        return data;
    }
}

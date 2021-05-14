package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.mapper.CodesetMapper;
import com.pd.server.main.mapper.EquipmentFileMapper;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        //Map<String,String> map = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBSNCENTERCODE);
        EquipmentFileMapper equipmentFileMapper = SpringUtil.getBean(EquipmentFileMapper.class);
        EquipmentFile entity = new EquipmentFile();
        entity.setId(UuidUtil.getShortUuid());
        entity.setSbbh(sbbh);
        entity.setTplj(tplj);
        entity.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
        entity.setNf(DateUtil.getFormatDate(entity.getCjsj(),"yyyy"));
        entity.setYf(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM"));
        entity.setRq(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd"));
        entity.setXs(DateUtil.getFormatDate(entity.getCjsj(),"HH"));
        entity.setFz(DateUtil.getFormatDate(entity.getCjsj(),"mm"));
        WaterEquipmentMapper waterEquipmentMapper = SpringUtil.getBean(WaterEquipmentMapper.class);
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        ca.andSbsnEqualTo(sbbh);
        List<WaterEquipment> lists = waterEquipmentMapper.selectByExample(example);
        if(!StringUtils.isEmpty(lists)&&lists.size()>0&&!StringUtils.isEmpty(lists.get(0).getDeptcode())){
            entity.setDeptcode(lists.get(0).getDeptcode());
        }
        entity.setCreateTime(new Date());
        equipmentFileMapper.insert(entity);
        data="保存成功";
        return data;
    }

}

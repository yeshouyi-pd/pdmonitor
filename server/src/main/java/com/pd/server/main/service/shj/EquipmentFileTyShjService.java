package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.mapper.EquipmentFileMapper;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class EquipmentFileTyShjService extends AbstractScanRequest{

    /**
     * 拖曳设备文件
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {
        String sbbh = jsonParam.getString("sbbh");
        String tplj = jsonParam.getString("tplj");
        String cjsj = jsonParam.getString("cjsj");
        String sm1 = jsonParam.getString("sm1");
        if(StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(tplj)||StringUtils.isEmpty(cjsj)||StringUtils.isEmpty(sm1)){
            data = "参数错误";
            return data;
        }
        EquipmentFileMapper equipmentFileMapper = SpringUtil.getBean(EquipmentFileMapper.class);
        EquipmentFileExample exampleFile = new EquipmentFileExample();
        EquipmentFileExample.Criteria caFile = exampleFile.createCriteria();
        caFile.andTpljEqualTo(tplj);
        caFile.andSbbhEqualTo(sbbh);
        List<EquipmentFile> comment = equipmentFileMapper.selectByExample(exampleFile);
        if(comment==null || comment.isEmpty()){
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
            entity.setLy("1");//实时数据
            entity.setSm1(sm1);
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
        }else {
            data="该图片已保存过";
            return data;
        }
    }

}

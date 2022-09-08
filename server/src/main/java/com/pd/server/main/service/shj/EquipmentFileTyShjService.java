package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.EquipmentFileTyMapper;
import com.pd.server.main.mapper.EquipmentFileTyTodayMapper;
import com.pd.server.main.mapper.EquipmentTyEventMapper;
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
        try {
            JSONObject obj = JSONObject.parseObject(sm1);
            EquipmentTyEventMapper equipmentTyEventMapper = SpringUtil.getBean(EquipmentTyEventMapper.class);
            EquipmentFileTyMapper equipmentFileTyMapper = SpringUtil.getBean(EquipmentFileTyMapper.class);
            EquipmentFileTyTodayMapper todayMapper = SpringUtil.getBean(EquipmentFileTyTodayMapper.class);
            EquipmentFileTyExample exampleFile = new EquipmentFileTyExample();
            EquipmentFileTyExample.Criteria caFile = exampleFile.createCriteria();
            caFile.andTpljEqualTo(tplj);
            caFile.andSbbhEqualTo(sbbh);
            List<EquipmentFileTy> comment = equipmentFileTyMapper.selectByExample(exampleFile);
            if(comment==null || comment.isEmpty()){
                EquipmentFileTy entity = new EquipmentFileTy();
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
                entity.setSm2(obj.getString("jd")+","+obj.getString("wd"));
                WaterEquipmentMapper waterEquipmentMapper = SpringUtil.getBean(WaterEquipmentMapper.class);
                WaterEquipmentExample example = new WaterEquipmentExample();
                WaterEquipmentExample.Criteria ca = example.createCriteria();
                ca.andSbsnEqualTo(sbbh);
                List<WaterEquipment> lists = waterEquipmentMapper.selectByExample(example);
                String deptcode = "";
                if(!StringUtils.isEmpty(lists)&&lists.size()>0&&!StringUtils.isEmpty(lists.get(0).getDeptcode())){
                    entity.setDeptcode(lists.get(0).getDeptcode());
                    deptcode = lists.get(0).getDeptcode();
                }
                entity.setCreateTime(new Date());
                if(tplj.contains("A2")&&tplj.contains("txt")){
                    String wjmc = tplj.substring(tplj.lastIndexOf("/")+1,tplj.lastIndexOf("_A2.txt"));
                    entity.setSm3("3");
                    EquipmentTyEvent tyEvent = new EquipmentTyEvent();
                    tyEvent.setId(UuidUtil.getShortUuid());
                    tyEvent.setSbbh(sbbh);
                    tyEvent.setDeptcode(deptcode);
                    String kssj = wjmc.substring(0,4)+"-"+wjmc.substring(5,7)+"-"+wjmc.substring(8,10)+" "+wjmc.substring(11,13)+":"+wjmc.substring(14,16)+":"+wjmc.substring(17,19);
                    String jssj = wjmc.substring(20,24)+"-"+wjmc.substring(25,27)+"-"+wjmc.substring(28,30)+" "+wjmc.substring(31,33)+":"+wjmc.substring(34,36)+":"+wjmc.substring(37,39);
                    tyEvent.setKssj(DateUtil.toDate(kssj,"yyyy-MM-dd HH:mm:ss"));
                    tyEvent.setJssj(DateUtil.toDate(jssj,"yyyy-MM-dd HH:mm:ss"));
                    tyEvent.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
                    tyEvent.setTs("0".equals(wjmc.substring(wjmc.lastIndexOf("_")+1))?"1":wjmc.substring(wjmc.lastIndexOf("_")+1));
                    equipmentTyEventMapper.insert(tyEvent);
                }
                equipmentFileTyMapper.insert(entity);
                todayMapper.save(entity);
                data="保存成功";
                return data;
            }else {
                data="该图片已保存过";
                return data;
            }
        }catch (Exception e){
            data="数据解析出错";
            return data;
        }
    }

}

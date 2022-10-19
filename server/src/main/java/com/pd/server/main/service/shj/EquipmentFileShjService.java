package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.PointerDayDto;
import com.pd.server.main.dto.PointerSecondDto;
import com.pd.server.main.mapper.*;
import com.pd.server.main.service.PointerDayService;
import com.pd.server.main.service.PointerSecondService;
import com.pd.server.util.DateUtil;
import com.pd.server.util.TypeUtils;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        EquipmentFileMapper equipmentFileMapper = SpringUtil.getBean(EquipmentFileMapper.class);
        EquipmentFileTodayMapper todayMapper = SpringUtil.getBean(EquipmentFileTodayMapper.class);
        EquipmentFileTodayExample exampleTodayFile = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria caFile = exampleTodayFile.createCriteria();
        caFile.andTpljEqualTo(tplj);
        caFile.andSbbhEqualTo(sbbh);
        List<EquipmentFileToday> comment = todayMapper.selectByExample(exampleTodayFile);
        if(comment==null || comment.isEmpty()){
            WaterEquipmentMapper waterEquipmentMapper = SpringUtil.getBean(WaterEquipmentMapper.class);
            WaterEquipmentExample example = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria ca = example.createCriteria();
            ca.andSbsnEqualTo(sbbh);
            List<WaterEquipment> lists = waterEquipmentMapper.selectByExample(example);
            if(lists==null || lists.isEmpty()){
                data="设备编号不存在";
                return data;
            }
            String deptcode = lists.get(0).getDeptcode();
            EquipmentFile entity = new EquipmentFile();
            entity.setId(UuidUtil.getShortUuid());
            entity.setSbbh(sbbh);
            entity.setTplj(tplj);
            entity.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
            entity.setNf(DateUtil.getFormatDate(entity.getCjsj(),"yyyy"));
            entity.setYf(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM"));
            entity.setRq(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd"));
            entity.setXs(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd HH"));
            entity.setFz(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd HH:mm"));
            entity.setLy("1");//实时数据
            entity.setDeptcode(deptcode);
            if(tplj.contains("predation")){
                entity.setJczl("1");//捕食
            }
            if(!StringUtils.isEmpty(jsonParam.get("sm1"))){
                entity.setSm1(jsonParam.getString("sm1"));
            }
            entity.setCreateTime(new Date());
            //调用方法，传递文件名称，获取wjlx.type,txtlx,ts,wjmc
            Map<String, String> typeUtil = TypeUtils.getType(tplj.substring(tplj.lastIndexOf("/")+1));
            entity.setTs(typeUtil.get(TypeUtils.TS));
            entity.setType(typeUtil.get(TypeUtils.TYPE));
            entity.setTxtlx(typeUtil.get(TypeUtils.TXTLX));
            entity.setWjmc(typeUtil.get(TypeUtils.WJMC));
            entity.setWjlx(typeUtil.get(TypeUtils.WJLX));
            if("1020".equals(entity.getType())||"1022".equals(entity.getType())||"1024".equals(entity.getType())||"1026".equals(entity.getType())){
                EquipmentFileEventMapper equipmentFileEventMapper = SpringUtil.getBean(EquipmentFileEventMapper.class);
                String temp = tplj.substring(tplj.lastIndexOf("/")+1,tplj.lastIndexOf("_A4.txt"));
                String[] arr = temp.split("_");
                EquipmentFileEvent fileEvent = new EquipmentFileEvent();
                fileEvent.setId(UuidUtil.getShortUuid());
                fileEvent.setSbbh(sbbh);
                fileEvent.setKssj(arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5]);
                fileEvent.setJssj("1020".equals(entity.getType())||"1026".equals(entity.getType())?arr[7]+"-"+arr[8]+"-"+arr[9]+" "+arr[10]+":"+arr[11]+":"+arr[12]:arr[6]+"-"+arr[7]+"-"+arr[8]+" "+arr[9]+":"+arr[10]+":"+arr[11]);
                fileEvent.setRq(arr[0]+"-"+arr[1]+"-"+arr[2]);
                fileEvent.setTs(entity.getTs());
                fileEvent.setJtnr(entity.getSm1());
                fileEvent.setDeptcode(deptcode);
                fileEvent.setEquipmentFileId(entity.getId());
                equipmentFileEventMapper.insertSelective(fileEvent);
            }else if("1018".equals(entity.getType())){//指针数据每秒
                PointerSecondService service = SpringUtil.getBean(PointerSecondService.class);
                PointerSecondDto dto = new PointerSecondDto();
                dto.setDecibelValue(entity.getTs());
                dto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
                dto.setSm(sbbh);
                dto.setCreateTime(new Date());
                service.save(dto);
            }else if("1019".equals(entity.getType())){//指针数据每天
                PointerDayService service = SpringUtil.getBean(PointerDayService.class);
                PointerDayDto dto = new PointerDayDto();
                dto.setDecibelValue(entity.getTs());
                dto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
                dto.setSm(sbbh);
                dto.setCreateTime(new Date());
                service.save(dto);
            }
            equipmentFileMapper.insert(entity);
            todayMapper.insertEquipFile(entity);
            data="保存成功";
            return data;
        }else {
            data="该图片已保存过";
            return data;
        }
    }

}

package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.PointerDayDto;
import com.pd.server.main.dto.PointerSecondDto;
import com.pd.server.main.mapper.EquipmentFileTyMapper;
import com.pd.server.main.mapper.EquipmentFileTyTodayMapper;
import com.pd.server.main.mapper.EquipmentTyEventMapper;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.main.service.PointerDayService;
import com.pd.server.main.service.PointerSecondService;
import com.pd.server.util.DateUtil;
import com.pd.server.util.TypeUtils;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentFileTyShjService extends AbstractScanRequest{

    public static EquipmentTyEventMapper equipmentTyEventMapperStatic;
    public static EquipmentFileTyMapper equipmentFileTyMapperStatic;
    public static EquipmentFileTyTodayMapper todayMapperStatic;
    public static WaterEquipmentMapper waterEquipmentMapperStatic;
    public static PointerSecondService pointerSecondServiceStatic;
    public static PointerDayService pointerDayServiceStatic;

    @Resource
    private EquipmentTyEventMapper equipmentTyEventMapper;
    @Resource
    private EquipmentFileTyMapper equipmentFileTyMapper;
    @Resource
    private EquipmentFileTyTodayMapper todayMapper;
    @Resource
    private WaterEquipmentMapper waterEquipmentMapper;
    @Resource
    private PointerSecondService pointerSecondService;
    @Resource
    private PointerDayService pointerDayService;

    @PostConstruct
    protected void init() {
        equipmentTyEventMapperStatic = equipmentTyEventMapper;
        equipmentFileTyMapperStatic = equipmentFileTyMapper;
        todayMapperStatic = todayMapper;
        waterEquipmentMapperStatic = waterEquipmentMapper;
        pointerSecondServiceStatic = pointerSecondService;
        pointerDayServiceStatic = pointerDayService;
    }

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
        String wzlx = jsonParam.getString("wzlx");
        if(StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(tplj)||StringUtils.isEmpty(cjsj)||StringUtils.isEmpty(sm1)){
            data = "参数错误";
            return data;
        }
        try {
            JSONObject obj = JSONObject.parseObject(sm1);
            EquipmentFileTyTodayExample exampleFile = new EquipmentFileTyTodayExample();
            EquipmentFileTyTodayExample.Criteria caFile = exampleFile.createCriteria();
            caFile.andTpljEqualTo(tplj);
            caFile.andSbbhEqualTo(sbbh);
            List<EquipmentFileTyToday> comment = todayMapperStatic.selectByExample(exampleFile);
            if(comment==null || comment.isEmpty()){
                WaterEquipmentExample example = new WaterEquipmentExample();
                WaterEquipmentExample.Criteria ca = example.createCriteria();
                ca.andSbsnEqualTo(sbbh);
                List<WaterEquipment> lists = waterEquipmentMapperStatic.selectByExample(example);
                if(lists==null || lists.isEmpty()){
                    data="设备编号不存在";
                    return data;
                }
                String deptcode = lists.get(0).getDeptcode();
                EquipmentFileTy entity = new EquipmentFileTy();
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
                entity.setSm1(sm1);
                entity.setSm2(obj.getString("jd")+","+obj.getString("wd"));
                if(tplj.contains("predation")){
                    entity.setJczl("1");//捕食
                }
                entity.setSm6(StringUtils.isEmpty(wzlx)?"1":wzlx);//物种类型
                entity.setCreateTime(new Date());
                //调用方法，传递文件名称，获取wjlx.type,txtlx,ts,wjmc
                Map<String, String> typeUtil = TypeUtils.getType(tplj.substring(tplj.lastIndexOf("/")+1));
                entity.setTs(typeUtil.get(TypeUtils.TS));
                entity.setType(typeUtil.get(TypeUtils.TYPE));
                entity.setTxtlx(typeUtil.get(TypeUtils.TXTLX));
                entity.setWjmc(typeUtil.get(TypeUtils.WJMC));
                entity.setWjlx(typeUtil.get(TypeUtils.WJLX));
                if("1012".equals(entity.getType())){
                    String wjmc = tplj.substring(tplj.lastIndexOf("/")+1,tplj.lastIndexOf("_A2.txt"));
                    EquipmentTyEvent tyEvent = new EquipmentTyEvent();
                    tyEvent.setId(UuidUtil.getShortUuid());
                    tyEvent.setSbbh(sbbh);
                    tyEvent.setDeptcode(deptcode);
                    String kssj = wjmc.substring(0,4)+"-"+wjmc.substring(5,7)+"-"+wjmc.substring(8,10)+" "+wjmc.substring(11,13)+":"+wjmc.substring(14,16)+":"+wjmc.substring(17,19);
                    String jssj = wjmc.substring(20,24)+"-"+wjmc.substring(25,27)+"-"+wjmc.substring(28,30)+" "+wjmc.substring(31,33)+":"+wjmc.substring(34,36)+":"+wjmc.substring(37,39);
                    tyEvent.setKssj(kssj);
                    tyEvent.setJssj(jssj);
                    tyEvent.setRq(wjmc.substring(0,4)+"-"+wjmc.substring(5,7)+"-"+wjmc.substring(8,10));
                    tyEvent.setTs(entity.getTs());
                    tyEvent.setBz(entity.getId());
                    tyEvent.setSm(entity.getSm2());
                    tyEvent.setSm1(obj.getString("sd"));
                    equipmentTyEventMapperStatic.insert(tyEvent);
                }else if("1018".equals(entity.getType())){//指针数据每秒
                    PointerSecondDto dto = new PointerSecondDto();
                    dto.setDecibelValue(entity.getTs());
                    dto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
                    dto.setSm(sbbh);
                    dto.setCreateTime(new Date());
                    dto.setBz(deptcode);
                    pointerSecondServiceStatic.save(dto);
                }else if("1019".equals(entity.getType())){//指针数据每天
                    PointerDayDto dto = new PointerDayDto();
                    dto.setDecibelValue(entity.getTs());
                    dto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
                    dto.setSm(sbbh);
                    dto.setCreateTime(new Date());
                    dto.setBz(deptcode);
                    pointerDayServiceStatic.save(dto);
                }
                equipmentFileTyMapperStatic.insert(entity);
                todayMapperStatic.insertEquipTy(entity);
                data="保存成功";
                JSONObject result = new JSONObject();
                result.put("data",data);
                result.put("entity",entity);
                return result.toJSONString();
            }else {
                data="该图片已保存过";
                JSONObject result = new JSONObject();
                result.put("data",data);
                return result.toJSONString();
            }
        }catch (Exception e){
            JSONObject result = new JSONObject();
            result.put("data",e.getMessage());
            return result.toJSONString();
        }
    }

}

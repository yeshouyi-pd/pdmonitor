package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.mapper.EquipmentFileTyMapper;
import com.pd.server.main.mapper.EquipmentFileTyTodayMapper;
import com.pd.server.main.mapper.EquipmentTyEventMapper;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.main.service.*;
import com.pd.server.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class EquipmentFileTyShjService extends AbstractScanRequest{

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileTyShjService.class);

    public static RedisTemplate redisTstaticemplate;
    public static EquipmentTyEventMapper equipmentTyEventMapperStatic;
    public static EquipmentFileTyMapper equipmentFileTyMapperStatic;
    public static EquipmentFileTyTodayMapper todayMapperStatic;
    public static WaterEquipmentMapper waterEquipmentMapperStatic;
    public static PointerSecondService pointerSecondServiceStatic;
    public static PointerDayService pointerDayServiceStatic;
    public static BeconFileTyService beconFileTyServiceStatic;
    public static BeconFileTyTodayService beconFileTyTodayServiceStatic;
    public static AngleFileService angleFileServiceStatic;
    public static AttrService attrServiceStatic;

    @Resource
    private RedisTemplate redisTemplate;
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
    @Resource
    private BeconFileTyService beconFileTyService;
    @Resource
    private BeconFileTyTodayService beconFileTyTodayService;
    @Resource
    private AngleFileService angleFileService;
    @Resource
    private AttrService attrService;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
        equipmentTyEventMapperStatic = equipmentTyEventMapper;
        equipmentFileTyMapperStatic = equipmentFileTyMapper;
        todayMapperStatic = todayMapper;
        waterEquipmentMapperStatic = waterEquipmentMapper;
        pointerSecondServiceStatic = pointerSecondService;
        pointerDayServiceStatic = pointerDayService;
        beconFileTyServiceStatic = beconFileTyService;
        beconFileTyTodayServiceStatic = beconFileTyTodayService;
        angleFileServiceStatic = angleFileService;
        attrServiceStatic = attrService;
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
        //判断是不是信标文件，如果是则调用信标方法
        if(tplj.contains("Becon")){
            return beconMethod(jsonParam);
        }
        //指针数据每秒
        if(tplj.contains("FISH")){
            return fishMethod(jsonParam);
        }
        //指针数据每天
        if(tplj.contains("YFP")){
            return yfpMethod(jsonParam);
        }
        try {
            JSONObject obj = JSONObject.parseObject(sm1);
            EquipmentFileTyTodayExample exampleFile = new EquipmentFileTyTodayExample();
            EquipmentFileTyTodayExample.Criteria caFile = exampleFile.createCriteria();
            caFile.andTpljEqualTo(tplj);
            caFile.andSbbhEqualTo(sbbh);
            List<EquipmentFileTyToday> comment = todayMapperStatic.selectByExample(exampleFile);
            if(comment==null || comment.isEmpty()){
                Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTstaticemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
                if(!sbbhEquipMap.keySet().contains("equip-"+sbbh)){
                    return "设备编号不存在";
                }
                WaterEquipment waterEquipment = JSONObject.toJavaObject(sbbhEquipMap.get("equip-"+sbbh), WaterEquipment.class);
                String deptcode = waterEquipment.getDeptcode();
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
                    entity.setSm6(obj.getString("sd"));//参分表参数传递
                    tyEvent.setSm1(obj.getString("sd"));
                    equipmentTyEventMapperStatic.insert(tyEvent);
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

    public static String fishMethod(JSONObject jsonParam){
        try {
            String sbbh = jsonParam.getString("sbbh");
            String tplj = jsonParam.getString("tplj");
            String cjsj = jsonParam.getString("cjsj");
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTstaticemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            if(!sbbhEquipMap.keySet().contains("equip-"+sbbh)){
                return "设备编号不存在";
            }
            if(!Pattern.matches(TypeUtils.ZZ_18, tplj.substring(tplj.lastIndexOf("/")+1))){
                return "文件名称命名错误";
            }
            WaterEquipment waterEquipment = JSONObject.toJavaObject(sbbhEquipMap.get("equip-"+sbbh), WaterEquipment.class);
            String deptcode = waterEquipment.getDeptcode();
            PointerSecondDto dto = new PointerSecondDto();
            dto.setDecibelValue(tplj.substring(tplj.indexOf("FISH_")+5,tplj.lastIndexOf(".txt")));
            dto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
            dto.setSm(sbbh);
            dto.setCreateTime(new Date());
            dto.setBz(deptcode);
            pointerSecondServiceStatic.save(dto);
            JSONObject result = new JSONObject();
            result.put("data","保存成功");
            result.put("otherFile",true);
            return result.toJSONString();
        }catch (Exception e){
            LOG.error("保存指针文件出错："+e.getMessage());
            return "保存指针文件出错";
        }
    }

    public static String yfpMethod(JSONObject jsonParam){
        try {
            String sbbh = jsonParam.getString("sbbh");
            String tplj = jsonParam.getString("tplj");
            String cjsj = jsonParam.getString("cjsj");
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTstaticemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            if(!sbbhEquipMap.keySet().contains("equip-"+sbbh)){
                return "设备编号不存在";
            }
            if(!Pattern.matches(TypeUtils.ZZ_19, tplj.substring(tplj.lastIndexOf("/")+1))){
                return "文件名称命名错误";
            }
            WaterEquipment waterEquipment = JSONObject.toJavaObject(sbbhEquipMap.get("equip-"+sbbh), WaterEquipment.class);
            String deptcode = waterEquipment.getDeptcode();
            PointerDayDto dto = new PointerDayDto();
            dto.setDecibelValue(tplj.substring(tplj.indexOf("YFP_")+4,tplj.lastIndexOf(".txt")));
            dto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
            dto.setSm(sbbh);
            dto.setCreateTime(new Date());
            dto.setBz(deptcode);
            pointerDayServiceStatic.save(dto);
            JSONObject result = new JSONObject();
            result.put("data","保存成功");
            result.put("otherFile",true);
            return result.toJSONString();
        }catch (Exception e){
            LOG.error("保存指针文件出错："+e.getMessage());
            return "保存指针文件出错";
        }
    }

    //信标文件
    public static String beconMethod(JSONObject jsonParam){
        try {
            String sbbh = jsonParam.getString("sbbh");
            String tplj = jsonParam.getString("tplj");
            String cjsj = jsonParam.getString("cjsj");
            String sm1 = jsonParam.getString("sm1");
            String sm2 = jsonParam.getString("sm2");
            JSONObject obj = JSONObject.parseObject(sm1);
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTstaticemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            if(StringUtils.isEmpty(sm2)){
                return "错误，缺少必要参数";
            }
            if(!sbbhEquipMap.keySet().contains("equip-"+sbbh)){
                return "设备编号不存在";
            }
            if(!Pattern.matches(TypeUtils.ZZ_32, tplj.substring(tplj.lastIndexOf("/")+1))){
                return "文件名称命名错误";
            }
            BeconFileTyTodayExample example = new BeconFileTyTodayExample();
            BeconFileTyTodayExample.Criteria caFile = example.createCriteria();
            caFile.andWjljEqualTo(tplj);
            List<BeconFileTyToday> comment = beconFileTyTodayServiceStatic.selectByExample(example);
            if(!(comment==null || comment.isEmpty())){
                return "该数据已保存过";
            }
            WaterEquipment waterEquipment = JSONObject.toJavaObject(sbbhEquipMap.get("equip-"+sbbh), WaterEquipment.class);
            BeconFileTyDto beconFileTyDto = new BeconFileTyDto();
            beconFileTyDto.setSbbh(sbbh);
            beconFileTyDto.setWjlj(tplj);
            beconFileTyDto.setXbid(sm2);
            beconFileTyDto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
            beconFileTyDto.setJssj(new Date());
            beconFileTyDto.setDeptcode(waterEquipment.getDeptcode());
            beconFileTyDto.setRq(DateUtil.getFormatDate(beconFileTyDto.getCjsj(),"yyyy-MM-dd"));
            beconFileTyDto.setSm1(sm1);
            beconFileTyDto.setGps(obj.getString("jd")+","+obj.getString("wd"));
            beconFileTyDto.setJd(obj.getString("jd"));
            beconFileTyDto.setWd(obj.getString("wd"));
            beconFileTyServiceStatic.save(beconFileTyDto);
            BeconFileTyToday beconFileToday = CopyUtil.copy(beconFileTyDto, BeconFileTyToday.class);
            beconFileTyTodayServiceStatic.insert(beconFileToday);
            JSONObject result = new JSONObject();
            result.put("data","保存成功");
            result.put("otherFile",true);
            return result.toJSONString();
        }catch (Exception e){
            return "保存基站文件数据错误";
        }
    }
}

package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.mapper.*;
import com.pd.server.main.service.*;
import com.pd.server.util.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class EquipmentFileShjService extends AbstractScanRequest{

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileShjService.class);

    public static RedisTemplate redisTstaticemplate;
    public static AttrService attrServiceStatic;
    public static EquipmentFileMapper equipmentFileMapperStatic;
    public static EquipmentFileTodayMapper todayMapperStatic;
    public static WaterEquipmentMapper waterEquipmentMapperStatic;
    public static EquipmentFileEventMapper equipmentFileEventMapperStatic;
    public static PointerSecondService pointerSecondServiceStatic;
    public static PointerDayService pointerDayServiceStatic;
    public static CameraInfoService cameraInfoServiceStatic;
    public static CameraMiddleService cameraMiddleServiceStatic;
    public static CodesetMapper codesetMapperStatic;
    public static AzimuthAngleService azimuthAngleServiceStatic;
    public static AzimuthAngleRqService azimuthAngleRqServiceStatic;
    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private AttrService attrService;
    @Resource
    private EquipmentFileMapper equipmentFileMapper;
    @Resource
    private EquipmentFileTodayMapper todayMapper;
    @Resource
    private WaterEquipmentMapper waterEquipmentMapper;
    @Resource
    private EquipmentFileEventMapper equipmentFileEventMapper;
    @Resource
    private PointerSecondService pointerSecondService;
    @Resource
    private PointerDayService pointerDayService;
    @Resource
    private CameraInfoService cameraInfoService;
    @Resource
    private CameraMiddleService cameraMiddleService;
    @Resource
    private CodesetMapper codesetMapper;
    @Resource
    private AzimuthAngleService azimuthAngleService;
    @Resource
    private AzimuthAngleRqService azimuthAngleRqService;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
        attrServiceStatic = attrService;
        equipmentFileMapperStatic = equipmentFileMapper;
        todayMapperStatic = todayMapper;
        waterEquipmentMapperStatic = waterEquipmentMapper;
        equipmentFileEventMapperStatic = equipmentFileEventMapper;
        pointerSecondServiceStatic = pointerSecondService;
        pointerDayServiceStatic = pointerDayService;
        cameraInfoServiceStatic = cameraInfoService;
        cameraMiddleServiceStatic = cameraMiddleService;
        codesetMapperStatic = codesetMapper;
        azimuthAngleServiceStatic = azimuthAngleService;
        azimuthAngleRqServiceStatic = azimuthAngleRqService;
    }

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
        String wzlx = jsonParam.getString("wzlx");
        boolean pushData = false;
        if(StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(tplj)||StringUtils.isEmpty(cjsj)){
            data = "参数错误";
            return data;
        }
        EquipmentFileTodayExample exampleTodayFile = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria caFile = exampleTodayFile.createCriteria();
        caFile.andTpljEqualTo(tplj);
        caFile.andSbbhEqualTo(sbbh);
        List<EquipmentFileToday> comment = todayMapperStatic.selectByExample(exampleTodayFile);
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
            entity.setSm6(StringUtils.isEmpty(wzlx)?"1":wzlx);//物种类型
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
            if("4".equals(typeUtil.get(TypeUtils.WJLX))&&("1021".equals(entity.getType())||"1023".equals(entity.getType())||"1025".equals(entity.getType())||"1027".equals(entity.getType()))){
                String temp = tplj.substring(tplj.lastIndexOf("/")+1,tplj.lastIndexOf("_A4"));
                String[] arr = temp.split("_");
                String kssj = arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5];
                String jssj = "1021".equals(entity.getType())||"1027".equals(entity.getType())?arr[7]+"-"+arr[8]+"-"+arr[9]+" "+arr[10]+":"+arr[11]+":"+arr[12]:arr[6]+"-"+arr[7]+"-"+arr[8]+" "+arr[9]+":"+arr[10]+":"+arr[11];
                if(kssj.equals(jssj)){
                    data="该数据不用保存";
                    return data;
                }
            }
            if(!"0".equals(typeUtil.get(TypeUtils.TS))&&("1020".equals(entity.getType())||"1022".equals(entity.getType())||"1024".equals(entity.getType())||"1026".equals(entity.getType()))){
                String temp = tplj.substring(tplj.lastIndexOf("/")+1,tplj.lastIndexOf("_A4.txt"));
                String[] arr = temp.split("_");
                String kssj = arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5];
                String jssj = "1020".equals(entity.getType())||"1026".equals(entity.getType())?arr[7]+"-"+arr[8]+"-"+arr[9]+" "+arr[10]+":"+arr[11]+":"+arr[12]:arr[6]+"-"+arr[7]+"-"+arr[8]+" "+arr[9]+":"+arr[10]+":"+arr[11];
                String eventsbsn = attrServiceStatic.findByAttrKey("eventsbsn");
                if(eventsbsn.contains(sbbh)){
                    EquipmentFileEvent fileEvent = new EquipmentFileEvent();
                    fileEvent.setId(UuidUtil.getShortUuid());
                    fileEvent.setSbbh(sbbh);
                    fileEvent.setKssj(kssj);
                    fileEvent.setJssj(jssj);
                    fileEvent.setRq(arr[0]+"-"+arr[1]+"-"+arr[2]);
                    fileEvent.setTs(entity.getTs());
                    fileEvent.setJtnr(entity.getSm1());
                    fileEvent.setDeptcode(deptcode);
                    fileEvent.setEquipmentFileId(entity.getId());
                    equipmentFileEventMapperStatic.insertSelective(fileEvent);
                    staticAzimuthAngle(sbbh,deptcode,fileEvent.getRq(),fileEvent.getJtnr());
                    //南京设备对接无人机
                    pushMqMsg(sbbh,entity.getSm1());
                }else {
                    if(!kssj.equals(jssj)){
                        EquipmentFileEvent fileEvent = new EquipmentFileEvent();
                        fileEvent.setId(UuidUtil.getShortUuid());
                        fileEvent.setSbbh(sbbh);
                        fileEvent.setKssj(kssj);
                        fileEvent.setJssj(jssj);
                        fileEvent.setRq(arr[0]+"-"+arr[1]+"-"+arr[2]);
                        fileEvent.setTs(entity.getTs());
                        fileEvent.setJtnr(entity.getSm1());
                        fileEvent.setDeptcode(deptcode);
                        fileEvent.setEquipmentFileId(entity.getId());
                        equipmentFileEventMapperStatic.insertSelective(fileEvent);
                        staticAzimuthAngle(sbbh,deptcode,fileEvent.getRq(),fileEvent.getJtnr());
                        //南京设备对接无人机
                        pushMqMsg(sbbh,entity.getSm1());
                        //推送文件
                        if("JXYSA4001".equals(sbbh)&&!pushData){
                            PushFile.pushFile1(entity.getTplj());
                            pushData = true;
                        }
                    }
                }
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
            String predationsbsn = attrServiceStatic.findByAttrKey("predationsbsn");
            if(predationsbsn.contains(sbbh)&&tplj.contains("txt")&&("1001".equals(typeUtil.get(TypeUtils.TYPE))||"1007".equals(typeUtil.get(TypeUtils.TYPE)))){
                //判断是否是雾报(前后三分钟都没有报警的数据是雾报数据，雾报数据不保存)
                EquipmentFile beforeEntity = new EquipmentFile();
                LOG.error("缓存中的数据："+redisTstaticemplate.opsForValue().get(sbbh+"WB"));
                if(!StringUtils.isEmpty(redisTstaticemplate.opsForValue().get(sbbh+"WB"))){
                    String entityJson = (String) redisTstaticemplate.opsForValue().get(sbbh+"WB");
                    beforeEntity = JSONObject.parseObject(entityJson,EquipmentFile.class);
                    if(!StringUtils.isEmpty(beforeEntity.getCjsj())&&isOverThreeMinute(DateUtil.getFormatDate(beforeEntity.getCjsj(),"yyyy-MM-dd HH:mm:ss"),cjsj)){
                        equipmentFileMapperStatic.insert(beforeEntity);
                        todayMapperStatic.insertEquipFile(beforeEntity);
                        redisTstaticemplate.opsForValue().set(sbbh+"WB", JSONObject.toJSONString(entity));
                        //白海豚写剪切视频的事件，李响读了去剪切视频
                        if(("1001,1007,1009,1010,1020,1022,1024,1026".contains(beforeEntity.getType()))&&sbbh.contains("RPCD")){
                            saveNewEvent(beforeEntity);
                        }
                    }else{
                        try{
                            EquipmentFile lastFile = equipmentFileMapperStatic.selectLastOneBySbbh(sbbh);
                            if(!StringUtils.isEmpty(beforeEntity.getCjsj())&&!StringUtils.isEmpty(lastFile.getCjsj())&&isOverThreeMinute(DateUtil.getFormatDate(lastFile.getCjsj(),"yyyy-MM-dd HH:mm:ss"),DateUtil.getFormatDate(beforeEntity.getCjsj(),"yyyy-MM-dd HH:mm:ss"))){
                                equipmentFileMapperStatic.insert(beforeEntity);
                                todayMapperStatic.insertEquipFile(beforeEntity);
                                //白海豚写剪切视频的事件，李响读了去剪切视频
                                if(("1001,1007,1009,1010,1020,1022,1024,1026".contains(beforeEntity.getType()))&&sbbh.contains("RPCD")){
                                    saveNewEvent(beforeEntity);
                                }
                            }
                        }catch (Exception e){
                            LOG.error("错误："+e.getMessage());
                        }
                        redisTstaticemplate.opsForValue().set(sbbh+"WB", JSONObject.toJSONString(entity));
                    }
                }else{
                    redisTstaticemplate.opsForValue().set(sbbh+"WB", JSONObject.toJSONString(entity));
                }
                data="保存成功";
                JSONObject result = new JSONObject();
                result.put("data",data);
                result.put("entity",beforeEntity);
                return result.toJSONString();
            }else {
                equipmentFileMapperStatic.insert(entity);
                todayMapperStatic.insertEquipFile(entity);
                data="保存成功";
                JSONObject result = new JSONObject();
                result.put("data",data);
                result.put("entity",entity);
                //白海豚写剪切视频的事件，李响读了去剪切视频
                if(("1001,1007,1009,1010,1020,1022,1024,1026".contains(entity.getType()))&&(sbbh.contains("RPCD")||sbbh.contains("tl"))){
//                    if("A4".equals(lists.get(0).getDqzl())&&"1020,1022,1024,1026".contains(entity.getType())){
//                        saveNewEvent(entity);
//                    }else if("A1".equals(lists.get(0).getDqzl())){
//                        saveNewEvent(entity);
//                    }
                    try {
                        saveNewEvent(entity);
                    }catch (Exception e){
                        LOG.error("保存视频剪切出错：" + e.getMessage());
                    }
                }
                //推送文件
                if("JXYSA4001".equals(sbbh)&&!pushData){
                    try {
                        PushFile.pushFile1(entity.getTplj());
                    }catch (Exception e){
                        LOG.error("推送文件3出错：" + e.getMessage());
                    }
                }
                return result.toJSONString();
            }
        }else {
            data="该图片已保存过";
            return data;
        }
    }

    public static Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        String predationInterval = attrServiceStatic.findByAttrKey("predationInterval");
        if(minute<=Integer.parseInt(predationInterval)){
            return true;
        }
        return false;
    }

    public static void saveNewEvent(EquipmentFile record){
        LOG.error("剪切视频数据："+JSONObject.toJSONString(record));
        String[] arr = record.getWjmc().split("_");
        if(Integer.parseInt(arr[3])<Integer.parseInt(attrServiceStatic.findByAttrKey("zskssj")) || Integer.parseInt(arr[3])>Integer.parseInt(attrServiceStatic.findByAttrKey("wsjssj"))){
            return;
        }
        String temp = arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5];
        String temJssj = (String) redisTstaticemplate.opsForValue().get("JQ"+record.getSbbh());
        if(StringUtils.isEmpty(temJssj) || !StringUtils.isEmpty(temJssj)&&DateUtil.toDate(temp,"yyyy-MM-dd HH:mm:ss").getTime()>DateUtil.toDate(temJssj,"yyyy-MM-dd HH:mm:ss").getTime()){
            List<CameraInfo> cameraInfoList = cameraInfoServiceStatic.findBySbbh(record.getSbbh());
            String jssj = "";
            for(CameraInfo cameraInfo: cameraInfoList){
                CameraMiddleDto cameraMiddle = new CameraMiddleDto();
                cameraMiddle.setSbbh(record.getSbbh());//设备编号
                cameraMiddle.setIp(cameraInfo.getIp());//摄像头ip
                cameraMiddle.setPort(cameraInfo.getPort()+"");//nvr剪切端口
                cameraMiddle.setUsername(cameraInfo.getUsername());//nvr用户名
                cameraMiddle.setCamerapws(cameraInfo.getCamerapws());//nvr密码
                cameraMiddle.setTdh(cameraInfo.getSbdk()+"");//通道号
                cameraMiddle.setDvrip(cameraInfo.getDvrip());//nvrip
                if("1001,1007,1009,1010".contains(record.getType())){
                    //cameraMiddle.setJgsj(attrServiceStatic.findByAttrKey("spjqjgsjq"));//视频剪切间隔时间往前推的时间
                    //cameraMiddle.setJgsj(attrServiceStatic.findByAttrKey("spjqjgsj"));//视频剪切间隔时间往后推的时间
                    String kssj = DateUtil.getFormatDate(DateUtil.getSecondLater(DateUtil.toDate(temp,"yyyy-MM-dd HH:mm:ss"),Integer.parseInt(attrServiceStatic.findByAttrKey("spjqjgsjq"))),"yyyy-MM-dd HH:mm:ss");
                    jssj = DateUtil.getFormatDate(DateUtil.getSecondLater(DateUtil.toDate(temp,"yyyy-MM-dd HH:mm:ss"),Integer.parseInt(attrServiceStatic.findByAttrKey("spjqjgsj"))),"yyyy-MM-dd HH:mm:ss");
                    cameraMiddle.setJqsj(kssj+","+jssj);
                    cameraMiddle.setJgsj("0");
                    //cameraMiddle.setJqsj(arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5]);//剪切时间
                }else if("1020,1022,1024,1026".contains(record.getType())){
                    arr = record.getTplj().substring(record.getTplj().lastIndexOf("/")+1,record.getTplj().lastIndexOf("_A4.txt")).split("_");
                    jssj = "1020".equals(record.getType())||"1026".equals(record.getType())?arr[7]+"-"+arr[8]+"-"+arr[9]+" "+arr[10]+":"+arr[11]+":"+arr[12]:arr[6]+"-"+arr[7]+"-"+arr[8]+" "+arr[9]+":"+arr[10]+":"+arr[11];
                    if(temp.equals(jssj)){
                        return;
                    }
                    cameraMiddle.setJqsj(temp+","+jssj);
                    cameraMiddle.setJgsj("0");
                }
                cameraMiddle.setSfjq("0");
                cameraMiddle.setBz(cameraInfo.getSm4());//默认预置位
                if("1001,1007,1009,1010".contains(record.getType())){
                    // 提交一个延迟执行的任务
                    scheduledExecutorService.schedule(() -> {
                        LOG.error("cameraMiddle数据："+cameraMiddle.toString());
                        cameraMiddleServiceStatic.save(cameraMiddle);
                    }, Integer.parseInt(attrServiceStatic.findByAttrKey("spjqjgsj")), TimeUnit.SECONDS);
                }else{
                    LOG.error("cameraMiddle数据："+cameraMiddle.toString());
                    cameraMiddleServiceStatic.save(cameraMiddle);
                }

            }
            redisTstaticemplate.opsForValue().set("JQ"+record.getSbbh(), jssj);
        }else if("1020,1022,1024,1026".contains(record.getType())){
            arr = record.getTplj().substring(record.getTplj().lastIndexOf("/")+1,record.getTplj().lastIndexOf("_A4.txt")).split("_");
            String jssj = "1020".equals(record.getType())||"1026".equals(record.getType())?arr[7]+"-"+arr[8]+"-"+arr[9]+" "+arr[10]+":"+arr[11]+":"+arr[12]:arr[6]+"-"+arr[7]+"-"+arr[8]+" "+arr[9]+":"+arr[10]+":"+arr[11];
            if(StringUtils.isEmpty(temJssj) || !StringUtils.isEmpty(temJssj)&&(DateUtil.toDate(temJssj,"yyyy-MM-dd HH:mm:ss").getTime()-DateUtil.toDate(temp,"yyyy-MM-dd HH:mm:ss").getTime()>5000 && DateUtil.toDate(jssj,"yyyy-MM-dd HH:mm:ss").getTime()-DateUtil.toDate(temJssj,"yyyy-MM-dd HH:mm:ss").getTime()>5000)){
                List<CameraInfo> cameraInfoList = cameraInfoServiceStatic.findBySbbh(record.getSbbh());
                for(CameraInfo cameraInfo: cameraInfoList){
                    CameraMiddleDto cameraMiddle = new CameraMiddleDto();
                    cameraMiddle.setSbbh(record.getSbbh());//设备编号
                    cameraMiddle.setIp(cameraInfo.getIp());//摄像头ip
                    cameraMiddle.setPort(cameraInfo.getPort()+"");//nvr剪切端口
                    cameraMiddle.setUsername(cameraInfo.getUsername());//nvr用户名
                    cameraMiddle.setCamerapws(cameraInfo.getCamerapws());//nvr密码
                    cameraMiddle.setTdh(cameraInfo.getSbdk()+"");//通道号
                    cameraMiddle.setDvrip(cameraInfo.getDvrip());//nvrip
                    cameraMiddle.setJqsj(StringUtils.isEmpty(temJssj)?temp:temJssj+","+jssj);
                    cameraMiddle.setJgsj("0");
                    cameraMiddle.setSfjq("0");
                    cameraMiddle.setBz(cameraInfo.getSm4());//默认预置位
                    LOG.error("cameraMiddle数据："+cameraMiddle.toString());
                    cameraMiddleServiceStatic.save(cameraMiddle);
                }
                redisTstaticemplate.opsForValue().set("JQ"+record.getSbbh(), jssj);
            }
        }
    }



    public static void pushMqMsg(String sbbh,String jtnr){
        try {
            CodesetExample codesetExample = new CodesetExample();
            CodesetExample.Criteria codesetCa = codesetExample.createCriteria();
            codesetCa.andTypeEqualTo("15");
            List<Codeset> list = codesetMapperStatic.selectByExample(codesetExample);
            Map<String,String> codesetMap = list==null?new HashMap<>():list.stream().collect(Collectors.toMap(p -> p.getCode(), p -> p.getName()));
            Set<String> nja4sbsn = codesetMap.keySet();
            if(nja4sbsn!= null && nja4sbsn.contains(sbbh)){
                Connection connection = MQUtil.getConnection();
                Channel channel = connection.createChannel();
                channel.queueDeclare(MQUtil.QUEUE_NAME,true,false,false,null);
                String message = sbbh+"&"+codesetMap.get(sbbh)+"@"+jtnr;
                channel.basicPublish("",MQUtil.QUEUE_NAME,null,message.getBytes("UTF-8"));
                channel.close();
                connection.close();
                LOG.error("无人机数据已发送："+message);
            }
        }catch (Exception e){
            LOG.error("无人机数据错误："+e.getMessage());
        }
    }

    //方位角统计
    public void staticAzimuthAngle(String sbbh,String deptcode,String rq,String jtnr){
        String[] nrArr = jtnr.split("/");
        Map<String,Map<String,Integer>> rqMap = new HashMap<>();
        Map<String,Integer> rqTsMap = new HashMap<>();
        for(String rqandjdStr : nrArr){
            String[] rqandjd = rqandjdStr.split("-");//2022_10_15_02_15_14 0:105,1:235
            String rqStr = rqandjd[0].substring(0,16);//2022_10_15_02_15
            String jdStr = rqandjd[1];//0:105,1:235
            if(rqMap.containsKey(rqStr)){
                Map<String,Integer> jdNum = rqMap.get(rqStr);
                String[] jdarr = jdStr.split(",");//0:105 1:235
                rqTsMap.put(rqStr, rqTsMap.get(rqStr)+jdarr.length);
                for(String jd : jdarr){
                    String[] arr = jd.split(":");//0 105
                    Float jtjd = Float.parseFloat(arr[1]);
                    if(jtjd>=0 && jtjd<45){
                        jdNum.put("north_northeast",jdNum.get("north_northeast")+1);
                    }else if(jtjd>=45 && jtjd<90){
                        jdNum.put("northeast_east",jdNum.get("northeast_east")+1);
                    }else if(jtjd>=90 && jtjd<135){
                        jdNum.put("east_eastsouth",jdNum.get("east_eastsouth")+1);
                    }else if(jtjd>=135 && jtjd<180){
                        jdNum.put("eastsouth_south",jdNum.get("eastsouth_south")+1);
                    }else if(jtjd>=180 && jtjd<225){
                        jdNum.put("south_southwest",jdNum.get("south_southwest")+1);
                    }else if(jtjd>=225 && jtjd<270){
                        jdNum.put("southwest_west",jdNum.get("southwest_west")+1);
                    }else if(jtjd>=270 && jtjd<315){
                        jdNum.put("west_westnorth",jdNum.get("west_westnorth")+1);
                    }else if(jtjd>=315 && jtjd<360){
                        jdNum.put("westnorth_north",jdNum.get("westnorth_north")+1);
                    }
                }
                rqMap.put(rqStr, jdNum);
            }else{
                Map<String,Integer> jdNum = new HashMap<>();
                jdNum.put("north_northeast",0);
                jdNum.put("northeast_east",0);
                jdNum.put("east_eastsouth",0);
                jdNum.put("eastsouth_south",0);
                jdNum.put("south_southwest",0);
                jdNum.put("southwest_west",0);
                jdNum.put("west_westnorth",0);
                jdNum.put("westnorth_north",0);
                String[] jdarr = jdStr.split(",");
                rqTsMap.put(rqStr, jdarr.length);
                for(String jd : jdarr){
                    String[] arr = jd.split(":");//0 105
                    Float jtjd = Float.parseFloat(arr[1]);
                    if(jtjd>=0 && jtjd<45){
                        jdNum.put("north_northeast",jdNum.get("north_northeast")+1);
                    }else if(jtjd>=45 && jtjd<90){
                        jdNum.put("northeast_east",jdNum.get("northeast_east")+1);
                    }else if(jtjd>=90 && jtjd<135){
                        jdNum.put("east_eastsouth",jdNum.get("east_eastsouth")+1);
                    }else if(jtjd>=135 && jtjd<180){
                        jdNum.put("eastsouth_south",jdNum.get("eastsouth_south")+1);
                    }else if(jtjd>=180 && jtjd<225){
                        jdNum.put("south_southwest",jdNum.get("south_southwest")+1);
                    }else if(jtjd>=225 && jtjd<270){
                        jdNum.put("southwest_west",jdNum.get("southwest_west")+1);
                    }else if(jtjd>=270 && jtjd<315){
                        jdNum.put("west_westnorth",jdNum.get("west_westnorth")+1);
                    }else if(jtjd>=315 && jtjd<360){
                        jdNum.put("westnorth_north",jdNum.get("westnorth_north")+1);
                    }
                }
                rqMap.put(rqStr, jdNum);
            }
        }
        for(String xsStr : rqMap.keySet()){
            Map<String,Integer> jdNum = rqMap.get(xsStr);
            String[] rqArr = xsStr.split("_");
            AzimuthAngleDto dto = new AzimuthAngleDto();
            dto.setSbbh(sbbh);
            dto.setRq(rq);
            dto.setXs(rqArr[0]+"-"+rqArr[1]+"-"+rqArr[2]+" "+rqArr[3]);
            dto.setFz(rqArr[0]+"-"+rqArr[1]+"-"+rqArr[2]+" "+rqArr[3]+":"+rqArr[4]);
            dto.setTs(rqTsMap.get(xsStr));
            dto.setDeptcode(deptcode);
            dto.setNorthNortheast(jdNum.get("north_northeast"));
            dto.setNortheastEast(jdNum.get("northeast_east"));
            dto.setEastEastsouth(jdNum.get("east_eastsouth"));
            dto.setEastsouthSouth(jdNum.get("eastsouth_south"));
            dto.setSouthSouthwest(jdNum.get("south_southwest"));
            dto.setSouthwestWest(jdNum.get("southwest_west"));
            dto.setWestWestnorth(jdNum.get("west_westnorth"));
            dto.setWestnorthNorth(jdNum.get("westnorth_north"));
            azimuthAngleServiceStatic.save(dto);
            AzimuthAngleRqDto rqDto = new AzimuthAngleRqDto();
            rqDto.setSbbh(sbbh);
            rqDto.setRq(rq);
            azimuthAngleRqServiceStatic.save(rqDto);
        }
    }
}

package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.MqttClientSpace;
import com.pd.server.config.RedisCode;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
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
    public static SpaceFileService spaceFileServiceStatic;
    public static BeconFileService beconFileServiceStatic;
    public static BeconFileTodayService beconFileTodayServiceStatic;
    public static AngleFileService angleFileServiceStatic;
    public static EquipmentFileSplitShjService equipmentFileSplitShjServiceStatic;
    public static VoicePowerDeviceService voicePowerDeviceServiceStatic;
    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    // 专门用于数据分发的线程池
    public static ExecutorService distributeExecutorService = Executors.newFixedThreadPool(10);

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
    @Resource
    private SpaceFileService spaceFileService;
    @Resource
    private BeconFileService beconFileService;
    @Resource
    private BeconFileTodayService beconFileTodayService;
    @Resource
    private AngleFileService angleFileService;
    @Resource
    private VoicePowerDeviceService voicePowerDeviceService;
    @Resource
    private EquipmentFileSplitShjService equipmentFileSplitShjService;

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
        spaceFileServiceStatic = spaceFileService;
        beconFileServiceStatic = beconFileService;
        beconFileTodayServiceStatic = beconFileTodayService;
        angleFileServiceStatic = angleFileService;
        equipmentFileSplitShjServiceStatic = equipmentFileSplitShjService;
        voicePowerDeviceServiceStatic = voicePowerDeviceService;
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
        if(StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(tplj)||StringUtils.isEmpty(cjsj)){
            data = "参数错误";
            return data;
        }
        //判断是不是仪器姿态文件，如果是则调用仪器姿态方法
        if(tplj.contains("angle")){
            return angleMethod(jsonParam);
        }
        //判断是不是驱离文件，如果是则调用驱离方法
        if(tplj.contains("Space")){
            return spaceMethod(jsonParam);
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
        EquipmentFileTodayExample exampleTodayFile = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria caFile = exampleTodayFile.createCriteria();
        caFile.andTpljEqualTo(tplj);
        caFile.andSbbhEqualTo(sbbh);
        List<EquipmentFileToday> comment = todayMapperStatic.selectByExample(exampleTodayFile);
        if(comment==null || comment.isEmpty()){
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTstaticemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            if(!sbbhEquipMap.keySet().contains("equip-"+sbbh)){
                return "设备编号不存在";
            }
            WaterEquipment waterEquipment = JSONObject.toJavaObject(sbbhEquipMap.get("equip-"+sbbh), WaterEquipment.class);
            String deptcode = waterEquipment.getDeptcode();
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
            // 异步分发保存数据
            distributeExecutorService.execute(() -> {
                try {
                    equipmentFileSplitShjServiceStatic.distributeAndSave(entity);
                } catch (Exception e) {
                    LOG.error("异步分发保存失败，EquipmentFile ID: {}, 错误信息: {}",
                            entity.getId(), e.getMessage(), e);
                }
            });
            //正式更新需要加上下面一行
            entity.setSyncFlag(3);
            //equipmentFileMapperStatic.insert(entity);
            todayMapperStatic.insertEquipFile(entity);
            data="保存成功";
            JSONObject result = new JSONObject();
            result.put("data",data);
            result.put("entity",entity);
            return result.toJSONString();
        }else {
            data="该图片已保存过";
            return data;
        }
    }

    //仪器姿态文件
    public static String angleMethod(JSONObject jsonParam){
        try {
            String sbbh = jsonParam.getString("sbbh");
            String tplj = jsonParam.getString("tplj");
            String cjsj = jsonParam.getString("cjsj");
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTstaticemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            if(!sbbhEquipMap.keySet().contains("equip-"+sbbh)){
                return "设备编号不存在";
            }
            if(!Pattern.matches(TypeUtils.ZZ_34, tplj.substring(tplj.lastIndexOf("/")+1))){
                return "文件名称命名错误";
            }
            WaterEquipment waterEquipment = JSONObject.toJavaObject(sbbhEquipMap.get("equip-"+sbbh), WaterEquipment.class);
            AngleFileDto angleFileDto = new AngleFileDto();
            angleFileDto.setSbbh(sbbh);
            angleFileDto.setWjlj(tplj);
            angleFileDto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
            angleFileDto.setJssj(new Date());
            angleFileDto.setDeptcode(waterEquipment.getDeptcode());
            angleFileDto.setRq(DateUtil.getFormatDate(angleFileDto.getCjsj(),"yyyy-MM-dd"));
            angleFileServiceStatic.save(angleFileDto);
            if(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd").equals(angleFileDto.getRq())){
                sendMsg(sbbh);
            }
            JSONObject result = new JSONObject();
            result.put("data","保存成功");
            result.put("otherFile",true);
            return result.toJSONString();
        }catch (Exception e){
            LOG.error("保存仪器姿态文件出错："+e.getMessage());
            return "保存仪器姿态文件出错";
        }
    }

    public static void sendMsg(String sbbh){
        try {
            Map<String,String> attrMap = (Map<String, String>) redisTstaticemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
            String angleTemplateId = attrMap.get("angleTemplateId");
            String phoneNum = attrMap.get("anglePhone");
            SendSmsTool.sendSms(angleTemplateId,sbbh, phoneNum);
        }catch (Exception e){
            LOG.error("仪器姿态发送短信失败："+e.getMessage());
        }
    }

    //驱离文件，文件类型2022_09_05_20_18_16_Space_144.txt
    public static String spaceMethod(JSONObject jsonParam){
        try {
            String sbbh = jsonParam.getString("sbbh");
            String tplj = jsonParam.getString("tplj");
            String cjsj = jsonParam.getString("cjsj");
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTstaticemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            if(!sbbhEquipMap.keySet().contains("equip-"+sbbh)){
                return "设备编号不存在";
            }
            if(!Pattern.matches(TypeUtils.ZZ_33, tplj.substring(tplj.lastIndexOf("/")+1))){
                return "文件名称命名错误";
            }
            WaterEquipment waterEquipment = JSONObject.toJavaObject(sbbhEquipMap.get("equip-"+sbbh), WaterEquipment.class);
            SpaceFileDto spaceFileDto = new SpaceFileDto();
            spaceFileDto.setSbbh(sbbh);
            spaceFileDto.setWjlj(tplj);
            spaceFileDto.setYjz(tplj.substring(tplj.indexOf("Space_")+6,tplj.lastIndexOf(".txt")));
            spaceFileDto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
            spaceFileDto.setJssj(new Date());
            spaceFileDto.setDeptcode(waterEquipment.getDeptcode());
            spaceFileDto.setRq(DateUtil.getFormatDate(spaceFileDto.getCjsj(),"yyyy-MM-dd"));
            spaceFileServiceStatic.save(spaceFileDto);
            //发送指令，播放音频
            publishMessage(waterEquipment.getSbsn(),waterEquipment.getJdfw(),waterEquipment.getSm1(),cjsj);
            JSONObject result = new JSONObject();
            result.put("data","保存成功");
            result.put("otherFile",true);
            return result.toJSONString();
        }catch (Exception e){
            LOG.error("保存驱离文件出错："+e.getMessage());
            return "保存驱离文件出错";
        }
    }

    //发送指令，播放驱离文件，停指播放驱离文件
    public static void publishMessage(String sbbh,String jdfw,String hex, String cjsj){
        try {
            if(!StringUtils.isEmpty(jdfw) && !StringUtils.isEmpty(hex)){
                Map<String,String> attrMap = (Map<String, String>) redisTstaticemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
                if(StringUtils.isEmpty(attrMap.get("spaceInterval"))){
                    LOG.error("未配置声驱播放间隔时间");
                    return;
                }
                int spaceInterval = Integer.parseInt(attrMap.get("spaceInterval"));
                //如果接收文件时间和生成文件时间没有超过5分钟
                if(new Date().getTime()-DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss").getTime()<=spaceInterval*60*1000){
                    //1先根据设备编号去缓存中取数据，如果有，就比较缓存中数据的发送指令时间和接收文件时间有没有超过五分钟
                    //1.1如果超过5分钟，就走发送指令流程；如果没有超过5分钟就忽略当前文件
                    //2缓存中没有数据，就根据设备编号和当天日期查询数据库最后一条数据
                    //2.2如果没有查询出来数据，就走发送指令流程；如果查询出来数据就比较发送指令时间和当前时间有没有超过五分钟，后续执行1.1
                    String[] topicArr = jdfw.split(";");//WHPD[meg],WHPD[updata];RPCD[meg],RPCD[updata]
                    for(int i=0;i<topicArr.length;i++){
                        String oneitem = topicArr[i];
                        String[] oneArr = oneitem.split(",");
                        if(oneArr.length < 2){
                            LOG.error("主题配置格式错误: {}", oneitem);
                            continue;
                        }
                        LOG.info("处理主题配置 - 发布主题: {}, 订阅主题: {}", oneArr[0], oneArr[1]);
                        String topicName = oneArr[0].substring(0,oneArr[0].indexOf("["));
                        if(!StringUtils.isEmpty(redisTstaticemplate.opsForValue().get(topicName+"QLWJ"))) {
                            String entityJson = (String) redisTstaticemplate.opsForValue().get(topicName + "QLWJ");
                            VoicePowerDevice voicePowerDevice = JSONObject.parseObject(entityJson, VoicePowerDevice.class);
                            if(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss").getTime()-voicePowerDevice.getSendTime().getTime()>spaceInterval*60*1000){
                                sendTopTopic(sbbh,oneArr[0],hex,spaceInterval);
                            }
                        }else{
                            VoicePowerDevice voicePowerDevice = voicePowerDeviceServiceStatic.selectTodayLastData(topicName);
                            if(voicePowerDevice==null){
                                sendTopTopic(sbbh,oneArr[0],hex,spaceInterval);
                            }else{
                                if(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss").getTime()-voicePowerDevice.getSendTime().getTime()>spaceInterval*60*1000){
                                    sendTopTopic(sbbh,oneArr[0],hex,spaceInterval);
                                }
                            }
                        }
                    }

                }
            }
        }catch (Exception e){
            LOG.error("发送指令失败："+e.getMessage());
        }
    }

    //发送指令，停止指令
    public static void sendTopTopic(String sbbh,String topic,String hex,int spaceInterval){
        // 获取MQTT客户端实例
        MqttClientSpace client = MqttClientSpace.getInstance();
        if(client == null){
            LOG.error("MQTT客户端未初始化，无法发送消息。请检查monitor.jar是否正常启动");
            return;
        }
        // 确保client是final的，以便在lambda中使用
        final MqttClientSpace finalClient = client;
        byte[] messageStart = hexStringToByteArray(hex);
        LOG.info("MQTT客户端状态: {}", finalClient.getConnectionStatus());
        // 2. 订阅返回主题
        // finalClient.subTopic(oneArr[1]);
        // 3. 发布消息 保存发送指令
        String topicName = topic.substring(0,topic.indexOf("["));
        VoicePowerDevice voicePowerDevice = voicePowerDeviceServiceStatic.saveData(topicName);
        redisTstaticemplate.opsForValue().set(topicName+"QLWJ", JSONObject.toJSONString(voicePowerDevice));
        finalClient.publishMessage(topic, messageStart, 2);
        LOG.error("发声设备发送指令-设备编号: {},主题: {}", sbbh, topic);
        // 确保oneArr[0]是final的，以便在lambda中使用
        final String publishTopic = topic;
        // 提交一个延迟执行的任务，关闭指令
        scheduledExecutorService.schedule(() -> {
            voicePowerDeviceServiceStatic.updateStopTime(topicName);
            //43 52 44 02 00 07 10 02 4C
            byte[] messageStop = new byte[] { 0x43, 0x52, 0x44, 0x02, 0x00, 0x07, 0x10, 0x02, 0x4C };
            finalClient.publishMessage(publishTopic, messageStop, 2);
            LOG.error("发声设备停止指令-设备编号: {},主题: {}", sbbh, publishTopic);
        },spaceInterval*60, TimeUnit.SECONDS);
    }


    public static byte[] hexStringToByteArray(String hex) {
        hex = hex.replaceAll("\\s+", ""); // 去掉空格
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

    //信标文件
    public static String beconMethod(JSONObject jsonParam){
        try {
            String sbbh = jsonParam.getString("sbbh");
            String tplj = jsonParam.getString("tplj");
            String cjsj = jsonParam.getString("cjsj");
            String sm2 = jsonParam.getString("sm2");
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
            BeconFileTodayExample example = new BeconFileTodayExample();
            BeconFileTodayExample.Criteria caFile = example.createCriteria();
            caFile.andWjljEqualTo(tplj);
            List<BeconFileToday> comment = beconFileTodayServiceStatic.selectByExample(example);
            if(!(comment==null || comment.isEmpty())){
                return "该数据已保存过";
            }
            WaterEquipment waterEquipment = JSONObject.toJavaObject(sbbhEquipMap.get("equip-"+sbbh), WaterEquipment.class);
            String[] xbids = sm2.split(",");
            for(String xbid : xbids){
                BeconFileDto beconFileDto = new BeconFileDto();
                beconFileDto.setSbbh(sbbh);
                beconFileDto.setWjlj(tplj);
                beconFileDto.setXbid(xbid);
                beconFileDto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
                beconFileDto.setJssj(new Date());
                beconFileDto.setDeptcode(waterEquipment.getDeptcode());
                beconFileDto.setRq(DateUtil.getFormatDate(beconFileDto.getCjsj(),"yyyy-MM-dd"));
                beconFileServiceStatic.save(beconFileDto);
                BeconFileToday beconFileToday = CopyUtil.copy(beconFileDto, BeconFileToday.class);
                beconFileTodayServiceStatic.insert(beconFileToday);
            }
            JSONObject result = new JSONObject();
            result.put("data","保存成功");
            result.put("otherFile",true);
            return result.toJSONString();
        }catch (Exception e){
            return "保存基站文件数据错误";
        }
    }

    //指针数据每秒
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

    //指针数据每天
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

}

package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.CameraMiddleDto;
import com.pd.server.main.dto.PointerDayDto;
import com.pd.server.main.dto.PointerSecondDto;
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
import java.util.stream.Collectors;

@Service
public class EquipmentFileShjService extends AbstractScanRequest{

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileShjService.class);

    public  static RedisTemplate  redisTstaticemplate;


    @Resource
    private RedisTemplate redisTemplate;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
    }

    public  static AttrService attrService = SpringUtil.getBean(AttrService.class);
    public  static   EquipmentFileMapper equipmentFileMapper = SpringUtil.getBean(EquipmentFileMapper.class);
    public  static EquipmentFileTodayMapper todayMapper = SpringUtil.getBean(EquipmentFileTodayMapper.class);
    public  static WaterEquipmentMapper waterEquipmentMapper = SpringUtil.getBean(WaterEquipmentMapper.class);
    public  static EquipmentFileEventMapper equipmentFileEventMapper = SpringUtil.getBean(EquipmentFileEventMapper.class);

    public  static   PointerSecondService pointerSecondService = SpringUtil.getBean(PointerSecondService.class);

    public  static  PointerDayService pointerDayService = SpringUtil.getBean(PointerDayService.class);

    public  static  CameraInfoService cameraInfoService = SpringUtil.getBean(CameraInfoService.class);
    public  static  CameraMiddleService cameraMiddleService = SpringUtil.getBean(CameraMiddleService.class);

    public  static  CodesetMapper codesetMapper = SpringUtil.getBean(CodesetMapper.class);
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
        boolean pushData = false;
        if(StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(tplj)||StringUtils.isEmpty(cjsj)){
            data = "参数错误";
            return data;
        }


        EquipmentFileTodayExample exampleTodayFile = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria caFile = exampleTodayFile.createCriteria();
        caFile.andTpljEqualTo(tplj);
        caFile.andSbbhEqualTo(sbbh);
        List<EquipmentFileToday> comment = todayMapper.selectByExample(exampleTodayFile);
        if(comment==null || comment.isEmpty()){

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
                String eventsbsn = attrService.findByAttrKey("eventsbsn");
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
                    equipmentFileEventMapper.insertSelective(fileEvent);
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
                        equipmentFileEventMapper.insertSelective(fileEvent);
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
                pointerSecondService.save(dto);
            }else if("1019".equals(entity.getType())){//指针数据每天

                PointerDayDto dto = new PointerDayDto();
                dto.setDecibelValue(entity.getTs());
                dto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
                dto.setSm(sbbh);
                dto.setCreateTime(new Date());
                dto.setBz(deptcode);
                pointerDayService.save(dto);
            }
            String predationsbsn = attrService.findByAttrKey("predationsbsn");
            if(predationsbsn.contains(sbbh)&&tplj.contains("txt")&&("1001".equals(typeUtil.get(TypeUtils.TYPE))||"1007".equals(typeUtil.get(TypeUtils.TYPE)))){

                //判断是否是雾报(前后三分钟都没有报警的数据是雾报数据，雾报数据不保存)
                EquipmentFile beforeEntity = new EquipmentFile();
                LOG.error("缓存中的数据："+redisTstaticemplate.opsForValue().get(sbbh+"WB"));
                if(!StringUtils.isEmpty(redisTstaticemplate.opsForValue().get(sbbh+"WB"))){
                    String entityJson = (String) redisTstaticemplate.opsForValue().get(sbbh+"WB");
                    beforeEntity = JSONObject.parseObject(entityJson,EquipmentFile.class);
                    if(!StringUtils.isEmpty(beforeEntity.getCjsj())&&isOverThreeMinute(DateUtil.getFormatDate(beforeEntity.getCjsj(),"yyyy-MM-dd HH:mm:ss"),cjsj)){
                        equipmentFileMapper.insert(beforeEntity);
                        todayMapper.insertEquipFile(beforeEntity);
                        redisTstaticemplate.opsForValue().set(sbbh+"WB", JSONObject.toJSONString(entity));
                    }else{
                        try{
                            EquipmentFile lastFile = equipmentFileMapper.selectLastOneBySbbh(sbbh);
                            if(!StringUtils.isEmpty(beforeEntity.getCjsj())&&!StringUtils.isEmpty(lastFile.getCjsj())&&isOverThreeMinute(DateUtil.getFormatDate(lastFile.getCjsj(),"yyyy-MM-dd HH:mm:ss"),DateUtil.getFormatDate(beforeEntity.getCjsj(),"yyyy-MM-dd HH:mm:ss"))){
                                equipmentFileMapper.insert(beforeEntity);
                                todayMapper.insertEquipFile(beforeEntity);
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
                equipmentFileMapper.insert(entity);
                todayMapper.insertEquipFile(entity);
                data="保存成功";
                JSONObject result = new JSONObject();
                result.put("data",data);
                result.put("entity",entity);
                //白海豚写剪切视频的事件，李响读了去剪切视频
                if(("1001,1007,1009,1010,1020,1022,1024,1026".contains(entity.getType()))&&sbbh.contains("RPCD")){
                    saveNewEvent(entity);
                }
                //推送文件
                if("JXYSA4001".equals(sbbh)&&!pushData){
                    PushFile.pushFile1(entity.getTplj());
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
        String predationInterval = attrService.findByAttrKey("predationInterval");
        if(minute<=Integer.parseInt(predationInterval)){
            return true;
        }
        return false;
    }

    public static void saveNewEvent(EquipmentFile record){


        List<CameraInfo> cameraInfoList = cameraInfoService.findBySbbh(record.getSbbh());
        for(CameraInfo cameraInfo: cameraInfoList){
            CameraMiddleDto cameraMiddle = new CameraMiddleDto();
            cameraMiddle.setSbbh(record.getSbbh());//设备编号
            cameraMiddle.setIp(cameraInfo.getIp());//摄像头ip
            cameraMiddle.setPort(cameraInfo.getPort()+"");//摄像头端口号
            cameraMiddle.setUsername(cameraInfo.getUsername());//摄像头用户名
            cameraMiddle.setCamerapws(cameraInfo.getCamerapws());//摄像头密码
            cameraMiddle.setTdh(cameraInfo.getSbdk()+"");//通道号
            cameraMiddle.setDvrip(cameraInfo.getDvrip());//所属DVR的IP（备用地址）
            if("1001,1007,1009,1010".contains(record.getType())){
                cameraMiddle.setJgsj(attrService.findByAttrKey("spjqjgsj"));//视频剪切间隔时间
                String[] arr = record.getWjmc().split("_");
                cameraMiddle.setJqsj(arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5]);//剪切时间
            }else if("1020,1022,1024,1026".contains(record.getType())){
                String temp = record.getTplj().substring(record.getTplj().lastIndexOf("/")+1,record.getTplj().lastIndexOf("_A4.txt"));
                String[] arr = temp.split("_");
                String kssj = arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5];
                String jssj = "1020".equals(record.getType())||"1026".equals(record.getType())?arr[7]+"-"+arr[8]+"-"+arr[9]+" "+arr[10]+":"+arr[11]+":"+arr[12]:arr[6]+"-"+arr[7]+"-"+arr[8]+" "+arr[9]+":"+arr[10]+":"+arr[11];
                cameraMiddle.setJqsj(kssj+","+jssj);
            }
            cameraMiddleService.save(cameraMiddle);
        }
    }



    public static void pushMqMsg(String sbbh,String jtnr){
        try {

            CodesetExample codesetExample = new CodesetExample();
            CodesetExample.Criteria codesetCa = codesetExample.createCriteria();
            codesetCa.andTypeEqualTo("15");
            List<Codeset> list = codesetMapper.selectByExample(codesetExample);
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

}

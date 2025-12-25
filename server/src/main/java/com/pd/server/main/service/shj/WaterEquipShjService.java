package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.AttrMapper;
import com.pd.server.main.mapper.WaterEquiplogMapper;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.main.dto.LdTaskListDto;
import com.pd.server.main.service.LdTaskListService;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.SendSmsTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WaterEquipShjService extends AbstractScanRequest{

    private static final Logger LOG = LoggerFactory.getLogger(WaterEquipShjService.class);

    private static final String key = "";


    public static RedisTemplate redisTstaticemplate;
    public static WaterEquiplogMapper waterEquiplogMapperStatic;
    public static WaterEquipmentMapper waterEquipmentMapperStatic;
    public static AttrMapper attrMapperStatic;
    public static LdTaskListService ldTaskListServiceStatic;
    public static Map<String, String> sbbhrqMap = new HashMap<>();

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private WaterEquiplogMapper waterEquiplogMapper;
    @Resource
    private WaterEquipmentMapper waterEquipmentMapper;
    @Resource
    private AttrMapper attrMapper;
    @Resource
    private LdTaskListService ldTaskListService;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
        waterEquiplogMapperStatic = waterEquiplogMapper;
        waterEquipmentMapperStatic = waterEquipmentMapper;
        attrMapperStatic = attrMapper;
        ldTaskListServiceStatic = ldTaskListService;
    }

    /**
     * 设备心跳包
     * @param jsonParam
     * @return+
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {
        String sbbh = jsonParam.getString("sbbh");
        String code = jsonParam.getString("code");
        String msg = jsonParam.getString("msg");//gps数据
        if(StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(code)){
            data = "参数错误";
            return data;
        }
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSbsnEqualTo(sbbh);
        List<WaterEquipment> listWater = waterEquipmentMapperStatic.selectByExample(example);
        if(listWater.size() == 0){
            data = "设备未备案";
            return data;
        }
        Map<String,String> attrMap = (Map<String, String>) redisTstaticemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
        try {
            WaterEquiplog record = new WaterEquiplog();
            record.setSbbh(sbbh);
            record.setCode(code);
            record.setMsg(msg);
            record.setReqmsg(JSONObject.toJSONString(jsonParam));
            record.setCjsj(new Date());
            record.setRespmsg("保存成功");
            record.setSm1(listWater.get(0).getSbcj());
            record.setSm2(listWater.get(0).getSbmc());
            waterEquiplogMapperStatic.updateBySbbhSelective(record);
            String sbbhHeart = attrMap.get("sbbhHeart");
            if(!StringUtils.isEmpty(sbbhHeart)&&sbbhHeart.contains(sbbh)){
                if(!StringUtils.isEmpty(msg) && !msg.equals("0,0") && !StringUtils.isEmpty(listWater.get(0).getBz())){
                    if(!StringUtils.isEmpty(redisTstaticemplate.opsForValue().get("XT"+sbbh))){
                        String cs = (String) redisTstaticemplate.opsForValue().get("XT"+sbbh);
                        if(Integer.parseInt(cs)>30){
                            String distance = attrMap.get("distance");
                            String[] arr = msg.split(",");
                            String[] arr1 = listWater.get(0).getBz().split(",");
                            try {
                                double realDistance = calculateDistance(Double.parseDouble(arr[1]),Double.parseDouble(arr[0]),Double.parseDouble(arr1[1]),Double.parseDouble(arr1[0]));
                                LOG.error("高德地图计算真实差距------------>"+realDistance);
                                if(realDistance>Long.parseLong(distance)){
                                    String phoneNum = attrMap.get("heartPhone");
                                    //发送短信
                                    isSendMsg(sbbh,phoneNum);
                                    //SendSmsTool.sendSms("1860261",sbbh,phoneNum);
                                }
                                redisTstaticemplate.opsForValue().set("XT"+sbbh, "0");
                            }catch (Exception e){
                                LOG.error("出错："+e.getMessage());
                            }
                        }else{
                            redisTstaticemplate.opsForValue().set("XT"+sbbh, String.valueOf(Integer.parseInt(cs)+1));
                        }
                    }else{
                        redisTstaticemplate.opsForValue().set("XT"+sbbh, "0");
                    }
                }
            }
            if(StringUtils.isEmpty(attrMap.get("sbbhHeart"))){
                data = "60";
            }else{
                data = attrMap.get("reqinterval");
            }
        } catch (Exception e){
            data = "保存失败";
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 判断是否需要发送短信
     * 一个设备一天只可发送三条短信
     */
    public void isSendMsg(String sbbh,String phoneNum){
        String ymd = DateTools.getYMD();
        if(!StringUtils.isEmpty(redisTstaticemplate.opsForValue().get("ISSENDMSG"+sbbh))){
            String redisValue = (String) redisTstaticemplate.opsForValue().get("ISSENDMSG"+sbbh);
            String[] arr = redisValue.split("-");
            //判断日期是否是今天，如果是，则判断次数是否大于3
            if(ymd.equals(arr[1])){
                int cs = Integer.parseInt(arr[2]);
                if(cs<=3){
                    //发送次数少于等于3次
                    SendSmsTool.sendSms("1860261",sbbh,phoneNum);
                    cs++;
                    redisTstaticemplate.opsForValue().set("ISSENDMSG"+sbbh, sbbh+"-"+ymd+"-"+cs);
                }
            }else{
                //不是今天的数据，直接发送短信
                SendSmsTool.sendSms("1860261",sbbh,phoneNum);
                redisTstaticemplate.opsForValue().set("ISSENDMSG"+sbbh, sbbh+"-"+ymd+"-1");
            }
        }else{
            //发送短信
            SendSmsTool.sendSms("1860261",sbbh,phoneNum);
            redisTstaticemplate.opsForValue().set("ISSENDMSG"+sbbh, sbbh+"-"+ymd+"-1");
        }
    }

    /**
     * 计算两个GPS坐标之间的距离
     * @param lat1 第一个点的纬度
     * @param lon1 第一个点的经度
     * @param lat2 第二个点的纬度
     * @param lon2 第二个点的经度
     * @return 距离（单位：米）
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371000 * c;
    }

    //发送请求
    public static String getResponse(String serverUrl){
        StringBuffer result = new StringBuffer();
        try{
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null){
                result.append(line);
            }
            in.close();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return result.toString();
    }

    //网络不通，重启设备，发送短信
    public static void restartEquip(WaterEquipment waterEquipment) throws InterruptedException {
        LOG.error("设备网络不通，重启设备，发送短信");
        sbbhrqMap.put(waterEquipment.getSbsn(), DateUtil.getYMD());
        Map<String,String> attrMap = (Map<String, String>) redisTstaticemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
        //发送短信
        String phoneNum = attrMap.get("offlinePhone");
        SendSmsTool.sendSms("2142996",waterEquipment.getSbsn(),phoneNum);
        //重启设备
        if(!StringUtils.isEmpty(waterEquipment.getSbcj())){
            String restartinterval = attrMap.get("restartinterval");
            LdTaskListDto dto = new LdTaskListDto();
            dto.setIccid(waterEquipment.getSbcj());
            dto.setTask("cmd:203");
            dto.setFsdate(new Date());
            ldTaskListServiceStatic.save(dto);
            Thread.sleep(Long.parseLong(restartinterval));
            LdTaskListDto dto1 = new LdTaskListDto();
            dto1.setIccid(waterEquipment.getSbcj());
            dto1.setTask("cmd:202");
            dto1.setFsdate(new Date());
            ldTaskListServiceStatic.save(dto1);
            LOG.error("设备已重启");
        }
    }

    public static void main(String[] args){
//        long distance = getDistance("117.729483166667,30.851357","117.73000,30.84791");
//        System.out.println(distance);
//        if(distance>Long.parseLong("300")){
//            System.out.println(true);
//        }
        System.out.println(calculateDistance(30.851357,117.729483166667,30.84791,117.73000));
    }

}

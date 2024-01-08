package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.AttrMapper;
import com.pd.server.main.mapper.WaterEquiplogMapper;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.util.SendSmsTool;
import com.pd.server.util.UuidUtil;
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
import java.util.List;

@Service
public class WaterEquipShjService extends AbstractScanRequest{

    private static final Logger LOG = LoggerFactory.getLogger(WaterEquipShjService.class);

    private static final String key = "39340ac3d444a24d3ef8a72b3089ec73";


    public static RedisTemplate redisTstaticemplate;
    public static WaterEquiplogMapper waterEquiplogMapperStatic;
    public static WaterEquipmentMapper waterEquipmentMapperStatic;
    public static AttrMapper attrMapperStatic;

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private WaterEquiplogMapper waterEquiplogMapper;
    @Resource
    private WaterEquipmentMapper waterEquipmentMapper;
    @Resource
    private AttrMapper attrMapper;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
        waterEquiplogMapperStatic = waterEquiplogMapper;
        waterEquipmentMapperStatic = waterEquipmentMapper;
        attrMapperStatic = attrMapper;
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
            String sbbhHeart = attrMapperStatic.selectByAttrKey("sbbhHeart");
            if(!StringUtils.isEmpty(sbbhHeart)&&sbbhHeart.contains(sbbh)){
                if(!StringUtils.isEmpty(msg) && !msg.equals("0,0") && !StringUtils.isEmpty(listWater.get(0).getBz())){
                    if(!StringUtils.isEmpty(redisTstaticemplate.opsForValue().get("XT"+sbbh))){
                        String cs = (String) redisTstaticemplate.opsForValue().get("XT"+sbbh);
                        if(Integer.parseInt(cs)>30){
                            String distance = attrMapperStatic.selectByAttrKey("distance");
                            long realDistance = getDistance(msg,listWater.get(0).getBz());
                            if(realDistance>Long.parseLong(distance)){
                                String phoneNum = attrMapperStatic.selectByAttrKey("heartPhone");
                                //发送短信
                                SendSmsTool.sendSms("1860261",sbbh,phoneNum);
                            }
                            redisTstaticemplate.opsForValue().set("XT"+sbbh, "0");
                        }else{
                            redisTstaticemplate.opsForValue().set("XT"+sbbh, String.valueOf(Integer.parseInt(cs)+1));
                        }
                    }else{
                        redisTstaticemplate.opsForValue().set("XT"+sbbh, "0");
                    }
                }
            }
            AttrExample attrExample = new AttrExample();
            attrExample.createCriteria().andAttrcodeEqualTo("reqinterval");
            List<Attr> list = attrMapperStatic.selectByExample(attrExample);
            if(list.size() == 0){
                data = "60";
            }else{
                data = list.get(0).getAttrkey();
            }
        } catch (Exception e){
            data = "保存失败";
            e.printStackTrace();
        }
        return data;
    }

    //计算两点间距离
    public static long getDistance(String startLonLat, String endLonLat){
        try {
            String queryUrl = "https://restapi.amap.com/v3/distance?key="+key+"&type=0&origins="+startLonLat+"&destination="+endLonLat;
            String queryResult = getResponse(queryUrl);
            LOG.error("高德地图返回------------>"+queryResult);
            JSONObject job = JSONObject.parseObject(queryResult);
            if("OK".equals(job.getString("info"))){
                JSONArray ja = job.getJSONArray("results");
                JSONObject jsonObject = JSON.parseObject(ja.getString(0));
                return Long.parseLong(jsonObject.get("distance").toString());
            }else{
                return -1;
            }
        }catch (Exception e){
            return -1;
        }
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

    public static void main(String[] args){
        long distance = getDistance("117.729483166667,30.851357","117.73000,30.84791");
        System.out.println(distance);
    }
}

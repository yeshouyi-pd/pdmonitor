package com.pd.monitor.quartz;

import com.pd.server.main.domain.EquipmentFileEvent;
import com.pd.server.main.domain.EquipmentFileEventExample;
import com.pd.server.main.dto.AzimuthAngleDto;
import com.pd.server.main.service.AzimuthAngleService;
import com.pd.server.main.service.EquipmentFileEventService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class TestQuartz {

    @Resource
    private EquipmentFileEventService equipmentFileEventService;
    @Resource
    private AzimuthAngleService azimuthAngleService;

    //@Scheduled(cron = "0 10 10 * * ? ")
    public void test() throws ParseException {
        EquipmentFileEventExample example = new EquipmentFileEventExample();
        EquipmentFileEventExample.Criteria ca = example.createCriteria();
        ca.andSbbhEqualTo("JXYSA4001");
        ca.andRqEqualTo("2024-11-27");
        List<EquipmentFileEvent> list = equipmentFileEventService.list(example);
        for(EquipmentFileEvent entity : list){
            staticAzimuthAngle(entity.getSbbh(),entity.getDeptcode(),entity.getRq(),entity.getJtnr());
        }
    }

    //方位角统计
    public void staticAzimuthAngle(String sbbh,String deptcode,String rq,String jtnr){
        String[] nrArr = jtnr.split("/");
        Map<String, Map<String,Integer>> rqMap = new HashMap<>();
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
            azimuthAngleService.save(dto);
        }
    }
}

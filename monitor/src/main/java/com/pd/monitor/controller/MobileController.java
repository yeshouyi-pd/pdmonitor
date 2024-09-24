package com.pd.monitor.controller;


import cn.hutool.core.date.DateUtil;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.*;
import com.pd.server.util.DateTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/mobile")
public class MobileController  extends BaseWxController {
    private static final Logger LOG = LoggerFactory.getLogger(MobileController.class);
    public static final String BUSINESS_NAME = "手机端";

    /**
     * 水值监测数据
     */
    @Resource
    private WaterQualityResultService waterQualityResultService;
    /**
     * 水质告警
     */
    @Resource
    private EquipmentFileService equipmentFileService;
    /**
     * 设备信息
     */
    @Resource
    private WaterEquipmentService waterEquipmentService;
    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;

    @Resource
    private EquipmentFileEventService  equipmentFileEventService;



    /**
     * 水环境数据
     * @return
     */
    @PostMapping("/getAlljcsjByDept")
    public ResponseDto getAlljcsjByDept(@RequestBody  LoginUserDto user){
        ResponseDto responseDto = new ResponseDto();
       
        if(null != user){
            if(!StringUtils.isEmpty(user.getDeptcode())){
               List<String > listdept   =  getUpdeptcode(user.getDeptcode());
                WaterQualityResultExample waterQualityResultExample = new WaterQualityResultExample();
                WaterQualityResultExample.Criteria  waterQualityResultca = waterQualityResultExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    waterQualityResultca.andSm1In(listdept);
                }
                List<KvIntDto>  list   = waterQualityResultService.getAlljcsjByDept(waterQualityResultExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }



    /**
     * equipment_file_event 获取设备聚类事件 今天
     * @return
     */
    @PostMapping("/getEquipmentEventByDept")
    public ResponseDto getEquipmentEventByDept(@RequestBody  LoginUserDto user){
        ResponseDto responseDto = new ResponseDto();
       
        if(null != user){
            if(!StringUtils.isEmpty(user.getDeptcode())){
                List<String > listdept   =  getUpdeptcode(user.getDeptcode());
                EquipmentFileEventExample equipmentFileEventExample = new EquipmentFileEventExample();
                EquipmentFileEventExample.Criteria  ca = equipmentFileEventExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }

                //统计当天数据
                ca.andRqEqualTo(DateTools.getFormatDate(new Date(), DateTools.yyyy_MM_dd));
                List<EventDto>  list   = equipmentFileEventService.getEquipmentEventByDept(equipmentFileEventExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }

    @PostMapping("/getEquipmentEventByDeptday")
    public ResponseDto getEquipmentEventByDeptday(@RequestBody  LoginUserDto user){
        ResponseDto responseDto = new ResponseDto();
        Map<String,Object> map  = new HashMap<String,Object>();
        if(null != user){
            if(!StringUtils.isEmpty(user.getDeptcode())){
                List<String > listdept   =  getUpdeptcode(user.getDeptcode());
                EquipmentFileEventExample equipmentFileEventExample = new EquipmentFileEventExample();
                EquipmentFileEventExample.Criteria  ca = equipmentFileEventExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }
                List<EquipmentFileEvent> equipmentEventByDeptdate = equipmentFileEventService.getEquipmentEventByDeptdate(equipmentFileEventExample);
                if(!CollectionUtils.isEmpty(equipmentEventByDeptdate)){
                    EquipmentFileEvent equipmentFileEvent =equipmentEventByDeptdate.get(0);
                    //统计当天数据
                    ca.andRqEqualTo(equipmentFileEvent.getRq());
                    List<EventDto>  list   = equipmentFileEventService.getEquipmentEventByDept(equipmentFileEventExample);
                    map.put("list", list);
                    map.put("date",equipmentFileEvent.getRq());
                }


                responseDto.setContent(map);
            }
        }
        return responseDto;

    }
    /**
     * equipment_file_event 获取设备聚类事件 本周
     * @return
     */
    @PostMapping("/getBzEquipmentEventByDept")
    public ResponseDto getBzEquipmentEventByDept(@RequestBody  LoginUserDto user){
        ResponseDto responseDto = new ResponseDto();
       
        if(null != user){
            if(!StringUtils.isEmpty(user.getDeptcode())){
                List<String > listdept   =  getUpdeptcode(user.getDeptcode());
                EquipmentFileEventExample equipmentFileEventExample = new EquipmentFileEventExample();
                EquipmentFileEventExample.Criteria  ca = equipmentFileEventExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }
                //获取7天前事件
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DATE, - 7);
                Date date = calendar.getTime();
                //统计当天数据
                ca.andRqGreaterThanOrEqualTo(DateTools.getFormatDate(date, DateTools.yyyy_MM_dd));
                List<EventDto>  list   = equipmentFileEventService.getEquipmentEventByDept(equipmentFileEventExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }




    /**
     * 江豚预警
     * @return
     */
    @PostMapping("/getAlljtByDept")
    public ResponseDto getAlljtByDept(@RequestBody  LoginUserDto user){
        ResponseDto responseDto = new ResponseDto();
       
        if(null != user){
            if(!StringUtils.isEmpty(user.getDeptcode())){
                List<String > listdept   =  getUpdeptcode(user.getDeptcode());
                EquipmentFileTodayExample example = new EquipmentFileTodayExample();
                EquipmentFileTodayExample.Criteria  ca = example.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }
                List<KvIntDto>  list   = equipmentFileTodayService.getAlljcsjByDept(example);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }


    /**
     * 江豚预警
     * @return
     */
    @PostMapping("/getAlljtByDeptDay")
    public ResponseDto getAlljtByDeptDay(@RequestBody  LoginUserDto user){
        ResponseDto responseDto = new ResponseDto();
        Map<String,Object> map  = new HashMap<String,Object>();
        if(null != user){
            if(!StringUtils.isEmpty(user.getDeptcode())){

                List<String > listdept   =  getUpdeptcode(user.getDeptcode());
                EquipmentFileExample example = new EquipmentFileExample();
                EquipmentFileExample.Criteria  ca = example.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }

                List<EquipmentFile>  info =  equipmentFileService.getAlljcsjByDeptgetDay(example);
                if(!CollectionUtils.isEmpty(info)){
                    EquipmentFile vo = info.get(0);
                    ca.andCjsjBetween(DateUtil.beginOfDay(vo.getCjsj()) ,DateUtil.endOfDay(vo.getCjsj()) );
                    List<KvIntDto>  list   =equipmentFileService.getAlljcsjByDept(example);
                    map.put("list", list);
                    map.put("date",DateUtil.format(vo.getCjsj(),"yyyy-MM-dd"));
                }

                responseDto.setContent(map);
            }
        }
        return responseDto;

    }



    /**
     * 获取当前部门的所有数据
     * @return
     */
    @PostMapping("/getthisDeptjxsj")
    public ResponseDto getthisDeptjxsj(@RequestBody WaterQualityResultDto waterQualityResultDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != waterQualityResultDto){
            if(!StringUtils.isEmpty(waterQualityResultDto.getSm1())){
                WaterQualityResultExample waterQualityResultExample = new WaterQualityResultExample();
                WaterQualityResultExample.Criteria  waterQualityResultca = waterQualityResultExample.createCriteria();
                waterQualityResultca.andSm1EqualTo(waterQualityResultDto.getSm1());
                List<WaterQualityResultDto>  list   = waterQualityResultService.getthisDeptjxsj(waterQualityResultExample);
                Map<String,String> map  =   new LinkedHashMap<String,String>();
                if(!CollectionUtils.isEmpty(list)){
                    for(WaterQualityResultDto vo :list){
                        map.put(vo.getIp(),vo.getSbmc());
                    }
                }
                Map<String ,Object> remap = new LinkedHashMap<String ,Object>();
                remap.put("list" ,list);
                remap.put("map", map);
                responseDto.setContent(remap);

            }
        }
        return responseDto;

    }



    /**
     * 获取当前部门的所有数据 ---江豚
     * @return
     */
    @PostMapping("/getthisDeptjxsjJT")
    public ResponseDto getthisDeptjxsjJT(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFileDto){
            if(!StringUtils.isEmpty(equipmentFileDto.getDeptcode())){
                EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria  equipmentFileca = equipmentFileExample.createCriteria();
                equipmentFileca.andDeptcodeEqualTo(equipmentFileDto.getDeptcode());
                List<EquipmentMainDto>  list   = equipmentFileService.getthisDeptjxsjJT(equipmentFileExample);
                responseDto.setContent(list);

            }
        }
        return responseDto;

    }




    /**
     * 获取当前部门当天的设备信息
     * @return
     */
    @PostMapping("/getthisDeptEquipment")
    public ResponseDto getthisDeptEquipment(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFileDto){
            if(!StringUtils.isEmpty(equipmentFileDto.getDeptcode())){
                EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria  equipmentFileca = equipmentFileExample.createCriteria();
                equipmentFileca.andDeptcodeEqualTo(equipmentFileDto.getDeptcode());
                List<EquipmenInfo>  list   = equipmentFileService.getthisDeptEquipment(equipmentFileExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;
    }

    @PostMapping("/getthisDeptEquipmentday")
    public ResponseDto getthisDeptEquipmentday(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFileDto){
            if(!StringUtils.isEmpty(equipmentFileDto.getDeptcode())){
                EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria  equipmentFileca = equipmentFileExample.createCriteria();
                equipmentFileca.andDeptcodeEqualTo(equipmentFileDto.getDeptcode());
                equipmentFileca.andCjsjBetween(DateUtil.beginOfDay(DateUtil.parse(equipmentFileDto.getRq(), "yyyy-MM-dd")) ,DateUtil.endOfDay(DateUtil.parse(equipmentFileDto.getRq(), "yyyy-MM-dd")) );
                List<EquipmenInfo>  list   = equipmentFileService.getthisDeptEquipmentday(equipmentFileExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;
    }


    /**
     * 获取当前部门当天的设备信息---具体信息
     * @return
     */
    @PostMapping("/getthisDeptEquipmentinfo")
    public ResponseDto getthisDeptEquipmentinfo(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFileDto){
            if(!StringUtils.isEmpty(equipmentFileDto.getSbbh())){
                EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria  equipmentFileca = equipmentFileExample.createCriteria();
                equipmentFileca.andSbbhEqualTo(equipmentFileDto.getSbbh());
                List<EquipmentMainListDto>  list   = equipmentFileService.getthisDeptEquipmentinfo(equipmentFileExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;
    }


    @PostMapping("/getthisDeptEquipmentinfoday")
    public ResponseDto getthisDeptEquipmentinfoday(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFileDto){
            if(!StringUtils.isEmpty(equipmentFileDto.getSbbh())){
                EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria  equipmentFileca = equipmentFileExample.createCriteria();
                equipmentFileca.andSbbhEqualTo(equipmentFileDto.getSbbh());
                equipmentFileca.andCjsjBetween(DateUtil.beginOfDay(DateUtil.parse(equipmentFileDto.getRq(), "yyyy-MM-dd")) ,DateUtil.endOfDay(DateUtil.parse(equipmentFileDto.getRq(), "yyyy-MM-dd")) );
                List<EquipmentMainListDto>  list   = equipmentFileService.getthisDeptEquipmentinfoday(equipmentFileExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;
    }

    /**
     * 根据设备编号获取聚类信息
     * sm 1 本周
     * sm 2 当天
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EquipmentFileEventDto equipmentFileEventDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFileEventDto){
            if(!StringUtils.isEmpty(equipmentFileEventDto.getSbbh())){
                EquipmentFileEventExample equipmentFileEventExample = new EquipmentFileEventExample();
                EquipmentFileEventExample.Criteria  ca = equipmentFileEventExample.createCriteria();
                ca.andSbbhEqualTo(equipmentFileEventDto.getSbbh());
                Date  date = new Date();
                //本周
                if(equipmentFileEventDto.getSm().equals("1")){
                    //获取7天前事件
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DATE, - 7);
                    date = calendar.getTime();
                    ca.andRqGreaterThanOrEqualTo(DateTools.getFormatDate(date, DateTools.yyyy_MM_dd));
                }else{
                    ca.andRqEqualTo(DateTools.getFormatDate(date, DateTools.yyyy_MM_dd));
                }
                equipmentFileEventExample.setOrderByClause("kssj desc");
                List<EquipmentFileEvent>  list   = equipmentFileEventService.list(equipmentFileEventExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }

    @PostMapping("/listday")
    public ResponseDto listday(@RequestBody EquipmentFileEventDto equipmentFileEventDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFileEventDto){
            if(!StringUtils.isEmpty(equipmentFileEventDto.getSbbh())){
                EquipmentFileEventExample equipmentFileEventExample = new EquipmentFileEventExample();
                EquipmentFileEventExample.Criteria  ca = equipmentFileEventExample.createCriteria();
                ca.andSbbhEqualTo(equipmentFileEventDto.getSbbh());
                Date  date = new Date();
                ca.andRqEqualTo(equipmentFileEventDto.getRq());
                equipmentFileEventExample.setOrderByClause("kssj desc");
                List<EquipmentFileEvent>  list   = equipmentFileEventService.list(equipmentFileEventExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }




}

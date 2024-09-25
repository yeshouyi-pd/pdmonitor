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
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

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


    @Resource
    private PredationNumService predationNumService;


    private static  Set<String> getDateMonth(Date sdate ,Date edate){
        Set<String> set = new TreeSet<>();
        // 获取当月第一天的日期
        LocalDate firstDayOfMonth =DateUtil.toLocalDateTime(sdate).toLocalDate();
        // 获取当月最后一天的日期
        LocalDate lastDayOfMonth = DateUtil.toLocalDateTime(edate).toLocalDate();
        // 遍历输出每一天的日期
        for (LocalDate date = firstDayOfMonth; date.isBefore(lastDayOfMonth.plusDays(1)); date = date.plusDays(1)) {
            System.out.println(date);
            set.add(date.toString());
            if(date.toString().equals(DateUtil.format(new Date(),"yyyy-MM-dd"))){
                break;
            }
        }
        return set;
    }

    private static  Set<String> getDateMonth(int data){
        Set<String> set = new TreeSet<>();
        YearMonth yearMonth = YearMonth.of(data, 1); // 初始化为指定的年份和月份
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        int currentYear = Year.now().getValue();
        if(currentYear >= data){
            do {
                String monthStr = yearMonth.format(formatter);
                System.out.println(monthStr);
                set.add(monthStr);
                yearMonth = yearMonth.plusMonths(1); // 增加一个月
            } while (yearMonth.getYear() == data); // 循环继续直到年份改变

        }

        return set;
    }





    /**
     * 根据部门统计江豚侦测和聚类时间
     * @param user
     * @return
     */
    @PostMapping("/gettjByDept")
    public ResponseDto gettjByDept(@RequestBody  LoginUserDto user){
        ResponseDto responseDto = new ResponseDto();

        if(null != user){
            if(!StringUtils.isEmpty(user.getDeptcode()) && !StringUtils.isEmpty(user.getType()) && !StringUtils.isEmpty(user.getRq())){
                List<String > listdept   =  getUpdeptcode(user.getDeptcode());
                PredationNumExample example = new PredationNumExample();
                PredationNumExample.Criteria  ca = example.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }
                List<KvIntDtoTj>  list = new ArrayList<>();
                Set<String>    set = new TreeSet<>();
                //周统计
                 if("day".equals(user.getType())){
                     Date  endDate= DateUtil.parse(user.getRq(), "yyyy-MM-dd");
                     Date startDate =  DateUtil.offsetDay(endDate,-7);
                     ca.andCjsjBetween(DateUtil.beginOfDay(startDate),DateUtil.endOfDay(endDate));
                     set=getDateMonth(startDate,endDate);
                     list   = predationNumService.gettjByDept(example);


                    //月统计
                }else if("month".equals(user.getType())){
                     Date  date= DateUtil.parse(user.getRq()+"-01", "yyyy-MM-dd");
                     Date startDate =  DateUtil.beginOfMonth(date);
                     Date  endDate= DateUtil.endOfMonth(date);
                     ca.andCjsjBetween(DateUtil.beginOfDay(startDate),DateUtil.endOfDay(endDate));
                     set=getDateMonth(startDate,endDate);
                     list   = predationNumService.gettjByDept(example);

                     //年统计
                } else if("year".equals(user.getType())){
                     Date  date= DateUtil.parse(user.getRq()+"-01-01", "yyyy-MM-dd");
                     Date startDate =  DateUtil.beginOfYear(date);
                     Date  endDate= DateUtil.endOfYear(date);
                     ca.andCjsjBetween(DateUtil.beginOfDay(startDate),DateUtil.endOfDay(endDate));
                     set=getDateMonth(Integer.parseInt(user.getRq()));
                     list   = predationNumService.gettjByDeptyear(example);
                }
                 Set<String> setsb = new TreeSet<>();
                 for (KvIntDtoTj kvIntDtoTj : list){
                     setsb.add(kvIntDtoTj.getSbbh());
                 }

                /**返回个数服下
                 *{
                 *             categories: ["2018","2019","2020","2021","2022","2023","2024"],
                 *             series: [
                 *               {
                 *                 name: "目标值",
                 *                 data: [35,36,31,33,13,34,dara]
                 *               },
                 *               {
                 *                 name: "完成量",
                 *                 data: [18,27,21,24,6,28,dara]
                 *               }
                 *             ]
                 *           }
                 */
                 NameAndDataRes  nameAndDataRes = new NameAndDataRes();
                 NameAndDataRes  nameAndDataRes2 = new NameAndDataRes();
                 nameAndDataRes.setCategories(set.toArray(new String[set.size()]));
                 nameAndDataRes2.setCategories(set.toArray(new String[set.size()]));

                 List<NameAndData>  listnameAndData = new ArrayList<>();
                 List<NameAndData>  listnameAndData2 = new ArrayList<>();

                 if(setsb.size() > 0){
                     for (String b : setsb){
                         NameAndData  nameAndData = new NameAndData();
                         NameAndData  nameAndData2 = new NameAndData();
                         nameAndData.setName(b);
                         nameAndData2.setName(b);

                         int data[] = new int[set.size()];
                         int data2[] = new int[set.size()];
                         int i = 0;
                         for (String s : set){

                             List<KvIntDtoTj> collect = list.stream().filter(x -> x.getSbbh().equals(b) && x.getSj().equals(s)).collect(Collectors.toList());
                             if(!CollectionUtils.isEmpty(collect)){
                                 data[i] = collect.get(0).getV1();
                             }else{
                                 data[i] = 0;
                             }

                             if(!CollectionUtils.isEmpty(collect)){
                                 data2[i] = collect.get(0).getV2();
                             }else{
                                 data2[i] = 0;
                             }
                             nameAndData.setData(data);
                             nameAndData2.setData(data2);
                             i = i+1;
                         }

                         listnameAndData.add(nameAndData);
                         listnameAndData2.add(nameAndData2);
                     }

                 }else {
                     NameAndData  nameAndData = new NameAndData();
                     NameAndData  nameAndData2 = new NameAndData();
                     nameAndData.setName("");
                     nameAndData2.setName("");
                     int data[] = new int[set.size()];
                     int data2[] = new int[set.size()];
                     nameAndData.setData(data);
                     nameAndData2.setData(data2);
                     listnameAndData.add(nameAndData);
                     listnameAndData2.add(nameAndData2);
                 }
                nameAndDataRes.setSeries(listnameAndData);
                nameAndDataRes2.setSeries(listnameAndData2);
                Map<String,NameAndDataRes> map  = new HashMap<String,NameAndDataRes>();
                map.put("v1",nameAndDataRes);
                map.put("v2",nameAndDataRes2);

                responseDto.setContent(map);
            }
        }
        return responseDto;

    }


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

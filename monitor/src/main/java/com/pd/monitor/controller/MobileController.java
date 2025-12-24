package com.pd.monitor.controller;


import cn.hutool.core.collection.CollectionUtil;
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


    /**
     * equipment_p_file_聚类 33 35
     */
    @Resource
    private EquipmentFilePClusterService equipmentFilePClusterService;

    /**
     * equipment_p_file图片pic 10 11
     */
    @Resource
    private EquipmentFilePPicService equipmentFilePPicService;

    /**
     * equipment_p_file文件 31 32 34 36
     */

    @Resource
    private EquipmentFilePTxtService equipmentFilePTxtService;


    /**
     * equipment_p_file_mp4 40"
     */
    @Resource
    private EquipmentFilePVideoService equipmentFilePVideoService;

    /**
     * equipment_p_file音档 20 21
     */
    @Resource
    private EquipmentFilePWavService equipmentFilePWavService;

    /**
     * 移动端Service
     */
    @Resource
    private MobileService mobileService;

    /**
     * 设备计划Service
     */
    @Resource
    private DeviceSchedulesService deviceSchedulesService;


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

                  Set<String>    resset = new TreeSet<>();
                  for (String s : set){
                      if(!"year".equals(user.getType())){
                          resset.add(  s.substring(5,10).replaceAll( "-" , "/"));
                      }else{
                          resset.add(s.replaceAll( "-" , "/"));
                      }

                  }
                 nameAndDataRes.setCategories(resset.toArray(new String[resset.size()]));
                 nameAndDataRes2.setCategories(resset.toArray(new String[resset.size()]));

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
                EquipmentFilePClusterExample equipmentFilePClusterExample = new EquipmentFilePClusterExample();
                EquipmentFilePClusterExample.Criteria  ca = equipmentFilePClusterExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }

                //统计当天数据
                ca.andRqEqualTo(DateTools.getFormatDate(new Date(), DateTools.yyyy_MM_dd));
                List<EventDto>  list   = equipmentFilePClusterService.getEquipmentEventByDept(equipmentFilePClusterExample);
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
                EquipmentFilePClusterExample  equipmentFilePClusterExample = new EquipmentFilePClusterExample();
                EquipmentFilePClusterExample.Criteria  ca = equipmentFilePClusterExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }
                ca.andRqEqualTo(DateTools.getFormatDate(new Date(), DateTools.yyyy_MM_dd));

                List<EventDto>  list   =equipmentFilePClusterService.getEquipmentEventByDept(equipmentFilePClusterExample);

                if(CollectionUtil.isNotEmpty(list)){
                    map.put("list", list);
                    map.put("date",DateTools.getFormatDate(new Date(), DateTools.yyyy_MM_dd));
                    responseDto.setContent(map);
                }else{
                    equipmentFilePClusterExample.clear();
                    if(!CollectionUtils.isEmpty(listdept)){
                        ca.andDeptcodeIn(listdept);
                    }
                    List<EquipmentFilePCluster> equipmentEventByDeptdate =  equipmentFilePClusterService.getEquipmentEventByDeptdate(equipmentFilePClusterExample);
                    if(!CollectionUtils.isEmpty(equipmentEventByDeptdate)){
                        EquipmentFilePCluster equipmentFileEvent =equipmentEventByDeptdate.get(0);
                        //统计当天数据


                        ca.andRqEqualTo(equipmentFileEvent.getRq());
                        List<EventDto>  lists   =equipmentFilePClusterService.getEquipmentEventByDept(equipmentFilePClusterExample);
                        map.put("list", lists);
                        map.put("date",equipmentFileEvent.getRq());
                    }
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

                EquipmentFilePClusterExample equipmentFilePClusterExample = new EquipmentFilePClusterExample();
                EquipmentFilePClusterExample.Criteria  ca = equipmentFilePClusterExample.createCriteria();
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
                List<EventDto>  list   =  equipmentFilePClusterService.getEquipmentEventByDept(equipmentFilePClusterExample);
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
                EquipmentFilePPicExample example = new EquipmentFilePPicExample();
                EquipmentFilePPicExample.Criteria  ca = example.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }

                List<EquipmentFilePPic>  info =  equipmentFilePPicService.getAlljcsjByDeptgetDay(example);
                if(!CollectionUtils.isEmpty(info)){
                    EquipmentFilePPic vo = info.get(0);
                    ca.andCjsjBetween(DateUtil.beginOfDay(vo.getCjsj()) ,DateUtil.endOfDay(vo.getCjsj()) );

                    List<KvIntDto>  list   =equipmentFilePPicService.getAlljcsjByDept(example);
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
    public ResponseDto getthisDeptjxsjJT(@RequestBody EquipmentFilePPicDto equipmentFilePPicDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFilePPicDto){
            if(!StringUtils.isEmpty(equipmentFilePPicDto.getDeptcode())){
                EquipmentFilePPicExample equipmentFileExample = new EquipmentFilePPicExample();
                EquipmentFilePPicExample.Criteria  equipmentFileca = equipmentFileExample.createCriteria();
                equipmentFileca.andDeptcodeEqualTo(equipmentFilePPicDto.getDeptcode());

                List<EquipmentMainDto>  list   =equipmentFilePPicService.getthisDeptjxsjJT(equipmentFileExample);

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
    public ResponseDto getthisDeptEquipmentday(@RequestBody EquipmentFilePPicDto equipmentFilePPicDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFilePPicDto){
            if(!StringUtils.isEmpty(equipmentFilePPicDto.getDeptcode())){
                EquipmentFilePPicExample equipmentFileExample = new EquipmentFilePPicExample();
                EquipmentFilePPicExample.Criteria  equipmentFileca = equipmentFileExample.createCriteria();
                equipmentFileca.andDeptcodeEqualTo(equipmentFilePPicDto.getDeptcode());
                equipmentFileca.andCjsjBetween(DateUtil.beginOfDay(DateUtil.parse(equipmentFilePPicDto.getRq(), "yyyy-MM-dd")) ,DateUtil.endOfDay(DateUtil.parse(equipmentFilePPicDto.getRq(), "yyyy-MM-dd")) );
                List<EquipmenInfo>  list   = equipmentFilePPicService.getthisDeptEquipmentday(equipmentFileExample);

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
    public ResponseDto getthisDeptEquipmentinfoday(@RequestBody EquipmentFilePPicDto equipmentFilePPicDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFilePPicDto){
            if(!StringUtils.isEmpty(equipmentFilePPicDto.getSbbh())){
                EquipmentFilePPicExample equipmentFileExample = new EquipmentFilePPicExample();
                EquipmentFilePPicExample.Criteria  equipmentFileca = equipmentFileExample.createCriteria();
                equipmentFileca.andSbbhEqualTo(equipmentFilePPicDto.getSbbh());
                equipmentFileca.andCjsjBetween(DateUtil.beginOfDay(DateUtil.parse(equipmentFilePPicDto.getRq(), "yyyy-MM-dd")) ,DateUtil.endOfDay(DateUtil.parse(equipmentFilePPicDto.getRq(), "yyyy-MM-dd")) );
                equipmentFilePPicService.getthisDeptEquipmentinfoday(equipmentFilePPicDto, equipmentFileExample);

                responseDto.setContent(equipmentFilePPicDto);
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
                EquipmentFilePClusterExample  equipmentFilePClusterExample = new EquipmentFilePClusterExample();
                EquipmentFilePClusterExample.Criteria  ca = equipmentFilePClusterExample.createCriteria();
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
                equipmentFilePClusterExample.setOrderByClause("kssj desc");
                List<EquipmentFilePCluster>  list   = equipmentFilePClusterService.listByexample(equipmentFilePClusterExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }

    @PostMapping("/listday")
    public ResponseDto listday(@RequestBody EquipmentFilePClusterDto equipmentFilePClusterDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFilePClusterDto){
            if(!StringUtils.isEmpty(equipmentFilePClusterDto.getSbbh())){
                EquipmentFilePClusterExample equipmentFilePClusterExample = new EquipmentFilePClusterExample();
                EquipmentFilePClusterExample.Criteria  ca = equipmentFilePClusterExample.createCriteria();
                ca.andSbbhEqualTo(equipmentFilePClusterDto.getSbbh());
                Date  date = new Date();
                ca.andRqEqualTo(equipmentFilePClusterDto.getRq());
                equipmentFilePClusterExample.setOrderByClause("kssj desc");
                equipmentFilePClusterService.listByexample(equipmentFilePClusterDto, equipmentFilePClusterExample);
                responseDto.setContent(equipmentFilePClusterDto);
            }
        }
        return responseDto;

    }

    /**
     * 获取用户关联的所有项目和设备
     * @param requestDto 请求参数，包含xm字段（用户账号）
     * @return 用户项目设备列表
     */
    @PostMapping("/getUserProjectEquipment")
    public ResponseDto getUserProjectEquipment(@RequestBody MobileUserRequestDto requestDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != requestDto && !StringUtils.isEmpty(requestDto.getXm())){
            List<UserProjectEquipmentDto> list = mobileService.getUserProjectEquipment(requestDto.getXm());
            responseDto.setContent(list);
        }
        return responseDto;
    }

    /**
     * 获取设备计划列表
     * @param requestDto 请求参数，包含device_id字段
     * @return 设备计划列表
     */
    @PostMapping("/getDeviceSchedules")
    public ResponseDto getDeviceSchedules(@RequestBody DeviceSchedulesDto requestDto){
        ResponseDto responseDto = new ResponseDto();

        try {
            if(null != requestDto && requestDto.getDeviceId() != null){
                List<DeviceSchedulesDto> list = deviceSchedulesService.getByDeviceId(requestDto.getDeviceId());
                responseDto.setContent(list);
                responseDto.setSuccess(true);
            } else {
                responseDto.setSuccess(false);
                responseDto.setMessage("参数不能为空或缺少device_id");
            }
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setMessage("查询失败：" + e.getMessage());
            e.printStackTrace();
        }
        return responseDto;
    }

    /**
     * 创建设备计划
     * @param requestDto 计划信息
     * @return 操作结果
     */
    @PostMapping("/createDeviceSchedule")
    public ResponseDto createDeviceSchedule(@RequestBody DeviceSchedulesDto requestDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != requestDto){
            try {
                
                deviceSchedulesService.save(requestDto);
                responseDto.setSuccess(true);
                responseDto.setMessage("创建成功");
            } catch (Exception e) {
                responseDto.setSuccess(false);
                responseDto.setMessage("创建失败：" + e.getMessage());
            }
        } else {
            responseDto.setSuccess(false);
            responseDto.setMessage("参数不能为空");
        }
        return responseDto;
    }

    /**
     * 更新设备计划
     * @param requestDto 计划信息，必须包含id
     * @return 操作结果
     */
    @PostMapping("/updateDeviceSchedule")
    public ResponseDto updateDeviceSchedule(@RequestBody DeviceSchedulesDto requestDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != requestDto && requestDto.getId() != null){
            try {
                DeviceSchedules byId = deviceSchedulesService.getById(requestDto.getId());
                if(0 != byId.getIsRunning()){
                    responseDto.setSuccess(false);
                    responseDto.setMessage("计划运行中，禁止更新");
                    return responseDto;
                }

                deviceSchedulesService.save(requestDto);
                responseDto.setSuccess(true);
                responseDto.setMessage("更新成功");
            } catch (Exception e) {
                responseDto.setSuccess(false);
                responseDto.setMessage("更新失败：" + e.getMessage());
            }
        } else {
            responseDto.setSuccess(false);
            responseDto.setMessage("参数不能为空或缺少id");
        }
        return responseDto;
    }

    @PostMapping("/deleteDeviceSchedule")
    public ResponseDto deleteDeviceSchedule(@RequestBody DeviceSchedulesDto requestDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != requestDto && requestDto.getId() != null){
            try {
                deviceSchedulesService.delete(requestDto.getId());
                responseDto.setSuccess(true);
                responseDto.setMessage("删除成功");
            } catch (Exception e) {
                responseDto.setSuccess(false);
                responseDto.setMessage("删除失败：" + e.getMessage());
            }
        } else {
            responseDto.setSuccess(false);
            responseDto.setMessage("参数不能为空或缺少id");
        }
        return responseDto;
    }

    @PostMapping("/getDeviceStuts")
    public ResponseDto getDeviceStuts(@RequestBody DeviceSchedulesDto requestDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != requestDto && requestDto.getDeviceId() != null){
            DeviceSchedulesExample deviceSchedulesExample = new DeviceSchedulesExample();
            DeviceSchedulesExample.Criteria criteria = deviceSchedulesExample.createCriteria();
            criteria.andDeviceIdEqualTo(requestDto.getDeviceId());
            criteria.andIsRunningNotEqualTo(0);
            criteria.andIsActiveEqualTo(1);
            List<DeviceSchedules> list =deviceSchedulesService.findExample(deviceSchedulesExample);
            if(CollectionUtils.isEmpty(list)){
                responseDto.setSuccess(true);
                responseDto.setContent(false);
                responseDto.setMessage("设备未运行");
            }else{
                responseDto.setSuccess(true);
                responseDto.setContent(true);
                responseDto.setMessage("设备运行中");
            }
        } else {
            responseDto.setSuccess(false);
            responseDto.setMessage("参数不能为空或缺少id");
        }
        return responseDto;
    }


}

package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/equipmentFile")
public class EquipmentFileController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileController.class);
    public static final String BUSINESS_NAME = "水噪声图片";

    @Resource
    private EquipmentFileService equipmentFileService;
    @Resource
    private WaterEquipmentService waterEquipmentService;
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/statisticsAlarmNumsByMinute")
    public ResponseDto statisticsAlarmNumsByMinute(@RequestBody AlarmNumbersDto entityDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(entityDto.getSbbh())){
            ca.andSbbhEqualTo(entityDto.getSbbh());
        }
        if(!StringUtils.isEmpty(entityDto.getDeptcode())){
            ca.andDeptcodeEqualTo(entityDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(entityDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(entityDto.getStime());
        }
        if(!StringUtils.isEmpty(entityDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(entityDto.getEtime());
        }
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNums(example);
        List<String> xAixsData = lists.stream().filter(Objects::nonNull).map(u->u.getBjsj()+" "+u.getXs()+":"+u.getFz()).collect(Collectors.toList());
        List<Integer> yAixsData = lists.stream().filter(Objects::nonNull).map(u-> u.getAlarmNum()).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("xAixsData",xAixsData);
        map.put("yAixsData",yAixsData);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 大屏展示，根据部门，查询所有设备的出现占比
     * @param entityDto
     * @return
     */
    @PostMapping("/statisticsAlarmNumsByHourDP")
    public ResponseDto statisticsAlarmNumsByHourDP(@RequestBody AlarmNumbersDto entityDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(entityDto.getSbbh())){
            ca.andSbbhEqualTo(entityDto.getSbbh());
        }
        if(!StringUtils.isEmpty(entityDto.getDeptcode())){
            ca.andDeptcodeEqualTo(entityDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(entityDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(entityDto.getStime());
        }
        if(!StringUtils.isEmpty(entityDto.getEtime())){
            ca.andRqLessThanOrEqualTo(entityDto.getEtime());
        }
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByHourAndDP(example);
        Optional<Integer> op = lists.stream().filter(Objects::nonNull).map(AlarmNumbersDto::getAlarmNum).reduce(Integer::sum);
        List<String> xAixsData = lists.stream().filter(Objects::nonNull).map(u->u.getBjsj()+" "+u.getXs()).collect(Collectors.toList());
        List<String> yAixsData = new ArrayList<>();
        if(op.isPresent()){
            yAixsData = lists.stream().filter(Objects::nonNull).map(u-> calculateResultOfPercent(u.getAlarmNum(),op.get())).collect(Collectors.toList());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xAixsData",xAixsData);
        map.put("yAixsData",yAixsData);
        responseDto.setContent(map);
        return responseDto;
    }

    @PostMapping("/statisticsAlarmNumsByHour")
    public ResponseDto statisticsAlarmNumsByHour(@RequestBody AlarmNumbersDto entityDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(entityDto.getSbbh())){
            ca.andSbbhEqualTo(entityDto.getSbbh());
        }
        if(!StringUtils.isEmpty(entityDto.getDeptcode())){
            ca.andDeptcodeEqualTo(entityDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(entityDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(entityDto.getStime());
        }
        if(!StringUtils.isEmpty(entityDto.getEtime())){
            ca.andRqLessThanOrEqualTo(entityDto.getEtime());
        }
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByHour(example);
        Optional<Integer> op = lists.stream().filter(Objects::nonNull).map(AlarmNumbersDto::getAlarmNum).reduce(Integer::sum);
        List<String> xAixsData = lists.stream().filter(Objects::nonNull).map(u->u.getBjsj()+" "+u.getXs()).collect(Collectors.toList());
        List<String> yAixsData = new ArrayList<>();
        if(op.isPresent()){
            yAixsData = lists.stream().filter(Objects::nonNull).map(u-> calculateResultOfPercent(u.getAlarmNum(),op.get())).collect(Collectors.toList());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xAixsData",xAixsData);
        map.put("yAixsData",yAixsData);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 统计出现次数
     * @param alarmNumbersDto
     * @return
     */
    @PostMapping("/statisticsAlarmNums")
    public ResponseDto statisticsAlarmNums(@RequestBody AlarmNumbersDto alarmNumbersDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        PageHelper.startPage(alarmNumbersDto.getPage(), alarmNumbersDto.getSize());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getSbbh())){
            ca.andSbbhEqualTo(alarmNumbersDto.getSbbh());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
            ca.andDeptcodeEqualTo(alarmNumbersDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(alarmNumbersDto.getStime());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(alarmNumbersDto.getEtime());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(alarmNumbersDto.getXmbh()))){
                ca.andSbbhIn(user.getXmbhsbsns().get(alarmNumbersDto.getXmbh()));
            }
        }
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByPage(example);
        PageInfo<AlarmNumbersDto> pageInfo = new PageInfo<>(lists);
        alarmNumbersDto.setTotal(pageInfo.getTotal());
        alarmNumbersDto.setList(lists);
        responseDto.setContent(alarmNumbersDto);
        return responseDto;
    }

    /**
     * 通过事件统计出现次数
     * @param alarmNumbersDto
     * @return
     */
    @PostMapping("/statisticsAlarmNumsByTime")
    public ResponseDto statisticsAlarmNumsByTime(@RequestBody AlarmNumbersDto alarmNumbersDto) throws ParseException {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getSbbh())){
            ca.andSbbhEqualTo(alarmNumbersDto.getSbbh());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
            ca.andDeptcodeEqualTo(alarmNumbersDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(alarmNumbersDto.getStime());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(alarmNumbersDto.getEtime());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(alarmNumbersDto.getXmbh()))){
                ca.andSbbhIn(user.getXmbhsbsns().get(alarmNumbersDto.getXmbh()));
            }
        }
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByPage(example);
        List<AlarmNumbersDto> resultList = new ArrayList<>();
        Map<String, List<AlarmNumbersDto>> mapList = lists.stream().collect(Collectors.groupingBy(AlarmNumbersDto::getSbbh));
        for(String key : mapList.keySet()){
            List<AlarmNumbersDto> listsTemp = mapList.get(key);
            if(!CollectionUtils.isEmpty(listsTemp)){
                AlarmNumbersDto firstEntity = listsTemp.get(0);
                String curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                //String lastDateStr = laterThreeMinute(curDateStr);
                Integer bjsl = firstEntity.getAlarmNum();
                for(int i=1;i<listsTemp.size();i++){
                    AlarmNumbersDto entity = listsTemp.get(i);
                    AlarmNumbersDto beforeEntity = listsTemp.get(i-1);
                    String beforeDateStr = beforeEntity.getBjsj()+" "+beforeEntity.getXs()+":"+beforeEntity.getFz();
                    String nextDateStr = entity.getBjsj()+" "+entity.getXs()+":"+entity.getFz();
                    if(entity.getSbbh().equals(firstEntity.getSbbh())){
                        if(isOverThreeMinute(beforeDateStr, nextDateStr)){
                            bjsl = bjsl + entity.getAlarmNum();
                        }else {
                            AlarmNumbersDto result = new AlarmNumbersDto();
                            result.setDeptcode(entity.getDeptcode());
                            result.setSbbh(entity.getSbbh());
                            result.setBjsj(curDateStr+" 至 "+beforeDateStr);
                            result.setAlarmNum(bjsl);
                            resultList.add(result);
                            firstEntity = entity;
                            curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                            //lastDateStr = laterThreeMinute(curDateStr);
                            bjsl = firstEntity.getAlarmNum();
                        }
                    }else {
                        AlarmNumbersDto result = new AlarmNumbersDto();
                        result.setDeptcode(firstEntity.getDeptcode());
                        result.setSbbh(firstEntity.getSbbh());
                        result.setBjsj(curDateStr+" 至 "+beforeDateStr);
                        result.setAlarmNum(bjsl);
                        resultList.add(result);
                        firstEntity = entity;
                        curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                        //lastDateStr = laterThreeMinute(curDateStr);
                        bjsl = firstEntity.getAlarmNum();
                    }
                    if(i==listsTemp.size()-1){
                        AlarmNumbersDto result = new AlarmNumbersDto();
                        result.setDeptcode(entity.getDeptcode());
                        result.setSbbh(entity.getSbbh());
                        result.setBjsj(curDateStr+" 至 "+nextDateStr);
                        result.setAlarmNum(bjsl);
                        resultList.add(result);
                    }
                }
                if(listsTemp.size()==1){
                    AlarmNumbersDto result = new AlarmNumbersDto();
                    result.setDeptcode(firstEntity.getDeptcode());
                    result.setSbbh(firstEntity.getSbbh());
                    result.setBjsj(curDateStr+" 至 "+curDateStr);
                    result.setAlarmNum(bjsl);
                    resultList.add(result);
                }
            }
        }
        responseDto.setContent(resultList);
        return responseDto;
    }

    /**
     * 当日出现次数、事件、捕食次数
     * @param alarmNumbersDto
     * @return
     * @throws ParseException
     */
    @PostMapping("/statisticsAlarmNumsByTimeSum")
    public ResponseDto statisticsAlarmNumsByTimeSum(@RequestBody AlarmNumbersDto alarmNumbersDto) throws ParseException {
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        ca.andDeptcodeEqualTo(alarmNumbersDto.getDeptcode());
        if(!StringUtils.isEmpty(alarmNumbersDto.getSbbh())){
            ca.andSbbhEqualTo(alarmNumbersDto.getSbbh());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
            ca.andDeptcodeEqualTo(alarmNumbersDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(alarmNumbersDto.getStime());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getEtime())){
            ca.andRqLessThanOrEqualTo(alarmNumbersDto.getEtime());
        }
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByPage(example);
        List<EquipmentFile> allList = equipmentFileService.listAll(example);
        List<AlarmNumbersDto> resultList = new ArrayList<>();
        Map<String, List<AlarmNumbersDto>> mapList = lists.stream().collect(Collectors.groupingBy(AlarmNumbersDto::getSbbh));
        for(String key : mapList.keySet()){
            List<AlarmNumbersDto> listsTemp = mapList.get(key);
            if(!CollectionUtils.isEmpty(listsTemp)){
                AlarmNumbersDto firstEntity = listsTemp.get(0);
                String curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                //String lastDateStr = laterThreeMinute(curDateStr);
                Integer bjsl = firstEntity.getAlarmNum();
                for(int i=1;i<listsTemp.size();i++){
                    AlarmNumbersDto entity = listsTemp.get(i);
                    AlarmNumbersDto beforeEntity = listsTemp.get(i-1);
                    String beforeDateStr = beforeEntity.getBjsj()+" "+beforeEntity.getXs()+":"+beforeEntity.getFz();
                    String nextDateStr = entity.getBjsj()+" "+entity.getXs()+":"+entity.getFz();
                    if(entity.getSbbh().equals(firstEntity.getSbbh())){
                        if(isOverThreeMinute(beforeDateStr, nextDateStr)){
                            bjsl = bjsl + entity.getAlarmNum();
                        }else {
                            AlarmNumbersDto result = new AlarmNumbersDto();
                            result.setDeptcode(entity.getDeptcode());
                            result.setSbbh(entity.getSbbh());
                            result.setBjsj(curDateStr+" 至 "+beforeDateStr);
                            result.setAlarmNum(bjsl);
                            resultList.add(result);
                            firstEntity = entity;
                            curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                            //lastDateStr = laterThreeMinute(curDateStr);
                            bjsl = firstEntity.getAlarmNum();
                        }
                    }else {
                        AlarmNumbersDto result = new AlarmNumbersDto();
                        result.setDeptcode(firstEntity.getDeptcode());
                        result.setSbbh(firstEntity.getSbbh());
                        result.setBjsj(curDateStr+" 至 "+beforeDateStr);
                        result.setAlarmNum(bjsl);
                        resultList.add(result);
                        firstEntity = entity;
                        curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                        //lastDateStr = laterThreeMinute(curDateStr);
                        bjsl = firstEntity.getAlarmNum();
                    }
                    if(i==listsTemp.size()-1){
                        AlarmNumbersDto result = new AlarmNumbersDto();
                        result.setDeptcode(entity.getDeptcode());
                        result.setSbbh(entity.getSbbh());
                        result.setBjsj(curDateStr+" 至 "+nextDateStr);
                        result.setAlarmNum(bjsl);
                        resultList.add(result);
                    }
                }
                if(listsTemp.size()==1){
                    AlarmNumbersDto result = new AlarmNumbersDto();
                    result.setDeptcode(firstEntity.getDeptcode());
                    result.setSbbh(firstEntity.getSbbh());
                    result.setBjsj(curDateStr+" 至 "+curDateStr);
                    result.setAlarmNum(bjsl);
                    resultList.add(result);
                }
            }
        }
        Integer num = 0;
        for (int i = 0; i < resultList.size(); i++) {
            Integer alarmNum = resultList.get(i).getAlarmNum();
            num = num + alarmNum;
        }
        List<EquipmentFile> predationList = allList.stream().filter(entity -> entity.getTplj().contains("predation")).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<String, Object>();
        String sum = String.valueOf(resultList.size());
        map.put("num",num);
        map.put("nnm",resultList.size());
        map.put("sum",sum.split(""));
        map.put("bnum",predationList.size());
        responseDto.setContent(map);
        return responseDto;
    }

    @PostMapping("/findSbbh")
    public ResponseDto findSbbh(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        ca.andSblbEqualTo("0001");
        if(!StringUtils.isEmpty(equipmentFileDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(equipmentFileDto.getXmbh()))){
                ca.andSbsnIn(user.getXmbhsbsns().get(equipmentFileDto.getXmbh()));
            }
        }
        List<String> sbbhList = waterEquipmentService.findSbbh(example);
        responseDto.setContent(sbbhList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EquipmentFileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        equipmentFileService.list(pageDto,list,user);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/listsbbh")
    public ResponseDto listsbbh(@RequestBody EquipmentFileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria criteria = example.createCriteria();
        criteria.andSbbhEqualTo(pageDto.getSbbh());
        criteria.andCjsjGreaterThan(DateTools.toDate(DateTools.getFormatDate(new Date(),DateTools.yyyy_MM_dd),DateTools.yyyy_MM_dd));
        criteria.andTpljLike("%.png");
        example.setOrderByClause(" cjsj desc ");
        List<EquipmentFile> equipmentFiles = equipmentFileService.listAll(example);
        responseDto.setContent(equipmentFiles);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileDto equipmentFileDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileDto.getTplj(), "图片路径", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileService.save(equipmentFileDto);
        responseDto.setContent(equipmentFileDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileService.delete(id);
        return responseDto;
    }

    /**
     * 计算结果百分比，保留1位小数
     *
     * @param v1 除数
     * @param v2 被除数
     * @return
     */
    private static String calculateResultOfPercent(double v1, double v2) {
        if (v2 == 0) {
            return "0%";
        }

        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(v1 / v2);
    }

    public Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        if(minute<=2){
            return true;
        }
        return false;
    }

}

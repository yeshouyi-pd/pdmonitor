package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.EquipmentFileToday;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.EquipmentFileTodayService;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/equipmentFileToday")
public class EquipmentFileTodayController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileTodayController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;

    /**
     * 当日出现次数、事件、捕食次数
     * @param alarmNumbersDto
     * @return
     * @throws ParseException
     */
    @PostMapping("/statisticsAlarmNumsByTimeSum")
    public ResponseDto statisticsAlarmNumsByTimeSum(@RequestBody AlarmNumbersDto alarmNumbersDto) throws ParseException {
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTodayExample example = new EquipmentFileTodayExample();
        EquipmentFileTodayExample example1 = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria ca = example.createCriteria();
        EquipmentFileTodayExample.Criteria ca1 = example1.createCriteria();
        LoginUserDto userDto = getRequestHeader();
        List<String> deptList = getUpdeptcode(userDto.getDeptcode());
        if(!StringUtils.isEmpty(alarmNumbersDto.getType())&&"zjglj".equals(alarmNumbersDto.getType())){
            if(!StringUtils.isEmpty(alarmNumbersDto.getSbbh())){
                ca.andSbbhEqualTo(alarmNumbersDto.getSbbh());
                ca1.andSbbhEqualTo(alarmNumbersDto.getSbbh());
            }
        }else{
            if(!CollectionUtils.isEmpty(deptList)){
                ca.andDeptcodeIn(deptList);
                ca1.andDeptcodeIn(deptList);
            }
            if(!StringUtils.isEmpty(alarmNumbersDto.getSbbh())){
                ca.andSbbhEqualTo(alarmNumbersDto.getSbbh());
                ca1.andSbbhEqualTo(alarmNumbersDto.getSbbh());
            }
            if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
                ca.andDeptcodeEqualTo(alarmNumbersDto.getDeptcode());
                ca1.andDeptcodeEqualTo(alarmNumbersDto.getDeptcode());
            }
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(alarmNumbersDto.getStime());
            ca1.andRqGreaterThanOrEqualTo(alarmNumbersDto.getStime());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getEtime())){
            ca.andRqLessThanOrEqualTo(alarmNumbersDto.getEtime());
            ca1.andRqLessThanOrEqualTo(alarmNumbersDto.getEtime());
        }
        ca.andTxtlxEqualTo("1");
        List<AlarmNumbersDto> lists = equipmentFileTodayService.statisticsAlarmNums(example);
        ca1.andTxtlxEqualTo("1");
        ca1.andJczlEqualTo("1");
        List<EquipmentFileToday> predationList = equipmentFileTodayService.listAll(example1);
        List<AlarmNumbersDto> resultList = new ArrayList<>();
        Map<String, List<AlarmNumbersDto>> mapList = lists.stream().collect(Collectors.groupingBy(AlarmNumbersDto::getSbbh));
        for(String key : mapList.keySet()){
            List<AlarmNumbersDto> listsTemp = mapList.get(key);
            if(!CollectionUtils.isEmpty(listsTemp)){
                AlarmNumbersDto firstEntity = listsTemp.get(0);
                String curDateStr = firstEntity.getFz();
                Integer bjsl = firstEntity.getAlarmNum();
                for(int i=1;i<listsTemp.size();i++){
                    AlarmNumbersDto entity = listsTemp.get(i);
                    AlarmNumbersDto beforeEntity = listsTemp.get(i-1);
                    String beforeDateStr = beforeEntity.getFz();
                    String nextDateStr = entity.getFz();
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
                            curDateStr = firstEntity.getFz();
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
                        curDateStr = firstEntity.getFz();
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
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("num",num);
        map.put("nnm",resultList.size());
        map.put("bnum",predationList.size());
        responseDto.setContent(map);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTodayService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/listsbbh")
    public ResponseDto listsbbh(@RequestBody EquipmentFileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTodayExample example = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria criteria = example.createCriteria();
        criteria.andSbbhEqualTo(pageDto.getSbbh());
        criteria.andTxtlxEqualTo("1");
        example.setOrderByClause(" cjsj desc ");
        List<EquipmentFileToday> equipmentFiles = equipmentFileTodayService.listAll(example);
        responseDto.setContent(equipmentFiles);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileTodayDto equipmentFileTodayDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileTodayDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileTodayDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFileTodayDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFileTodayDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFileTodayDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFileTodayDto.getJczl(), "监测种类1江豚2白海豚", 1, 1);
                ValidatorUtil.length(equipmentFileTodayDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFileTodayDto.getSm1(), "坐标原数据", 1, 500);
                ValidatorUtil.length(equipmentFileTodayDto.getSm2(), "坐标信息", 1, 255);
                ValidatorUtil.length(equipmentFileTodayDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTodayDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTodayDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTodayDto.getSm6(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTodayDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFileTodayDto.getTs(), "sm为2或3的时候才会有数据，表示头数", 1, 10);
                ValidatorUtil.length(equipmentFileTodayDto.getTxtlx(), "txt文件类型", 1, 5);
                ValidatorUtil.length(equipmentFileTodayDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileTodayService.save(equipmentFileTodayDto);
        responseDto.setContent(equipmentFileTodayDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTodayService.delete(id);
        return responseDto;
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

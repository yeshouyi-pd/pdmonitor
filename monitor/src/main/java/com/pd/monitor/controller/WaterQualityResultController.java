package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterQualityResult;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WaterQualityResultDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityResultService;
import com.pd.server.util.DateUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/waterQualityResult")
public class WaterQualityResultController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterQualityResultController.class);
    public static final String BUSINESS_NAME = "监测点设备管理";

    @Resource
    private WaterQualityResultService waterQualityResultService;

    @Resource
    private WaterEquipmentService waterEquipmentService;

    @PostMapping("/findWaterQualityResultByDay")
    public ResponseDto findWaterQualityResultByDay(@RequestBody WaterQualityResultDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> lists = getUpdeptcode(user.getDeptcode());
        WaterEquipment waterEquipment = waterEquipmentService.findById(pageDto.getDeviceId());
        WaterQualityResultExample example = new WaterQualityResultExample();
        WaterQualityResultExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(lists)&&lists.size()>0){
            ca.andSm1In(lists);
        }
        if(!StringUtils.isEmpty(waterEquipment)&&!StringUtils.isEmpty(waterEquipment.getCenterCode())){
            ca.andDatacenterEqualTo(waterEquipment.getCenterCode());
        }
        if(!StringUtils.isEmpty(waterEquipment)&&!StringUtils.isEmpty(waterEquipment.getSbsn())){
            ca.andIpEqualTo(waterEquipment.getSbsn());
        }
        if(!StringUtils.isEmpty(pageDto.getJcxm())){
            ca.andJcxmEqualTo(pageDto.getJcxm());
        }
        ca.andCreateTimeEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        example.setOrderByClause(" create_time ");
        List<WaterQualityResult> list = waterQualityResultService.findByExample(example);
        Map<String, List<WaterQualityResult>> map = list.stream().collect(Collectors.groupingBy(WaterQualityResult::getJcxm));
        Map<String, List<List<String>>> resultMap = new HashMap<>();
        for(String key:map.keySet()){
            List<WaterQualityResult> jcxmList = map.get(key);
            List<List<String>> innerList = new ArrayList<>();
            for(WaterQualityResult entity : jcxmList){
                List<String> obj = new ArrayList<>();
                obj.add(DateUtil.getFormatDate(entity.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                obj.add(entity.getDataResult());
                innerList.add(obj);
            }
            resultMap.put(key,innerList);
        }
        responseDto.setContent(resultMap);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaterQualityResultDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> lists = getUpdeptcode(user.getDeptcode());
        List<String> sbsns = new ArrayList<>();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(pageDto.getXmbh()))){
                sbsns = user.getXmbhsbsns().get(pageDto.getXmbh());
            }
        }
        waterQualityResultService.list(pageDto, lists, sbsns);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterQualityResultDto waterQualityResultDto) {
        // 保存校验
                ValidatorUtil.length(waterQualityResultDto.getIp(), "设备IP", 1, 20);
                ValidatorUtil.length(waterQualityResultDto.getDatacenter(), "数据中心", 1, 128);
                ValidatorUtil.length(waterQualityResultDto.getJcxm(), "检测项目", 1, 128);
                ValidatorUtil.length(waterQualityResultDto.getInstruct(), "指令", 1, 128);
                ValidatorUtil.length(waterQualityResultDto.getDataResult(), "检测数据", 1, 128);
                ValidatorUtil.length(waterQualityResultDto.getDataOriginal(), "原始数据", 1, 128);
                ValidatorUtil.length(waterQualityResultDto.getBz(), "备注", 1, 2000);
                ValidatorUtil.length(waterQualityResultDto.getSm1(), "", 1, 200);
                ValidatorUtil.length(waterQualityResultDto.getSm2(), "", 1, 200);
                ValidatorUtil.length(waterQualityResultDto.getSm3(), "", 1, 200);
                ValidatorUtil.length(waterQualityResultDto.getSm4(), "", 1, 200);

        ResponseDto responseDto = new ResponseDto();
        waterQualityResultService.save(waterQualityResultDto);
        responseDto.setContent(waterQualityResultDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterQualityResultService.delete(id);
        return responseDto;
    }

}

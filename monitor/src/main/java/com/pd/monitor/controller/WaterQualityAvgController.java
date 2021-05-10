package com.pd.monitor.controller;

import com.pd.server.main.domain.*;
import com.pd.server.main.dto.WaterQualityAvgDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WaterQualityResultDto;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityAvgService;
import com.pd.server.util.DateUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/waterQualityAvg")
public class WaterQualityAvgController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterQualityAvgController.class);
    public static final String BUSINESS_NAME = "数据平均值";

    @Resource
    private WaterQualityAvgService waterQualityAvgService;

    @Resource
    private WaterEquipmentService waterEquipmentService;

    @PostMapping("/findWaterQualityResultByMonth")
    public ResponseDto findWaterQualityResultByMonth(@RequestBody WaterQualityResultDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        WaterEquipment waterEquipment = waterEquipmentService.findById(pageDto.getDeviceId());
        WaterQualityAvgExample example = new WaterQualityAvgExample();
        WaterQualityAvgExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(waterEquipment)&&!StringUtils.isEmpty(waterEquipment.getCenterCode())){
            ca.andDatacenterEqualTo(waterEquipment.getCenterCode());
        }
        if(!StringUtils.isEmpty(waterEquipment)&&!StringUtils.isEmpty(waterEquipment.getSbsn())){
            ca.andIpEqualTo(waterEquipment.getSbsn());
        }
//        if(!StringUtils.isEmpty(waterEquipment)&&!StringUtils.isEmpty(waterEquipment.getPort())){
//            ca.andPortEqualTo(waterEquipment.getPort());
//        }
        if(!StringUtils.isEmpty(pageDto.getJcxm())){
            ca.andJcxmEqualTo(pageDto.getJcxm());
        }
        if(!StringUtils.isEmpty(pageDto.getChooseTimeType())&&pageDto.getChooseTimeType().equals("1")){
            ca.andCreateTimeBetween(DateUtil.getMonthBeforeOrLater(-1),new Date());
        }else if(!StringUtils.isEmpty(pageDto.getChooseTimeType())&&pageDto.getChooseTimeType().equals("2")){
            ca.andCreateTimeBetween(DateUtil.getNextNumDay(new Date(), -15),new Date());
        }else {
            ca.andCreateTimeBetween(DateUtil.getNextNumDay(new Date(), -7),new Date());
        }
        example.setOrderByClause(" create_time ");
        List<WaterQualityAvg> list = waterQualityAvgService.findByExample(example);
        List<List<String>> result = new ArrayList<>();
        for(WaterQualityAvg entity : list){
            List<String> obj = new ArrayList<>();
            obj.add(DateUtil.getFormatDate(entity.getCreateTime(),"yyyy-MM-dd"));
            obj.add(entity.getDataResult());
            result.add(obj);
        }
        responseDto.setContent(result);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        waterQualityAvgService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterQualityAvgDto waterQualityAvgDto) {
        // 保存校验
                ValidatorUtil.length(waterQualityAvgDto.getIp(), "设备IP", 1, 20);
                ValidatorUtil.length(waterQualityAvgDto.getDatacenter(), "数据中心", 1, 128);
                ValidatorUtil.length(waterQualityAvgDto.getJcxm(), "检测项目", 1, 128);
                ValidatorUtil.length(waterQualityAvgDto.getDataResult(), "检测数据", 1, 128);
                ValidatorUtil.length(waterQualityAvgDto.getSm1(), "", 1, 200);
                ValidatorUtil.length(waterQualityAvgDto.getSm2(), "", 1, 200);

        ResponseDto responseDto = new ResponseDto();
        waterQualityAvgService.save(waterQualityAvgDto);
        responseDto.setContent(waterQualityAvgDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterQualityAvgService.delete(id);
        return responseDto;
    }

}

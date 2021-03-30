package com.pd.monitor.controller;

import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterQualityResult;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.WaterQualityResultDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityResultService;
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
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/waterQualityResult")
public class WaterQualityResultController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterQualityResultController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private WaterQualityResultService waterQualityResultService;

    @Resource
    private WaterEquipmentService waterEquipmentService;

    @PostMapping("/findWaterQualityResultByMonth")
    public ResponseDto findWaterQualityResultByMonth(@RequestBody WaterQualityResultDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        WaterEquipment waterEquipment = waterEquipmentService.findById(pageDto.getDeviceId());
        WaterQualityResultExample example = new WaterQualityResultExample();
        WaterQualityResultExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(waterEquipment)&&!StringUtils.isEmpty(waterEquipment.getCenterCode())){
            ca.andDatacenterEqualTo(waterEquipment.getCenterCode());
        }
        if(!StringUtils.isEmpty(waterEquipment)&&!StringUtils.isEmpty(waterEquipment.getIp())){
            ca.andIpEqualTo(waterEquipment.getIp());
        }
        if(!StringUtils.isEmpty(waterEquipment)&&!StringUtils.isEmpty(waterEquipment.getPort())){
            ca.andPortEqualTo(waterEquipment.getPort());
        }
        if(!StringUtils.isEmpty(pageDto.getJcxm())){
            ca.andJcxmEqualTo(pageDto.getJcxm());
        }
        ca.andCreateTimeBetween(DateUtil.getMonthBeforeOrLater(-1),new Date());
        example.setOrderByClause(" create_time ");
        List<WaterQualityResult> list = waterQualityResultService.findByExample(example);
       List<List<String>> result = new ArrayList<>();
        for(WaterQualityResult entity : list){
            List<String> obj = new ArrayList<>();
            obj.add(DateUtil.getFormatDate(entity.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
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
    public ResponseDto list(@RequestBody WaterQualityResultDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        waterQualityResultService.list(pageDto);
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

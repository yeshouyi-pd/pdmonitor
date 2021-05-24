package com.pd.monitor.controller;

import com.pd.server.main.dto.WaterQualityDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WaterQualityService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/waterQuality")
public class WaterQualityController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterQualityController.class);
    public static final String BUSINESS_NAME = "多项监测数据表";

    @Resource
    private WaterQualityService waterQualityService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        waterQualityService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterQualityDto waterQualityDto) {
        // 保存校验
                ValidatorUtil.length(waterQualityDto.getSbsn(), "设备sn", 1, 30);
                ValidatorUtil.length(waterQualityDto.getIp(), "设备IP", 1, 30);
                ValidatorUtil.length(waterQualityDto.getDatacenter(), "数据中心", 1, 20);
                ValidatorUtil.length(waterQualityDto.getTemperature(), "温度", 1, 20);
                ValidatorUtil.length(waterQualityDto.getOxidative(), "溶解氧", 1, 20);
                ValidatorUtil.length(waterQualityDto.getSalinity(), "电导率/盐度", 1, 128);
                ValidatorUtil.length(waterQualityDto.getCod(), "COD", 1, 128);
                ValidatorUtil.length(waterQualityDto.getTurbidity(), "浊度", 1, 128);
                ValidatorUtil.length(waterQualityDto.getPh(), "PH值", 1, 20);
                ValidatorUtil.length(waterQualityDto.getTransparency(), "透明度", 1, 20);
                ValidatorUtil.length(waterQualityDto.getAd(), "氨氮", 1, 20);
                ValidatorUtil.length(waterQualityDto.getSuspended(), "悬浮物", 1, 20);
                ValidatorUtil.length(waterQualityDto.getPpm(), "余氯", 1, 20);
                ValidatorUtil.length(waterQualityDto.getChlFlu(), "氯离子、氟离子", 1, 20);
                ValidatorUtil.length(waterQualityDto.getChlorophyll(), "叶绿素", 1, 20);
                ValidatorUtil.length(waterQualityDto.getCyanobacteria(), "蓝绿藻", 1, 20);
                ValidatorUtil.length(waterQualityDto.getOrp(), "ORP氧化还原", 1, 20);
                ValidatorUtil.length(waterQualityDto.getOil(), "油", 1, 20);
                ValidatorUtil.length(waterQualityDto.getPhosphorus(), "总磷", 1, 20);
                ValidatorUtil.length(waterQualityDto.getFzr(), "负责人", 1, 128);
                ValidatorUtil.length(waterQualityDto.getBz(), "备注", 1, 2000);
                ValidatorUtil.length(waterQualityDto.getSm1(), "说明1", 1, 2000);
                ValidatorUtil.length(waterQualityDto.getSm2(), "", 1, 200);
                ValidatorUtil.length(waterQualityDto.getSm3(), "", 1, 200);
                ValidatorUtil.length(waterQualityDto.getSm4(), "", 1, 200);

        ResponseDto responseDto = new ResponseDto();
        waterQualityService.save(waterQualityDto);
        responseDto.setContent(waterQualityDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterQualityService.delete(id);
        return responseDto;
    }

}

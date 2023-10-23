package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.WaterQualityNew;
import com.pd.server.main.domain.WaterQualityNewExample;
import com.pd.server.main.dto.WaterQualityNewDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WaterQualityNewService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/waterQualityNew")
public class WaterQualityNewController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterQualityNewController.class);
    public static final String BUSINESS_NAME = "水质数据-新的";

    @Resource
    private WaterQualityNewService waterQualityNewService;

    @PostMapping("/getAllDataByTime")
    public ResponseDto getAllDataByTime(@RequestBody WaterQualityNewDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        WaterQualityNewExample waterQualityNewExample = new WaterQualityNewExample();
        WaterQualityNewExample.Criteria ca = waterQualityNewExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getBz())){
            ca.andBzEqualTo(pageDto.getBz());
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        waterQualityNewExample.setOrderByClause(" cjsj desc ");
        List<WaterQualityNew> waterQualityNewDtoList = waterQualityNewService.selectByExample(waterQualityNewExample);
        responseDto.setContent(waterQualityNewDtoList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaterQualityNewDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaterQualityNewExample waterQualityNewExample = new WaterQualityNewExample();
        WaterQualityNewExample.Criteria ca = waterQualityNewExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getBz())){
            ca.andBzEqualTo(pageDto.getBz());
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        waterQualityNewExample.setOrderByClause(" cjsj desc ");
        List<WaterQualityNew> waterQualityNewList = waterQualityNewService.selectByExample(waterQualityNewExample);
        PageInfo<WaterQualityNew> pageInfo = new PageInfo<>(waterQualityNewList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterQualityNewDto> waterQualityNewDtoList = CopyUtil.copyList(waterQualityNewList, WaterQualityNewDto.class);
        pageDto.setList(waterQualityNewDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterQualityNewDto waterQualityNewDto) {
        // 保存校验
                ValidatorUtil.length(waterQualityNewDto.getSbbh(), "", 1, 50);
                ValidatorUtil.length(waterQualityNewDto.getOxidative(), "溶解氧", 1, 50);
                ValidatorUtil.length(waterQualityNewDto.getChlorophyll(), "叶绿素", 1, 50);
                ValidatorUtil.length(waterQualityNewDto.getPh(), "ph", 1, 50);
                ValidatorUtil.length(waterQualityNewDto.getAd(), "氨氮", 1, 50);
                ValidatorUtil.length(waterQualityNewDto.getBz(), "", 1, 50);
                ValidatorUtil.length(waterQualityNewDto.getSm(), "", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        waterQualityNewService.save(waterQualityNewDto);
        responseDto.setContent(waterQualityNewDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterQualityNewService.delete(id);
        return responseDto;
    }

}

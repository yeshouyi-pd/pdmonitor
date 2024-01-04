package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.CurrentMeter;
import com.pd.server.main.domain.CurrentMeterExample;
import com.pd.server.main.dto.CurrentMeterDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.CurrentMeterService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/currentMeter")
public class CurrentMeterController {

    private static final Logger LOG = LoggerFactory.getLogger(CurrentMeterController.class);
    public static final String BUSINESS_NAME = "海流计数据";

    @Resource
    private CurrentMeterService currentMeterService;

    @PostMapping("/getAllDataByTime")
    public ResponseDto getAllDataByTime(@RequestBody CurrentMeterDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        CurrentMeterExample currentMeterExample = new CurrentMeterExample();
        CurrentMeterExample.Criteria ca = currentMeterExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getBz())){
            ca.andBzEqualTo(pageDto.getBz());
        }
        currentMeterExample.setOrderByClause(" cjsj desc ");
        List<CurrentMeter> currentMeterList = currentMeterService.selectByExample(currentMeterExample);
        responseDto.setContent(currentMeterList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody CurrentMeterDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CurrentMeterExample currentMeterExample = new CurrentMeterExample();
        CurrentMeterExample.Criteria ca = currentMeterExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getBz())){
            ca.andBzEqualTo(pageDto.getBz());
        }
        currentMeterExample.setOrderByClause(" cjsj desc ");
        List<CurrentMeter> currentMeterList = currentMeterService.selectByExample(currentMeterExample);
        PageInfo<CurrentMeter> pageInfo = new PageInfo<>(currentMeterList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CurrentMeterDto> currentMeterDtoList = CopyUtil.copyList(currentMeterList, CurrentMeterDto.class);
        pageDto.setList(currentMeterDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CurrentMeterDto currentMeterDto) {
        // 保存校验
                ValidatorUtil.length(currentMeterDto.getProductNumber(), "产品编号", 1, 50);
                ValidatorUtil.length(currentMeterDto.getSerialNumber(), "序列号", 1, 50);
                ValidatorUtil.length(currentMeterDto.getAbsSpeed(), "abs速度", 1, 50);
                ValidatorUtil.length(currentMeterDto.getDirection(), "方向", 1, 50);
                ValidatorUtil.length(currentMeterDto.getNorth(), "北", 1, 50);
                ValidatorUtil.length(currentMeterDto.getEast(), "东", 1, 50);
                ValidatorUtil.length(currentMeterDto.getHeading(), "朝向", 1, 50);
                ValidatorUtil.length(currentMeterDto.getTiltX(), "倾斜度X", 1, 50);
                ValidatorUtil.length(currentMeterDto.getTiltY(), "倾斜度Y", 1, 50);
                ValidatorUtil.length(currentMeterDto.getSpStd(), "Sp标准", 1, 50);
                ValidatorUtil.length(currentMeterDto.getStrength(), "力度", 1, 50);
                ValidatorUtil.length(currentMeterDto.getPingCount(), "平计数", 1, 50);
                ValidatorUtil.length(currentMeterDto.getAbsTilt(), " Abs倾斜度", 1, 50);
                ValidatorUtil.length(currentMeterDto.getMaxTilt(), "最大倾斜度", 1, 50);
                ValidatorUtil.length(currentMeterDto.getStdTilt(), "标准倾斜度", 1, 50);
                ValidatorUtil.length(currentMeterDto.getBz(), "备注", 1, 50);
                ValidatorUtil.length(currentMeterDto.getSm(), "说明", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        currentMeterService.save(currentMeterDto);
        responseDto.setContent(currentMeterDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        currentMeterService.delete(id);
        return responseDto;
    }

}

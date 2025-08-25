package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.SonarSingleTarget;
import com.pd.server.main.domain.SonarSingleTargetExample;
import com.pd.server.main.dto.SonarSingleTargetDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SonarSingleTargetService;
import com.pd.server.main.service.SonarSingleValueService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/sonarSingleTarget")
public class SonarSingleTargetController {

    private static final Logger LOG = LoggerFactory.getLogger(SonarSingleTargetController.class);
    public static final String BUSINESS_NAME = "单体目标信息";

    @Resource
    private SonarSingleTargetService sonarSingleTargetService;
    @Resource
    private SonarSingleValueService sonarSingleValueService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SonarSingleTargetDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SonarSingleTargetExample sonarSingleTargetExample = new SonarSingleTargetExample();
        SonarSingleTargetExample.Criteria ca = sonarSingleTargetExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andAcTimeGreaterThanOrEqualTo(pageDto.getStime()+" 00:00:00");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andAcTimeLessThanOrEqualTo(pageDto.getEtime()+" 23:59:59");
        }
        if(!StringUtils.isEmpty(pageDto.getDeviceCode())){
            ca.andDeviceCodeEqualTo(pageDto.getDeviceCode());
        }
        sonarSingleTargetExample.setOrderByClause(" ac_time desc ");
        List<SonarSingleTarget> sonarSingleTargetList = sonarSingleTargetService.selectByExample(sonarSingleTargetExample);
        PageInfo<SonarSingleTarget> pageInfo = new PageInfo<>(sonarSingleTargetList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SonarSingleTargetDto> sonarSingleTargetDtoList = CopyUtil.copyList(sonarSingleTargetList, SonarSingleTargetDto.class);
        for(SonarSingleTargetDto singleTargetDto : sonarSingleTargetDtoList){
            singleTargetDto.setSonarSingleValueList(sonarSingleValueService.selectBySingleTarget(singleTargetDto.getId()));
        }
        pageDto.setList(sonarSingleTargetDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SonarSingleTargetDto sonarSingleTargetDto) {
        // 保存校验
                ValidatorUtil.length(sonarSingleTargetDto.getDeviceCode(), "声呐换能器的编号", 1, 50);
                ValidatorUtil.length(sonarSingleTargetDto.getAcTime(), "采集时间", 1, 20);

        ResponseDto responseDto = new ResponseDto();
        sonarSingleTargetService.save(sonarSingleTargetDto);
        responseDto.setContent(sonarSingleTargetDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        sonarSingleTargetService.delete(id);
        return responseDto;
    }

}

package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.Turbidity;
import com.pd.server.main.domain.TurbidityExample;
import com.pd.server.main.dto.TurbidityDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.TurbidityService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/turbidity")
public class TurbidityController {

    private static final Logger LOG = LoggerFactory.getLogger(TurbidityController.class);
    public static final String BUSINESS_NAME = "温盐深浊度仪数据";

    @Resource
    private TurbidityService turbidityService;

    @PostMapping("/getAllDataByTime")
    public ResponseDto getAllDataByTime(@RequestBody TurbidityDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        TurbidityExample turbidityExample = new TurbidityExample();
        TurbidityExample.Criteria ca = turbidityExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andDateTimeGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andDateTimeLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        turbidityExample.setOrderByClause(" date_time desc ");
        List<Turbidity> turbidityList = turbidityService.selectByExample(turbidityExample);
        responseDto.setContent(turbidityList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody TurbidityDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        TurbidityExample turbidityExample = new TurbidityExample();
        TurbidityExample.Criteria ca = turbidityExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andDateTimeGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andDateTimeLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        turbidityExample.setOrderByClause(" date_time desc ");
        List<Turbidity> turbidityList = turbidityService.selectByExample(turbidityExample);
        PageInfo<Turbidity> pageInfo = new PageInfo<>(turbidityList);
        pageDto.setTotal(pageInfo.getTotal());
        List<TurbidityDto> turbidityDtoList = CopyUtil.copyList(turbidityList, TurbidityDto.class);
        pageDto.setList(turbidityDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody TurbidityDto turbidityDto) {
        // 保存校验
                ValidatorUtil.length(turbidityDto.getTurbidityH(), "浊度高量程", 1, 50);
                ValidatorUtil.length(turbidityDto.getTuribidityL(), "浊度低量程", 1, 50);
                ValidatorUtil.length(turbidityDto.getDepth(), "深度", 1, 50);
                ValidatorUtil.length(turbidityDto.getTemperature(), "温度", 1, 50);
                ValidatorUtil.length(turbidityDto.getConductivity(), "电导率", 1, 50);
                ValidatorUtil.length(turbidityDto.getSalinity(), "盐度", 1, 50);
                ValidatorUtil.length(turbidityDto.getBatVolt(), "电池电压", 1, 50);
                ValidatorUtil.length(turbidityDto.getBz(), "备注", 1, 50);
                ValidatorUtil.length(turbidityDto.getSm(), "说明", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        turbidityService.save(turbidityDto);
        responseDto.setContent(turbidityDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        turbidityService.delete(id);
        return responseDto;
    }

}

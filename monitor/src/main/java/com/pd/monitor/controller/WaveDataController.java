package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.WaveData;
import com.pd.server.main.domain.WaveDataExample;
import com.pd.server.main.dto.WaveDataDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WaveDataService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/waveData")
public class WaveDataController {

    private static final Logger LOG = LoggerFactory.getLogger(WaveDataController.class);
    public static final String BUSINESS_NAME = "波浪数据";

    @Resource
    private WaveDataService waveDataService;

    @PostMapping("/getAllDataByTime")
    public ResponseDto getAllDataByTime(@RequestBody WaveDataDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        WaveDataExample waveDataExample = new WaveDataExample();
        WaveDataExample.Criteria ca = waveDataExample.createCriteria();
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
        waveDataExample.setOrderByClause(" cjsj desc ");
        List<WaveData> waterQualityNewDtoList = waveDataService.selectByExample(waveDataExample);
        responseDto.setContent(waterQualityNewDtoList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaveDataDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaveDataExample waveDataExample = new WaveDataExample();
        WaveDataExample.Criteria ca = waveDataExample.createCriteria();
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
        waveDataExample.setOrderByClause(" cjsj desc ");
        List<WaveData> waveDataList = waveDataService.selectByExample(waveDataExample);
        PageInfo<WaveData> pageInfo = new PageInfo<>(waveDataList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaveDataDto> waveDataDtoList = CopyUtil.copyList(waveDataList, WaveDataDto.class);
        pageDto.setList(waveDataDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaveDataDto waveDataDto) {
        // 保存校验
                ValidatorUtil.length(waveDataDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(waveDataDto.getWaveH(), "有效波高(m)", 1, 50);
                ValidatorUtil.length(waveDataDto.getWaveDirection(), "波向(°)", 1, 50);
                ValidatorUtil.length(waveDataDto.getWavePeriod(), "波周期", 1, 50);
                ValidatorUtil.length(waveDataDto.getBz(), "备注", 1, 50);
                ValidatorUtil.length(waveDataDto.getSm(), "说明", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        waveDataService.save(waveDataDto);
        responseDto.setContent(waveDataDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waveDataService.delete(id);
        return responseDto;
    }

}

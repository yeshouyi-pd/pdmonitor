package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.MeteorologicalData;
import com.pd.server.main.domain.MeteorologicalDataExample;
import com.pd.server.main.dto.MeteorologicalDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.MeteorologicalDataService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/meteorologicalData")
public class MeteorologicalDataController {

    private static final Logger LOG = LoggerFactory.getLogger(MeteorologicalDataController.class);
    public static final String BUSINESS_NAME = "气象数据";

    @Resource
    private MeteorologicalDataService meteorologicalDataService;

    @PostMapping("/getAllDataByTime")
    public ResponseDto getAllDataByTime(@RequestBody MeteorologicalDataDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        MeteorologicalDataExample meteorologicalDataExample = new MeteorologicalDataExample();
        MeteorologicalDataExample.Criteria ca = meteorologicalDataExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        meteorologicalDataExample.setOrderByClause(" cjsj desc ");
        List<MeteorologicalData> meteorologicalDataList = meteorologicalDataService.selectByExample(meteorologicalDataExample);
        responseDto.setContent(meteorologicalDataList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody MeteorologicalDataDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        MeteorologicalDataExample meteorologicalDataExample = new MeteorologicalDataExample();
        MeteorologicalDataExample.Criteria ca = meteorologicalDataExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        meteorologicalDataExample.setOrderByClause(" cjsj desc ");
        List<MeteorologicalData> meteorologicalDataList = meteorologicalDataService.selectByExample(meteorologicalDataExample);
        PageInfo<MeteorologicalData> pageInfo = new PageInfo<>(meteorologicalDataList);
        pageDto.setTotal(pageInfo.getTotal());
        List<MeteorologicalDataDto> meteorologicalDataDtoList = CopyUtil.copyList(meteorologicalDataList, MeteorologicalDataDto.class);
        pageDto.setList(meteorologicalDataDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody MeteorologicalDataDto meteorologicalDataDto) {
        // 保存校验
        ValidatorUtil.length(meteorologicalDataDto.getBz(), "备注", 1, 50);
        ValidatorUtil.length(meteorologicalDataDto.getSm(), "说明", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        meteorologicalDataService.save(meteorologicalDataDto);
        responseDto.setContent(meteorologicalDataDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        meteorologicalDataService.delete(id);
        return responseDto;
    }

}

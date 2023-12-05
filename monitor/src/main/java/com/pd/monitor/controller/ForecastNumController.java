package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.ForecastNum;
import com.pd.server.main.domain.ForecastNumExample;
import com.pd.server.main.dto.ForecastNumDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.ForecastNumService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/forecastNum")
public class ForecastNumController {

    private static final Logger LOG = LoggerFactory.getLogger(ForecastNumController.class);
    public static final String BUSINESS_NAME = "江豚出现次数预测数据";

    @Resource
    private ForecastNumService forecastNumService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody ForecastNumDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ForecastNumExample forecastNumExample = new ForecastNumExample();
        ForecastNumExample.Criteria ca = forecastNumExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCxrqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCxrqLessThanOrEqualTo(pageDto.getEtime());
        }
        forecastNumExample.setOrderByClause(" cxsj desc ");
        List<ForecastNum> forecastNumList = forecastNumService.selectByExample(forecastNumExample);
        PageInfo<ForecastNum> pageInfo = new PageInfo<>(forecastNumList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ForecastNumDto> forecastNumDtoList = CopyUtil.copyList(forecastNumList, ForecastNumDto.class);
        pageDto.setList(forecastNumDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ForecastNumDto forecastNumDto) {
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBSNCENTERCODE);
        forecastNumDto.setCxrq(forecastNumDto.getCxsj().substring(0,10));
        forecastNumDto.setDepcode(map.get(forecastNumDto.getSbbh()));
        forecastNumService.save(forecastNumDto);
        responseDto.setContent(forecastNumDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        forecastNumService.delete(id);
        return responseDto;
    }

}

package com.pd.power.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.power.main.dto.ResponseDto;
import com.pd.power.main.domain.TdDataSec;
import com.pd.power.main.domain.TdDataSecExample;
import com.pd.power.main.dto.TdDataSecDto;
import com.pd.power.main.service.TdDataSecService;
import com.pd.power.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/tdDataSec")
public class TdDataSecController {

    private static final Logger LOG = LoggerFactory.getLogger(TdDataSecController.class);
    public static final String BUSINESS_NAME = "通道数据";

    @Resource
    private TdDataSecService tdDataSecService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody TdDataSecDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        TdDataSecExample tdDataSecExample = new TdDataSecExample();
        TdDataSecExample.Criteria ca = tdDataSecExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getIccid())){
            ca.andIccidEqualTo(pageDto.getIccid());
        }
        if(!StringUtils.isEmpty(pageDto.getIpport())){
            ca.andIpportEqualTo(pageDto.getIpport());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andSjdateGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andSjdateLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        tdDataSecExample.setOrderByClause("sjdate desc");
        List<TdDataSec> tdDataSecList = tdDataSecService.list(tdDataSecExample);
        PageInfo<TdDataSec> pageInfo = new PageInfo<>(tdDataSecList);
        pageDto.setTotal(pageInfo.getTotal());
        List<TdDataSecDto> tdDataSecDtoList = CopyUtil.copyList(tdDataSecList, TdDataSecDto.class);
        pageDto.setList(tdDataSecDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody TdDataSecDto tdDataSecDto) {
        ResponseDto responseDto = new ResponseDto();
        tdDataSecService.save(tdDataSecDto);
        responseDto.setContent(tdDataSecDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id) {
        ResponseDto responseDto = new ResponseDto();
        tdDataSecService.delete(id);
        return responseDto;
    }

}

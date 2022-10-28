package com.pd.power.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.power.main.dto.ResponseDto;
import com.pd.power.main.domain.DcbDataSec;
import com.pd.power.main.domain.DcbDataSecExample;
import com.pd.power.main.dto.DcbDataSecDto;
import com.pd.power.main.service.DcbDataSecService;
import com.pd.power.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/dcbDataSec")
public class DcbDataSecController {

    private static final Logger LOG = LoggerFactory.getLogger(DcbDataSecController.class);
    public static final String BUSINESS_NAME = "电压电流";

    @Resource
    private DcbDataSecService dcbDataSecService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody DcbDataSecDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DcbDataSecExample dcbDataSecExample = new DcbDataSecExample();
        DcbDataSecExample.Criteria ca = dcbDataSecExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getIpport())){
            ca.andIpportEqualTo(pageDto.getIpport());
        }
        if(!StringUtils.isEmpty(pageDto.getIccid())){
            ca.andIccidEqualTo(pageDto.getIccid());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andSjdateGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andSjdateLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        dcbDataSecExample.setOrderByClause("sjdate desc");
        List<DcbDataSec> dcbDataSecList = dcbDataSecService.list(dcbDataSecExample);
        PageInfo<DcbDataSec> pageInfo = new PageInfo<>(dcbDataSecList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DcbDataSecDto> dcbDataSecDtoList = CopyUtil.copyList(dcbDataSecList, DcbDataSecDto.class);
        pageDto.setList(dcbDataSecDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DcbDataSecDto dcbDataSecDto) {
        ResponseDto responseDto = new ResponseDto();
        dcbDataSecService.save(dcbDataSecDto);
        responseDto.setContent(dcbDataSecDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id) {
        ResponseDto responseDto = new ResponseDto();
        dcbDataSecService.delete(id);
        return responseDto;
    }

}

package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.DlbDataExample;
import com.pd.server.secondary.domain.DlbDataSec;
import com.pd.server.secondary.domain.DlbDataSecExample;
import com.pd.server.secondary.dto.DlbDataSecDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.secondary.service.DlbDataSecService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/dlbDataSec")
public class DlbDataSecController {

    private static final Logger LOG = LoggerFactory.getLogger(DlbDataSecController.class);
    public static final String BUSINESS_NAME = "设备温度";

    @Resource
    private DlbDataSecService dlbDataSecService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody DlbDataSecDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DlbDataSecExample dlbDataSecExample = new DlbDataSecExample();
        DlbDataSecExample.Criteria ca = dlbDataSecExample.createCriteria();
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
        dlbDataSecExample.setOrderByClause(" sjdate desc ");
        List<DlbDataSec> dlbDataSecList = dlbDataSecService.list(dlbDataSecExample);
        PageInfo<DlbDataSec> pageInfo = new PageInfo<>(dlbDataSecList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DlbDataSecDto> dlbDataSecDtoList = CopyUtil.copyList(dlbDataSecList, DlbDataSecDto.class);
        pageDto.setList(dlbDataSecDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DlbDataSecDto dlbDataSecDto) {
        ResponseDto responseDto = new ResponseDto();
        dlbDataSecService.save(dlbDataSecDto);
        responseDto.setContent(dlbDataSecDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id) {
        ResponseDto responseDto = new ResponseDto();
        dlbDataSecService.delete(id);
        return responseDto;
    }

}

package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.TdData;
import com.pd.server.main.domain.TdDataExample;
import com.pd.server.main.dto.TdDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.TdDataService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/tdData")
public class TdDataController {

    private static final Logger LOG = LoggerFactory.getLogger(TdDataController.class);
    public static final String BUSINESS_NAME = "通道数据";

    @Resource
    private TdDataService tdDataService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody TdDataDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        TdDataExample tdDataExample = new TdDataExample();
        TdDataExample.Criteria ca = tdDataExample.createCriteria();
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
        tdDataExample.setOrderByClause("sjdate desc");
        List<TdData> tdDataList = tdDataService.list(tdDataExample);
        PageInfo<TdData> pageInfo = new PageInfo<>(tdDataList);
        pageDto.setTotal(pageInfo.getTotal());
        List<TdDataDto> tdDataDtoList = CopyUtil.copyList(tdDataList, TdDataDto.class);
        pageDto.setList(tdDataDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody TdDataDto tdDataDto) {
        ResponseDto responseDto = new ResponseDto();
        tdDataService.save(tdDataDto);
        responseDto.setContent(tdDataDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id) {
        ResponseDto responseDto = new ResponseDto();
        tdDataService.delete(id);
        return responseDto;
    }

}

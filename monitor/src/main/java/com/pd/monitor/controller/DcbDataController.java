package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.DcbData;
import com.pd.server.main.domain.DcbDataExample;
import com.pd.server.main.dto.DcbDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DcbDataService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/dcbData")
public class DcbDataController {

    private static final Logger LOG = LoggerFactory.getLogger(DcbDataController.class);
    public static final String BUSINESS_NAME = "电压电流";

    @Resource
    private DcbDataService dcbDataService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody DcbDataDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DcbDataExample dcbDataExample = new DcbDataExample();
        DcbDataExample.Criteria ca = dcbDataExample.createCriteria();
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
        dcbDataExample.setOrderByClause("sjdate desc");
        List<DcbData> dcbDataList = dcbDataService.list(dcbDataExample);
        PageInfo<DcbData> pageInfo = new PageInfo<>(dcbDataList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DcbDataDto> dcbDataDtoList = CopyUtil.copyList(dcbDataList, DcbDataDto.class);
        pageDto.setList(dcbDataDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DcbDataDto dcbDataDto) {
        ResponseDto responseDto = new ResponseDto();
        dcbDataService.save(dcbDataDto);
        responseDto.setContent(dcbDataDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id) {
        ResponseDto responseDto = new ResponseDto();
        dcbDataService.delete(id);
        return responseDto;
    }

}

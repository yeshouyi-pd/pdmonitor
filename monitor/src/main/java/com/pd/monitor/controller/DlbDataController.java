package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.DlbData;
import com.pd.server.main.domain.DlbDataExample;
import com.pd.server.main.dto.DlbDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DlbDataService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/dlbData")
public class DlbDataController {

    private static final Logger LOG = LoggerFactory.getLogger(DlbDataController.class);
    public static final String BUSINESS_NAME = "设备温度";

    @Resource
    private DlbDataService dlbDataService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody DlbDataDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DlbDataExample dlbDataExample = new DlbDataExample();
        DlbDataExample.Criteria ca = dlbDataExample.createCriteria();
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
        dlbDataExample.setOrderByClause(" sjdate desc ");
        List<DlbData> dlbDataList = dlbDataService.list(dlbDataExample);
        PageInfo<DlbData> pageInfo = new PageInfo<>(dlbDataList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DlbDataDto> dlbDataDtoList = CopyUtil.copyList(dlbDataList, DlbDataDto.class);
        pageDto.setList(dlbDataDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DlbDataDto dlbDataDto) {
        ResponseDto responseDto = new ResponseDto();
        dlbDataService.save(dlbDataDto);
        responseDto.setContent(dlbDataDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id) {
        ResponseDto responseDto = new ResponseDto();
        dlbDataService.delete(id);
        return responseDto;
    }

}

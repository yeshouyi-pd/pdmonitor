package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.SeaSurfaceSalinity;
import com.pd.server.main.domain.SeaSurfaceSalinityExample;
import com.pd.server.main.dto.SeaSurfaceSalinityDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SeaSurfaceSalinityService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/seaSurfaceSalinity")
public class SeaSurfaceSalinityController {

    private static final Logger LOG = LoggerFactory.getLogger(SeaSurfaceSalinityController.class);
    public static final String BUSINESS_NAME = "海表盐度图";

    @Resource
    private SeaSurfaceSalinityService seaSurfaceSalinityService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SeaSurfaceSalinityDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SeaSurfaceSalinityExample seaSurfaceSalinityExample = new SeaSurfaceSalinityExample();
        SeaSurfaceSalinityExample.Criteria ca = seaSurfaceSalinityExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andTprqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andTprqLessThanOrEqualTo(pageDto.getEtime());
        }
        seaSurfaceSalinityExample.setOrderByClause(" tprq desc ");
        List<SeaSurfaceSalinity> seaSurfaceSalinityList = seaSurfaceSalinityService.selectByExample(seaSurfaceSalinityExample);
        PageInfo<SeaSurfaceSalinity> pageInfo = new PageInfo<>(seaSurfaceSalinityList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SeaSurfaceSalinityDto> seaSurfaceSalinityDtoList = CopyUtil.copyList(seaSurfaceSalinityList, SeaSurfaceSalinityDto.class);
        pageDto.setList(seaSurfaceSalinityDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SeaSurfaceSalinityDto seaSurfaceSalinityDto) {
        ResponseDto responseDto = new ResponseDto();
        seaSurfaceSalinityService.save(seaSurfaceSalinityDto);
        responseDto.setContent(seaSurfaceSalinityDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        seaSurfaceSalinityService.delete(id);
        return responseDto;
    }

}

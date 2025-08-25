package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.SonarEcho;
import com.pd.server.main.domain.SonarEchoExample;
import com.pd.server.main.dto.SonarEchoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SonarEchoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/sonarEcho")
public class SonarEchoController {

    private static final Logger LOG = LoggerFactory.getLogger(SonarEchoController.class);
    public static final String BUSINESS_NAME = "回波图信息";

    @Resource
    private SonarEchoService sonarEchoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SonarEchoDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SonarEchoExample sonarEchoExample = new SonarEchoExample();
        SonarEchoExample.Criteria ca = sonarEchoExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andAcTimeGreaterThanOrEqualTo(pageDto.getStime()+" 00:00:00");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andAcTimeLessThanOrEqualTo(pageDto.getEtime()+" 23:59:59");
        }
        if(!StringUtils.isEmpty(pageDto.getDeviceCode())){
            ca.andDeviceCodeEqualTo(pageDto.getDeviceCode());
        }
        if(!StringUtils.isEmpty(pageDto.getType()+"")){
            ca.andTypeEqualTo(pageDto.getType());
        }
        sonarEchoExample.setOrderByClause(" ac_time desc ");
        List<SonarEcho> sonarEchoList = sonarEchoService.selectByExample(sonarEchoExample);
        PageInfo<SonarEcho> pageInfo = new PageInfo<>(sonarEchoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SonarEchoDto> sonarEchoDtoList = CopyUtil.copyList(sonarEchoList, SonarEchoDto.class);
        pageDto.setList(sonarEchoDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SonarEchoDto sonarEchoDto) {
        // 保存校验
                ValidatorUtil.length(sonarEchoDto.getDeviceCode(), "声呐换能器的编号", 1, 50);
                ValidatorUtil.length(sonarEchoDto.getAcTime(), "采集时间", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        sonarEchoService.save(sonarEchoDto);
        responseDto.setContent(sonarEchoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        sonarEchoService.delete(id);
        return responseDto;
    }

}

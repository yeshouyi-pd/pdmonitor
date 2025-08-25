package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.SonarBiomass;
import com.pd.server.main.domain.SonarBiomassExample;
import com.pd.server.main.dto.SonarBiomassDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SonarBiomassService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/sonarBiomass")
public class SonarBiomassController {

    private static final Logger LOG = LoggerFactory.getLogger(SonarBiomassController.class);
    public static final String BUSINESS_NAME = "生物量数据";

    @Resource
    private SonarBiomassService sonarBiomassService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SonarBiomassDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SonarBiomassExample sonarBiomassExample = new SonarBiomassExample();
        SonarBiomassExample.Criteria ca = sonarBiomassExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andAcTimeGreaterThanOrEqualTo(pageDto.getStime()+" 00:00:00");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andAcTimeLessThanOrEqualTo(pageDto.getEtime()+" 23:59:59");
        }
        if(!StringUtils.isEmpty(pageDto.getDeviceCode())){
            ca.andDeviceCodeEqualTo(pageDto.getDeviceCode());
        }
        sonarBiomassExample.setOrderByClause(" ac_time desc ");
        List<SonarBiomass> sonarBiomassList = sonarBiomassService.selectByExample(sonarBiomassExample);
        PageInfo<SonarBiomass> pageInfo = new PageInfo<>(sonarBiomassList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SonarBiomassDto> sonarBiomassDtoList = CopyUtil.copyList(sonarBiomassList, SonarBiomassDto.class);
        pageDto.setList(sonarBiomassDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SonarBiomassDto sonarBiomassDto) {
        // 保存校验
                ValidatorUtil.length(sonarBiomassDto.getDeviceCode(), "声呐换能器的编号", 1, 50);
                ValidatorUtil.length(sonarBiomassDto.getAcTime(), "采集时间", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        sonarBiomassService.save(sonarBiomassDto);
        responseDto.setContent(sonarBiomassDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        sonarBiomassService.delete(id);
        return responseDto;
    }

}

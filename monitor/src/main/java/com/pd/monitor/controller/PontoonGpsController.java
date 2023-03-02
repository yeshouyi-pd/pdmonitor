package com.pd.monitor.controller;

import com.pd.server.main.domain.PontoonGpsExample;
import com.pd.server.main.dto.PontoonGpsDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.PontoonGpsService;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/pontoonGps")
public class PontoonGpsController {

    private static final Logger LOG = LoggerFactory.getLogger(PontoonGpsController.class);
    public static final String BUSINESS_NAME = "趸船gps";

    @Resource
    private PontoonGpsService pontoonGpsService;

    @PostMapping("/selectGps")
    public ResponseDto selectGps(@RequestBody PontoonGpsDto pontoonGpsDto){
        ResponseDto responseDto = new ResponseDto();
        PontoonGpsExample example = new PontoonGpsExample();
        PontoonGpsExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pontoonGpsDto.getSbbh())){
            ca.andSbbhEqualTo(pontoonGpsDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pontoonGpsDto.getRq())){
            ca.andRqEqualTo(pontoonGpsDto.getRq());
        }
        example.setOrderByClause(" cjsj desc ");
        List<String> gpsList = pontoonGpsService.selectGpsByExample(example);
        responseDto.setContent(gpsList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        pontoonGpsService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody PontoonGpsDto pontoonGpsDto) {
        // 保存校验
                ValidatorUtil.length(pontoonGpsDto.getSbbh(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getGps(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getRq(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getBz(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getSm(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getSm1(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getSm2(), "", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        pontoonGpsService.save(pontoonGpsDto);
        responseDto.setContent(pontoonGpsDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        pontoonGpsService.delete(id);
        return responseDto;
    }

}

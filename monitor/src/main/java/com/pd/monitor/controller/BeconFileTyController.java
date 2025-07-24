package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.BeconFile;
import com.pd.server.main.domain.BeconFileTy;
import com.pd.server.main.domain.BeconFileTyExample;
import com.pd.server.main.dto.BeconFileTyDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.BeconFileTyService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/beconFileTy")
public class BeconFileTyController {

    private static final Logger LOG = LoggerFactory.getLogger(BeconFileTyController.class);
    public static final String BUSINESS_NAME = "移动基站管理";

    @Resource
    private BeconFileTyService beconFileTyService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody BeconFileTyDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        BeconFileTyExample beconFileTyExample = new BeconFileTyExample();
        BeconFileTyExample.Criteria ca = beconFileTyExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        beconFileTyExample.setOrderByClause(" cjsj desc ");
        List<BeconFileTy> beconFileTyList = new ArrayList<>();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            beconFileTyList = beconFileTyService.selectByExampleSpecial(pageDto);
        }else{
            beconFileTyList = beconFileTyService.selectByExample(beconFileTyExample);
        }
        PageInfo<BeconFileTy> pageInfo = new PageInfo<>(beconFileTyList);
        pageDto.setTotal(pageInfo.getTotal());
        List<BeconFileTyDto> beconFileTyDtoList = CopyUtil.copyList(beconFileTyList, BeconFileTyDto.class);
        pageDto.setList(beconFileTyDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody BeconFileTyDto beconFileTyDto) {
        // 保存校验
                ValidatorUtil.length(beconFileTyDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(beconFileTyDto.getWjlj(), "文件路径", 1, 255);
                ValidatorUtil.length(beconFileTyDto.getXbid(), "信标id", 1, 500);
                ValidatorUtil.length(beconFileTyDto.getDeptcode(), "部门编号", 1, 50);
                ValidatorUtil.length(beconFileTyDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(beconFileTyDto.getGps(), "gps", 1, 100);
                ValidatorUtil.length(beconFileTyDto.getJd(), "经度", 1, 50);
                ValidatorUtil.length(beconFileTyDto.getWd(), "纬度", 1, 50);
                ValidatorUtil.length(beconFileTyDto.getSm(), "说明", 1, 100);
                ValidatorUtil.length(beconFileTyDto.getBz(), "备注", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        beconFileTyService.save(beconFileTyDto);
        responseDto.setContent(beconFileTyDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        beconFileTyService.delete(id);
        return responseDto;
    }

}

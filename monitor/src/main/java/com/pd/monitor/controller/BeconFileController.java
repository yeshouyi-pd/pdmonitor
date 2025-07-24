package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.BeconFile;
import com.pd.server.main.domain.BeconFileExample;
import com.pd.server.main.domain.SpaceFile;
import com.pd.server.main.dto.BeconFileDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.BeconFileService;
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
@RequestMapping("/admin/beconFile")
public class BeconFileController {

    private static final Logger LOG = LoggerFactory.getLogger(BeconFileController.class);
    public static final String BUSINESS_NAME = "定点基站管理";

    @Resource
    private BeconFileService beconFileService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody BeconFileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        BeconFileExample beconFileExample = new BeconFileExample();
        BeconFileExample.Criteria ca = beconFileExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getXbid())){
            ca.andXbidEqualTo(pageDto.getXbid());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        beconFileExample.setOrderByClause(" cjsj desc ");
        List<BeconFile> beconFileList = new ArrayList<>();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            beconFileList = beconFileService.selectByExampleSpecial(pageDto);
        }else{
            beconFileList = beconFileService.selectByExample(beconFileExample);
        }
        PageInfo<BeconFile> pageInfo = new PageInfo<>(beconFileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<BeconFileDto> beconFileDtoList = CopyUtil.copyList(beconFileList, BeconFileDto.class);
        pageDto.setList(beconFileDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody BeconFileDto beconFileDto) {
        // 保存校验
                ValidatorUtil.length(beconFileDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(beconFileDto.getWjlj(), "文件路径", 1, 255);
                ValidatorUtil.length(beconFileDto.getXbid(), "信标id", 1, 50);
                ValidatorUtil.length(beconFileDto.getDeptcode(), "部门编号", 1, 50);
                ValidatorUtil.length(beconFileDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(beconFileDto.getBz(), "备注", 1, 100);
                ValidatorUtil.length(beconFileDto.getSm(), "说明", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        beconFileService.save(beconFileDto);
        responseDto.setContent(beconFileDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        beconFileService.delete(id);
        return responseDto;
    }

}

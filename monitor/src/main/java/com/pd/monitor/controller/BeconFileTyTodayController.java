package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.BeconFileTyToday;
import com.pd.server.main.domain.BeconFileTyTodayExample;
import com.pd.server.main.dto.BeconFileTyTodayDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.BeconFileTyTodayService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/beconFileTyToday")
public class BeconFileTyTodayController {

    private static final Logger LOG = LoggerFactory.getLogger(BeconFileTyTodayController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private BeconFileTyTodayService beconFileTyTodayService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody BeconFileTyTodayDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        BeconFileTyTodayExample beconFileTyTodayExample = new BeconFileTyTodayExample();
        BeconFileTyTodayExample.Criteria ca = beconFileTyTodayExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        beconFileTyTodayExample.setOrderByClause(" cjsj desc ");
        List<BeconFileTyToday> beconFileTyTodayList = beconFileTyTodayService.selectByExample(beconFileTyTodayExample);
        PageInfo<BeconFileTyToday> pageInfo = new PageInfo<>(beconFileTyTodayList);
        pageDto.setTotal(pageInfo.getTotal());
        List<BeconFileTyTodayDto> beconFileTyTodayDtoList = CopyUtil.copyList(beconFileTyTodayList, BeconFileTyTodayDto.class);
        pageDto.setList(beconFileTyTodayDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody BeconFileTyTodayDto beconFileTyTodayDto) {
        // 保存校验
                ValidatorUtil.length(beconFileTyTodayDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(beconFileTyTodayDto.getWjlj(), "文件路径", 1, 255);
                ValidatorUtil.length(beconFileTyTodayDto.getXbid(), "信标id", 1, 500);
                ValidatorUtil.length(beconFileTyTodayDto.getDeptcode(), "部门编号", 1, 50);
                ValidatorUtil.length(beconFileTyTodayDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(beconFileTyTodayDto.getGps(), "gps", 1, 100);
                ValidatorUtil.length(beconFileTyTodayDto.getJd(), "经度", 1, 50);
                ValidatorUtil.length(beconFileTyTodayDto.getWd(), "纬度", 1, 50);
                ValidatorUtil.length(beconFileTyTodayDto.getSm(), "说明", 1, 100);
                ValidatorUtil.length(beconFileTyTodayDto.getBz(), "备注", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        beconFileTyTodayService.save(beconFileTyTodayDto);
        responseDto.setContent(beconFileTyTodayDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        beconFileTyTodayService.delete(id);
        return responseDto;
    }

}

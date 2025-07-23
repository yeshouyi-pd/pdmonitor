package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.BeconFileToday;
import com.pd.server.main.domain.BeconFileTodayExample;
import com.pd.server.main.dto.BeconFileTodayDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.BeconFileTodayService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/beconFileToday")
public class BeconFileTodayController {

    private static final Logger LOG = LoggerFactory.getLogger(BeconFileTodayController.class);
    public static final String BUSINESS_NAME = "定点基站管理(当日数据)";

    @Resource
    private BeconFileTodayService beconFileTodayService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody BeconFileTodayDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        BeconFileTodayExample beconFileTodayExample = new BeconFileTodayExample();
        BeconFileTodayExample.Criteria ca = beconFileTodayExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        beconFileTodayExample.setOrderByClause(" cjsj desc ");
        List<BeconFileToday> beconFileTodayList = beconFileTodayService.selectByExample(beconFileTodayExample);
        PageInfo<BeconFileToday> pageInfo = new PageInfo<>(beconFileTodayList);
        pageDto.setTotal(pageInfo.getTotal());
        List<BeconFileTodayDto> beconFileTodayDtoList = CopyUtil.copyList(beconFileTodayList, BeconFileTodayDto.class);
        pageDto.setList(beconFileTodayDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody BeconFileTodayDto beconFileTodayDto) {
        // 保存校验
                ValidatorUtil.length(beconFileTodayDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(beconFileTodayDto.getWjlj(), "文件路径", 1, 255);
                ValidatorUtil.length(beconFileTodayDto.getXbid(), "信标id", 1, 50);
                ValidatorUtil.length(beconFileTodayDto.getDeptcode(), "部门编号", 1, 50);
                ValidatorUtil.length(beconFileTodayDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(beconFileTodayDto.getBz(), "备注", 1, 100);
                ValidatorUtil.length(beconFileTodayDto.getSm(), "说明", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        beconFileTodayService.save(beconFileTodayDto);
        responseDto.setContent(beconFileTodayDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        beconFileTodayService.delete(id);
        return responseDto;
    }

}

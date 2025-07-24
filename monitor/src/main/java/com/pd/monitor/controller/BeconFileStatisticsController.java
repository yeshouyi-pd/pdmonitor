package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.BeconFileStatistics;
import com.pd.server.main.domain.BeconFileStatisticsExample;
import com.pd.server.main.dto.BeconFileStatisticsDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.BeconFileStatisticsService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/beconFileStatistics")
public class BeconFileStatisticsController {

    private static final Logger LOG = LoggerFactory.getLogger(BeconFileStatisticsController.class);
    public static final String BUSINESS_NAME = "定点基站统计";

    @Resource
    private BeconFileStatisticsService beconFileStatisticsService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody BeconFileStatisticsDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        BeconFileStatisticsExample example = new BeconFileStatisticsExample();
        BeconFileStatisticsExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXbid())){
            ca.andXbidEqualTo(pageDto.getXbid());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        example.setOrderByClause(" rq desc ");
        List<BeconFileStatistics> beconFileStatisticsList = beconFileStatisticsService.selectByExample(example);
        PageInfo<BeconFileStatistics> pageInfo = new PageInfo<>(beconFileStatisticsList);
        pageDto.setTotal(pageInfo.getTotal());
        List<BeconFileStatisticsDto> beconFileStatisticsDtoList = CopyUtil.copyList(beconFileStatisticsList, BeconFileStatisticsDto.class);
        pageDto.setList(beconFileStatisticsDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody BeconFileStatisticsDto beconFileStatisticsDto) {
        // 保存校验
                ValidatorUtil.length(beconFileStatisticsDto.getXbid(), "信标id", 1, 10);
                ValidatorUtil.length(beconFileStatisticsDto.getSbbhs(), "设备编号合集", 1, 500);
                ValidatorUtil.length(beconFileStatisticsDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(beconFileStatisticsDto.getSbmcs(), "设备名称合集", 1, 500);
                ValidatorUtil.length(beconFileStatisticsDto.getCjsjs(), "时间合集", 1, 500);
                ValidatorUtil.length(beconFileStatisticsDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(beconFileStatisticsDto.getSm(), "说明", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        beconFileStatisticsService.save(beconFileStatisticsDto);
        responseDto.setContent(beconFileStatisticsDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        beconFileStatisticsService.delete(id);
        return responseDto;
    }

}

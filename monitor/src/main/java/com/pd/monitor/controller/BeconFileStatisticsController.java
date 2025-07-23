package com.pd.monitor.controller;

import com.pd.server.main.dto.BeconFileStatisticsDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.BeconFileStatisticsService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/beconFileStatistics")
public class BeconFileStatisticsController {

    private static final Logger LOG = LoggerFactory.getLogger(BeconFileStatisticsController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private BeconFileStatisticsService beconFileStatisticsService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        beconFileStatisticsService.list(pageDto);
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

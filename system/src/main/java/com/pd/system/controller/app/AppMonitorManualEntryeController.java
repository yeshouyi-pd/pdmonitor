package com.pd.system.controller.app;

import com.pd.server.main.dto.AppMonitorManualEntryeDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppMonitorManualEntryeService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/appMonitorManualEntrye")
public class AppMonitorManualEntryeController {

    private static final Logger LOG = LoggerFactory.getLogger(AppMonitorManualEntryeController.class);
    public static final String BUSINESS_NAME = "人工录入数据";

    @Resource
    private AppMonitorManualEntryeService appMonitorManualEntryeService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        appMonitorManualEntryeService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppMonitorManualEntryeDto appMonitorManualEntryeDto) {
        // 保存校验
                ValidatorUtil.require(appMonitorManualEntryeDto.getMid(), "");
                ValidatorUtil.length(appMonitorManualEntryeDto.getMid(), "", 1, 32);
                ValidatorUtil.length(appMonitorManualEntryeDto.getPZfgcz(), "左方观察者", 1, 100);
                ValidatorUtil.length(appMonitorManualEntryeDto.getPJlz(), "记录者", 1, 100);
                ValidatorUtil.length(appMonitorManualEntryeDto.getPYfgcz(), "右方观察者", 1, 100);
                ValidatorUtil.length(appMonitorManualEntryeDto.getPDlgcz(), "独立观察者", 1, 100);
                ValidatorUtil.length(appMonitorManualEntryeDto.getPDlgczsyff(), "独立观察者所用方法", 1, 100);
                ValidatorUtil.length(appMonitorManualEntryeDto.getPFjgcz(), "附加观察者", 1, 100);
                ValidatorUtil.length(appMonitorManualEntryeDto.getWZtgczk(), "总体观察状况", 1, 30);
                ValidatorUtil.length(appMonitorManualEntryeDto.getWXgszfw(), "眩光所在范围", 1, 30);
                ValidatorUtil.length(appMonitorManualEntryeDto.getWXgzk(), "眩光状况", 1, 30);
                ValidatorUtil.length(appMonitorManualEntryeDto.getNJajl(), "近岸距离(米)", 1, 10);
                ValidatorUtil.length(appMonitorManualEntryeDto.getNSs(), "水深(米)", 1, 10);
                ValidatorUtil.length(appMonitorManualEntryeDto.getNCs(), "船速", 1, 10);
                ValidatorUtil.length(appMonitorManualEntryeDto.getNJafx(), "近岸方向", 1, 10);
                ValidatorUtil.length(appMonitorManualEntryeDto.getNFx(), "方向", 1, 10);
                ValidatorUtil.length(appMonitorManualEntryeDto.getVYcsl(), "渔船数量", 1, 10);
                ValidatorUtil.length(appMonitorManualEntryeDto.getVDcsl(), "大船数量", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        appMonitorManualEntryeService.save(appMonitorManualEntryeDto);
        responseDto.setContent(appMonitorManualEntryeDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        appMonitorManualEntryeService.delete(id);
        return responseDto;
    }

}

package com.pd.system.controller.app;

import com.pd.server.main.dto.AppMonitorInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppMonitorInfoService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/appMonitorInfo")
public class AppMonitorInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(AppMonitorInfoController.class);
    public static final String BUSINESS_NAME = "观察周期";

    @Resource
    private AppMonitorInfoService appMonitorInfoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        appMonitorInfoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppMonitorInfoDto appMonitorInfoDto) {
        // 保存校验
                ValidatorUtil.length(appMonitorInfoDto.getKcclx(), "考察船类型", 1, 30);
                ValidatorUtil.length(appMonitorInfoDto.getGczxm(), "观察者姓名", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getGcqy(), "考察区域", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getGcdw(), "考察单位", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPZfgcz(), "左方观察者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPJlz(), "记录者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPYfgcz(), "右方观察者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPDlgcz(), "独立观察者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPDlgczsyff(), "独立观察者所用方法", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPFjgcz(), "附加观察者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getWZtgczk(), "总体观察状况", 1, 30);
                ValidatorUtil.length(appMonitorInfoDto.getWXgszfw(), "眩光所在范围", 1, 30);
                ValidatorUtil.length(appMonitorInfoDto.getWXgzk(), "眩光状况", 1, 30);
                ValidatorUtil.length(appMonitorInfoDto.getNJajl(), "近岸距离(米)", 1, 10);
                ValidatorUtil.length(appMonitorInfoDto.getNSs(), "水深(米)", 1, 10);
                ValidatorUtil.length(appMonitorInfoDto.getNCs(), "船速", 1, 10);
                ValidatorUtil.length(appMonitorInfoDto.getNJafx(), "近岸方向", 1, 10);
                ValidatorUtil.length(appMonitorInfoDto.getNFx(), "方向", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        appMonitorInfoService.save(appMonitorInfoDto);
        responseDto.setContent(appMonitorInfoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        appMonitorInfoService.delete(id);
        return responseDto;
    }

}

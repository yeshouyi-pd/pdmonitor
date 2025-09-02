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
@RequestMapping("/admin/appMonitorInfo")
public class AppMonitorInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(AppMonitorInfoController.class);
    public static final String BUSINESS_NAME = "观察周期";

    @Resource
    private AppMonitorInfoService appMonitorInfoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody  AppMonitorInfoDto appMonitorInfoDto) {
        ResponseDto responseDto = new ResponseDto();
        PageDto pageDto = appMonitorInfoService.list(appMonitorInfoDto);
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
                ValidatorUtil.length(appMonitorInfoDto.getPzfgcz(), "左方观察者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPjlz(), "记录者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPyfgcz(), "右方观察者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPdlgcz(), "独立观察者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPdlgczsyff(), "独立观察者所用方法", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getPfjgcz(), "附加观察者", 1, 100);
                ValidatorUtil.length(appMonitorInfoDto.getWztgczk(), "总体观察状况", 1, 30);
                ValidatorUtil.length(appMonitorInfoDto.getWxgszfw(), "眩光所在范围", 1, 30);
                ValidatorUtil.length(appMonitorInfoDto.getWxgzk(), "眩光状况", 1, 30);
                ValidatorUtil.length(appMonitorInfoDto.getNjajl(), "近岸距离(米)", 1, 10);
                ValidatorUtil.length(appMonitorInfoDto.getNss(), "水深(米)", 1, 10);
                ValidatorUtil.length(appMonitorInfoDto.getNcs(), "船速", 1, 10);
                ValidatorUtil.length(appMonitorInfoDto.getNjafx(), "近岸方向", 1, 10);
                ValidatorUtil.length(appMonitorInfoDto.getNfx(), "方向", 1, 10);
                ValidatorUtil.length(appMonitorInfoDto.getDeptcode(), "登录用户所在部门", 1, 100);

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

package com.pd.system.controller.app;


import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.AppCodeSet;
import com.pd.server.main.domain.AppCodeType;
import com.pd.server.main.domain.AppMonitorInfo;
import com.pd.server.main.domain.AppVersion;
import com.pd.server.main.dto.AppMonitorDiscoveryDto;
import com.pd.server.main.dto.AppMonitorInfoDto;
import com.pd.server.main.dto.AppMonitorManualEntryeDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.*;
import com.pd.system.controller.conf.HttpResult;
import com.pd.system.controller.conf.RedisConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/ob")
public class AppObserverController {

    @Resource
    private AppVersionService appVersionService;

    @Resource
    private AppCodeSetService appCodeSetService;

    @Resource
    private AppCodeTypeService appCodeTypeService;

    @Resource
    private AppMonitorInfoService appMonitorInfoService;

    @Resource
    private AppMonitorDiscoveryService appMonitorDiscoveryService;

    @Resource
    private AppMonitorManualEntryeService appMonitorManualEntryeService;


    /**
     * 获取版本信息
     * @return
     */
    @GetMapping("/getVersion")
    public HttpResult getVersion() {
        List<AppVersion> versions = appVersionService.selectByExample();
        if (!versions.isEmpty()) {
            AppVersion version = versions.get(0);
            return HttpResult.ok(version);
        }
        return HttpResult.error("查询失败");
    }

    /**
     * 查询app Code set 配置
     * @return
     */
    @GetMapping("/getAppCodeSet")
    public HttpResult getAppCodeSet() {
        List<AppCodeSet> list = appCodeSetService.selectByExample();
        try {
            return HttpResult.ok(list);
        }catch (Exception e){
            return HttpResult.error("查询失败");
        }

    }

    /**
     * 查询app Code type 配置
     * @return
     */
    @GetMapping("/getAppCodeType")
    public HttpResult getAppCodeType() {
        List<AppCodeType> list = appCodeTypeService.selectByExample();
        try {
            return HttpResult.ok(list);
        }catch (Exception e){
            return HttpResult.error("查询失败");
        }

    }


    /**
     * 上传开始和结束信息
     * @param appMonitorInfoDto
     * @return
     */

    @PostMapping("/uploadMonitorInfo")
    public HttpResult uploadMonitorInfo(@RequestBody AppMonitorInfoDto appMonitorInfoDto) {
        if(StringUtils.isBlank(appMonitorInfoDto.getId()) || StringUtils.isBlank(appMonitorInfoDto.getDeptcode())){
            return HttpResult.error("上传参数异常");
        }
        try {
            appMonitorInfoService.save(appMonitorInfoDto);
            return HttpResult.ok();
        }catch (Exception e){
            return HttpResult.error("上传失败");
        }
    }

    /**
     * 上传10分钟 N 信息
     * 上传 30 分钟P  V  W  N 信息
     * 人工观察信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    @PostMapping("/uploadManualEntrye")
    public HttpResult uploadMonituploadManualEntryeorInfo(@RequestBody AppMonitorManualEntryeDto appMonitorManualEntryeDto) {
        if(StringUtils.isBlank(appMonitorManualEntryeDto.getMid()) || StringUtils.isBlank(appMonitorManualEntryeDto.getId())){
            return HttpResult.error("上传参数异常");
        }
        try {
            appMonitorManualEntryeService.save(appMonitorManualEntryeDto);
            return HttpResult.ok();
        }catch (Exception e){
            return HttpResult.error("上传失败");
        }
    }

    /**
     * 获取发现江豚信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    @PostMapping("/uploadMonitorDiscovery")
    public HttpResult uploadMonitorDiscovery(@RequestBody AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        if(StringUtils.isBlank(appMonitorDiscoveryDto.getMid()) || StringUtils.isBlank(appMonitorDiscoveryDto.getId())){
            return HttpResult.error("上传参数异常");
        }
        try {
            appMonitorDiscoveryService.save(appMonitorDiscoveryDto);
            return HttpResult.ok();
        }catch (Exception e){
            return HttpResult.error("上传失败");
        }
    }





}

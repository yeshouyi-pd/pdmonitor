package com.pd.system.controller.app;


import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.AppCodeSet;
import com.pd.server.main.domain.AppCodeType;
import com.pd.server.main.domain.AppVersion;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppCodeSetService;
import com.pd.server.main.service.AppCodeTypeService;
import com.pd.server.main.service.AppVersionService;
import com.pd.system.controller.conf.HttpResult;
import com.pd.system.controller.conf.RedisConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //上传查验开始结束信息




    //上传10分钟 N 信息


    //上传 30 分钟P  V  W  N 信息




}

package com.pd.system.controller.app;

import com.pd.server.main.dto.AppMonitorDiscoveryDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppMonitorDiscoveryService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/appMonitorDiscovery")
public class AppMonitorDiscoveryController {

    private static final Logger LOG = LoggerFactory.getLogger(AppMonitorDiscoveryController.class);
    public static final String BUSINESS_NAME = "发现江豚信息";

    @Resource
    private AppMonitorDiscoveryService appMonitorDiscoveryService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        appMonitorDiscoveryService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        // 保存校验
                ValidatorUtil.length(appMonitorDiscoveryDto.getMid(), "观察周期主表ID", 1, 36);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSJl(), "观察者与动物间距离(米)", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSGcz(), "观察者姓名", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSFw(), "方位:船首为0度，左方为负值", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSFf(), "方法", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSWzm(), "物种名", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSQtZx(), "群体大小-最少", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSQtZy(), "群体大小-最优", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSQtZd(), "群体大小-最多", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getCZsbh(), "注释编码", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getHZjajl(), "动物距最近岸的距离", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getHMztds(), "母子豚对数", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getHXwms(), "行为描述", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getHHjlxdm(), "环境类型代码", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        appMonitorDiscoveryService.save(appMonitorDiscoveryDto);
        responseDto.setContent(appMonitorDiscoveryDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        appMonitorDiscoveryService.delete(id);
        return responseDto;
    }

}

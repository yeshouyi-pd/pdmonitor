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

    // 新增：引入下列服务用于根据 mid 取关联详情
    @Resource
    private com.pd.server.main.service.AppMonitorInfoService appMonitorInfoService;
    @Resource
    private com.pd.server.main.service.AppMonitorManualEntryeService appMonitorManualEntryeService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        ResponseDto responseDto = new ResponseDto();
        PageDto pageDto = appMonitorDiscoveryService.list(appMonitorDiscoveryDto);
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
                ValidatorUtil.length(appMonitorDiscoveryDto.getSjl(), "观察者与动物间距离(米)", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSgcz(), "观察者姓名", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSfw(), "方位:船首为0度，左方为负值", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSff(), "方法", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSwzm(), "物种名", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSqtzx(), "群体大小-最少", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSqtzy(), "群体大小-最优", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getSqtzd(), "群体大小-最多", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getCzsbh(), "注释编码", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getHzjajl(), "动物距最近岸的距离", 1, 100);
                ValidatorUtil.length(appMonitorDiscoveryDto.getHmztds(), "母子豚对数", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getHxwms(), "行为描述", 1, 10);
                ValidatorUtil.length(appMonitorDiscoveryDto.getHhjlxdm(), "环境类型代码", 1, 10);

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

    /**
     * 根据 mid 获取关联详情（AppMonitorInfo 基本信息 + 该 mid 下的发现与人工观察列表）
     */
    @GetMapping("/getDetailByMid/{mid}")
    public ResponseDto getDetailByMid(@PathVariable String mid) {
        ResponseDto responseDto = new ResponseDto();
        try {
            com.pd.server.main.domain.AppMonitorInfo appMonitorInfo = appMonitorInfoService.findById(mid);
            if (appMonitorInfo == null) {
                responseDto.setSuccess(false);
                responseDto.setMessage("未找到对应的观察周期记录");
                return responseDto;
            }
            com.pd.server.main.dto.AppMonitorInfoDto appMonitorInfoDto = com.pd.server.util.CopyUtil.copy(appMonitorInfo, com.pd.server.main.dto.AppMonitorInfoDto.class);
            java.util.List<com.pd.server.main.dto.AppMonitorDiscoveryDto> discoveryList = appMonitorDiscoveryService.findByMidOrderByFxsjDesc(mid);
            java.util.List<com.pd.server.main.dto.AppMonitorManualEntryeDto> manualEntryeList = appMonitorManualEntryeService.findByMidOrderByKsgcsjDesc(mid);

            java.util.Map<String, Object> detailData = new java.util.HashMap<>();
            detailData.put("appMonitorInfo", appMonitorInfoDto);
            detailData.put("discoveryList", discoveryList);
            detailData.put("manualEntryeList", manualEntryeList);
            responseDto.setContent(detailData);
        } catch (Exception e) {
            LOG.error("根据mid获取详情失败", e);
            responseDto.setSuccess(false);
            responseDto.setMessage("获取详情失败");
        }
        return responseDto;
    }
}

package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.SolarPannel;
import com.pd.server.main.domain.SolarPannelExample;
import com.pd.server.main.dto.SolarPannelDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SolarPannelService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/solarPannel")
public class SolarPannelController {

    private static final Logger LOG = LoggerFactory.getLogger(SolarPannelController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private SolarPannelService solarPannelService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SolarPannelDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SolarPannelExample solarPannelExample = new SolarPannelExample();
        SolarPannelExample.Criteria ca = solarPannelExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getDeviceId())){
            ca.andDeviceIdEqualTo(pageDto.getDeviceId());
        }
        if(!StringUtils.isEmpty(pageDto.getDeviceName())){
            ca.andDeviceNameEqualTo(pageDto.getDeviceName());
        }
        if(!StringUtils.isEmpty(pageDto.getDeviceNumber())){
            ca.andDeviceNumberEqualTo(pageDto.getDeviceNumber());
        }
        solarPannelExample.setOrderByClause(" update_time desc ");
        List<SolarPannel> solarPannelList = solarPannelService.list(solarPannelExample);
        PageInfo<SolarPannel> pageInfo = new PageInfo<>(solarPannelList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SolarPannelDto> solarPannelDtoList = CopyUtil.copyList(solarPannelList, SolarPannelDto.class);
        pageDto.setList(solarPannelDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SolarPannelDto solarPannelDto) {
        // 保存校验
                ValidatorUtil.length(solarPannelDto.getDel(), "删除标志 1 删除 0 未删除", 1, 10);
                ValidatorUtil.length(solarPannelDto.getDeviceId(), "设备号", 1, 10);
                ValidatorUtil.length(solarPannelDto.getChannelId(), "channelId", 1, 10);
                ValidatorUtil.length(solarPannelDto.getDeviceNumber(), "设备编号", 1, 20);
                ValidatorUtil.length(solarPannelDto.getDeviceName(), "设备名称", 1, 20);
                ValidatorUtil.length(solarPannelDto.getPassword(), "设备密码", 1, 50);
                ValidatorUtil.length(solarPannelDto.getUserName(), "用户名", 1, 10);
                ValidatorUtil.length(solarPannelDto.getTemperature(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getBatteryVoltage(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getDailyElectricityConsumption(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getMonthlyElectricityConsumption(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getDailyCharge(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getMonthlyCharge(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getBatteryCurrent(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getSolarPanelVoltage(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getSolarPannelCurrent(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getPowerGeneration(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getLoadVoltage(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getLoadCurrent(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getBatteryPercent(), "", 1, 20);
                ValidatorUtil.length(solarPannelDto.getErrmsg(), "", 1, 255);
                ValidatorUtil.length(solarPannelDto.getLedmsg(), "", 1, 255);
                ValidatorUtil.length(solarPannelDto.getCardNumber(), "", 1, 50);
                ValidatorUtil.length(solarPannelDto.getCsq(), "", 1, 255);
                ValidatorUtil.length(solarPannelDto.getBatterycorrect(), "", 1, 20);

        ResponseDto responseDto = new ResponseDto();
        solarPannelService.save(solarPannelDto);
        responseDto.setContent(solarPannelDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        solarPannelService.delete(id);
        return responseDto;
    }

}

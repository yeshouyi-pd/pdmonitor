package com.pd.monitor.controller;

import com.pd.server.main.dto.DeviceMonitorDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DeviceMonitorDataService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/deviceMonitorData")
public class DeviceMonitorDataController {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceMonitorDataController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private DeviceMonitorDataService deviceMonitorDataService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        deviceMonitorDataService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DeviceMonitorDataDto deviceMonitorDataDto) {
        // 保存校验
                ValidatorUtil.length(deviceMonitorDataDto.getSbbh(), "设备编号", 1, 255);
                ValidatorUtil.length(deviceMonitorDataDto.getType(), "类型（电流electricCurrent、电压Voltage、功率power）", 1, 255);
                ValidatorUtil.length(deviceMonitorDataDto.getValue(), "数据值", 1, 255);
                ValidatorUtil.length(deviceMonitorDataDto.getBz(), "备注", 1, 500);
                ValidatorUtil.length(deviceMonitorDataDto.getSm(), "说明", 1, 500);
                ValidatorUtil.length(deviceMonitorDataDto.getSm1(), "说明1", 1, 500);
                ValidatorUtil.length(deviceMonitorDataDto.getSm2(), "说明2", 1, 500);
                ValidatorUtil.length(deviceMonitorDataDto.getSm3(), "说明3", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        deviceMonitorDataService.save(deviceMonitorDataDto);
        responseDto.setContent(deviceMonitorDataDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        deviceMonitorDataService.delete(id);
        return responseDto;
    }

}

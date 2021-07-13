package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.EquipmentFileAlarmEvent;
import com.pd.server.main.dto.EquipmentFileAlarmEventDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFileAlarmEventService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/equipmentFileAlarmEvent")
public class EquipmentFileAlarmEventController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileAlarmEventController.class);
    public static final String BUSINESS_NAME = "按事件统计";

    @Resource
    private EquipmentFileAlarmEventService equipmentFileAlarmEventService;

    /**
     * 列表查询
     */
    @PostMapping("/detailByParam")
    public ResponseDto detailByParam(@RequestBody EquipmentFileAlarmEvent entity) {
        ResponseDto responseDto = new ResponseDto();
        List<EquipmentFileAlarmEvent> list = equipmentFileAlarmEventService.detailByParam(entity);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EquipmentFileAlarmEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        List<String> list = getUpdeptcode(userDto.getDeptcode());
        equipmentFileAlarmEventService.list(pageDto, list);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/statistics")
    public ResponseDto statistics(@RequestBody EquipmentFileAlarmEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        List<String> list = getUpdeptcode(userDto.getDeptcode());
        equipmentFileAlarmEventService.listStatistics(pageDto, list);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileAlarmEventDto equipmentFileAlarmEventDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileAlarmEventDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getEventTime(), "事件时间", 1, 100);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getBjsj(), "报警时间", 1, 50);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getSm1(), "", 1, 50);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getSm2(), "", 1, 50);
                ValidatorUtil.length(equipmentFileAlarmEventDto.getSm3(), "", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileAlarmEventService.save(equipmentFileAlarmEventDto);
        responseDto.setContent(equipmentFileAlarmEventDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileAlarmEventService.delete(id);
        return responseDto;
    }

}
